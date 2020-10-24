package ddd.application.company.add.usecase;

import lombok.Value;

@Value
public class CompanyAddInputData {
    private String companyName;
    private String companyNameKana;
    private String postalCode;
    private String addressCode;
    private String addressName;
    private String phoneNumber;
    private String status;
}
