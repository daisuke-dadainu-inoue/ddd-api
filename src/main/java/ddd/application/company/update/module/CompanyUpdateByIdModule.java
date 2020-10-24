package ddd.application.company.update.module;

import org.glassfish.hk2.utilities.binding.AbstractBinder;

import ddd.application.company.update.presenter.CompanyUpdateByIdPresenterIF;
import ddd.application.company.update.usecase.CompanyUpdateByIdInteractor;
import ddd.application.company.update.usecase.CompanyUpdateByIdUseCaseIF;
import ddd.domain.company.repository.CompanyRepositoryIF;
import ddd.infrastructure.common.transaction.MyBatisTransaction;
import ddd.infrastructure.common.transaction.TransactionIF;
import ddd.infrastructure.company.CompanyRepository;
import ddd.interfaces.company.update.presenter.CompanyUpdateByIdPresenter;

public class CompanyUpdateByIdModule extends AbstractBinder {

    @Override
    protected void configure() {
        bind(CompanyUpdateByIdInteractor.class).to(CompanyUpdateByIdUseCaseIF.class);
        bind(MyBatisTransaction.class).to(TransactionIF.class);
        bind(CompanyRepository.class).to(CompanyRepositoryIF.class);
        bind(CompanyUpdateByIdPresenter.class).to(CompanyUpdateByIdPresenterIF.class);
    }
}
