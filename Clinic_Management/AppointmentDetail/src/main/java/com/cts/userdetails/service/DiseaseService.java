package com.cts.userdetails.service;

import com.cts.userdetails.exception.EmptyDataAccessException;
import com.cts.userdetails.model.Clinic;
import com.cts.userdetails.model.Disease;
import com.cts.userdetails.model.Doctors;
import com.cts.userdetails.model.SuccessResponse;
import com.cts.userdetails.repository.ClinicDao;
import com.cts.userdetails.repository.DiseaseDao;
import com.cts.userdetails.repository.DoctorRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service

public class DiseaseService {
	@Autowired
    private DiseaseDao repo;
	@Autowired
	private ClinicDao clinicRepo;
	@Autowired
	private DoctorRepo doctorRepo;
	@Autowired
	private SuccessResponse resp;

	public SuccessResponse createDisease(Disease disease) throws EmptyDataAccessException {
		   if(disease==null){
	            throw new EmptyDataAccessException("Object is null");
	        }
		    try {
	            repo.save(disease);
	        }catch (Exception e){
				resp.setMessage("Disease not registered");
			}

           resp.setMessage("Disease is registered successfully");
			return resp;
	}

	public SuccessResponse createClinic(Clinic clinic) throws EmptyDataAccessException {
		 if(clinic==null){
	            throw new EmptyDataAccessException("Object is null");
	        }
		    try {
	           clinicRepo.save(clinic);
	        }catch (Exception e) {
				resp.setMessage("Clinic not registered");
			}
				resp.setMessage("Clinic is registered successfully");
				return resp;
			}


	public Disease getAllClinics(long id) throws EmptyDataAccessException {

		Disease d=repo.getReferenceById(id);
		if(d==null){
			throw new EmptyDataAccessException("No Disease Record Found");
		}
		return d;
	}

	public List<Disease> getAllDiseases() throws EmptyDataAccessException {
		List<Disease> diseaseList=repo.findAll();
		if(diseaseList.isEmpty()){
			throw new EmptyDataAccessException("No  Records found");
		}
		return diseaseList;
	}

	public Clinic clinicDetailById(long id) throws EmptyDataAccessException {
		Clinic c=clinicRepo.getReferenceById(id);
		if(c==null){
			throw new EmptyDataAccessException("No Clinic Record Found");
		}
		return c;
	}

	public SuccessResponse addDoctor(Doctors doctor, long id) throws EmptyDataAccessException {
		if(doctor==null){
			throw new EmptyDataAccessException("Object is null");
		}
		try {

			Clinic c=clinicRepo.getReferenceById(id);
			doctor.setEngagedClinic(c);
			doctorRepo.save(doctor);
		}catch (Exception e){
			resp.setMessage("Doctor is not registered...Try again..");}

		resp.setMessage("Doctor is added successfully.");
		return resp;
	}
	public SuccessResponse deleteClinic(long id,long clinicId) {
Optional<Disease> diseaseOptional=repo.findById(id);
Optional<Clinic> clinicOptional=clinicRepo.findById(clinicId);

if(diseaseOptional.isPresent()){
	Disease d=diseaseOptional.get();
	Clinic c=clinicOptional.get();
	Set<Clinic> s=new LinkedHashSet<>();
	s.addAll(d.getClinics());
	System.out.println(s);
	s.remove(c);
	System.out.println(s);
	d.setClinics(s);
//	d.getClinics().remove(c);
    repo.save(d);
	resp.setMessage("Successful");

}
else{
	resp.setMessage("not successful");
}
return resp;
	}

	public SuccessResponse addClinicToDisease(Clinic clinic, long id) throws EmptyDataAccessException {

		if(clinic==null){
			throw new EmptyDataAccessException("Object is null");
		}
		try {
			Disease d=repo.getReferenceById(id);
            Set<Clinic> c=new LinkedHashSet<>();
			c.addAll( d.getClinics());
			c.add(clinic);
			d.setClinics(c);
			repo.save(d);
		}catch (Exception e){resp.setMessage("Clinic is not added to the disease");}

		resp.setMessage("Clinic is added successfully.");
		return resp;
	}

	public List<Clinic> getAllClinic() throws EmptyDataAccessException {
		List<Clinic> clinicList = clinicRepo.findAll();
		if (clinicList != null){
			return clinicList;
	}
	else{
			throw new EmptyDataAccessException("The clinicList is empty");

		}
	}


	public SuccessResponse deleteClinicById(long clinicId) throws EmptyDataAccessException {
		try {
			clinicRepo.deleteById(clinicId);
		}catch (Exception e){
			throw new EmptyDataAccessException("The Record is not presented with id : "+clinicId);
		}
		resp.setMessage("Clinic is deleted successfully");
		return resp;
	}

    public SuccessResponse updateClinicDetails(Clinic clinic) throws EmptyDataAccessException {
		if (clinic == null) {
			throw new EmptyDataAccessException("Object is null");
		}
		else{
		Clinic existingClinic= clinicRepo.findById(clinic.getClinicId()).orElseThrow(new EmptyDataAccessException("Clinic data is not Found"));
		existingClinic.setClinicName(clinic.getClinicName());
		existingClinic.setAddress(clinic.getAddress());
		existingClinic.setDays(clinic.getDays());
		existingClinic.setStartTime(clinic.getStartTime());
		existingClinic.setEndTime(clinic.getEndTime());
		existingClinic.setContactUs(clinic.getContactUs());
		clinicRepo.save(existingClinic);
		resp.setMessage("Clinic is updated successfully");
    }
		return resp;
}

	public SuccessResponse deleteClinicDoctorById(long doctorId) throws EmptyDataAccessException {
		try {
			doctorRepo.deleteById(doctorId);
		}catch (Exception e){
			throw new EmptyDataAccessException("The Record is not presented with id : "+doctorId);
		}
		resp.setMessage("Doctor is deleted successfully");
		return resp;
	}

	public SuccessResponse deleteDisease(long diseaseId) throws EmptyDataAccessException {
		try {
			repo.deleteById(diseaseId);
		}catch (Exception e){
			throw new EmptyDataAccessException("The Record is not presented with id : "+diseaseId);
		}
		resp.setMessage("Disease is deleted successfully");
		return resp;
	}
}
