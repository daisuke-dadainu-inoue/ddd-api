package ddd.domain.company.vo;

import java.util.Objects;

import ddd.application.common.exception.DDDItemCheckException;
import ddd.interfaces.ResponseStatus;
import lombok.Value;

/**
 * 住所名
 */
@Value
public class AddressName {

//    private static final String ERROR_USECASE_USER_EDIT_018 = "error.usecase.user.edit.018";
    private static final String ERROR_USECASE_USER_EDIT_019 = "error.usecase.user.edit.019";

    private static final int LENGTH_256 = 256;

    private String value;

    /**
     * @param inputValue 入力値
     * @throws DDDItemCheckException 項目チェック例外
     */
    public AddressName(String inputValue) throws DDDItemCheckException {
        checkValue(inputValue);
        this.value = inputValue;
    }

    /**
     * @param inputValue 入力値
     * @throws DDDItemCheckException 項目チェック例外
     */
    public void checkValue(String inputValue) throws DDDItemCheckException {
        if (Objects.isNull(inputValue) || inputValue.isEmpty()) {
            return;
        }
        if (inputValue.length() >= LENGTH_256) {
            throw new DDDItemCheckException(ERROR_USECASE_USER_EDIT_019, ResponseStatus.BAD_REQUEST.value());
        }
    }
}
