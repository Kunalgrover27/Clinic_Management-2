package com.cts.userdetails.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@Entity @Table(name="Diseases")
public class Disease {
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private long id;
	    @Column(name = "disease_name")
	   private String name;

	    @ManyToMany(fetch = FetchType.LAZY)
	    @JoinTable(name="Disease_Clinic",joinColumns =
	            {
	                    @JoinColumn(name="disease_id",referencedColumnName = "id")
	            },
	            inverseJoinColumns ={
	                    @JoinColumn(name="clinic_id",referencedColumnName = "clinicId")
	            }
	    )
	    private Set<Clinic> clinics = new LinkedHashSet<>();
}
