package ddd.application.company.add.presenter;

import ddd.application.company.add.usecase.CompanyAddOutputData;

public interface CompanyAddPresenterIF {

    public String complete(CompanyAddOutputData outputData);
}
