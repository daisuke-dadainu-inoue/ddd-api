package ddd.application.company.add.module;

import org.glassfish.hk2.utilities.binding.AbstractBinder;

import ddd.application.company.add.usecase.CompanyAddInteractor;
import ddd.application.company.add.usecase.CompanyAddUseCaseIF;
import ddd.application.company.find.presenter.CompanyFindPresenterIF;
import ddd.domain.company.repository.CompanyRepositoryIF;
import ddd.infrastructure.common.transaction.MyBatisTransaction;
import ddd.infrastructure.common.transaction.TransactionIF;
import ddd.infrastructure.company.CompanyRepository;
import ddd.interfaces.company.find.presenter.CompanyFindPresenter;

public class CompanyAddModule extends AbstractBinder {

    @Override
    protected void configure() {
        bind(CompanyAddInteractor.class).to(CompanyAddUseCaseIF.class);
        bind(MyBatisTransaction.class).to(TransactionIF.class);
        bind(CompanyRepository.class).to(CompanyRepositoryIF.class);
        bind(CompanyFindPresenter.class).to(CompanyFindPresenterIF.class);
    }
}
