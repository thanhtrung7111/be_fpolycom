package com.group4.fpolycom;

import dao.*;
import entity.*;
import entity.enum_package.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.password.PasswordEncoder;
import service.district.DistrictService;

import java.lang.reflect.Type;
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
	WardRepository wardRepository;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	StoreRepository storeRepository;

	@Autowired
	UserNotifycationRepository userNotifycationRepository;

	@Autowired
	RelationshipRepository relationshipRepository;

	@Autowired
	FollowedRepository followedRepository;

	@Autowired
	ProductRepository productRepository;

	@Autowired
	TypeGoodRepository typeGoodRepository;

	@Autowired
	LikedRepository likedRepository;

	@Autowired
	ProductDetailRepository productDetailRepository;

	@Autowired
	ShoppingCartRepository shoppingCartRepository;

	@Autowired
	BankRepository bankRepository;

	@Autowired
	BankBranchRepository bankBranchRepository;

	public static void main(String[] args) {
		SpringApplication.run(FpolycomApplication.class, args);
	}



	@Override
	public void run(String... args) throws Exception {
		UserAccount userAccount =  UserAccount.builder().id(1L).passwordBank("123456").email("thanhtrung711199@gmail.com").userLogin("thanhtrung").name("Thành Trung").password(encoder.encode("thanhtrung")).userStatus(UserStatus.active).address("35 Tran Dai Nghia,KP Noi Hoa 2, P. Binh AN, Tx. Di An, Tinh Binh Duong").createdDate(new Date()).updatedDate(null).deleted(false).deletedDate(null).build();
		UserAccount userAccount2 = UserAccount.builder().id(2L).email("thanhtrung7199@gmail.com").userLogin("thanhtrung1").name("Thành Trung 2").password(encoder.encode("thanhtrung1")).userStatus(UserStatus.active).address("35 Tran Dai Nghia,KP Noi Hoa 2, P. Binh AN, Tx. Di An, Tinh Binh Duong").createdDate(new Date()).updatedDate(null).deleted(false).deletedDate(null).build();
		UserAccount userAccount3 = UserAccount.builder().id(3L).email("ngocchau@gmail.com").userLogin("ngocchau").name("Ngọc Châu").password(encoder.encode("ngocchau")).userStatus(UserStatus.active).address("35 Tran Dai Nghia,KP Noi Hoa 2, P. Binh AN, Tx. Di An, Tinh Binh Duong").createdDate(new Date()).updatedDate(null).deleted(false).deletedDate(null).build();

		userAccountRepository.saveAll(List.of(userAccount,userAccount2,userAccount3));

		Relationship relationship = Relationship.builder().id(1).userAccountPrimary(userAccount).userAccountSecondary(userAccount2).friendshipStatus(FriendshipStatus.accepted).build();
		Relationship relationship2 = Relationship.builder().id(2).userAccountPrimary(userAccount3).userAccountSecondary(userAccount).friendshipStatus(FriendshipStatus.accepted).build();
		Relationship relationship3 = Relationship.builder().id(3).userAccountPrimary(userAccount2).userAccountSecondary(userAccount).friendshipStatus(FriendshipStatus.accepted).build();
		Relationship relationship4 = Relationship.builder().id(4).userAccountPrimary(userAccount).userAccountSecondary(userAccount3).friendshipStatus(FriendshipStatus.accepted).build();
		relationshipRepository.saveAll(List.of(relationship,relationship2,relationship3,relationship4));

		Store store = Store.builder().id(1).name("Cuawr hang thu cng").storeStatus(StoreStatus.active).userAccount(userAccount).password(encoder.encode("thanhtrung")).storeStatus(StoreStatus.active).address("35 Tran Dai Nghia,KP Noi Hoa 2, P. Binh AN, Tx. Di An, Tinh Binh Duong").id(1).createdDate(new Date()).updatedDate(null).deleted(false).deletedDate(null).build();
		storeRepository.save(store);
		Followed followed = Followed.builder().id(1).createdDate(new Date()).updatedDate(null).deleted(false).deletedDate(null).statusFollow(true).userAccount(userAccount2).store(store).build();
		followedRepository.save(followed);



		Administration administration = Administration.builder().id(1).userLogin("thanhtrung2").password(encoder.encode("thanhtrung2")).id(1).createdDate(new Date()).updatedDate(null).deleted(false).deletedDate(null).build();
		adminRepository.save(administration);

		Province province = Province.builder().id(1).createdDate(new Date()).deleted(false).deletedDate(null).updatedDate(null).name("Bình Dương").build();
		Province province2 = Province.builder().id(2).createdDate(new Date()).deleted(false).deletedDate(null).updatedDate(null).name("Đồng Nai").build();
		provinceRepository.saveAll(List.of(province,province2));

		District district = District.builder().id(1).createdDate(new Date()).deleted(false).deletedDate(null).updatedDate(null).name("Thuan an").province(province).build();
		District district2= District.builder().id(2).createdDate(new Date()).deleted(false).deletedDate(null).updatedDate(null).name("Di An").province(province).build();
		districtRepository.saveAll(List.of(district2,district));

		Ward ward = Ward.builder().id(1).createdDate(new Date()).deleted(false).deletedDate(null).updatedDate(null).name("Binh An").district(district).build();
		wardRepository.saveAll(List.of(ward));

		NotifycationUser notifycationUser = NotifycationUser.builder().id(1).createdDate(new Date()).deleted(false).typeNotifycation(TypeNotifycationUser.voucher).content("Thong bao").readed(false).title("Thong bao so 1").userAccount(userAccount).build();
		NotifycationUser notifycationUser2 = NotifycationUser.builder().id(2).createdDate(new Date()).deleted(false).typeNotifycation(TypeNotifycationUser.voucher).content("Thong bao").readed(false).title("Thong bao so 2").userAccount(userAccount).build();

		userNotifycationRepository.saveAll(List.of(notifycationUser,notifycationUser2));


		TypeGood typeGood = TypeGood.builder().id(1).name("Laptop").build();
		typeGoodRepository.save(typeGood);

		Product product = Product.builder().id(1).createdDate(new Date()).updatedDate(null).productStatus(ProductStatus.active).name("San pharm 01").store(store).typeGood(typeGood).build();
		productRepository.save(product);

		ProductDetail productDetail = ProductDetail.builder().price(50000.0).id(1).name("RAM").product(product).build();
		productDetailRepository.save(productDetail);

		ShoppingCart shoppingCart = ShoppingCart.builder().id(1).productDetail(productDetail).userAccount(userAccount).quantity(5).build();
		shoppingCartRepository.save(shoppingCart);

		Liked liked = Liked.builder().id(1).createdDate(new Date()).updatedDate(null).deletedDate(null).deleted(false).userAccount(userAccount).product(product).build();
		likedRepository.save(liked);

		Bank bank	 = Bank.builder().id(1).name("Ngan hang bidv").shortName("BIDV").build();
		Bank bank2	 = Bank.builder().id(2).name("Ngan hang mb bank").shortName("MB").build();
		bankRepository.saveAll(List.of(bank,bank2));

		BankBranch bankBranch = BankBranch.builder().id(1).name("chi nhanh binh duong").bank(bank).build();
		BankBranch bankBranch2 = BankBranch.builder().id(2).name("chi nhanh binh duong mien bac").bank(bank).build();
		bankBranchRepository.saveAll(List.of(bankBranch,bankBranch2));

	}

}
