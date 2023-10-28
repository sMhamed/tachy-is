package de.axone.model;

import de.axone.enums.ApplicationType;
import de.axone.enums.PaymentType;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;
import java.util.List;

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

    @Column(name = "temperature")
    private Double temperature;

    // installation Tire
    @Column(columnDefinition = "SMALLINT", name = "isInstMetrologicalNotebookComplaint")
    private Boolean isInstMetrologicalNotebookComplaint;

    @Column(columnDefinition = "SMALLINT", name = "isInstMetrologicalPlateComplaint")
    private Boolean isInstMetrologicalPlateComplaint;

    @Column(columnDefinition = "SMALLINT", name = "isInstInitialVerifMarkPresent")
    private Boolean isInstInitialVerificationMarkPresent;

    @Column(columnDefinition = "SMALLINT", name = "isInstEmptyVehicle")
    private Boolean isInstEmptyVehicle;

    @Column(columnDefinition = "SMALLINT", name = "isInstFullyFuncVehicle")
    private Boolean isInstFullyFuncVehicle;

    @Column(columnDefinition = "SMALLINT", name = "isInstMatchingTiresOnSameAxle")
    private Boolean isInstMatchingTiresOnSameAxle;

    @Column(columnDefinition = "SMALLINT", name = "isInstTireComplaint")
    private Boolean isInstTireComplaint;

    @Column(columnDefinition = "SMALLINT", name = "isInstSealingAndMarkingOfInstDone")
    private Boolean isInstSealingAndMarkingOfInstallationDone;

    @Column(name = "simulationErrorPerSecond")
    private String simulationErrorPerSecond;

    @Column(name = "simulationErrorPerMinute")
    private String simulationErrorPerMinute;

    @Column(columnDefinition = "SMALLINT", name = "isSimulationComplaint")
    private Boolean isSimulationComplaint;

    @Column(columnDefinition = "SMALLINT", name = "isSimulationSpeedComplaint")
    private Boolean isSimulationSpeedComplaint;

    @Column(columnDefinition = "SMALLINT", name = "isSimulationDistanceComplaint")
    private Boolean isSimulationDistanceComplaint;

    @Column(columnDefinition = "SMALLINT", name = "isSimulationTracingComplaint")
    private Boolean isSimulationTracingComplaint;
    // Verification

    @Column(columnDefinition = "SMALLINT", name = "isVerifMetrologicalNotebookComplaint")
    private Boolean isVerifMetrologicalNotebookComplaint;

    @Column(columnDefinition = "SMALLINT", name = "isVerifMetrologicalPlateComplaint")
    private Boolean isVerifMetrologicalPlateComplaint;

    @Column(columnDefinition = "SMALLINT", name = "isVerifInitialMarkPresent")
    private Boolean isVerifInitialMarkPresent;

    @Column(columnDefinition = "SMALLINT", name = "isVerifInstSealingDone")
    private Boolean isVerifInstSealingDone;

    @Column(columnDefinition = "SMALLINT", name = "isVerifMatchingTiresOnSameAxle")
    private Boolean isVerifMatchingTiresOnSameAxle;

    @Column(columnDefinition = "SMALLINT", name = "isVerifTireComplaint")
    private Boolean isVerifTireComplaint;

    @Column(columnDefinition = "SMALLINT", name = "isClockComplaint")
    private Boolean isClockComplaint;

    @Column(name = "verifClockErrorPerSec")
    private Double verifClockErrorPerSec;

    @Column(name = "verifClockErrorPerMin")
    private Double verifClockErrorPerMin;

    @Column(name = "measuredDistance")
    private Double measuredDistance;

    @Column(name = "travelledDistance")
    private Double travelledDistance;

    @Column(name = "error")
    private Double error;

    @Column(name = "isComplaint", columnDefinition = "SMALLINT")
    private Boolean isComplaint;

    @Column(name = "isSealingAndMarkingComplaint", columnDefinition = "SMALLINT")
    private Boolean isSealingAndMarkingComplaint;

    @Column(name = "isPenalty", columnDefinition = "SMALLINT")
    private Boolean isPenalty;

    @Column(name = "isTachographComplaint", columnDefinition = "SMALLINT")
    private Boolean isTachographComplaint;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "service_order_id")
    private List<SpeedTest> speedTests;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "service_order_id")
    private List<DiskControl> diskControls;

    // Shared by installation and verification
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "service_order_id")
    private List<Tire> tires;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "service_order_id")
    private List<Measure> measures;

    // Verification report
    @Column(name = "certificateId")
    private String certificateId;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "service_order_id")
    private List<ControlAndDecision> controlAndDecisions;


}
