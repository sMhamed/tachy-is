package de.axone.model;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "installation_sheet")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InstallationSheet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="code")
    private String code;

    @Column(name="version")
    private String version;

    @Column(name="sheetId")
    private String sheetId;

    @Column(name="created")
    private Timestamp created;

    @Column(name="temperature")
    private Double temperature;

    @Column(columnDefinition = "SMALLINT",name="isMetrologicalNotebookComplaint")
    private Boolean isMetrologicalNotebookComplaint;

    @Column(columnDefinition = "SMALLINT",name="isMetrologicalPlateComplaint")
    private Boolean isMetrologicalPlateComplaint;

    @Column(columnDefinition = "SMALLINT",name="isInitialVerificationMarkPresent")
    private Boolean isInitialVerificationMarkPresent;

    @Column(columnDefinition = "SMALLINT",name="isEmptyVehicle")
    private Boolean isEmptyVehicle;

    @Column(columnDefinition = "SMALLINT",name="isFullyFunctionnalVehicle")
    private Boolean isFullyFunctionnalVehicle;

    @Column(columnDefinition = "SMALLINT",name="isMatchingTiresOnSameAxle")
    private Boolean isMatchingTiresOnSameAxle;

    @Column(columnDefinition = "SMALLINT",name="isTireComplaint")
    private Boolean isTireComplaint;

    @Column(columnDefinition = "SMALLINT",name="isSealingAndMarkingOfInstallationDone")
    private Boolean isSealingAndMarkingOfInstallationDone;

    @OneToOne(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
    @JoinColumn(name = "simulation_id")
    private Simulation simulation;

    @OneToOne(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
    @JoinColumn(name = "installation_measure_id")
    private InstallationMeasure installationMeasure;

    @OneToOne(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
    @JoinColumn(name = "vehicle_id")
    private Vehicle vehicle;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "installation_sheet_id")
    private List<InstallationTire> installationTires;


}
