package ddd.application.company.find.usecase;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import ddd.application.common.exception.DDDSystemException;
import ddd.application.company.find.presenter.CompanyFindPresenterIF;
import ddd.domain.company.model.Company;
import ddd.domain.company.repository.CompanyRepositoryIF;
import ddd.infrastructure.common.transaction.TransactionIF;
import ddd.infrastructure.company.CompanyQueryParam;

public class CompanyFindInteractor implements CompanyFindUseCaseIF {

    private final TransactionIF transaction;
    private final CompanyRepositoryIF companyRepository;
    private final CompanyFindPresenterIF presenter;

    @Inject
    public CompanyFindInteractor(TransactionIF transaction, CompanyRepositoryIF companyRepository, CompanyFindPresenterIF presenter) {
        this.transaction = transaction;
        this.companyRepository = companyRepository;
        this.presenter = presenter;
    }

    @Override
    public String handle(CompanyFindInputData inputData) throws DDDSystemException {
        SqlSessionFactory sqlSessionFactory = transaction.createRefSessionFactory();

        try (SqlSession session = sqlSessionFactory.openSession()) {

            CompanyQueryParam queryParam = new CompanyQueryParam(inputData.getStatus(), inputData.getSort());
            List<Company> companys = companyRepository.find(session, queryParam);

            List<CompanyFindOutputDataItem> items = new ArrayList<>();
            for (Company company : companys) {
                items.add(new CompanyFindOutputDataItem(
                        company.getCompanyId().getValue(), company.getCompanyName().getValue(),
                        company.getCompanyNameKana().getValue(), company.getPostalCode().getValue(),
                        company.getAddressCode().getValue(), company.getAddressName().getValue(),
                        company.getPhoneNumber().getValue(), company.getStatus().getValue().getName()));
            }
            return presenter.complete(new CompanyFindOutputData(items));
        } catch (Exception e) {
            throw e;
        }
    }
}
