package entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Date;

@MappedSuperclass
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public abstract class EntityCommon {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Temporal(TemporalType.DATE)
    private Date createdDate = new Date();

    @Temporal(TemporalType.DATE)
    private Date updatedDate;

    @Temporal(TemporalType.DATE)
    private Date deletedDate;


    private boolean deleted;

}
