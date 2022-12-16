package com.cleaningan.model.response;

import com.cleaningan.constant.SuccessCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

public class SuccessResponse<T> extends CommonResponse {
    @Getter @Setter
    T data;

    public SuccessResponse(String message, T data) {
        super.setCode(SuccessCode.SS.toString());
        super.setMessage(message);
        setStatus(HttpStatus.OK.name());
        this.data = data;
    }
}
