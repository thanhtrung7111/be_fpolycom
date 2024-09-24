package entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Nationalized;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@SuperBuilder
@AttributeOverride(name = "id",column = @Column(name = "deliveryTypeCode"))
public class DeliveryType extends EntityCommon{

    @Lob
    @Nationalized
    String name;

    Double fee;

    @OneToMany(mappedBy = "deliveryType")
    List<Orders> ordersList;

}
