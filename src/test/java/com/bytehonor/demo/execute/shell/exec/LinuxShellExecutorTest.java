package com.bytehonor.demo.execute.shell.exec;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;

import com.bytehonor.demo.execute.shell.exec.LinuxShellExecutor;

public class LinuxShellExecutorTest {

    private static final Logger LOG = LoggerFactory.getLogger(LinuxShellExecutorTest.class);

//    @Test
    public void testExecPython() {
        ClassPathResource resource = new ClassPathResource("script/python-test.py");
        try {
            File file = resource.getFile();
            LinuxShellExecutor.execPython(file.getPath());
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
            LinuxShellExecutor.execShell(file);
        } catch (IOException e) {
            LOG.error("testExecShell", e);
        }
        assertTrue("*testExecShell*", true);
    }
    
    @Test
    public void testExecCmd() {
        try {
            LinuxShellExecutor.execCommand("cmd /k d: && dir");
        } catch (IOException e) {
            LOG.error("testExecCmd", e);
        }
        assertTrue("*testExecCmd*", true);
    }

}
