package ddd.application.company.find.usecase;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CompanyFindOutputDataItem {
    private int companyId;
    private String companyName;
    private String companyNameKana;
    private String postalCode;
    private String addressCode;
    private String addressName;
    private String phoneNumber;
    private String status;
}
