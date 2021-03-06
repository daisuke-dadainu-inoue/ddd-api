package ddd.interfaces.company.add.controller;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ddd.application.common.exception.DDDApplicationException;
import ddd.application.common.exception.DDDItemCheckException;
import ddd.application.common.exception.DDDSystemException;
import ddd.application.common.exception.ErrorResponse;
import ddd.application.company.add.usecase.CompanyAddInputData;
import ddd.application.company.add.usecase.CompanyAddUseCaseIF;
import ddd.infrastructure.common.config.DDDConfig;
import ddd.interfaces.ResponseStatus;
import ddd.interfaces.common.JsonConverter;

@Path("/company")
public class CompanyAddApi {

    private static final Logger LOGGER = LoggerFactory.getLogger(CompanyAddApi.class);

    private static final String ERROR_EXCEPTION_001 = "error.controller.exception.001";

    private final CompanyAddUseCaseIF useCase;

    @Inject
    public CompanyAddApi(CompanyAddUseCaseIF useCase) {
        this.useCase = useCase;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response exec(String body) {
        try {
            CompanyAddInputData inputData = JsonConverter.fromJson(body, CompanyAddInputData.class);
            String resultJson = useCase.handle(inputData);
            return Response.status(ResponseStatus.OK.value()).entity(resultJson).build();
        } catch (DDDItemCheckException e) {
            LOGGER.info(e.getMessage(), e);
            return Response.status(e.getStatus()).entity(e.responseMessage()).build();
        } catch (DDDApplicationException e) {
            LOGGER.warn(e.getMessage(), e);
            return Response.status(e.getStatus()).entity(e.responseMessage()).build();
        } catch (DDDSystemException e) {
            LOGGER.error(e.getMessage(), e);
            return Response.status(e.getStatus()).entity(e.responseMessage()).build();
        } catch (Exception e) {
            LOGGER.warn(e.getMessage(), e);
            ErrorResponse errorResponse = new ErrorResponse(ERROR_EXCEPTION_001, ResponseStatus.INTERNAL_SERVER_ERROR.value(),
                    DDDConfig.getErrorPropertyValue(ERROR_EXCEPTION_001));
            return Response.status(errorResponse.getStatus()).entity(JsonConverter.toJson(errorResponse)).build();
        }
    }

}
