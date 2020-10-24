package ddd.domain.common.vo;

import lombok.Value;

/**
 * ステータス
 */
@Value
public class Status {

    private StatusConst value;

    /**
     * @param inputValue 設定値
     */
    public Status(StatusConst inputValue) {
        this.value = inputValue;
    }

}
