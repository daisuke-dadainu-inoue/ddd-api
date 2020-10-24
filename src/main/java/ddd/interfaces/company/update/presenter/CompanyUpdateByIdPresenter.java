package ddd.interfaces.company.update.presenter;

import ddd.application.company.update.presenter.CompanyUpdateByIdPresenterIF;
import ddd.application.company.update.usecase.CompanyUpdateByIdOutputData;
import ddd.interfaces.common.JsonConverter;

public class CompanyUpdateByIdPresenter implements CompanyUpdateByIdPresenterIF {

    @Override
    public String complete(CompanyUpdateByIdOutputData outputData) {

        return JsonConverter.toJson(outputData);
    }

}
