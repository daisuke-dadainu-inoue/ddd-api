package ddd.application.company.find.module;

import org.glassfish.hk2.utilities.binding.AbstractBinder;

import ddd.application.company.find.presenter.CompanyFindPresenterIF;
import ddd.application.company.find.usecase.CompanyFindInteractor;
import ddd.application.company.find.usecase.CompanyFindUseCaseIF;
import ddd.domain.company.repository.CompanyRepositoryIF;
import ddd.infrastructure.common.transaction.MyBatisTransaction;
import ddd.infrastructure.common.transaction.TransactionIF;
import ddd.infrastructure.company.CompanyRepository;
import ddd.interfaces.company.find.presenter.CompanyFindPresenter;

public class CompanyFindModule extends AbstractBinder {

    @Override
    protected void configure() {
        bind(CompanyFindInteractor.class).to(CompanyFindUseCaseIF.class);
        bind(MyBatisTransaction.class).to(TransactionIF.class);
        bind(CompanyRepository.class).to(CompanyRepositoryIF.class);
        bind(CompanyFindPresenter.class).to(CompanyFindPresenterIF.class);
    }
}
