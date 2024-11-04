package entity;

import entity.enum_package.TypeImportExportOrders;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@SuperBuilder
@EqualsAndHashCode(callSuper = false)
@AttributeOverride(name = "id",column = @Column(name = "importExportOrdersCode"))
public class ImportExportOrders extends EntityCommon{



    @ManyToOne
    @JoinColumn(name = "ordersCode")
    Orders orders;

    @ManyToOne
    @JoinColumn(name = "warehouseList")
    Warehouse warehouse;

    @Enumerated(EnumType.STRING)
    TypeImportExportOrders typeImportExportOrders;

    @Temporal(TemporalType.TIMESTAMP)
    LocalDateTime date;
}
