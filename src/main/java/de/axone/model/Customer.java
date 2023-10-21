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

    @Column(name = "lastName")
    private String lastName;

    @Column(name = "firstName")
    private String firstName;

    @Column(name = "email")
    private String email;

    @Column(name = "address")
    private String address;

    @Column(name = "fax")
    private String fax;

    @Column(name = "phoneNumber")
    private String phoneNumber;

    @Column(name = "reachBy")
    @Enumerated(EnumType.STRING)
    private ReachabilityType reachBy;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "vehicle_id")
    private Vehicle vehicle;

}
