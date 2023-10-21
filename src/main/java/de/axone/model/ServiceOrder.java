package de.axone.model;

import de.axone.enums.ApplicationType;
import de.axone.enums.PaymentType;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;

@Entity
@Table(name = "service_order")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ServiceOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "code")
    private String code;

    @Column(name = "version")
    private String version;

    @Column(name = "sheetId")
    private String sheetId;

    @Column(name = "paymentType")
    @Enumerated(EnumType.STRING)
    private PaymentType paymentType;

    @Column(name = "created")
    private Timestamp created;

    @Column(name = "applicationType")
    @Enumerated(EnumType.STRING)
    private ApplicationType applicationType;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "installation_sheet_id")
    private InstallationSheet installationSheet;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "verification_sheet_id")
    private VerificationSheet verificationSheet;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "verification_report_id")
    private VerificationReport verificationReport;
}
