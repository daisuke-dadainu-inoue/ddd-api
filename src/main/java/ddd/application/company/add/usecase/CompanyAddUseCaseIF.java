package ddd.application.company.add.usecase;

import ddd.application.common.exception.DDDApplicationException;
import ddd.application.common.exception.DDDItemCheckException;
import ddd.application.common.exception.DDDSystemException;

public interface CompanyAddUseCaseIF {

    public String handle(CompanyAddInputData inputData) throws DDDItemCheckException, DDDApplicationException, DDDSystemException;
}
