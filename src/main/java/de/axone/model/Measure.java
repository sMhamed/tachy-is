package de.axone.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "measure")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Measure {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "w1")
    private Integer w1;

    @Column(name = "w2")
    private Integer w2;

    @Column(name = "w3")
    private Integer w3;

    @Column(name = "w4")
    private Integer w4;

    @Column(name = "wAvg")
    private Integer wAvg;

    @Column(name = "lRight")
    private Integer lRight;

    @Column(name = "lLeft")
    private Integer lLeft;

    @Column(name = "lAvg")
    private Integer lAvg;

    @Column(name = "kConstant")
    private Integer kConstant;
}
