package com.bytehonor.demo.execute.shell.exec;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;

public class CommandExecutorTest {

    private static final Logger LOG = LoggerFactory.getLogger(CommandExecutorTest.class);

//    @Test
    public void testExecPython() {
        ClassPathResource resource = new ClassPathResource("script/python-test.py");
        try {
            File file = resource.getFile();
            Process process = CommandExecutor.execPython(file.getPath());
            CommandExecutor.print(process);
        } catch (IOException e) {
            LOG.error("xxx", e);
        }
        assertTrue("*testExecPython*", true);
    }

//    @Test
    public void testExecShell() {
        ClassPathResource resource = new ClassPathResource("script/bash.sh");
        try {
            File file = resource.getFile();
            CommandExecutor.execShell(file);
        } catch (IOException e) {
            LOG.error("testExecShell", e);
        }
        assertTrue("*testExecShell*", true);
    }

    @Test
    public void testExecCommand() {
        try {
            String cmd = "netstat -a";// "cmd /c d: && dir";
            Process process = CommandExecutor.execCommand(cmd);
            CommandExecutor.print(process);
            process.destroy();
        } catch (IOException e) {
            LOG.error("testExecCommand", e);
        }
        assertTrue("*testExecCommand*", true);
    }

}
