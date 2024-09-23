package entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Nationalized;

import java.util.List;

@Entity
@AttributeOverride(name = "id", column = @Column(name = "adminCode"))
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@EqualsAndHashCode(callSuper = false)
@Builder
public class Administration extends EntityCommon {

    private String userLogin;

    private String password;

    @Lob
    @Nationalized
    private String name;

    private String email;

    private String phone;

    private String image;

    @OneToMany(mappedBy = "administration")
    List<AdminRole> adminRoleList;

}
