package com.bytehonor.demo.execute.shell.model;

import java.util.List;

public class ExecResult {

    private List<String> inputStream;

    private List<String> errorStream;

    public List<String> getInputStream() {
        return inputStream;
    }

    public void setInputStream(List<String> inputStream) {
        this.inputStream = inputStream;
    }

    public List<String> getErrorStream() {
        return errorStream;
    }

    public void setErrorStream(List<String> errorStream) {
        this.errorStream = errorStream;
    }

}
