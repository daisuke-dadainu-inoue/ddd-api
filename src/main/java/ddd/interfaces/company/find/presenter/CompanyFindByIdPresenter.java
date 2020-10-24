package ddd.interfaces.company.find.presenter;

import ddd.application.company.find.presenter.CompanyFindByIdPresenterIF;
import ddd.application.company.find.usecase.CompanyFindByIdOutputData;
import ddd.interfaces.common.JsonConverter;

public class CompanyFindByIdPresenter implements CompanyFindByIdPresenterIF {

    @Override
    public String complete(CompanyFindByIdOutputData outputData) {
        return JsonConverter.toJson(outputData);
    }

}
