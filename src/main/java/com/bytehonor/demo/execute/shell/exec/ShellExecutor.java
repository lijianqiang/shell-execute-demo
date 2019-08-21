package com.bytehonor.demo.execute.shell.exec;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ShellExecutor {

    private static final Logger LOG = LoggerFactory.getLogger(ShellExecutor.class);

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

    public static void execShell(String shell) throws IOException {
        Objects.requireNonNull(shell, "filePath");
        if (shell.endsWith(".sh") == false) {
            throw new RuntimeException("not py file, " + shell);
        }
        File file = new File(shell);
        if (file.exists() == false) {
            throw new RuntimeException("not exist, " + shell);
        }
        if (file.canExecute() == false) {
            throw new RuntimeException("canExecute = false, " + shell);
        }

        Process process = Runtime.getRuntime().exec(file.getPath());

        LOG.info("--getInputStream--");
        printStream(process.getInputStream());

        LOG.info("--getErrorStream--");
        printStream(process.getErrorStream());

    }

    private static void printStream(InputStream inputStream) {
        if (inputStream == null) {
            LOG.error("input null");
            return;
        }

        String line = "";

        try (BufferedReader input = new BufferedReader(new InputStreamReader(inputStream))) {
            while ((line = input.readLine()) != null) {
                LOG.info("line:{}", line);
            }
        } catch (IOException e1) {
            LOG.error("输出流失败", e1);
        }

    }

}
