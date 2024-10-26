package entity;

import entity.enum_package.OrderStatus;
import entity.enum_package.TypeOrder;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Nationalized;

import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@SuperBuilder
@EqualsAndHashCode(callSuper = false)
@AttributeOverride(name = "id", column = @Column(name = "orderCode"))
public class Orders extends EntityCommon {

    @Enumerated(EnumType.STRING)
    OrderStatus orderStatus;

    @Temporal(TemporalType.DATE)
    Date pickupDate;

    @Temporal(TemporalType.DATE)
    Date deliveryDate;

    Double totalAmount;

    Double totalAmountVoucher;

    Double totalAmountDiscount;

    Double totalAmountShip;

    Boolean confirmOrder;

    Boolean confirmPrepare;

    Boolean confirmDelivery;

    Boolean confirmPickup;

    Double finalTotal;

    String orderBillCode;

    @Lob
    @Nationalized
    String noteContent;

    @Lob
    @Nationalized
    String addressDetail;

    @Lob
    @Nationalized
    String address;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shippingFeeCode")
    ShippingFee shippingFee;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "paymentTypeCode")
    PaymentType paymentType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userCode")
    UserAccount userAccount;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "storeCode")
    Store store;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "provinceCode")
    Province province;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "districtCode")
    District district;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "wardCode")
    Ward ward;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "deliveryTypeCode")
    DeliveryType deliveryType;

    @OneToMany(mappedBy = "orders",cascade = CascadeType.PERSIST)
    List<VoucherApply> voucherApplyList;

    @Enumerated(EnumType.STRING)
    TypeOrder typeOrder;

    @OneToMany(mappedBy = "orders",cascade = CascadeType.PERSIST)
    List<OrderDetail> orderDetailList;

    @OneToMany(mappedBy = "orders")
    List<PaymentReceipt> paymentReceiptList;

    @OneToMany(mappedBy = "orders")
    List<ReceiveDelivery> receiveShippingList;

    @OneToMany(mappedBy = "orders")
    List<Gift> giftList;
}
