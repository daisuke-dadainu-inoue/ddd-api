package ddd.application.company.find.usecase;

import lombok.Value;

@Value
public class CompanyFindByIdOutputData {
    private int companyId;
    private String companyName;
    private String companyNameKana;
    private String postalCode;
    private String addressCode;
    private String addressName;
    private String phoneNumber;
    private String status;
}
