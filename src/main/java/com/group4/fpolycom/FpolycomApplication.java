package com.group4.fpolycom;

import dao.UserAccountRepository;
import entity.UserAccount;
import entity.enum_package.UserStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Date;

@SpringBootApplication
@EntityScan(basePackages = {"entity"})
@EnableJpaRepositories(basePackages = {"dao"})
@ComponentScan(basePackages = {"security","service","admin_controller","user_controller","dto"})
public class FpolycomApplication implements CommandLineRunner {

	@Autowired
	UserAccountRepository userAccountRepository;

	@Autowired
	PasswordEncoder encoder;

	public static void main(String[] args) {
		SpringApplication.run(FpolycomApplication.class, args);
	}



	@Override
	public void run(String... args) throws Exception {
		UserAccount userAccount = UserAccount.builder().userLogin("thanhtrung").password(encoder.encode("thanhtrung")).userStatus(UserStatus.active).address("35 Tran Dai Nghia,KP Noi Hoa 2, P. Binh AN, Tx. Di An, Tinh Binh Duong").id(1).createdDate(new Date()).updatedDate(null).deleted(false).deletedDate(null).build();
		userAccountRepository.save(userAccount);
	}
}
