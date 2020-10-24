package ddd.application.company.find.presenter;

import ddd.application.company.find.usecase.CompanyFindByIdOutputData;

public interface CompanyFindByIdPresenterIF {

    public String complete(CompanyFindByIdOutputData outputData);
}
