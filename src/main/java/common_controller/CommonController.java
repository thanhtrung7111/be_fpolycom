package common_controller;

import dao.DeliveryTypeRepository;
import dao.ShippingFeeRepository;
import dao.TypeGoodRepository;
import dto.delivery_type.DeliveryTypeMapper;
import dto.shipping_fee.ShippingFeeMapper;
import dto.shipping_fee.ShippingFeeResponse;
import dto.status.StatusDTO;
import entity.DeliveryType;
import entity.PaymentType;
import entity.enum_package.*;
import exeception_handler.DataNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import service.StoreDetailService;
import service.data_return.DataReturnService;
import service.discount.DiscountService;
import service.evaluate.EvaluateService;
import service.payment_type.PaymentTypeService;
import service.product.ProductService;
import service.store.StoreService;
import service.type_good.TypeGoodService;
import service.type_good_attr.TypeGoodAttrService;
import service.voucher.VoucherService;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.stream.Collectors;

@RestController
public class CommonController {

    @Autowired
    DataReturnService dataReturnService;

    @Autowired
    EvaluateService evaluateService;

    @Autowired
    VoucherService voucherService;

    @Autowired
    ProductService productService;

    @Autowired
    StoreService storeService;

    @Autowired
    PaymentTypeService paymentTypeService;

    @Autowired
    ShippingFeeRepository shippingFeeRepository;

    @Autowired
    DeliveryTypeRepository deliveryTypeRepository;


    @Autowired
    TypeGoodService typeGoodService;

    @Autowired
    TypeGoodAttrService typeGoodAttrService;

    @Autowired
    DiscountService discountService;

    @PostMapping(value = "/product/evaluate/all")
    public ResponseEntity<Object> getAllProvince(@RequestBody HashMap<String,String> request) {
        if( request.get("productCode") == null || request.get("productCode").isBlank()){
            throw new DataNotFoundException("Khong de trong ma san pham!");
        }
        return ResponseEntity.ok(dataReturnService.success(evaluateService.getAllEvaluateProduct(Long.valueOf(request.get("productCode")))));
    }

    @PostMapping(value = "/common/store/all-voucher")
    public ResponseEntity<Object> getALlVoucherByStore(@RequestBody HashMap<String,String> request) {
        if( request.get("storeCode") == null || request.get("storeCode").isBlank()){
            throw new DataNotFoundException("Khong de trong ma cua hang!");
        }
        return ResponseEntity.ok(dataReturnService.success(voucherService.getVoucherByStore(Long.valueOf(request.get("storeCode")))));
    }

    @GetMapping(value = "/common/product/all")
    public ResponseEntity<Object> getAllProduct() {
        return ResponseEntity.ok(dataReturnService.success(productService.getALlProductByStatus(ProductStatus.active)));
    }

    @PostMapping(value = "/common/store/all-product")
    public ResponseEntity<Object> getALlProductByStore(@RequestBody HashMap<String,String> request) {
        if( request.get("storeCode") == null || request.get("storeCode").isBlank()){
            throw new DataNotFoundException("Khong de trong ma cua hang!");
        }
        return ResponseEntity.ok(dataReturnService.success(productService.getALlProductByStore(Long.valueOf(request.get("storeCode")))));
    }

    @PostMapping(value = "/common/product/detail")
    public ResponseEntity<Object> getProductById(@RequestBody HashMap<String,String> request) {
        if( request.get("productCode") == null || request.get("productCode").isBlank()){
            throw new DataNotFoundException("Khong de trong ma cua hang!");
        }
        return ResponseEntity.ok(dataReturnService.success(productService.getProductByIdAndUserLogin(Long.valueOf(request.get("productCode")),request.get("userLogin"))));
    }

    @PostMapping(value = "/common/store/detail")
    public ResponseEntity<Object> getStoreById(@RequestBody HashMap<String,String> request) {
        if( request.get("storeCode") == null || request.get("storeCode").isBlank()){
            throw new DataNotFoundException("Khong de trong ma cua hang!");
        }
        return ResponseEntity.ok(dataReturnService.success(storeService.getStoreByCode(Long.valueOf(request.get("storeCode")))));
    }
    @GetMapping(value = "/common/store/all")
    public ResponseEntity<Object> getAllStoreByActive() {
        return ResponseEntity.ok(dataReturnService.success(storeService.getAllStoreByStatus(StoreStatus.active)));
    }

