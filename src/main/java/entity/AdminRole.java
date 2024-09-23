package entity;


import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@AttributeOverride(name = "id", column = @Column(name = "adminRoleCode"))
@EqualsAndHashCode(callSuper = false)
@SuperBuilder
public class AdminRole extends EntityCommon {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "roleCode")
    Role role;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "adminCode")
    Administration administration;

}
