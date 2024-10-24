package entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
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
@AttributeOverride(name="id",column = @Column(name = "discountCode"))
public class Discount extends EntityCommon{

    @Lob
    @Nationalized
    String name;

    Integer percentDecrease =0;

    @Lob
    @Nationalized
    String description;

    @Temporal(TemporalType.DATE)
    Date beginDate;



    @OneToMany(mappedBy = "discount")
    List<ProductDetail> productDetailList;


    @OneToMany(mappedBy = "discount")
    List<OrderDetail> orderDetailList;
}
