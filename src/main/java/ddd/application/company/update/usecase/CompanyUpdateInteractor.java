package ddd.application.company.update.usecase;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import ddd.application.common.exception.DDDApplicationException;
import ddd.application.common.exception.DDDItemCheckException;
import ddd.application.common.exception.DDDSystemException;
import ddd.application.company.update.presenter.CompanyUpdatePresenterIF;
import ddd.domain.company.model.Company;
import ddd.domain.company.repository.CompanyRepositoryIF;
import ddd.domain.company.vo.CompanyId;
import ddd.infrastructure.common.transaction.TransactionIF;

public class CompanyUpdateInteractor implements CompanyUpdateUseCaseIF {

    private final TransactionIF transaction;
    private final CompanyRepositoryIF companyRepository;
    private final CompanyUpdatePresenterIF presenter;

    @Inject
    public CompanyUpdateInteractor(TransactionIF transaction, CompanyRepositoryIF companyRepository, CompanyUpdatePresenterIF presenter) {
        this.transaction = transaction;
        this.companyRepository = companyRepository;
        this.presenter = presenter;
    }

    @Override
    public String handle(CompanyUpdateInputData inputData) throws DDDItemCheckException, DDDApplicationException, DDDSystemException {
        SqlSessionFactory sqlSessionFactory = transaction.createUpdSessionFactory();

        try (SqlSession session = sqlSessionFactory.openSession()) {

            List<Company> updateCompanys = new ArrayList<>();
            for (CompanyUpdateInputDataItem item : inputData.getItems()) {
                Company targetCompany = companyRepository.findById(session, new CompanyId(item.getCompanyId()));
                targetCompany.changeCompanyName(item.getCompanyName());
                targetCompany.changeCompanyNameKana(item.getCompanyNameKana());
                targetCompany.changePostalCode(item.getPostalCode());
                targetCompany.changeAddressCode(item.getAddressCode());
                targetCompany.changeAddressName(item.getAddressName());
                targetCompany.changePhoneNumber(item.getPhoneNumber());
                targetCompany.changeStatus(item.getStatus());
                updateCompanys.add(targetCompany);
            }

            List<Company> resultCompanys = new ArrayList<>();
            for (Company company : updateCompanys) {
                Company updateCompany = companyRepository.update(session, company);
                resultCompanys.add(updateCompany);
            }
            session.commit();

            List<CompanyUpdateOutputDataItem> items = new ArrayList<>();
            for (Company company : resultCompanys) {
                items.add(new CompanyUpdateOutputDataItem(
                        company.getCompanyId().getValue(), company.getCompanyName().getValue(),
                        company.getCompanyNameKana().getValue(), company.getPostalCode().getValue(),
                        company.getAddressCode().getValue(), company.getAddressName().getValue(),
                        company.getPhoneNumber().getValue(), company.getStatus().getValue().getName()));
            }
            return presenter.complete(new CompanyUpdateOutputData(items));
        } catch (DDDItemCheckException | DDDSystemException e) {
            throw e;
        }
    }
}
