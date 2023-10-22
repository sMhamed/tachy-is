package de.axone.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "Central")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Central {

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
    @JoinColumn(name = "central_id")
    private List<User> users;
}
