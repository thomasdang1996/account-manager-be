package com.dang.accountmanager.be;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(
		scanBasePackages = {"com.dang.accountmanager","com.dang.commonlib"}
)
public class AccountManagerBeApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccountManagerBeApplication.class, args);
	}

}
