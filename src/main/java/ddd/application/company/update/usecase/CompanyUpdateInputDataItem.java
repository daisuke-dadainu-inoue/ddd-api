package ddd.application.company.update.usecase;

import lombok.Value;

@Value
public class CompanyUpdateInputDataItem {
    private int companyId;
    private String companyName;
    private String companyNameKana;
    private String postalCode;
    private String addressCode;
    private String addressName;
    private String phoneNumber;
    private String status;
}
