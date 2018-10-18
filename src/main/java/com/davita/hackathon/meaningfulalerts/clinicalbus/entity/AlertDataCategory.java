package com.davita.hackathon.meaningfulalerts.clinicalbus.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ALERT_DATA_CATEGORY")
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class AlertDataCategory {

    @Id
    @Column(name = "ALERT_CATEGORY_ID")
    private Long id;

    @Column(name = "ALERT_CATEGORY_NAME")
    private String name;
}
