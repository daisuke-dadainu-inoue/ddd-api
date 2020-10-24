package ddd.infrastructure.company;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import ddd.domain.common.vo.StatusConst;
import lombok.Value;

@Value
public class CompanyQueryParam {

    private final Map<String, String> fieldMap = new HashMap<String, String>() {
        {
            put("name", "company_name");
            put("postalcode", "postal_code");
            put("address", "address_code");
        }
    };
    private List<String> status;
    private List<String> sort;

    public CompanyQueryParam(String status, String sort) {
        this.status = editStatus(status);
        this.sort = editSort(sort);
    }

    public List<String> editStatus(String status) {
        if (status == null) {
            return null;
        }
        String[] statusList = status.split(Pattern.quote("."));
        List<String> result = new ArrayList<>();
        for (String statusItem : statusList) {
            try {
                StatusConst statusConst = StatusConst.valueOf(statusItem.toUpperCase());
                result.add(String.valueOf(statusConst.getId()));
            } catch (Exception e) {
            }
        }
        return result.size() == 0 ? null : result;
    }

    public List<String> editSort(String sort) {
        if (sort == null) {
            return null;
        }
        String[] sortList = sort.split(",");
        List<String> result = new ArrayList<>();
        for (String sortItem : sortList) {
            String sortKey = sortItem.startsWith("-") ? sortItem.substring(1) : sortItem;
            if (!fieldMap.containsKey(sortKey)) {
                continue;
            }
            String format = sortItem.startsWith("-") ? "%s desc" : "%s asc";
            result.add(String.format(format, fieldMap.get(sortKey)));
        }
        return result.size() == 0 ? null : result;
    }
}
