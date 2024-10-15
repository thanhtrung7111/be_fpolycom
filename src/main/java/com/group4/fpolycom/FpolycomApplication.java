package com.group4.fpolycom;

import dao.*;
import entity.*;
import entity.enum_package.*;
import org.hibernate.query.Order;
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
@ComponentScan(basePackages = {"security","service","admin_controller","user_controller","dto","common_controller","exeception_handler","notifycation_controller","store_controller","configuration"})
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

	@Autowired
	VoucherRepository voucherRepository;

	@Autowired
	DiscountRepository discountRepository;

	@Autowired
	StoreTransactionRepository storeTransactionRepository;

	@Autowired
	OrdersRepository ordersRepository;

	@Autowired
	DeliveryTypeRepository deliveryTypeRepository;

	@Autowired
	BankStoreRepository bankStoreRepository;

	@Autowired
	BankStoreRepository bankStoreRepository;


	public static void main(String[] args) {
		SpringApplication.run(FpolycomApplication.class, args);
	}



	@Override
	public void run(String... args) throws Exception {
		UserAccount userAccount =  UserAccount.builder().id(1L).email("thanhtrung711199@gmail.com").userLogin("thanhtrung").name("Thành Trung").password(encoder.encode("thanhtrung")).userStatus(UserStatus.active).address("35 Tran Dai Nghia,KP Noi Hoa 2, P. Binh AN, Tx. Di An, Tinh Binh Duong").createdDate(new Date()).updatedDate(null).deleted(false).deletedDate(null).build();
		UserAccount userAccount2 = UserAccount.builder().id(2L).email("thanhtrung7199@gmail.com").userLogin("thanhtrung1").name("Thành Trung 2").password(encoder.encode("thanhtrung1")).userStatus(UserStatus.active).address("35 Tran Dai Nghia,KP Noi Hoa 2, P. Binh AN, Tx. Di An, Tinh Binh Duong").createdDate(new Date()).updatedDate(null).deleted(false).deletedDate(null).build();
		UserAccount userAccount3 = UserAccount.builder().id(3L).email("ngocchau@gmail.com").userLogin("ngocchau").name("Ngọc Châu").password(encoder.encode("ngocchau")).userStatus(UserStatus.active).address("35 Tran Dai Nghia,KP Noi Hoa 2, P. Binh AN, Tx. Di An, Tinh Binh Duong").createdDate(new Date()).updatedDate(null).deleted(false).deletedDate(null).build();

		userAccountRepository.saveAll(List.of(userAccount,userAccount2,userAccount3));

		Relationship relationship = Relationship.builder().id(1L).userAccountPrimary(userAccount).userAccountSecondary(userAccount2).friendshipStatus(FriendshipStatus.accepted).build();
		Relationship relationship2 = Relationship.builder().id(2L).userAccountPrimary(userAccount3).userAccountSecondary(userAccount).friendshipStatus(FriendshipStatus.accepted).build();
		Relationship relationship3 = Relationship.builder().id(3L).userAccountPrimary(userAccount2).userAccountSecondary(userAccount).friendshipStatus(FriendshipStatus.accepted).build();
		Relationship relationship4 = Relationship.builder().id(4L).userAccountPrimary(userAccount).userAccountSecondary(userAccount3).friendshipStatus(FriendshipStatus.accepted).build();
		relationshipRepository.saveAll(List.of(relationship,relationship2,relationship3,relationship4));





		Administration administration = Administration.builder().id(1L).name("Thaành Trung").image("sdfsdf").userLogin("thanhtrung2").password(encoder.encode("thanhtrung2")).id(1L).createdDate(new Date()).updatedDate(null).deleted(false).deletedDate(null).build();
		adminRepository.save(administration);

		Province province = Province.builder().id(1L).createdDate(new Date()).deleted(false).deletedDate(null).updatedDate(null).name("Bình Dương").build();
		Province province2 = Province.builder().id(2L).createdDate(new Date()).deleted(false).deletedDate(null).updatedDate(null).name("Đồng Nai").build();
		provinceRepository.saveAll(List.of(province,province2));

		District district = District.builder().id(1L).createdDate(new Date()).deleted(false).deletedDate(null).updatedDate(null).name("Thuan an").province(province).build();
		District district2= District.builder().id(2L).createdDate(new Date()).deleted(false).deletedDate(null).updatedDate(null).name("Di An").province(province).build();
		districtRepository.saveAll(List.of(district2,district));

		Ward ward = Ward.builder().id(1L).createdDate(new Date()).deleted(false).deletedDate(null).updatedDate(null).name("Binh An").district(district).build();
		wardRepository.saveAll(List.of(ward));

		Store store = Store.builder().id(1L).userAccount(userAccount).district(district).province(province).ward(ward).name("Cuawr hang thu cng").storeStatus(StoreStatus.active).userAccount(userAccount).password(encoder.encode("thanhtrung")).storeStatus(StoreStatus.active).address("35 Tran Dai Nghia,KP Noi Hoa 2, P. Binh AN, Tx. Di An, Tinh Binh Duong").createdDate(new Date()).updatedDate(null).deleted(false).deletedDate(null).build();
		storeRepository.save(store);
		Followed followed = Followed.builder().id(1L).createdDate(new Date()).updatedDate(null).deleted(false).deletedDate(null).statusFollow(true).userAccount(userAccount2).store(store).build();
		followedRepository.save(followed);
		NotifycationUser notifycationUser = NotifycationUser.builder().id(1L).createdDate(new Date()).deleted(false).typeNotifycation(TypeNotifycationUser.voucher).content("Thong bao").readed(false).title("Thong bao so 1").userAccount(userAccount).build();
		NotifycationUser notifycationUser2 = NotifycationUser.builder().id(2L).createdDate(new Date()).deleted(false).typeNotifycation(TypeNotifycationUser.voucher).content("Thong bao").readed(false).title("Thong bao so 2").userAccount(userAccount).build();

		userNotifycationRepository.saveAll(List.of(notifycationUser,notifycationUser2));


		TypeGood typeGood = TypeGood.builder().id(1L).name("Laptop").build();
		typeGoodRepository.save(typeGood);

		Product product = Product.builder().id(1L).createdDate(new Date()).updatedDate(null).productStatus(ProductStatus.active).name("San pharm 01").store(store).typeGood(typeGood).build();
		productRepository.save(product);

		Discount discount = Discount.builder().id(1L).percentDecrease(10).build();
		discountRepository.save(discount);

		ProductDetail productDetail = ProductDetail.builder().discount(discount).quantity(30).image("asdsad").price(50000.0).id(1L).name("RAM").product(product).build();

		ProductDetail productDetail2 = ProductDetail.builder().discount(discount).quantity(20).image("ádsad").price(500000.0).id(2L).name("RAM2").product(product).build();
		productDetailRepository.saveAll(List.of(productDetail,productDetail2));

		ShoppingCart shoppingCart = ShoppingCart.builder().id(1L).productDetail(productDetail).userAccount(userAccount).quantity(5).build();
		shoppingCartRepository.save(shoppingCart);

		Liked liked = Liked.builder().id(1L).createdDate(new Date()).updatedDate(null).deletedDate(null).deleted(false).userAccount(userAccount).product(product).build();
		likedRepository.save(liked);

		Bank bank	 = Bank.builder().id(1L).name("Ngan hang bidv").shortName("BIDV").build();
		Bank bank2	 = Bank.builder().id(2L).name("Ngan hang mb bank").shortName("MB").build();
		bankRepository.saveAll(List.of(bank,bank2));

		BankBranch bankBranch = BankBranch.builder().id(1L).name("chi nhanh binh duong").bank(bank).build();
		BankBranch bankBranch2 = BankBranch.builder().id(2L).name("chi nhanh binh duong mien bac").bank(bank).build();
		bankBranchRepository.saveAll(List.of(bankBranch,bankBranch2));


		BankStore bankStore = BankStore.builder().id(1L).bankStoreStatus(BankStatus.active).bankBranch(bankBranch).accountName("THANH TRUNG").accountNumber("123213").build();
		bankStoreRepository.save(bankStore);

		Voucher voucher = Voucher.builder().id(1L).voucherType(VoucherType.store).store(store).beginDate(new Date()).endDate(new Date()).percentDecrease(10).priceApply(100000.0).name("Khuye mai thang 10").beginDate(new Date()).endDate(new Date()).build();
		voucherRepository.save(voucher);

		DeliveryType deliveryType = DeliveryType.builder().id(1L).name("Ship hang sieu toc").createdDate(new Date()).deletedDate(new Date()).deleted(false).updatedDate(null).fee(20000.0).build();
		deliveryTypeRepository.save(deliveryType);



		PaymentType paymentType = PaymentType.builder().id(1L).name("Thanh toasn viet qr").image("sfsdf").build();
		paymentTypeRepository.save(paymentType);

		Orders orders = Orders.builder().paymentType(paymentType).shippingFee(shippingFee).orderStatus(OrderStatus.complete).createdDate(new Date()).deleted(false).address("35 Tran dai nghia").addressDetail("35 Trai Dai Nghiax kp noi hoa 2 ").updatedDate(null).deletedDate(null).id(1L).pickupDate(new Date()).noteContent("Content").store(store).deliveryType(deliveryType).userAccount(userAccount).finalTotal(50000.0).totalAmount(60000.0).totalAmountShip(80000.0).totalAmountVoucher(30000.0).deliveryDate(new Date()).build();
		ordersRepository.save(orders);

		StoreTransaction storeTransaction = StoreTransaction.builder().id(1L).typeTransaction(TypeTransaction.withdraw).totalAmount(10000.0).content("hmmmm").transactionStatus(TransactionStatus.pending).build();
		storeTransactionRepository.save(storeTransaction);

	}

}
