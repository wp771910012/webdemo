package com.wp.webdemo.web.domain.resp;

import lombok.Data;
import lombok.ToString;

/**
 * Created by 95 on 2017/3/17.
 */

@Data
@ToString
public class ResultResponse {
    private String resultCode;
    private String resultMessage;

    public ResultResponse(String resultCode, String resultMessage) {
        this.resultCode = resultCode;
        this.resultMessage = resultMessage;
    }

    public ResultResponse() {
    }
}
