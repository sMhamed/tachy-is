package de.axone.model;

import de.axone.enums.RoleType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.NaturalId;

@Entity
@Table(name = "role")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @NaturalId
    @Column (name = "name")
    private RoleType name;

    @Column (name = "description")
    private String description;

}
