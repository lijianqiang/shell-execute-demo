package com.bytehonor.demo.execute.shell.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class IndexController {

    private static final Logger LOG = LoggerFactory.getLogger(IndexController.class);

    @Value("${spring.application.name}")
    private String applicationName;

    @Value("${server.port}")
    private String serverPort;

    @ResponseBody
    @RequestMapping("/")
    public String actionIndex(HttpServletRequest request) {
        LOG.info("hello {}, {}", applicationName, serverPort);
        StringBuilder sb = new StringBuilder();
        sb.append(applicationName).append(":").append(serverPort);
        return sb.toString();
    }

}
