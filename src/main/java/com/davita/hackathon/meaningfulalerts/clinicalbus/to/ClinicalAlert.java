package com.davita.hackathon.meaningfulalerts.clinicalbus.to;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class ClinicalAlert {

    private String patientName;

    private Long physicianId;

    private String value;

    private String alertName;

    private String alertCategory;

    // will say positive/negative/low/high/mvp
    private String output;

    @JsonFormat
            (shape = JsonFormat.Shape.STRING, pattern = "MM/dd/yyyy")
    private Date alertDate;
}
