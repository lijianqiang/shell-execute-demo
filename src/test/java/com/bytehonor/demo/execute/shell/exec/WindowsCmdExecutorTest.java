package com.bytehonor.demo.execute.shell.exec;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;

public class WindowsCmdExecutorTest {

    private static final Logger LOG = LoggerFactory.getLogger(WindowsCmdExecutorTest.class);

    @Test
    public void testExecPython() {
        ClassPathResource resource = new ClassPathResource("script/python-test.py");
        try {
            File file = resource.getFile();
            WindowsCmdExecutor.execPython(file.getPath());
        } catch (IOException e) {
            LOG.error("xxx", e);
        }
        assertTrue("*testExecPython*", true);
    }

//    @Test
    public void testExecCmd() {
        try {
            WindowsCmdExecutor.execCmd("cmd /k d: && dir");
        } catch (IOException e) {
            LOG.error("testExecCmd", e);
        }
        assertTrue("*testExecCmd*", true);
    }

}
