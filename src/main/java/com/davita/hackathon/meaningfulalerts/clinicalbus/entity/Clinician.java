package com.davita.hackathon.meaningfulalerts.clinicalbus.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import javax.persistence.Id;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;

@Entity
@Table(name = "CLINICIAN")
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Clinician {

    @Id
    @Column(name = "CLINICIAN_ID")
    private Long id;

    @Column(name = "CLINICIAN_TYPE")
    private String type;

    @Column(name = "CLINICIAN_NAME")
    private String name;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "CLINICIAN_ALERTS_CATEGORY", joinColumns = @JoinColumn(name = "CLINICIAN_ID"), inverseJoinColumns = @JoinColumn(name = "ALERT_CATEGORY_ID"))
    private Set<AlertDataCategory> subscribedAlerts;
}
