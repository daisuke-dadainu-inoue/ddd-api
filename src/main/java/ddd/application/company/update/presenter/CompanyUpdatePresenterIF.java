package ddd.application.company.update.presenter;

import ddd.application.company.update.usecase.CompanyUpdateOutputData;

public interface CompanyUpdatePresenterIF {

    public String complete(CompanyUpdateOutputData outputData);
}
