package ddd.application.company.find;

import java.util.ArrayList;
import java.util.List;

import ddd.application.common.exception.DDDApplicationException;
import ddd.application.common.exception.DDDItemCheckException;
import ddd.application.common.exception.DDDSystemException;
import ddd.application.company.find.usecase.CompanyFindInputData;
import ddd.application.company.find.usecase.CompanyFindOutputData;
import ddd.application.company.find.usecase.CompanyFindOutputDataItem;
import ddd.application.company.find.usecase.CompanyFindUseCaseIF;
import ddd.interfaces.ResponseStatus;
import ddd.interfaces.common.JsonConverter;

public class CompanyFindInteractorMock implements CompanyFindUseCaseIF {

    @Override
    public String handle(CompanyFindInputData inputData) throws DDDItemCheckException, DDDApplicationException, DDDSystemException {
        if (inputData.getEmbed().equals("2")) {
            throw new DDDItemCheckException("error.test.usecase.mock.001", ResponseStatus.BAD_REQUEST.value());
        }
        if (inputData.getEmbed().equals("3")) {
            throw new DDDApplicationException("error.test.usecase.mock.002", ResponseStatus.BAD_REQUEST.value());
        }
        if (inputData.getEmbed().equals("4")) {
            throw new DDDSystemException("error.test.usecase.mock.003", ResponseStatus.BAD_REQUEST.value());
        }
        if (inputData.getEmbed().equals("5")) {
            String hoge = null;
            hoge.chars();
        }
        List<CompanyFindOutputDataItem> items = new ArrayList<>();
        items.add(new CompanyFindOutputDataItem(1, "hoge", "hogekana", "1234567", "13", "hogeadd", "0312345678", "open"));
        return JsonConverter.toJson(new CompanyFindOutputData(items));
    }

}
