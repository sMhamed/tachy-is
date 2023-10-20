package de.axone.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Tire {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Verificati
    //on du vehicule
    public String emptyVehicle;
    public String vehicleInGoodMarketCondition;
    public boolean tireInSameAxle;


}
