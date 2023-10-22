package de.axone.model;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "verification_sheet")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VerificationSheet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "code")
    private String code;

    @Column(name = "version")
    private String version;

    @Column(name = "sheetId")
    private String sheetId;

    @Column(name = "created")
    private Timestamp created;

    @Column(name = "temperature")
    private Double temperature;

    @Column(columnDefinition = "SMALLINT", name = "isMetrologicalNotebookComplaint")
    private Boolean isMetrologicalNotebookComplaint;

    @Column(columnDefinition = "SMALLINT", name = "isMetrologicalPlateComplaint")
    private Boolean isMetrologicalPlateComplaint;

    @Column(columnDefinition = "SMALLINT", name = "isInitialVerificationMarkPresent")
    private Boolean isInitialVerificationMarkPresent;

    @Column(columnDefinition = "SMALLINT", name = "isInstallationSealingDone")
    private Boolean isInstallationSealingDone;

    @Column(columnDefinition = "SMALLINT", name = "isMatchingTiresOnSameAxle")
    private Boolean isMatchingTiresOnSameAxle;

    @Column(columnDefinition = "SMALLINT", name = "isTireComplaint")
    private Boolean isTireComplaint;

    @Column(columnDefinition = "SMALLINT", name = "isClockComplaint")
    private Boolean isClockComplaint;

    @Column(name = "clockErrorPerSec")
    private Double clockErrorPerSec;

    @Column(name = "clockErrorPerMin")
    private Double clockErrorPerMin;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "verification_sheet_id")
    private List<SpeedTest> speedTests;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "verification_sheet_id")
    private List<DiskControl> diskControls;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "verification_measure_id")
    private VerificationMeasure verificationMeasure;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "finalControl_id")
    private FinalControl finalControl;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "vehicle_id")
    private Vehicle vehicle;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "verification_sheet_id")
    private List<VerificationTire> verificationTires;
}
