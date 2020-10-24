package ddd.application.company.find.usecase;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CompanyFindOutputData {
    private List<CompanyFindOutputDataItem> items;
}
