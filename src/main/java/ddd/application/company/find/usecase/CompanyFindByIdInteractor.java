package ddd.application.company.find.usecase;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import ddd.application.common.exception.DDDApplicationException;
import ddd.application.common.exception.DDDItemCheckException;
import ddd.application.common.exception.DDDSystemException;
import ddd.application.company.find.presenter.CompanyFindByIdPresenterIF;
import ddd.domain.company.model.Company;
import ddd.domain.company.repository.CompanyRepositoryIF;
import ddd.domain.company.vo.CompanyId;
import ddd.infrastructure.common.transaction.TransactionIF;

public class CompanyFindByIdInteractor implements CompanyFindByIdUseCaseIF {

    private final TransactionIF transaction;
    private final CompanyRepositoryIF companyRepository;
    private final CompanyFindByIdPresenterIF presenter;

    @Inject
    public CompanyFindByIdInteractor(TransactionIF transaction, CompanyRepositoryIF companyRepository, CompanyFindByIdPresenterIF presenter) {
        this.transaction = transaction;
        this.companyRepository = companyRepository;
        this.presenter = presenter;
    }

    @Override
    public String handle(CompanyFindByIdInputData inputData) throws DDDItemCheckException, DDDApplicationException, DDDSystemException {
        SqlSessionFactory sqlSessionFactory = transaction.createRefSessionFactory();

        try (SqlSession session = sqlSessionFactory.openSession()) {

            Company company = companyRepository.findById(session, new CompanyId(inputData.getCompanyId()));

            CompanyFindByIdOutputData outputData = new CompanyFindByIdOutputData(
                    company.getCompanyId().getValue(), company.getCompanyName().getValue(),
                    company.getCompanyNameKana().getValue(), company.getPostalCode().getValue(),
                    company.getAddressCode().getValue(), company.getAddressName().getValue(),
                    company.getPhoneNumber().getValue(), company.getStatus().getValue().getName());
            return presenter.complete(outputData);
        } catch (DDDItemCheckException | DDDSystemException e) {
            throw e;
        }
    }
}
