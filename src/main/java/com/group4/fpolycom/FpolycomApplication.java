package com.group4.fpolycom;

import dao.*;
import entity.*;
import entity.enum_package.StoreStatus;
import entity.enum_package.UserStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.password.PasswordEncoder;
import service.district.DistrictService;

import java.util.Date;
import java.util.List;

@SpringBootApplication
@EntityScan(basePackages = {"entity"})
@EnableJpaRepositories(basePackages = {"dao"})
@ComponentScan(basePackages = {"security","service","admin_controller","user_controller","dto","common_controller","exeception_handler","notifycation_controller","configuration"})
public class FpolycomApplication implements CommandLineRunner {

	@Autowired
	UserAccountRepository userAccountRepository;

	@Autowired
	AdminRepository adminRepository;

	@Autowired
	ProvinceRepository provinceRepository;

	@Autowired
	DistrictRepository districtRepository;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	StoreRepository storeRepository;

	public static void main(String[] args) {
		SpringApplication.run(FpolycomApplication.class, args);
	}



	@Override
	public void run(String... args) throws Exception {
		UserAccount userAccount = UserAccount.builder().id(1L).userLogin("thanhtrung").name("Thành Trung").password(encoder.encode("thanhtrung")).userStatus(UserStatus.active).address("35 Tran Dai Nghia,KP Noi Hoa 2, P. Binh AN, Tx. Di An, Tinh Binh Duong").id(1).createdDate(new Date()).updatedDate(null).deleted(false).deletedDate(null).build();
		userAccountRepository.save(userAccount);

		Store store = Store.builder().id(1L).name("Cuawr hang thu cng").userAccount(userAccount).password(encoder.encode("thanhtrung")).storeStatus(StoreStatus.active).address("35 Tran Dai Nghia,KP Noi Hoa 2, P. Binh AN, Tx. Di An, Tinh Binh Duong").id(1).createdDate(new Date()).updatedDate(null).deleted(false).deletedDate(null).build();
		storeRepository.save(store);

		Administration administration = Administration.builder().id(1L).userLogin("thanhtrung2").password(encoder.encode("thanhtrung2")).id(1).createdDate(new Date()).updatedDate(null).deleted(false).deletedDate(null).build();
		adminRepository.save(administration);

		Province province = Province.builder().id(1).createdDate(new Date()).deleted(false).deletedDate(null).updatedDate(null).name("Bình Dương").build();
		Province province2 = Province.builder().id(2).createdDate(new Date()).deleted(false).deletedDate(null).updatedDate(null).name("Đồng Nai").build();
		provinceRepository.saveAll(List.of(province,province2));

		District district = District.builder().id(1).createdDate(new Date()).deleted(false).deletedDate(null).updatedDate(null).name("Thuan an").province(province).build();
		District district2= District.builder().id(2).createdDate(new Date()).deleted(false).deletedDate(null).updatedDate(null).name("Di An").province(province).build();
		districtRepository.saveAll(List.of(district2,district));


	}

}
