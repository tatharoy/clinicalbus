package com.davita.hackathon.meaningfulalerts.clinicalbus.entity;


import com.davita.hackathon.meaningfulalerts.clinicalbus.util.RuleCategory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ALERT_DATA_TYPE")
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class AlertDataType {

    @Id
    @Column(name = "ALERT_DATA_ID")
    private Long id;

    @Column(name = "ALERT_DATA_NAME")
    private String name;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ALERT_CATEGORY", nullable = false)
    private AlertDataCategory alertDataCategory;

    @Column(name = "ALERT_UNIT")
    private String unit;

    @Column(name = "RULE_CATEGORY")
    @Enumerated(EnumType.STRING)
    private RuleCategory category;

    @Column(name = "MAX_VALID")
    private double maxValidUnit;

    @Column(name = "MIN_VALID")
    private double minValidUnit;

    @Column(name = "BOOLEAN_TYPE")
    private String booleanType;

}
