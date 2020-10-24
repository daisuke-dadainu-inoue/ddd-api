package ddd.interfaces.company.find.presenter;

import ddd.application.company.find.presenter.CompanyFindPresenterIF;
import ddd.application.company.find.usecase.CompanyFindOutputData;
import ddd.interfaces.common.JsonConverter;

public class CompanyFindPresenter implements CompanyFindPresenterIF {

    @Override
    public String complete(CompanyFindOutputData outputData) {
        return JsonConverter.toJson(outputData);
    }

}