    @GetMapping(value = "/common/type-good/all")
    public ResponseEntity<Object> getAllTypeGood() {
        return ResponseEntity.ok(dataReturnService.success(typeGoodService.getAllData()));
    }


    @GetMapping(value = "/common/discount/all")
    public ResponseEntity<Object> getAllDiscount() {
        return ResponseEntity.ok(dataReturnService.success(discountService.getAllData()));
    }

    @GetMapping(value = "/common/banner-position/all")
    public ResponseEntity<Object> getAllBannerPosition() {
        return ResponseEntity.ok(dataReturnService.success(Arrays.stream(BannerPosition.values()).map(status-> new StatusDTO(status.name(),status.getDescription())).collect(Collectors.toList())));
    }



    @GetMapping(value = "/common/payment-type/all")
    public ResponseEntity<Object> getAllPaymentType() {

        return ResponseEntity.ok(dataReturnService.success(paymentTypeService.getAllData()));
    }

    @GetMapping(value = "/common/shipping-fee/all")
    public ResponseEntity<Object> getALlShippingFee() {
        return ResponseEntity.ok(dataReturnService.success(ShippingFeeMapper.INSTANCE.toShippingFeeResponseList(shippingFeeRepository.findAll())));
    }

    @PostMapping(value = "/common/type-good-attr/all")
    public ResponseEntity<Object> getAllTypeGoodAttr(@RequestBody HashMap<String,String> request) {
        if(request.isEmpty() || request.get("typeGoodCode").isBlank()){
            throw new DataNotFoundException("Du lieu khong ton tai!");
        }
        return ResponseEntity.ok(dataReturnService.success(typeGoodAttrService.getAllTypeGoodAttrByTypeGood(Long.valueOf(request.get("typeGoodCode")))));
    }

    @GetMapping(value = "/common/delivery-type/all")
    public ResponseEntity<Object> getAllDeliveryType() {
        return ResponseEntity.ok(dataReturnService.success(DeliveryTypeMapper.INSTANCE.toDeliveryTypeResponseList(deliveryTypeRepository.findAll())));
    }

    @GetMapping(value = "/common/status/bank")
    public ResponseEntity<Object> getAllBankStatus() {

        return ResponseEntity.ok(dataReturnService.success(Arrays.stream(BankStatus.values()).map(status-> new StatusDTO(status.name(),status.getDescription())).collect(Collectors.toList())));
    }

    @GetMapping(value = "/common/status/document-type")
    public ResponseEntity<Object> getAllDocumentTypeStatus() {
        return ResponseEntity.ok(dataReturnService.success(Arrays.stream(DocumentType.values()).map(status-> new StatusDTO(status.name(),status.getDescription())).collect(Collectors.toList())));
    }

    @GetMapping(value = "/common/status/friendship")
    public ResponseEntity<Object> getAllFriendshipStatus() {
        return ResponseEntity.ok(dataReturnService.success(Arrays.stream(FriendshipStatus.values()).map(status-> new StatusDTO(status.name(),status.getDescription())).collect(Collectors.toList())));
    }

    @GetMapping(value = "/common/status/order")
    public ResponseEntity<Object> getAllOrderStatus() {
        return ResponseEntity.ok(dataReturnService.success(Arrays.stream(OrderStatus.values()).map(status-> new StatusDTO(status.name(),status.getDescription())).collect(Collectors.toList())));
    }

    @GetMapping(value = "/common/status/product")
    public ResponseEntity<Object> getAllProducStatus() {
        return ResponseEntity.ok(dataReturnService.success(Arrays.stream(ProductStatus.values()).map(status-> new StatusDTO(status.name(),status.getDescription())).collect(Collectors.toList())));
    }

