package com.bytehonor.demo.execute.shell.exec;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import com.bytehonor.demo.execute.shell.model.ExecResult;

public class CommandExecutor {

    private static final Logger LOG = LoggerFactory.getLogger(CommandExecutor.class);

    private static boolean IS_WINDOWS = false;

    static {
        Properties properties = System.getProperties();
        // 获取键值
//        Set<Object> keySet = properties.keySet();
//        for (Object object : keySet) {
//            System.out.println(object + ", " + properties.get(object));
//        }
        String os = (String) properties.get("os.name");
        IS_WINDOWS = os.toLowerCase().contains("windows");
    }

    public static Process execPython(String filePath) throws IOException {
        Objects.requireNonNull(filePath, "filePath");
        if (filePath.endsWith(".py") == false) {
            throw new RuntimeException("not py file, " + filePath);
        }
        File file = new File(filePath);
        if (file.exists() == false) {
            throw new RuntimeException("not exist, " + filePath);
        }
        String command = "python " + filePath;

        return execCommand(command);

    }

    public static Process execShell(File file) throws IOException {
        Objects.requireNonNull(file, "file");
        if (file.exists() == false) {
            throw new RuntimeException("not exist, " + file.getPath());
        }
        if (file.canExecute() == false) {
            throw new RuntimeException("canExecute = false, " + file.getPath());
        }

        return execCommand(file.getPath());
    }

    public static Process execCommand(String cmd) throws IOException {
        Objects.requireNonNull(cmd, "cmd");
        if (LOG.isDebugEnabled()) {
            LOG.debug("cmd:{}", cmd);
        }

        return Runtime.getRuntime().exec(cmd);
    }

    public static ExecResult toResult(Process process) {
        ExecResult result = new ExecResult();
        result.setInputStream(streamList(process.getInputStream()));
        result.setErrorStream(streamList(process.getErrorStream()));
        return result;
    }

    public static void print(Process process) {
        if (process == null) {
            LOG.error("process null");
            return;
        }
        ExecResult result = toResult(process);
        if (CollectionUtils.isEmpty(result.getInputStream()) == false) {
            LOG.info("--getInputStream--");
            for (String stream : result.getInputStream()) {
                LOG.info("line:{}", stream);
            }
        }
        if (CollectionUtils.isEmpty(result.getErrorStream()) == false) {
            LOG.info("--getErrorStream--");
            for (String stream : result.getErrorStream()) {
                LOG.info("error:{}", stream);
            }
        }
    }

    public static List<String> streamList(InputStream inputStream) {
        List<String> list = new ArrayList<String>();
        if (inputStream == null) {
            LOG.error("input null");
            return list;
        }

        String line = "";
        BufferedReader reader = null;
        try {
            if (IS_WINDOWS) {
                reader = new BufferedReader(new InputStreamReader(inputStream, "GBK"));
            } else {
                reader = new BufferedReader(new InputStreamReader(inputStream));
            }
            while ((line = reader.readLine()) != null) {
                list.add(line);
            }
        } catch (IOException e1) {
            LOG.error("输出流失败", e1);
        } finally {
            close(reader);
        }
        return list;
    }

    public static void printStream(InputStream inputStream) {
        if (inputStream == null) {
            LOG.error("input null");
            return;
        }

        String line = "";
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(inputStream));
            while ((line = reader.readLine()) != null) {
                LOG.info("line:{}", line);
            }
        } catch (IOException e1) {
            LOG.error("输出流失败", e1);
        } finally {
            close(reader);
        }
    }

    public static void printStreamGBK(InputStream inputStream) {
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
