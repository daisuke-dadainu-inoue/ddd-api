package ddd.application.company.find.usecase;

import lombok.Value;

@Value
public class CompanyFindInputData {
    private String embed;
    private String status;
    private String sort;
}
