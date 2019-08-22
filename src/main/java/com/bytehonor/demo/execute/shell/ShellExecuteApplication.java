package com.bytehonor.demo.execute.shell;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.bytehonor.sdk.server.spring.annotation.SpringBootStandard;

@SpringBootStandard
@SpringBootApplication
public class ShellExecuteApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShellExecuteApplication.class, args);
	}

}
