package de.axone.repository;

import de.axone.model.SpeedTest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpeedTestRepository extends JpaRepository<SpeedTest, Long> {
}
