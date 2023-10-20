package de.axone.model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "installation_sheet")
@Getter
@Setter
@Builder
public class InstallationSheet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Verification du vehicule
    public boolean emptyVehicle;
    public boolean vehicleInGoodMarketCondition;
    public boolean tireInSameAxle;


    // Pneumatique


    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "installation_sheet_id")
    private List<Tire> tires;


}
