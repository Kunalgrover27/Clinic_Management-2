package com.cts.userdetails.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name = "Doctors")
public class Doctors {
    @Id
    private long doctorId;
    private String doctorName;
    private String disease;
    private int consulationFee;
    private int experience;
    @JsonIgnore
    @ManyToOne(fetch=FetchType.LAZY)
    private Clinic engagedClinic;
}
