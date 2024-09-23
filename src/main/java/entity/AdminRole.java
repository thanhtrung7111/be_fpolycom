package entity;


import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@AttributeOverride(name = "id",column = @Column(name = "adminRoleCode"))
@EqualsAndHashCode(callSuper = false)
@Builder
public class AdminRole extends EntityCommon{

    @ManyToOne(fetch = FetchType.LAZY)
    Role role;


    @ManyToOne(fetch = FetchType.LAZY)
    Administration administration;

}
