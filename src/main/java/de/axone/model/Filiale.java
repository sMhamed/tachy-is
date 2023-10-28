package de.axone.model;


import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "filiale")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Filiale {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "logo")
    private String logo;

    @Column(name = "identifier")
    private String identifier;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "filiale_id")
    private List<User> users;


    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "filiale_id")
    private List<Customer> customers;
}
