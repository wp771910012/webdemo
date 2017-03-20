package com.wp.webdemo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wp.webdemo.web.domain.req.LoginReq;
import com.wp.webdemo.web.domain.resp.ResultResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;

/**
 * Created by wp on 2017/3/16.
 * 用户控制层
 */

@Controller
public class UserController {

    /*
    logger 初始化
     */
    private static Logger log= LoggerFactory.getLogger(UserController.class);

    /*
    变量注入 ： application.yml 中的service.url 变量
     */
    @Value("${service.url}")
    private String serviceUrl;

    /**
     * 首页跳转
     * @return
     */
    @RequestMapping(value = "index" , method = RequestMethod.GET , produces = MediaType.ALL_VALUE)
    public String index(){
        return "login";
    }


    /**
     * 登录action
     * @param loginReq
     * @return
     */
    @RequestMapping(value = "login" , method = RequestMethod.POST , produces = MediaType.ALL_VALUE)
    public ModelAndView login(LoginReq loginReq) {
        log.info("/login: 入参 "+loginReq.toString());
        String msg;
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        ObjectMapper objectMapper=new ObjectMapper();
        try {
            URL url =new URL("http://"+serviceUrl+"/login");
            HttpEntity<String> entity = new HttpEntity<String>(objectMapper.writeValueAsString(loginReq), headers);
            RestTemplate template=new RestTemplate();
            String result=template.postForObject(url.toString(),entity,String.class, new HashMap<String, Object>());
            ResultResponse response= objectMapper.readValue(result,ResultResponse.class);
            if (!response.getResultCode().equals("1")) {
                msg=response.getResultMessage();
            } else msg = "登录成功";

            log.info("/login:出参"+response.toString());
        } catch (IOException e1) {
            log.error(e1.getMessage());
            msg = "未知错误";
        }

        return new ModelAndView("login","msg",msg);
    }
}
