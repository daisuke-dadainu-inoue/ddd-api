package ddd.domain.common.vo;

import lombok.Value;

/**
 * 削除フラグ
 */
@Value
public class DeleteFlag {

    private int value;

    /**
     * @param inputValue 入力値
     */
    public DeleteFlag(DeleteFlagConst inputValue) {
        this.value = inputValue.getValue();
    }
}
