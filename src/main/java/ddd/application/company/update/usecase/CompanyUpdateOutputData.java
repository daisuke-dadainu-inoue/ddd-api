package ddd.application.company.update.usecase;

import java.util.List;

import lombok.Value;

@Value
public class CompanyUpdateOutputData {
    private List<CompanyUpdateOutputDataItem> items;
}
