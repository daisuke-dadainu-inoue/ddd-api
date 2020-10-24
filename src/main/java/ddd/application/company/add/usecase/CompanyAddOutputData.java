package ddd.application.company.add.usecase;

import lombok.Value;

@Value
public class CompanyAddOutputData {
    private int companyId;
    private String companyName;
    private String companyNameKana;
    private String postalCode;
    private String addressCode;
    private String addressName;
    private String phoneNumber;
    private String status;
}
