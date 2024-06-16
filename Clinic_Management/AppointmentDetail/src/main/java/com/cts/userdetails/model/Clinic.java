package com.cts.userdetails.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Clinics")
public class Clinic {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long clinicId;
    private String clinicName;
    private String address;
    private String days;
    private String startTime;
    private String endTime;
    private long contactUs;
    @OneToMany(mappedBy="engagedClinic" ,fetch=FetchType.LAZY,cascade=CascadeType.ALL)
    private List<Doctors> doctor=new ArrayList<>();
}
