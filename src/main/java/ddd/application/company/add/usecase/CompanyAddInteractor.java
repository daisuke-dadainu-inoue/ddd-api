package ddd.application.company.add.usecase;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import ddd.application.common.exception.DDDApplicationException;
import ddd.application.common.exception.DDDItemCheckException;
import ddd.application.common.exception.DDDSystemException;
import ddd.application.company.add.presenter.CompanyAddPresenterIF;
import ddd.domain.common.vo.Status;
import ddd.domain.common.vo.StatusConst;
import ddd.domain.company.model.Company;
import ddd.domain.company.repository.CompanyRepositoryIF;
import ddd.domain.company.vo.AddressCode;
import ddd.domain.company.vo.AddressName;
import ddd.domain.company.vo.CompanyId;
import ddd.domain.company.vo.CompanyName;
import ddd.domain.company.vo.CompanyNameKana;
import ddd.domain.company.vo.PhoneNumber;
import ddd.domain.company.vo.PostalCode;
import ddd.infrastructure.common.transaction.TransactionIF;

public class CompanyAddInteractor implements CompanyAddUseCaseIF {

    private final TransactionIF transaction;
    private final CompanyRepositoryIF companyRepository;
    private final CompanyAddPresenterIF presenter;

    @Inject
    public CompanyAddInteractor(TransactionIF transaction, CompanyRepositoryIF companyRepository, CompanyAddPresenterIF presenter) {
        this.transaction = transaction;
        this.companyRepository = companyRepository;
        this.presenter = presenter;
    }

    @Override
    public String handle(CompanyAddInputData inputData) throws DDDItemCheckException, DDDApplicationException, DDDSystemException {

        SqlSessionFactory sqlSessionFactory = transaction.createUpdSessionFactory();

        try (SqlSession session = sqlSessionFactory.openSession()) {

            CompanyId companyId = companyRepository.maxCompanyId(session);

            Company updateCompany = new Company(companyId, new CompanyName(inputData.getCompanyName()),
                    new CompanyNameKana(inputData.getCompanyNameKana()), new PostalCode(inputData.getPostalCode()),
                    new AddressCode(inputData.getAddressCode()), new AddressName(inputData.getAddressName()),
                    new PhoneNumber(inputData.getPhoneNumber()), new Status(StatusConst.nameOf(inputData.getStatus())));

            Company resultCompany = companyRepository.add(session, updateCompany);
            session.commit();

            CompanyAddOutputData outputData = new CompanyAddOutputData(
                    resultCompany.getCompanyId().getValue(), resultCompany.getCompanyName().getValue(),
                    resultCompany.getCompanyNameKana().getValue(), resultCompany.getPostalCode().getValue(),
                    resultCompany.getAddressCode().getValue(), resultCompany.getAddressName().getValue(),
                    resultCompany.getPhoneNumber().getValue(), resultCompany.getStatus().getValue().getName());
            return presenter.complete(outputData);
        } catch (DDDItemCheckException | DDDSystemException e) {
            throw e;
        } catch (Exception e) {
            throw e;
        }
    }

}
