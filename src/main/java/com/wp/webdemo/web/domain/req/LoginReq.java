package com.wp.webdemo.web.domain.req;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * Created by 95 on 2017/3/16.
 */

@Data
@ToString
public class LoginReq implements Serializable{

    private String email;
    private String password;
}
