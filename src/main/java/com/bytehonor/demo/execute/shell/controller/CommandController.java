package com.bytehonor.demo.execute.shell.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bytehonor.demo.execute.shell.exec.CommandExecutor;
import com.bytehonor.sdk.protocol.common.result.StringResultVO;

@RestController
@RequestMapping("/command")
public class CommandController {

    private static final Logger LOG = LoggerFactory.getLogger(CommandController.class);

    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<String> actionDefault(HttpServletRequest request) {
        List<String> result = new ArrayList<String>();
        result.add("/ansible?path=&yml=&param=");
        result.add("/python?path=");
        result.add("/shell?path=");
        result.add("/cmd?cmd=");
        return result;
    }

    @RequestMapping(value = "/ansible", method = RequestMethod.GET)
    public StringResultVO execAnsible(HttpServletRequest request) {
        String path = request.getParameter("path");
        String yml = request.getParameter("yml");
        String param = request.getParameter("param");
        if (StringUtils.isEmpty(path)) {
            ClassPathResource resource = new ClassPathResource("script/python-test.py");
            try {
                File file = resource.getFile();
                path = file.getPath();
            } catch (IOException e) {
                LOG.error("file", e);
            }
        }
        LOG.info("execPython, path:{}", path);
        try {
            StringBuilder cmd = new StringBuilder();
            cmd.append(path);
            if (!StringUtils.isEmpty(yml)) {
                cmd.append(" ").append(yml);
            }
            if (!StringUtils.isEmpty(param)) {
                cmd.append(" ").append(param);
            }
            Process process = CommandExecutor.execPython(cmd.toString());
            CommandExecutor.print(process);
        } catch (IOException e) {
            LOG.error("xxx", e);
        }
        return new StringResultVO();
    }

    @RequestMapping(value = "/python", method = RequestMethod.GET)
    public StringResultVO execPython(HttpServletRequest request) {
        String path = request.getParameter("path");
        if (StringUtils.isEmpty(path)) {
            ClassPathResource resource = new ClassPathResource("script/python-test.py");
            try {
                File file = resource.getFile();
                path = file.getPath();
            } catch (IOException e) {
                LOG.error("file", e);
            }
        }
        LOG.info("execPython, path:{}", path);
        try {
            Process process = CommandExecutor.execPython(path);
            CommandExecutor.print(process);
        } catch (IOException e) {
            LOG.error("xxx", e);
        }
        return new StringResultVO();
    }

    @RequestMapping(value = "/shell", method = RequestMethod.GET)
    public StringResultVO execShell(HttpServletRequest request) {
        String path = request.getParameter("path");
        File file = null;
        if (StringUtils.isEmpty(path)) {
            ClassPathResource resource = new ClassPathResource("script/bash.sh");
            try {
                file = resource.getFile();
            } catch (IOException e) {
                LOG.error("file", e);
            }
        } else {
            file = new File(path);
        }
        LOG.info("execShell, file:{}", file.getPath());
        try {
            Process process = CommandExecutor.execShell(file);
            CommandExecutor.print(process);
        } catch (IOException e) {
            LOG.error("xxx", e);
        }
        return new StringResultVO();
    }

    @RequestMapping(value = "/cmd", method = RequestMethod.GET)
    public StringResultVO execCmd(HttpServletRequest request) {
        String cmd = request.getParameter("cmd");
        LOG.info("execCmd, cmd:{}", cmd);
        try {
            Process process = CommandExecutor.execCommand(cmd);
            CommandExecutor.print(process);
        } catch (IOException e) {
            LOG.error("xxx", e);
        }
        return new StringResultVO();
    }

}
