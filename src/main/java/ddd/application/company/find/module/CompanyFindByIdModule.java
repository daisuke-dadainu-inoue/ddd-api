package ddd.application.company.find.module;

import org.glassfish.hk2.utilities.binding.AbstractBinder;

import ddd.application.company.find.presenter.CompanyFindByIdPresenterIF;
import ddd.application.company.find.usecase.CompanyFindByIdInteractor;
import ddd.application.company.find.usecase.CompanyFindByIdUseCaseIF;
import ddd.domain.company.repository.CompanyRepositoryIF;
import ddd.infrastructure.common.transaction.MyBatisTransaction;
import ddd.infrastructure.common.transaction.TransactionIF;
import ddd.infrastructure.company.CompanyRepository;
import ddd.interfaces.company.find.presenter.CompanyFindByIdPresenter;

public class CompanyFindByIdModule extends AbstractBinder {

    @Override
    protected void configure() {
        bind(CompanyFindByIdInteractor.class).to(CompanyFindByIdUseCaseIF.class);
        bind(MyBatisTransaction.class).to(TransactionIF.class);
        bind(CompanyRepository.class).to(CompanyRepositoryIF.class);
        bind(CompanyFindByIdPresenter.class).to(CompanyFindByIdPresenterIF.class);
    }
}
