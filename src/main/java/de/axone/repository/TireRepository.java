package de.axone.repository;

import de.axone.model.Tire;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TireRepository extends JpaRepository<Tire, Long> {
}

