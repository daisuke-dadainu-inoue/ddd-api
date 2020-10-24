package ddd.interfaces.company.update.presenter;

import ddd.application.company.update.presenter.CompanyUpdatePresenterIF;
import ddd.application.company.update.usecase.CompanyUpdateOutputData;
import ddd.interfaces.common.JsonConverter;

public class CompanyUpdatePresenter implements CompanyUpdatePresenterIF {

    @Override
    public String complete(CompanyUpdateOutputData outputData) {
        return JsonConverter.toJson(outputData);
    }

}
