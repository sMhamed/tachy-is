package de.axone.model;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "verification_report")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VerificationReport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "code")
    private String code;

    @Column(name = "version")
    private String version;

    @Column(name = "certificateId")
    private String certificateId;

    @Column(name = "created")
    private Timestamp created;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "vehicle_id")
    private Vehicle vehicle;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "verification_report_id")
    private List<ControlAndDecision> controlAndDecisions;
}
