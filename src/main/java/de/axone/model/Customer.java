package de.axone.model;

import de.axone.enums.ReachabilityType;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "customer")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String lastName;

    @Column
    private String firstName;

    @Column
    private String email;

    @Column
    private String address;

    @Column
    @Enumerated(EnumType.STRING)
    private ReachabilityType reachBy;

    @Column
    private String fax;

    @OneToOne(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
    @JoinColumn(name = "vehicle_id")
    private Vehicle vehicle;

}
