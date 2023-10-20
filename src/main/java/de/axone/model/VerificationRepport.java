package de.axone.model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "verification_repport")
@Getter
@Setter
@Builder
public class VerificationRepport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}