    @GetMapping(value = "/common/role-type")
    public ResponseEntity<Object> getAllRoleType() {
        return ResponseEntity.ok(dataReturnService.success(Arrays.stream(RoleType.values()).map(status-> new StatusDTO(status.name(),status.getDescription())).collect(Collectors.toList())));
    }

    @GetMapping(value = "/common/status/shipper")
    public ResponseEntity<Object> getAllShipperStatus() {
        return ResponseEntity.ok(dataReturnService.success(Arrays.stream(ShipperStatus.values()).map(status-> new StatusDTO(status.name(),status.getDescription())).collect(Collectors.toList())));
    }
    @GetMapping(value = "/common/status/delivery")
    public ResponseEntity<Object> getAllDeliveryrStatus() {
        return ResponseEntity.ok(dataReturnService.success(Arrays.stream(StatusDelivery.values()).map(status-> new StatusDTO(status.name(),status.getDescription())).collect(Collectors.toList())));
    }

    @GetMapping(value = "/common/status/store")
    public ResponseEntity<Object> getAllStoreStatus() {
        return ResponseEntity.ok(dataReturnService.success(Arrays.stream(StoreStatus.values()).map(status-> new StatusDTO(status.name(),status.getDescription())).collect(Collectors.toList())));
    }

    @GetMapping(value = "/common/status/transaction")
    public ResponseEntity<Object> getAllTransactionStatus() {
        return ResponseEntity.ok(dataReturnService.success(Arrays.stream(TransactionStatus.values()).map(status-> new StatusDTO(status.name(),status.getDescription())).collect(Collectors.toList())));
    }
    @GetMapping(value = "/common/type-delivery")
    public ResponseEntity<Object> getAllTypeDelivery() {
        return ResponseEntity.ok(dataReturnService.success(Arrays.stream(TypeDelivery.values()).map(status-> new StatusDTO(status.name(),status.getDescription())).collect(Collectors.toList())));
    }
    @GetMapping(value = "/common/type-notifycation-store")
    public ResponseEntity<Object> getAllTypeNotifycationStore() {
        return ResponseEntity.ok(dataReturnService.success(Arrays.stream(TypeNotifycationStore.values()).map(status-> new StatusDTO(status.name(),status.getDescription())).collect(Collectors.toList())));
    }

    @GetMapping(value = "/common/type-notifycation-user")
    public ResponseEntity<Object> getAllTypeNotifycationUser() {
        return ResponseEntity.ok(dataReturnService.success(Arrays.stream(TypeNotifycationUser.values()).map(status-> new StatusDTO(status.name(),status.getDescription())).collect(Collectors.toList())));
    }

    @GetMapping(value = "/common/type-order")
    public ResponseEntity<Object> getAllTypeOrder() {
        return ResponseEntity.ok(dataReturnService.success(Arrays.stream(TypeOrder.values()).map(status-> new StatusDTO(status.name(),status.getDescription())).collect(Collectors.toList())));
    }

    @GetMapping(value = "/common/type-shipping")
    public ResponseEntity<Object> getAllTypeShipping() {
        return ResponseEntity.ok(dataReturnService.success(Arrays.stream(TypeShipping.values()).map(status-> new StatusDTO(status.name(),status.getDescription())).collect(Collectors.toList())));
    }

    @GetMapping(value = "/common/type-transaction")
    public ResponseEntity<Object> getAllTypeTransaction() {
        return ResponseEntity.ok(dataReturnService.success(Arrays.stream(TypeTransaction.values()).map(status-> new StatusDTO(status.name(),status.getDescription())).collect(Collectors.toList())));
    }

    @GetMapping(value = "/common/user-status")
    public ResponseEntity<Object> getAllUserStatus() {
        return ResponseEntity.ok(dataReturnService.success(Arrays.stream(UserStatus.values()).map(status-> new StatusDTO(status.name(),status.getDescription())).collect(Collectors.toList())));
    }

    @GetMapping(value = "/common/voucher-type")
    public ResponseEntity<Object> getAllVoucherType() {
        return ResponseEntity.ok(dataReturnService.success(Arrays.stream(VoucherType.values()).map(status-> new StatusDTO(status.name(),status.getDescription())).collect(Collectors.toList())));
    }





}
