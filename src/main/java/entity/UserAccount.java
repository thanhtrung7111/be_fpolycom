package entity;

import entity.enum_package.UserStatus;
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
@EqualsAndHashCode(callSuper = false)
@AttributeOverride(name = "id", column = @Column(name = "userCode"))
@SuperBuilder
public class UserAccount extends EntityCommon {

    String userLogin;

    String password;

    @Lob
    @Nationalized
    String name;

    String phone;

    @Lob
    @Nationalized
    String addressDetail;


    @Lob
    @Nationalized
    String address;

    @Lob
    String image;

    @Lob
    String bannerImage;

    @Lob
    String email;

    @Temporal(TemporalType.DATE)
    Date dateOfBirth;

    @Enumerated(EnumType.STRING)
    UserStatus userStatus;

    Boolean gender;

    String tokenConfirm;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "provinceCode")
    Province province;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "districtCode")
    District district;

    @ManyToOne
    @JoinColumn(name = "wardCode")
    Ward ward;


    @OneToMany(mappedBy = "userAccountPrimary")
    List<Relationship> relationshipList;

    @OneToMany(mappedBy = "userAccount")
    List<NotifycationUser> notifycationUserList;

    @OneToOne(mappedBy = "userAccount")
    Store store;

    @OneToMany(mappedBy = "userAccount")
    List<BankUser> bankUserList;

    @OneToMany(mappedBy = "userAccount")
    List<VoucherUser> voucherUserList;

    @OneToMany(mappedBy = "userAccount")
    List<Liked> likedList;

    @OneToMany(mappedBy = "userAccount")
    List<Evaluate> evaluateList;

    @OneToMany(mappedBy = "userAccount")
    List<ShoppingCart> shoppingCartList;

    @OneToMany(mappedBy = "userAccount")
    List<Orders> ordersList;
}
