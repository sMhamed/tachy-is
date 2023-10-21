package de.axone.model;


import de.axone.enums.IconType;
import de.axone.enums.PeriodType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "speed_test")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SpeedTest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // To be renamed the text in form3 was too dark to get the correct name of this attribute
    @Column(name = "period")
    @Enumerated(EnumType.STRING)
    private PeriodType period;

    // To be renamed the text in form3 was too dark to get the correct name of this attribute
    @Column(name = "result")
    private Integer result;

    // To be renamed the text in form3 was too dark to get the correct name of this attribute
    @Column(name = "duration")
    private Integer duration;

    // To be renamed the text in form3 was too dark to get the correct name of this attribute
    @Column(name = "icon")
    @Enumerated(EnumType.STRING)
    private IconType icon;

    // To be renamed the text in form3 was too dark to get the correct name of this attribute
    @Column(name = "test")
    private String test;

    @Column(name = "isComplaint", columnDefinition = "SMALLINT")
    private Boolean isComplaint;

}
