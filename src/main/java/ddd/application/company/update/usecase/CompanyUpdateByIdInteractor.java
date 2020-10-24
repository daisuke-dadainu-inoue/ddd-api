package ddd.application.company.update.usecase;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import ddd.application.common.exception.DDDApplicationException;
import ddd.application.common.exception.DDDItemCheckException;
import ddd.application.common.exception.DDDSystemException;
import ddd.application.company.update.presenter.CompanyUpdateByIdPresenterIF;
import ddd.domain.company.model.Company;
import ddd.domain.company.repository.CompanyRepositoryIF;
import ddd.domain.company.vo.CompanyId;
import ddd.infrastructure.common.transaction.TransactionIF;

public class CompanyUpdateByIdInteractor implements CompanyUpdateByIdUseCaseIF {

    private final TransactionIF transaction;
    private final CompanyRepositoryIF companyRepository;
    private final CompanyUpdateByIdPresenterIF presenter;

    @Inject
    public CompanyUpdateByIdInteractor(TransactionIF transaction, CompanyRepositoryIF companyRepository, CompanyUpdateByIdPresenterIF presenter) {
        this.transaction = transaction;
        this.companyRepository = companyRepository;
        this.presenter = presenter;
    }

    @Override
    public String handle(CompanyUpdateByIdInputData inputData) throws DDDItemCheckException, DDDApplicationException, DDDSystemException {
        SqlSessionFactory sqlSessionFactory = transaction.createUpdSessionFactory();

        try (SqlSession session = sqlSessionFactory.openSession()) {

            Company updateCompany = companyRepository.findById(session, new CompanyId(inputData.getCompanyId()));
            updateCompany.changeCompanyName(inputData.getCompanyName());
            updateCompany.changeCompanyNameKana(inputData.getCompanyNameKana());
            updateCompany.changePostalCode(inputData.getPostalCode());
            updateCompany.changeAddressCode(inputData.getAddressCode());
            updateCompany.changeAddressName(inputData.getAddressName());
            updateCompany.changePhoneNumber(inputData.getPhoneNumber());
            updateCompany.changeStatus(inputData.getStatus());

            Company resultCompany = companyRepository.update(session, updateCompany);
            session.commit();

            return presenter.complete(new CompanyUpdateByIdOutputData(
                    resultCompany.getCompanyId().getValue(), resultCompany.getCompanyName().getValue(),
                    resultCompany.getCompanyNameKana().getValue(), resultCompany.getPostalCode().getValue(),
                    resultCompany.getAddressCode().getValue(), resultCompany.getAddressName().getValue(),
                    resultCompany.getPhoneNumber().getValue(), resultCompany.getStatus().getValue().getName()));
        } catch (DDDItemCheckException | DDDSystemException e) {
            throw e;
        }
    }
}
