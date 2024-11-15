package com.group4.fpolycom;

import dao.*;
import entity.*;
import entity.enum_package.*;
import jakarta.persistence.EntityNotFoundException;
import org.hibernate.query.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import service.district.DistrictService;

import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@SpringBootApplication
@EntityScan(basePackages = {"entity"})
@EnableJpaRepositories(basePackages = {"dao"})
@ComponentScan(basePackages = {"security","service","admin_controller","user_controller","dto","common_controller","exeception_handler","notifycation_controller","store_controller","configuration","shipper_controller"})
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
	PaymentTypeRepository paymentTypeRepository;


	@Autowired
	ShippingFeeRepository shippingFeeRepository;


	@Autowired
	PaymentWallerStoreRepository paymentWallerStoreRepository;


	@Autowired
	ShipperRepository shipperRepository;


	@Autowired
	OrderDetailRepository orderDetailRepository;


	@Autowired
	ReceiveDeliveryRepository receiveDeliveryRepository;



	public static void main(String[] args) {
		SpringApplication.run(FpolycomApplication.class, args);
	}



	@Override
	public void run(String... args) throws Exception {
//		UserAccount userAccount =  UserAccount.builder().id(1L).email("thanhtrung711199@gmail.com").userLogin("thanhtrung").name("Thành Trung").password(encoder.encode("thanhtrung")).userStatus(UserStatus.active).address("35 Tran Dai Nghia,KP Noi Hoa 2, P. Binh AN, Tx. Di An, Tinh Binh Duong").createdDate(new Date()).province(Province.builder().id(1L).build()).updatedDate(null).deleted(false).deletedDate(null).build();
//		UserAccount userAccount2 = UserAccount.builder().id(2L).email("thanhtrung7199@gmail.com").userLogin("thanhtrung1").name("Thành Trung 2").password(encoder.encode("thanhtrung1")).userStatus(UserStatus.active).address("35 Tran Dai Nghia,KP Noi Hoa 2, P. Binh AN, Tx. Di An, Tinh Binh Duong").createdDate(new Date()).updatedDate(null).deleted(false).deletedDate(null).build();
//		UserAccount userAccount3 = UserAccount.builder().id(3L).email("ngocchau@gmail.com").userLogin("ngocchau").name("Ngọc Châu").password(encoder.encode("ngocchau")).userStatus(UserStatus.active).address("35 Tran Dai Nghia,KP Noi Hoa 2, P. Binh AN, Tx. Di An, Tinh Binh Duong").createdDate(new Date()).updatedDate(null).deleted(false).deletedDate(null).build();
//
//		userAccountRepository.saveAll(List.of(userAccount,userAccount2,userAccount3));
//
//		Relationship relationship = Relationship.builder().id(1L).userAccountPrimary(userAccount).userAccountSecondary(userAccount2).friendshipStatus(FriendshipStatus.accepted).build();
//		Relationship relationship2 = Relationship.builder().id(2L).userAccountPrimary(userAccount3).userAccountSecondary(userAccount).friendshipStatus(FriendshipStatus.accepted).build();
//		Relationship relationship3 = Relationship.builder().id(3L).userAccountPrimary(userAccount2).userAccountSecondary(userAccount).friendshipStatus(FriendshipStatus.accepted).build();
//		Relationship relationship4 = Relationship.builder().id(4L).userAccountPrimary(userAccount).userAccountSecondary(userAccount3).friendshipStatus(FriendshipStatus.accepted).build();
//		relationshipRepository.saveAll(List.of(relationship,relationship2,relationship3,relationship4));
//
//		Administration administration = Administration.builder().id(1L).name("Thaành Trung").image("sdfsdf").userLogin("thanhtrung2").password(encoder.encode("thanhtrung2")).id(1L).createdDate(new Date()).updatedDate(null).deleted(false).deletedDate(null).build();
//		adminRepository.save(administration);
//
//		Province province = Province.builder().id(1L).createdDate(new Date()).deleted(false).deletedDate(null).updatedDate(null).name("Bình Dương").build();
//		Province province2 = Province.builder().id(2L).createdDate(new Date()).deleted(false).deletedDate(null).updatedDate(null).name("Đồng Nai").build();
//		provinceRepository.saveAll(List.of(province,province2));
//
//		District district = District.builder().id(1L).createdDate(new Date()).deleted(false).deletedDate(null).updatedDate(null).name("Thuan an").province(province).build();
//		District district2= District.builder().id(2L).createdDate(new Date()).deleted(false).deletedDate(null).updatedDate(null).name("Di An").province(province).build();
//		districtRepository.saveAll(List.of(district2,district));
//
//		Ward ward = Ward.builder().id(1L).createdDate(new Date()).deleted(false).deletedDate(null).updatedDate(null).name("Binh An").district(district).build();
//		wardRepository.saveAll(List.of(ward));
//
//		Store store = Store.builder().id(1L).userAccount(userAccount).district(district).province(province).ward(ward).name("Cửa hàng thú cưng").email("Thucungw@gmail.com").bannerImage("https://tdtdecor.vn/wp-content/uploads/2021/07/thiet-ke-shop-thu-cung-13.jpg").image("https://freelancervietnam.vn/wp-content/uploads/2020/06/post-thumb-pet-shop.jpg").phone("0909197031").password(encoder.encode("123456")).storeStatus(StoreStatus.active).address("35 Tran Dai Nghia,KP Noi Hoa 2, P. Binh AN, Tx. Di An, Tinh Binh Duong").createdDate(new Date()).updatedDate(null).deleted(false).deletedDate(null).build();
////		Store store2 = Store.builder().id(5L).userAccount(userAccount2).district(district).province(province).ward(ward).name("Dien thoai di dong").email("Th22ucungw@gmail.com").bannerImage("https://tdtdecor.vn/wp-content/uploads/2021/07/thiet-ke-shop-thu-cung-13.jpg").image("https://freelancervietnam.vn/wp-content/uploads/2020/06/post-thumb-pet-shop.jpg").password(encoder.encode("123456")).storeStatus(StoreStatus.active).address("35 Tran Dai Nghia,KP Noi Hoa 2, P. Binh AN, Tx. Di An, Tinh Binh Duong").createdDate(new Date()).updatedDate(null).deleted(false).deletedDate(null).build();
//		storeRepository.saveAll(List.of(store));
//
//		PaymentWalletStore paymentWalletStore  = PaymentWalletStore.builder().id(1L).store(store).balance(500000.0).createdDate(new Date()).updatedDate(null).deletedDate(null).deleted(false).build();
//		paymentWallerStoreRepository.save(paymentWalletStore);
//
//		Followed followed = Followed.builder().id(1L).createdDate(new Date()).updatedDate(null).deleted(false).deletedDate(null).statusFollow(true).userAccount(userAccount2).store(store).build();
//		followedRepository.save(followed);
//		NotifycationUser notifycationUser = NotifycationUser.builder().id(1L).createdDate(new Date()).deleted(false).typeNotifycation(TypeNotifycationUser.voucher).content("Thong bao").readed(false).title("Thong bao so 1").userAccount(userAccount).build();
//		NotifycationUser notifycationUser2 = NotifycationUser.builder().id(2L).createdDate(new Date()).deleted(false).typeNotifycation(TypeNotifycationUser.voucher).content("Thong bao").readed(false).title("Thong bao so 2").userAccount(userAccount).build();
//
//		userNotifycationRepository.saveAll(List.of(notifycationUser,notifycationUser2));
//
//
//		TypeGood typeGood = TypeGood.builder().id(1L).name("Laptop").build();
//		typeGoodRepository.save(typeGood);
//
//		Product product = Product.builder().id(1L).image("https://tiki.vn/blog/wp-content/uploads/2023/11/laptop-mini.jpeg").createdDate(new Date()).updatedDate(null).productStatus(ProductStatus.active).name("San pharm 01").store(store).typeGood(typeGood).build();
//		productRepository.save(product);
//
//		Discount discount = Discount.builder().id(1L).name("Không giảm giá").percentDecrease(10).build();
//		discountRepository.save(discount);
//
//		ProductDetail productDetail = ProductDetail.builder().discount(discount).quantity(30).image("asdsad").price(50000.0).id(1L).name("RAM").product(product).build();
//
//		ProductDetail productDetail2 = ProductDetail.builder().discount(discount).quantity(20).image("ádsad").price(500000.0).id(2L).name("RAM2").product(product).build();
//		productDetailRepository.saveAll(List.of(productDetail,productDetail2));
//
//		ShoppingCart shoppingCart = ShoppingCart.builder().id(1L).productDetail(productDetail).userAccount(userAccount).quantity(5).build();
//		shoppingCartRepository.save(shoppingCart);
//
//		Liked liked = Liked.builder().id(1L).createdDate(new Date()).updatedDate(null).deletedDate(null).deleted(false).userAccount(userAccount).product(product).build();
//		likedRepository.save(liked);
//
//		ShippingFee shippingFee = ShippingFee.builder().id(1L).createdDate(new Date()).updatedDate(null).deletedDate(null).deleted(false).typeShipping(TypeShipping.inner).fee(20000.0).build();
//		ShippingFee shippingFee2 = ShippingFee.builder().id(2L).createdDate(new Date()).updatedDate(null).deletedDate(null).deleted(false).typeShipping(TypeShipping.outer).fee(30000.0).build();
//		shippingFeeRepository.saveAll(List.of(shippingFee,shippingFee2));
//
//		Bank bank	 = Bank.builder().id(1L).name("Ngan hang bidv").shortName("BIDV").build();
//		Bank bank2	 = Bank.builder().id(2L).name("Ngan hang mb bank").shortName("MB").build();
//		bankRepository.saveAll(List.of(bank,bank2));
//
//
//		BankBranch bankBranch = BankBranch.builder().id(1L).name("chi nhanh binh duong").bank(bank).build();
//		BankBranch bankBranch2 = BankBranch.builder().id(2L).name("chi nhanh binh duong mien bac").bank(bank).build();
//		bankBranchRepository.saveAll(List.of(bankBranch,bankBranch2));
//
//
//		BankStore bankStore = BankStore.builder().id(1L).store(store).bankStoreStatus(BankStatus.active).accountName("THANH TRUNG").accountNumber("123213").build();
//
//		bankStoreRepository.save(bankStore);
//
//		Voucher voucher = Voucher.builder().id(1L).voucherType(VoucherType.store).store(store).beginDate(new Date()).endDate(new Date()).percentDecrease(10).priceApply(100000.0).name("Khuye mai thang 10").beginDate(new Date()).endDate(new Date()).build();
//		voucherRepository.save(voucher);
//
//		DeliveryType deliveryType = DeliveryType.builder().id(1L).name("Ship hang sieu toc").createdDate(new Date()).deletedDate(new Date()).deleted(false).updatedDate(null).fee(20000.0).build();
//		deliveryTypeRepository.save(deliveryType);
//
//
//
//		PaymentType paymentType = PaymentType.builder().id(1L).name("Thanh toasn viet qr").image("sfsdf").build();
//		paymentTypeRepository.save(paymentType);
//
//		Orders orders = Orders.builder().paymentType(paymentType).orderStatus(OrderStatus.complete).createdDate(new Date()).deleted(false).address("35 Tran dai nghia").addressDetail("35 Trai Dai Nghiax kp noi hoa 2 ").updatedDate(null).deletedDate(null).id(1L).pickupDate(new Date()).noteContent("Content").store(store).deliveryType(deliveryType).userAccount(userAccount).finalTotal(50000.0).totalAmount(60000.0).totalAmountShip(80000.0).totalAmountVoucher(30000.0).deliveryDate(new Date()).build();
//		ordersRepository.save(orders);

		ExampleData();
	}

	public void ExampleData(){
		provinceRepository.saveAll(Arrays.asList(
				Province.builder().id(1L).name("Thành phố Hồ Chí Minh").build(),
				Province.builder().id(2L).name("Thành phố Bình Dương").build(),
				Province.builder().id(3L).name("Tỉnh Lâm Đồng").build()
		));

		districtRepository.saveAll(Arrays.asList(
				District.builder().id(1L).name("Quận 1")			.province(provinceRepository.findById(1L).orElseThrow(() -> new EntityNotFoundException(""))).build(),
				District.builder().id(2L).name("Quận Bình Thạnh")	.province(provinceRepository.findById(1L).orElseThrow(() -> new EntityNotFoundException(""))).build(),
				District.builder().id(3L).name("Quận Châu Thành")	.province(provinceRepository.findById(2L).orElseThrow(() -> new EntityNotFoundException(""))).build(),
				District.builder().id(4L).name("Quận Lái Thiêu")	.province(provinceRepository.findById(2L).orElseThrow(() -> new EntityNotFoundException(""))).build(),
				District.builder().id(5L).name("Huyện Lâm Hà")		.province(provinceRepository.findById(3L).orElseThrow(() -> new EntityNotFoundException(""))).build(),
				District.builder().id(6L).name("Thành phố Đà Lạt")	.province(provinceRepository.findById(3L).orElseThrow(() -> new EntityNotFoundException(""))).build()
		));

		wardRepository.saveAll(Arrays.asList(
				Ward.builder().id(1L).name("Phường 1")			.district(districtRepository.findById(1L).orElseThrow(() -> new EntityNotFoundException(""))).build(),
				Ward.builder().id(2L).name("Phường 2")			.district(districtRepository.findById(1L).orElseThrow(() -> new EntityNotFoundException(""))).build(),
				Ward.builder().id(3L).name("Phường 13")			.district(districtRepository.findById(2L).orElseThrow(() -> new EntityNotFoundException(""))).build(),
				Ward.builder().id(4L).name("Phường 4")			.district(districtRepository.findById(2L).orElseThrow(() -> new EntityNotFoundException(""))).build(),
				Ward.builder().id(5L).name("Phường 5")			.district(districtRepository.findById(3L).orElseThrow(() -> new EntityNotFoundException(""))).build(),
				Ward.builder().id(6L).name("Phường 6")			.district(districtRepository.findById(3L).orElseThrow(() -> new EntityNotFoundException(""))).build(),
				Ward.builder().id(7L).name("Phường 7")			.district(districtRepository.findById(4L).orElseThrow(() -> new EntityNotFoundException(""))).build(),
				Ward.builder().id(8L).name("Phường 8")			.district(districtRepository.findById(4L).orElseThrow(() -> new EntityNotFoundException(""))).build(),
				Ward.builder().id(9L).name("Thị trấn Đinh Văn")	.district(districtRepository.findById(5L).orElseThrow(() -> new EntityNotFoundException(""))).build(),
				Ward.builder().id(10L).name("Thị trấn Nam Ban")	.district(districtRepository.findById(5L).orElseThrow(() -> new EntityNotFoundException(""))).build(),
				Ward.builder().id(11L).name("Phường 9")			.district(districtRepository.findById(6L).orElseThrow(() -> new EntityNotFoundException(""))).build(),
				Ward.builder().id(12L).name("Phường 10")		.district(districtRepository.findById(6L).orElseThrow(() -> new EntityNotFoundException(""))).build()
		));

		userAccountRepository.saveAll(Arrays.asList(
				UserAccount.builder().id(1L).email("Ngvd4901@gmail.com")	.userLogin("Ngvd4901")	.name("Nguyễn Viết Đức")	.password(encoder.encode("123456"))	.userStatus(UserStatus.active)
						.createdDate(new Date())
						.updatedDate(null)
						.deleted(false)
						.deletedDate(null)
						.province(	provinceRepository	.findById(1L).orElseThrow(() -> new EntityNotFoundException("")))
						.district(	districtRepository	.findById(2L).orElseThrow(() -> new EntityNotFoundException("")))
						.ward(		wardRepository		.findById(3L).orElseThrow(() -> new EntityNotFoundException("")))
						.address(		"Nơ Trang Long")
						.addressDetail(	"Số 2 - Hẻm 558")
						.phone("0356597539")
						.build(),
				UserAccount.builder().id(2L).email("ducnvps28090@fpt.edu.vn")	.userLogin("Ngvd49")	.name("Nguyễn Viết Tèo")	.password(encoder.encode("123456"))	.userStatus(UserStatus.active)
						.createdDate(new Date())
						.updatedDate(null)
						.deleted(false)
						.deletedDate(null)
						.province(	provinceRepository	.findById(3L).orElseThrow(() -> new EntityNotFoundException("")))
						.district(	districtRepository	.findById(5L).orElseThrow(() -> new EntityNotFoundException("")))
						.ward(		wardRepository		.findById(9L).orElseThrow(() -> new EntityNotFoundException("")))
						.address(		"Tổ dân phố Bồ Liêng")
						.addressDetail(	"Số 272 - Khu phố Bồ Liêng 2")
						.phone("0356597540")
						.build(),
				UserAccount.builder().id(3L).email("DTTrung0123@gmail.com")	.userLogin("user2")		.name("Đỗ Thành Trung")		.password(encoder.encode("123456"))	.userStatus(UserStatus.active).address("35 Tran Dai Nghia,KP Noi Hoa 2, P. Binh AN, Tx. Di An, Tinh Binh Duong")	.createdDate(new Date())	.province(provinceRepository.findById(1L).orElseThrow(() -> new EntityNotFoundException("")))	.updatedDate(null)	.deleted(false)	.deletedDate(null).build(),
				UserAccount.builder().id(4L).email("VTr0505@gmail.com")		.userLogin("vt0505")	.name("Vòng Trung")			.password(encoder.encode("123456"))	.userStatus(UserStatus.active).address("123 Đường 456, Phường 7, Quận 8, Thành Phố 9")								.createdDate(new Date())	.province(provinceRepository.findById(1L).orElseThrow(() -> new EntityNotFoundException("")))	.updatedDate(null)	.deleted(false)	.deletedDate(null).build()
		));

		String storeImg 		= "https://images.stockcake.com/public/7/a/4/7a49a413-44d8-42bb-a4f0-dcee546f439c_medium/electronics-store-interior-stockcake.jpg";
		String storeImg2 		= "https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.leafio.ai%2Fpet-store-software%2F&psig=AOvVaw3wRPuy0BWXOHZitP-P3JuF&ust=1730542864610000&source=images&cd=vfe&opi=89978449&ved=0CBQQjRxqFwoTCKj09db0uokDFQAAAAAdAAAAABAT";
		String storeBannerImg 	= "https://www.google.com/url?sa=i&url=https%3A%2F%2Fokcredit.in%2Fblog%2Fhow-to-open-a-computer-laptop-showroom%2F&psig=AOvVaw1acMwXcK125ldhmS2I2FW-&ust=1730542216883000&source=images&cd=vfe&opi=89978449&ved=0CBQQjRxqFwoTCLCszqLyuokDFQAAAAAdAAAAABAE";
		String storeBannerImg2 	= "https://www.google.com/url?sa=i&url=https%3A%2F%2Fpetstore.ae%2F&psig=AOvVaw3wRPuy0BWXOHZitP-P3JuF&ust=1730542864610000&source=images&cd=vfe&opi=89978449&ved=0CBQQjRxqFwoTCKj09db0uokDFQAAAAAdAAAAABAe";
		UserAccount userAcc 	= userAccountRepository.findById(1L).orElseThrow(() -> new EntityNotFoundException(""));
		UserAccount userAcc2 	= userAccountRepository.findById(3L).orElseThrow(() -> new EntityNotFoundException(""));


		storeRepository.saveAll(Arrays.asList(
				Store.builder().id(1L)
						.name("Laptop Shop")
						.password("123456")
						.phone("0356597539")
						.userAccount(userAcc)
						.image(storeImg2)
						.bannerImage(storeBannerImg2)
						.addressDetail("Cửa hàng Laptop - 123 Phạm Văn Đồng")
						.province(	provinceRepository	.findById(1L).orElseThrow(() -> new EntityNotFoundException("")))
						.district(	districtRepository	.findById(2L).orElseThrow(() -> new EntityNotFoundException("")))
						.ward(		wardRepository		.findById(3L).orElseThrow(() -> new EntityNotFoundException("")))
						.storeStatus(StoreStatus.active)
						.createdDate(new Date())
						.updatedDate(null)
						.deleted(false)
						.deletedDate(null)
						.build(),

				Store.builder().id(2L)
						.name("Cửa hàng thú cưng")
						.password("123456")
						.phone("0123456789")
						.userAccount(userAcc2)
						.image(storeImg)
						.bannerImage(storeBannerImg)
						.addressDetail("Cửa hàng thú cưng - 789 Dương Quảng Hàm")
						.province(	provinceRepository	.findById(2L).orElseThrow(() -> new EntityNotFoundException("")))
						.district(	districtRepository	.findById(3L).orElseThrow(() -> new EntityNotFoundException("")))
						.ward(		wardRepository		.findById(5L).orElseThrow(() -> new EntityNotFoundException("")))
						.storeStatus(StoreStatus.active)
						.createdDate(new Date())
						.updatedDate(null)
						.deleted(false)
						.deletedDate(null)
						.build()
		));

		typeGoodRepository.saveAll(Arrays.asList(
				TypeGood.builder().id(1L).name("Laptop").build(),
				TypeGood.builder().id(2L).name("Màn hình").build(),
				TypeGood.builder().id(3L).name("Máy tính bảng").build(),
				TypeGood.builder().id(4L).name("Thú cưng").build()
		));

		discountRepository.saveAll(Arrays.asList(
				Discount.builder().id(1L).percentDecrease(10).name("Mã giảm 10%").build(),
				Discount.builder().id(2L).percentDecrease(30).name("Mã giảm 30%").build()
		));

		Store st = storeRepository.findById(1L).orElseThrow(() -> new EntityNotFoundException(""));

		productRepository.saveAll(Arrays.asList(
				Product.builder().id(1L).name("MacBook Air M3 15 inch 2024, Chính hãng Apple Việt Nam").image("https://cdn2.cellphones.com.vn/358x/media/catalog/product/g/r/gray_2_5.png")
						.store(st)
						.typeGood(	typeGoodRepository	.findById(1L).orElseThrow(() -> new EntityNotFoundException("")))
						.createdDate(new Date()).updatedDate(null)
						.productStatus(ProductStatus.active)
						.build(),

				Product.builder().id(2L).name("Laptop Acer Aspire 7 A715-76G-5806").image("https://cdn2.cellphones.com.vn/insecure/rs:fill:0:358/q:90/plain/https://cellphones.com.vn/media/catalog/product/t/e/text_ng_n_14__3_3_1.png")
						.store(st)
						.typeGood(	typeGoodRepository	.findById(1L).orElseThrow(() -> new EntityNotFoundException("")))
						.createdDate(new Date()).updatedDate(null)
						.productStatus(ProductStatus.active)
						.build(),

				Product.builder().id(3L).name("Laptop HP 15S-FQ5231TU 8U241PA").image("https://cdn2.cellphones.com.vn/insecure/rs:fill:0:358/q:90/plain/https://cellphones.com.vn/media/catalog/product/l/a/laptop-hp-15s-fq5231tu-8u241pa_1_.jpg")
						.store(st)
						.typeGood(	typeGoodRepository	.findById(1L).orElseThrow(() -> new EntityNotFoundException("")))
						.createdDate(new Date()).updatedDate(null)
						.productStatus(ProductStatus.active)
						.build(),

				Product.builder().id(4L).name("Màn hình Gaming LG UltraGear 24GS50F-B 24 inch").image("https://cdn2.cellphones.com.vn/insecure/rs:fill:0:358/q:90/plain/https://cellphones.com.vn/media/catalog/product/m/a/man-hinh-gaming-lg-ultragear-24gs50f-b-24-inch.png")
						.store(st)
						.typeGood(	typeGoodRepository	.findById(2L).orElseThrow(() -> new EntityNotFoundException("")))
						.createdDate(new Date()).updatedDate(null)
						.productStatus(ProductStatus.active)
						.build(),

				Product.builder().id(5L).name("Màn hình Samsung Gaming Odyssey G5 LC34G55TWWEXXV 34 inch").image("https://cdn2.cellphones.com.vn/insecure/rs:fill:0:358/q:90/plain/https://cellphones.com.vn/media/catalog/product/3/6/36_1_25.jpg")
						.store(st)
						.typeGood(	typeGoodRepository	.findById(2L).orElseThrow(() -> new EntityNotFoundException("")))
						.createdDate(new Date()).updatedDate(null)
						.productStatus(ProductStatus.active)
						.build(),

				Product.builder().id(6L).name("Màn hình LG UltraWide 29WQ600 29 inch").image("https://cdn2.cellphones.com.vn/insecure/rs:fill:0:358/q:90/plain/https://cellphones.com.vn/media/catalog/product/m/a/man-hinh-lg-ultrawide-29wq600-29-inch-1.png")
						.createdDate(new Date()).updatedDate(null).productStatus(ProductStatus.active)
						.store(st)
						.typeGood(	typeGoodRepository	.findById(2L).orElseThrow(() -> new EntityNotFoundException("")))
						.build(),

				Product.builder().id(7L).name("iPad Gen 10 10.9 inch 2022 Wifi 64GB I Chính hãng Apple Việt Nam").image("https://cdn2.cellphones.com.vn/insecure/rs:fill:0:358/q:90/plain/https://cellphones.com.vn/media/catalog/product/i/p/ipad-10-9-inch-2022.png")
						.store(st)
						.typeGood(	typeGoodRepository	.findById(3L).orElseThrow(() -> new EntityNotFoundException("")))
						.createdDate(new Date()).updatedDate(null)
						.productStatus(ProductStatus.active)
						.build(),

				Product.builder().id(8L).name("Samsung Galaxy Tab S9 FE 5G 6GB 128GB").image("https://cdn2.cellphones.com.vn/insecure/rs:fill:0:358/q:90/plain/https://cellphones.com.vn/media/catalog/product/s/a/samsung-galaxy-tab-s9-fe-mint-13_1.jpg")
						.store(st)
						.typeGood(	typeGoodRepository	.findById(3L).orElseThrow(() -> new EntityNotFoundException("")))
						.createdDate(new Date()).updatedDate(null)
						.productStatus(ProductStatus.active)
						.build()
		));

		Discount discnt1 = discountRepository.findById(1L).orElseThrow(() -> new EntityNotFoundException(""));
		Discount discnt2 = discountRepository.findById(1L).orElseThrow(() -> new EntityNotFoundException(""));
		Product p1 = productRepository.findById(1L).orElseThrow(() -> new EntityNotFoundException(""));
		Product p2 = productRepository.findById(2L).orElseThrow(() -> new EntityNotFoundException(""));
		Product p3 = productRepository.findById(3L).orElseThrow(() -> new EntityNotFoundException(""));
		Product p4 = productRepository.findById(4L).orElseThrow(() -> new EntityNotFoundException(""));
		Product p5 = productRepository.findById(5L).orElseThrow(() -> new EntityNotFoundException(""));
		Product p6 = productRepository.findById(6L).orElseThrow(() -> new EntityNotFoundException(""));
		Product p7 = productRepository.findById(7L).orElseThrow(() -> new EntityNotFoundException(""));
		Product p8 = productRepository.findById(8L).orElseThrow(() -> new EntityNotFoundException(""));


		productDetailRepository.saveAll(Arrays.asList(
				ProductDetail.builder().discount(discnt1).quantity(30).image("https://cdn2.cellphones.com.vn/358x/media/catalog/product/t/e/text_ng_n_2__5_39.png")
						.price(50000.0).id(1L)
						.name("Bạc").product(p1)
						.build(),

				ProductDetail.builder().discount(discnt1).quantity(20).image("https://cdn2.cellphones.com.vn/358x/media/catalog/product/b/a/balck.png")
						.price(500000.0).id(2L)
						.name("Đen").product(p1)
						.build(),


				ProductDetail.builder().discount(discnt1).quantity(30).image("https://cdn2.cellphones.com.vn/insecure/rs:fill:0:358/q:90/plain/https://cellphones.com.vn/media/catalog/product/t/e/text_ng_n_14__3_3_1.png")
						.price(50000.0).id(3L)
						.name("I5-12450H | RTX 3050").product(p2)
						.build(),

				ProductDetail.builder().discount(discnt1).quantity(20).image("https://cdn2.cellphones.com.vn/insecure/rs:fill:0:358/q:90/plain/https://cellphones.com.vn/media/catalog/product/t/e/text_ng_n_14__3_3_1.png")
						.price(500000.0).id(4L)
						.name("I7-12650H | RTX 2050").product(p2)
						.build(),


				ProductDetail.builder().discount(discnt1).quantity(30).image("https://cdn2.cellphones.com.vn/insecure/rs:fill:0:358/q:90/plain/https://cellphones.com.vn/media/catalog/product/l/a/laptop-hp-15s-fq5231tu-8u241pa_1_.jpg")
						.price(50000.0).id(5L)
						.name("I5-1334U | 512GB").product(p3)
						.build(),

				ProductDetail.builder().discount(discnt1).quantity(20).image("https://cdn2.cellphones.com.vn/insecure/rs:fill:0:358/q:90/plain/https://cellphones.com.vn/media/catalog/product/l/a/laptop-hp-15s-fq5231tu-8u241pa_1_.jpg")
						.price(500000.0).id(6L)
						.name("I3-1215U | 256GB").product(p3)
						.build(),


				ProductDetail.builder().discount(discnt1).quantity(30).image("https://cdn2.cellphones.com.vn/insecure/rs:fill:0:358/q:90/plain/https://cellphones.com.vn/media/catalog/product/m/a/man-hinh-gaming-lg-ultragear-24gs50f-b-24-inch.png")
						.price(50000.0).id(7L)
						.name("180Hz").product(p4)
						.build(),

				ProductDetail.builder().discount(discnt1).quantity(20).image("https://cdn2.cellphones.com.vn/insecure/rs:fill:0:358/q:90/plain/https://cellphones.com.vn/media/catalog/product/m/a/man-hinh-gaming-lg-ultragear-24gs50f-b-24-inch.png")
						.price(500000.0).id(8L)
						.name("360Hz").product(p4)
						.build(),


				ProductDetail.builder().discount(discnt2).quantity(30).image("https://cdn2.cellphones.com.vn/insecure/rs:fill:0:358/q:90/plain/https://cellphones.com.vn/media/catalog/product/3/6/36_1_25.jpg")
						.price(50000.0).id(9L)
						.name("180Hz").product(p5)
						.build(),

				ProductDetail.builder().discount(discnt2).quantity(20).image("https://cdn2.cellphones.com.vn/insecure/rs:fill:0:358/q:90/plain/https://cellphones.com.vn/media/catalog/product/3/6/36_1_25.jpg")
						.price(500000.0).id(10L)
						.name("360Hz").product(p5)
						.build(),


				ProductDetail.builder().discount(discnt2).quantity(30).image("https://cdn2.cellphones.com.vn/insecure/rs:fill:0:358/q:90/plain/https://cellphones.com.vn/media/catalog/product/m/a/man-hinh-lg-ultrawide-29wq600-29-inch-1.png")
						.price(50000.0).id(11L)
						.name("180Hz").product(p6)
						.build(),

				ProductDetail.builder().discount(discnt2).quantity(20).image("https://cdn2.cellphones.com.vn/insecure/rs:fill:0:358/q:90/plain/https://cellphones.com.vn/media/catalog/product/m/a/man-hinh-lg-ultrawide-29wq600-29-inch-1.png")
						.price(500000.0).id(12L)
						.name("360Hz").product(p6)
						.build(),


				ProductDetail.builder().discount(discnt2).quantity(20).image("https://cdn2.cellphones.com.vn/insecure/rs:fill:0:358/q:90/plain/https://cellphones.com.vn/media/catalog/product/i/p/ipad-10-9-inch-2022.png")
						.price(600000.0).id(13L)
						.name("Bạc").product(p7)
						.build(),

				ProductDetail.builder().discount(discnt2).quantity(30).image("https://cdn2.cellphones.com.vn/insecure/rs:fill:0:358/q:90/plain/https://cellphones.com.vn/media/catalog/product/i/p/ipad-2022-hero-blue-wifi-select.png")
						.price(500000.0).id(14L)
						.name("Xanh").product(p7)
						.build(),

				ProductDetail.builder().discount(discnt2).quantity(20).image("https://cdn2.cellphones.com.vn/insecure/rs:fill:0:358/q:90/plain/https://cellphones.com.vn/media/catalog/product/i/p/ipad-2022-hero-pink-wifi-select.png")
						.price(700000.0).id(15L)
						.name("Hồng").product(p7)
						.build(),

				ProductDetail.builder().discount(discnt2).quantity(20).image("https://cdn2.cellphones.com.vn/insecure/rs:fill:0:358/q:90/plain/https://cellphones.com.vn/media/catalog/product/i/p/ipad-2022-hero-yellow-wifi-select.png")
						.price(800000.0).id(16L)
						.name("Vàng").product(p7)
						.build(),


				ProductDetail.builder().discount(discnt2).quantity(30).image("https://cdn2.cellphones.com.vn/insecure/rs:fill:0:358/q:90/plain/https://cellphones.com.vn/media/catalog/product/s/a/samsung-galaxy-tab-s9-fe-mint-13_1.jpg")
						.price(50000.0).id(17L)
						.name("Bạc").product(p8)
						.build(),

				ProductDetail.builder().discount(discnt2).quantity(20).image("https://cdn2.cellphones.com.vn/insecure/rs:fill:0:358/q:90/plain/https://cellphones.com.vn/media/catalog/product/s/a/samsung-galaxy-tab-fe-bac-12_1.jpg")
						.price(500000.0).id(18L)
						.name("Trắng").product(p8)
						.build(),

				ProductDetail.builder().discount(discnt2).quantity(20).image("https://cdn2.cellphones.com.vn/358x/media/catalog/product/t/a/tab-s9-fe-xanh_3.png")
						.price(500000.0).id(19L)
						.name("Lục").product(p8)
						.build()
		));

		shipperRepository.saveAll( Arrays.asList(
				Shipper.builder()
						.id(1L)
						.userLogin("NgvdRev")
						.password(encoder.encode("123456"))
						.name("Nguyễn Viết Đức")
						.email("Ngvd4901@gmail.com")
						.phone("0356597539")
						.address("560 - Nơ Trang Long")
						.addressDetail("Số 2 - Hẻm 558 - Đường Nơ Trang Long")
						.province(	provinceRepository	.findById(1L).orElseThrow(() -> new EntityNotFoundException("")))
						.district(	districtRepository	.findById(2L).orElseThrow(() -> new EntityNotFoundException("")))
						.ward(		wardRepository		.findById(3L).orElseThrow(() -> new EntityNotFoundException("")))
						.createdDate(new Date())
						.updatedDate(null)
						.deleted(false)
						.deletedDate(null)
						.shipperStatus(ShipperStatus.active)
						.typeShipper(TypeShipper.receive)
						.build(),

				Shipper.builder()
						.id(2L)
						.userLogin("NgvdDel")
						.password(encoder.encode("123456"))
						.name("Nguyễn Viết Đức")
						.email("Ngvd4901@gmail.com")
						.phone("0356597539")
						.address("560 - Nơ Trang Long")
						.addressDetail("Số 2 - Hẻm 558 - Đường Nơ Trang Long")
						.province(	provinceRepository	.findById(1L).orElseThrow(() -> new EntityNotFoundException("")))
						.district(	districtRepository	.findById(2L).orElseThrow(() -> new EntityNotFoundException("")))
						.ward(		wardRepository		.findById(3L).orElseThrow(() -> new EntityNotFoundException("")))
						.createdDate(new Date())
						.updatedDate(null)
						.deleted(false)
						.deletedDate(null)
						.shipperStatus(ShipperStatus.active)
						.typeShipper(TypeShipper.delivery)
						.build()
		));

		ShippingFee shippingFee = ShippingFee.builder().id(1L).createdDate(new Date()).updatedDate(null).deletedDate(null).deleted(false).typeShipping(TypeShipping.inner).fee(20000.0).build();
		ShippingFee shippingFee2 = ShippingFee.builder().id(2L).createdDate(new Date()).updatedDate(null).deletedDate(null).deleted(false).typeShipping(TypeShipping.outer).fee(30000.0).build();
		shippingFeeRepository.saveAll(List.of(shippingFee,shippingFee2));

		shippingFeeRepository.saveAll(Arrays.asList(
				ShippingFee.builder().id(1L).typeShipping(TypeShipping.inner).fee(20000.0).createdDate(new Date()).updatedDate(null).deletedDate(null).deleted(false).build(),
				ShippingFee.builder().id(2L).typeShipping(TypeShipping.outer).fee(32000.0).createdDate(new Date()).updatedDate(null).deletedDate(null).deleted(false).build()
		));

		deliveryTypeRepository.saveAll(Arrays.asList(
				DeliveryType.builder().id(1L).name("Giao hàng cơ bản").createdDate(new Date()).deletedDate(new Date()).deleted(false).updatedDate(null).fee(15000.0).build(),
				DeliveryType.builder().id(2L).name("Giao hàng hỏa tốc").createdDate(new Date()).deletedDate(new Date()).deleted(false).updatedDate(null).fee(40000.0).build()
		));


		paymentTypeRepository.saveAll(Arrays.asList(
				PaymentType.builder().id(1L).name("Thanh toán tiền mặt").image(null).build(),
				PaymentType.builder().id(2L).name("Thanh toán chuyển khoản - VietQR").image(null).build()
		));

		String context = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged.";

		ordersRepository.saveAll(Arrays.asList(
				Orders.builder().id(1L)
						.orderStatus(OrderStatus.delivery)
						.createdDate(new Date()).deleted(false)
						.address("Đường Kha vạn Cân")
						.addressDetail("Số 24 - Hẻm 36 - Đường Kha vạn Cân")
						.updatedDate(null)
						.deletedDate(null)
						.pickupDate(new Date()).noteContent("Content")
						.store(			storeRepository			.findById(1L).orElseThrow(() -> new EntityNotFoundException("")))
						.deliveryType(	deliveryTypeRepository	.findById(1L).orElseThrow(() -> new EntityNotFoundException("")))
						.userAccount(	userAccountRepository	.findById(2L).orElseThrow(() -> new EntityNotFoundException("")))
						.finalTotal(50000.0)
						.totalAmount(60000.0)
						.totalAmountShip(80000.0)
						.totalAmountVoucher(30000.0)
						.deliveryDate(new Date())
						.paymentType(paymentTypeRepository.findById(1L).orElseThrow(() -> new EntityNotFoundException("")))
						.noteContent(context)
						.build(),

				Orders.builder().id(2L)
						.orderStatus(OrderStatus.pickup)
						.createdDate(new Date()).deleted(false)
						.address("Đường Kha vạn Cân")
						.addressDetail("Số 24 - Hẻm 36 - Đường Kha vạn Cân")
						.updatedDate(null)
						.deletedDate(null)
						.pickupDate(new Date()).noteContent("Content")
						.store(			storeRepository			.findById(1L).orElseThrow(() -> new EntityNotFoundException("")))
						.deliveryType(	deliveryTypeRepository	.findById(1L).orElseThrow(() -> new EntityNotFoundException("")))
						.userAccount(	userAccountRepository	.findById(2L).orElseThrow(() -> new EntityNotFoundException("")))
						.finalTotal(50000.0)
						.totalAmount(60000.0)
						.totalAmountShip(80000.0)
						.totalAmountVoucher(30000.0)
						.deliveryDate(new Date())
						.paymentType(paymentTypeRepository.findById(1L).orElseThrow(() -> new EntityNotFoundException("")))
						.noteContent(context)
						.build(),

				Orders.builder().id(3L)
						.orderStatus(OrderStatus.pickup)
						.createdDate(new Date()).deleted(false)
						.address("Đường Kha vạn Cân")
						.addressDetail("Số 24 - Hẻm 36 - Đường Kha vạn Cân")
						.updatedDate(null)
						.deletedDate(null)
						.pickupDate(new Date()).noteContent("Content")
						.store(			storeRepository			.findById(1L).orElseThrow(() -> new EntityNotFoundException("")))
						.deliveryType(	deliveryTypeRepository	.findById(1L).orElseThrow(() -> new EntityNotFoundException("")))
						.userAccount(	userAccountRepository	.findById(2L).orElseThrow(() -> new EntityNotFoundException("")))
						.finalTotal(50000.0)
						.totalAmount(60000.0)
						.totalAmountShip(80000.0)
						.totalAmountVoucher(30000.0)
						.deliveryDate(new Date())
						.paymentType(paymentTypeRepository.findById(1L).orElseThrow(() -> new EntityNotFoundException("")))
						.noteContent(context)
						.build(),

				Orders.builder().id(4L)
						.orderStatus(OrderStatus.pickup)
						.createdDate(new Date()).deleted(false)
						.address("Đường Kha vạn Cân")
						.addressDetail("Số 24 - Hẻm 36 - Đường Kha vạn Cân")
						.updatedDate(null)
						.deletedDate(null)
						.pickupDate(new Date()).noteContent("Content")
						.store(			storeRepository			.findById(1L).orElseThrow(() -> new EntityNotFoundException("")))
						.deliveryType(	deliveryTypeRepository	.findById(1L).orElseThrow(() -> new EntityNotFoundException("")))
						.userAccount(	userAccountRepository	.findById(2L).orElseThrow(() -> new EntityNotFoundException("")))
						.finalTotal(50000.0)
						.totalAmount(60000.0)
						.totalAmountShip(80000.0)
						.totalAmountVoucher(30000.0)
						.deliveryDate(new Date())
						.paymentType(paymentTypeRepository.findById(1L).orElseThrow(() -> new EntityNotFoundException("")))
						.noteContent(context)
						.build()
		));

		Orders order1 = ordersRepository.findById(1L).orElseThrow(() -> new EntityNotFoundException(""));
		Orders order2 = ordersRepository.findById(2L).orElseThrow(() -> new EntityNotFoundException(""));
		Orders order3 = ordersRepository.findById(3L).orElseThrow(() -> new EntityNotFoundException(""));
		Orders order4 = ordersRepository.findById(4L).orElseThrow(() -> new EntityNotFoundException(""));

		orderDetailRepository.saveAll(Arrays.asList(
				OrderDetail.builder()
						.id(1L)
						.orders(order1)
						.productDetail(	productDetailRepository.findById(1L).orElseThrow(() -> new EntityNotFoundException("")))
						.quantity(2)
						.totalAmount(10000000.0)
						.totalDiscount(1000000.0)
						.finalTotal(9000000.0)
						.discount(null)
						.createdDate(new Date())
						.updatedDate(null)
						.deleted(false)
						.deletedDate(null)
						.build(),

				OrderDetail.builder()
						.id(2L)
						.orders(order1)
						.productDetail(	productDetailRepository.findById(2L).orElseThrow(() -> new EntityNotFoundException("")))
						.quantity(1)
						.totalAmount(10000000.0)
						.totalDiscount(1000000.0)
						.finalTotal(9000000.0)
						.discount(null)
						.createdDate(new Date())
						.updatedDate(null)
						.deleted(false)
						.deletedDate(null)
						.build(),

				OrderDetail.builder()
						.id(3L)
						.orders(order1)
						.productDetail(	productDetailRepository.findById(3L).orElseThrow(() -> new EntityNotFoundException("")))
						.quantity(1)
						.totalAmount(10000000.0)
						.totalDiscount(1000000.0)
						.finalTotal(9000000.0)
						.discount(null)
						.createdDate(new Date())
						.updatedDate(null)
						.deleted(false)
						.deletedDate(null)
						.build(),

				OrderDetail.builder()
						.id(4L)
						.orders(order2)
						.productDetail(	productDetailRepository.findById(4L).orElseThrow(() -> new EntityNotFoundException("")))
						.quantity(1)
						.totalAmount(10000000.0)
						.totalDiscount(1000000.0)
						.finalTotal(9000000.0)
						.discount(null)
						.createdDate(new Date())
						.updatedDate(null)
						.deleted(false)
						.deletedDate(null)
						.build(),

				OrderDetail.builder()
						.id(5L)
						.orders(order3)
						.productDetail(	productDetailRepository.findById(5L).orElseThrow(() -> new EntityNotFoundException("")))
						.quantity(1)
						.totalAmount(10000000.0)
						.totalDiscount(1000000.0)
						.finalTotal(9000000.0)
						.discount(null)
						.createdDate(new Date())
						.updatedDate(null)
						.deleted(false)
						.deletedDate(null)
						.build(),

				OrderDetail.builder()
						.id(6L)
						.orders(order3)
						.productDetail(	productDetailRepository.findById(6L).orElseThrow(() -> new EntityNotFoundException("")))
						.quantity(1)
						.totalAmount(10000000.0)
						.totalDiscount(1000000.0)
						.finalTotal(9000000.0)
						.discount(null)
						.createdDate(new Date())
						.updatedDate(null)
						.deleted(false)
						.deletedDate(null)
						.build(),

				OrderDetail.builder()
						.id(7L)
						.orders(order4)
						.productDetail(	productDetailRepository.findById(7L).orElseThrow(() -> new EntityNotFoundException("")))
						.quantity(1)
						.totalAmount(10000000.0)
						.totalDiscount(1000000.0)
						.finalTotal(9000000.0)
						.discount(null)
						.createdDate(new Date())
						.updatedDate(null)
						.deleted(false)
						.deletedDate(null)
						.build(),

				OrderDetail.builder()
						.id(8L)
						.orders(order4)
						.productDetail(	productDetailRepository.findById(8L).orElseThrow(() -> new EntityNotFoundException("")))
						.quantity(1)
						.totalAmount(10000000.0)
						.totalDiscount(1000000.0)
						.finalTotal(9000000.0)
						.discount(null)
						.createdDate(new Date())
						.updatedDate(null)
						.deleted(false)
						.deletedDate(null)
						.build()
		));

		Shipper shp1 = shipperRepository.findById(1L).orElseThrow(() -> new EntityNotFoundException(""));
		Shipper shp2 = shipperRepository.findById(2L).orElseThrow(() -> new EntityNotFoundException(""));

		receiveDeliveryRepository.saveAll(Arrays.asList(
				ReceiveDelivery.builder()
						.id(1L)
						.shipper( shp1 )
						.statusDelivery(StatusDelivery.taking)
						.typeDelivery(TypeDelivery.receive)
						.orders( order1 )
						.image(null)
						.createdDate(new Date())
						.updatedDate(null)
						.deleted(false)
						.deletedDate(null)
						.deliveryDate(addDaysToDate(new Date()))
						.build(),
				ReceiveDelivery.builder()
						.id(2L)
						.shipper( shp1 )
						.statusDelivery(StatusDelivery.appoinment)
						.typeDelivery(TypeDelivery.receive)
						.orders( order2 )
						.image(null)
						.createdDate(new Date())
						.updatedDate(null)
						.deleted(false)
						.deletedDate(null)
						.deliveryDate(addDaysToDate(new Date()))
						.build(),
				ReceiveDelivery.builder()
						.id(3L)
						.shipper( shp2 )
						.statusDelivery(StatusDelivery.taking)
						.typeDelivery(TypeDelivery.delivery)
						.orders( order3 )
						.image(null)
						.createdDate(new Date())
						.updatedDate(null)
						.deleted(false)
						.deletedDate(null)
						.deliveryDate(addDaysToDate(new Date()))
						.build(),
				ReceiveDelivery.builder()
						.id(4L)
						.shipper( shp2 )
						.statusDelivery(StatusDelivery.appoinment)
						.typeDelivery(TypeDelivery.delivery)
						.orders( order4 )
						.image(null)
						.createdDate(new Date())
						.updatedDate(null)
						.deleted(false)
						.deletedDate(null)
						.deliveryDate(addDaysToDate(new Date()))
						.build()
		));
	}
	private Date addDaysToDate(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_YEAR, 7);
		return calendar.getTime();
	}
}
