package ddd.application.company.update.module;

import org.glassfish.hk2.utilities.binding.AbstractBinder;

import ddd.application.company.update.presenter.CompanyUpdatePresenterIF;
import ddd.application.company.update.usecase.CompanyUpdateInteractor;
import ddd.application.company.update.usecase.CompanyUpdateUseCaseIF;
import ddd.domain.company.repository.CompanyRepositoryIF;
import ddd.infrastructure.common.transaction.MyBatisTransaction;
import ddd.infrastructure.common.transaction.TransactionIF;
import ddd.infrastructure.company.CompanyRepository;
import ddd.interfaces.company.update.presenter.CompanyUpdatePresenter;

public class CompanyUpdateModule extends AbstractBinder {

    @Override
    protected void configure() {
        bind(CompanyUpdateInteractor.class).to(CompanyUpdateUseCaseIF.class);
        bind(MyBatisTransaction.class).to(TransactionIF.class);
        bind(CompanyRepository.class).to(CompanyRepositoryIF.class);
        bind(CompanyUpdatePresenter.class).to(CompanyUpdatePresenterIF.class);
    }
}
