package entity;

import entity.enum_package.DocumentType;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@SuperBuilder
@EqualsAndHashCode(callSuper = false)
@AttributeOverride(name = "id",column = @Column(name = "storeDocumentCode"))
public class StoreDocument extends EntityCommon{

    @Lob
    String documentUrl;

    @Enumerated(EnumType.STRING)
    DocumentType documentType;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "storeCode")
    Store store;
}
