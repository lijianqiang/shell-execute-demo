package com.bytehonor.demo.execute.shell.exec;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WindowsCmdExecutor {

    private static final Logger LOG = LoggerFactory.getLogger(WindowsCmdExecutor.class);
    
    public static void execPython(String filePath) throws IOException {
        Objects.requireNonNull(filePath, "filePath");
        if (filePath.endsWith(".py") == false) {
            throw new RuntimeException("not py file, " + filePath);
        }
        File file = new File(filePath);
        if (file.exists() == false) {
            throw new RuntimeException("not exist, " + filePath);
        }
        String command = "python " + filePath;

        Process process = Runtime.getRuntime().exec(command);

        LOG.info("--getInputStream--");
        printStream(process.getInputStream());

        LOG.info("--getErrorStream--");
        printStream(process.getErrorStream());

    }

    public static void execCmd(String cmd) throws IOException {
        Objects.requireNonNull(cmd, "cmd");

        Process process = Runtime.getRuntime().exec(cmd);

        LOG.info("--getInputStream--");
        printStream(process.getInputStream());

        LOG.info("--getErrorStream--");
        printStream(process.getErrorStream());
    }

    public static void printStream(InputStream inputStream) {
        if (inputStream == null) {
            LOG.error("input null");
            return;
        }

        String line = "";
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(inputStream, "GBK"));
            while ((line = reader.readLine()) != null) {
                LOG.info("line:{}", line);
            }
        } catch (IOException e1) {
            LOG.error("输出流失败", e1);
        } finally {
            close(reader);
        }
    }

    private static void close(BufferedReader reader) {
        if (reader != null) {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
