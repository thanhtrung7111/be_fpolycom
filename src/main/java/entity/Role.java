package entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Entity
@Table
@AttributeOverride(name = "id" ,column = @Column(name = "roleCode"))
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@EqualsAndHashCode(callSuper = false)
@Builder
public class Role extends EntityCommon{

     String name;

     Integer rightCode;

     @OneToMany(mappedBy = "role")
     List<AdminRole> adminRoleList;
}
