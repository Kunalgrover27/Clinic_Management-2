package com.cts.userdetails.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Appointment")
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long appointmentId;
    private long diseaseId;
    private long clinicId;
    private long doctorId;
    private long patientId;
    private String date;
    private String time;
    private String clinicName;
    private String doctorName;
    private String diseaseName;
}
