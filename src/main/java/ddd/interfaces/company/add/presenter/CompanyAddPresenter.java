package ddd.interfaces.company.add.presenter;

import ddd.application.company.add.presenter.CompanyAddPresenterIF;
import ddd.application.company.add.usecase.CompanyAddOutputData;
import ddd.interfaces.common.JsonConverter;

public class CompanyAddPresenter implements CompanyAddPresenterIF {

    @Override
    public String complete(CompanyAddOutputData outputData) {
        return JsonConverter.toJson(outputData);
    }

}
