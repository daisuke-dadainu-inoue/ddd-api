package ddd.application.company.update.presenter;

import ddd.application.company.update.usecase.CompanyUpdateByIdOutputData;

public interface CompanyUpdateByIdPresenterIF {

    public String complete(CompanyUpdateByIdOutputData outputData);
}
