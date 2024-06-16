package com.cts.userdetails.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.cts.userdetails.exception.EmptyDataAccessException;
import com.cts.userdetails.model.Clinic;
import com.cts.userdetails.model.Disease;
import com.cts.userdetails.model.Doctors;
import com.cts.userdetails.model.SuccessResponse;
import com.cts.userdetails.repository.ClinicDao;
import com.cts.userdetails.repository.DiseaseDao;
import com.cts.userdetails.repository.DoctorRepo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Disabled;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@ContextConfiguration(classes = {DiseaseService.class})
@ExtendWith(SpringExtension.class)
class DiseaseServiceTest {
    @MockBean
    private ClinicDao clinicDao;

    @MockBean
    private DiseaseDao diseaseDao;

    @Autowired
    private DiseaseService diseaseService;

    @MockBean
    private DoctorRepo doctorRepo;

    @MockBean
    private SuccessResponse successResponse;

    /**
     * Method under test: {@link DiseaseService#createDisease(Disease)}
     */
    @Test
    void testCreateDisease() throws EmptyDataAccessException {
        Disease disease = new Disease();
        disease.setClinics(new HashSet<>());
        disease.setId(123L);
        disease.setName("Name");
        when(diseaseDao.save((Disease) any())).thenReturn(disease);
        doNothing().when(successResponse).setMessage((String) any());

        Disease disease1 = new Disease();
        disease1.setClinics(new HashSet<>());
        disease1.setId(123L);
        disease1.setName("Name");
        diseaseService.createDisease(disease1);
        verify(diseaseDao).save((Disease) any());
        verify(successResponse).setMessage((String) any());
    }

    /**
     * Method under test: {@link DiseaseService#createClinic(Clinic)}
     */
    @Test
    void testCreateClinic() throws EmptyDataAccessException {
        Clinic clinic = new Clinic();
        clinic.setAddress("42 Main St");
        clinic.setClinicId(123L);
        clinic.setClinicName("Clinic Name");
        clinic.setContactUs(1L);
        clinic.setDays("Days");
        clinic.setDoctor(new ArrayList<>());
        clinic.setEndTime("End Time");
        clinic.setStartTime("Start Time");
        when(clinicDao.save((Clinic) any())).thenReturn(clinic);
        doNothing().when(successResponse).setMessage((String) any());

        Clinic clinic1 = new Clinic();
        clinic1.setAddress("42 Main St");
        clinic1.setClinicId(123L);
        clinic1.setClinicName("Clinic Name");
        clinic1.setContactUs(1L);
        clinic1.setDays("Days");
        clinic1.setDoctor(new ArrayList<>());
        clinic1.setEndTime("End Time");
        clinic1.setStartTime("Start Time");
        diseaseService.createClinic(clinic1);
        verify(clinicDao).save((Clinic) any());
        verify(successResponse).setMessage((String) any());
    }

    /**
     * Method under test: {@link DiseaseService#getAllClinics(long)}
     */
    @Test
    void testGetAllClinics() throws EmptyDataAccessException {
        Disease disease = new Disease();
        disease.setClinics(new HashSet<>());
        disease.setId(123L);
        disease.setName("Name");
        when(diseaseDao.getReferenceById((Long) any())).thenReturn(disease);
        assertSame(disease, diseaseService.getAllClinics(123L));
        verify(diseaseDao).getReferenceById((Long) any());
    }

    /**
     * Method under test: {@link DiseaseService#getAllDiseases()}
     */
    @Test
    void testGetAllDiseases() throws EmptyDataAccessException {
        when(diseaseDao.findAll()).thenReturn(new ArrayList<>());
        assertThrows(EmptyDataAccessException.class, () -> diseaseService.getAllDiseases());
        verify(diseaseDao).findAll();
    }

    /**
     * Method under test: {@link DiseaseService#getAllDiseases()}
     */
    @Test
    void testGetAllDiseases2() throws EmptyDataAccessException {
        Disease disease = new Disease();
        disease.setClinics(new HashSet<>());
        disease.setId(123L);
        disease.setName("No  Records found");

        ArrayList<Disease> diseaseList = new ArrayList<>();
        diseaseList.add(disease);
        when(diseaseDao.findAll()).thenReturn(diseaseList);
        List<Disease> actualAllDiseases = diseaseService.getAllDiseases();
        assertSame(diseaseList, actualAllDiseases);
        assertEquals(1, actualAllDiseases.size());
        verify(diseaseDao).findAll();
    }

    /**
     * Method under test: {@link DiseaseService#clinicDetailById(long)}
     */
    @Test
    void testClinicDetailById() throws EmptyDataAccessException {
        Clinic clinic = new Clinic();
        clinic.setAddress("42 Main St");
        clinic.setClinicId(123L);
        clinic.setClinicName("Clinic Name");
        clinic.setContactUs(1L);
        clinic.setDays("Days");
        clinic.setDoctor(new ArrayList<>());
        clinic.setEndTime("End Time");
        clinic.setStartTime("Start Time");
        when(clinicDao.getReferenceById((Long) any())).thenReturn(clinic);
        assertSame(clinic, diseaseService.clinicDetailById(123L));
        verify(clinicDao).getReferenceById((Long) any());
    }

    /**
     * Method under test: {@link DiseaseService#addDoctor(Doctors, long)}
     */
    @Test
    void testAddDoctor() throws EmptyDataAccessException {
        Clinic clinic = new Clinic();
        clinic.setAddress("42 Main St");
        clinic.setClinicId(123L);
        clinic.setClinicName("Clinic Name");
        clinic.setContactUs(1L);
        clinic.setDays("Days");
        clinic.setDoctor(new ArrayList<>());
        clinic.setEndTime("End Time");
        clinic.setStartTime("Start Time");
        when(clinicDao.getReferenceById((Long) any())).thenReturn(clinic);

        Clinic clinic1 = new Clinic();
        clinic1.setAddress("42 Main St");
        clinic1.setClinicId(123L);
        clinic1.setClinicName("Clinic Name");
        clinic1.setContactUs(1L);
        clinic1.setDays("Days");
        clinic1.setDoctor(new ArrayList<>());
        clinic1.setEndTime("End Time");
        clinic1.setStartTime("Start Time");

        Doctors doctors = new Doctors();
        doctors.setConsulationFee(1);
        doctors.setDisease("Disease");
        doctors.setDoctorId(123L);
        doctors.setDoctorName("Doctor Name");
        doctors.setEngagedClinic(clinic1);
        doctors.setExperience(1);
        when(doctorRepo.save((Doctors) any())).thenReturn(doctors);
        doNothing().when(successResponse).setMessage((String) any());

        Clinic clinic2 = new Clinic();
        clinic2.setAddress("42 Main St");
        clinic2.setClinicId(123L);
        clinic2.setClinicName("Clinic Name");
        clinic2.setContactUs(1L);
        clinic2.setDays("Days");
        clinic2.setDoctor(new ArrayList<>());
        clinic2.setEndTime("End Time");
        clinic2.setStartTime("Start Time");

        Doctors doctors1 = new Doctors();
        doctors1.setConsulationFee(1);
        doctors1.setDisease("Disease");
        doctors1.setDoctorId(123L);
        doctors1.setDoctorName("Doctor Name");
        doctors1.setEngagedClinic(clinic2);
        doctors1.setExperience(1);
        diseaseService.addDoctor(doctors1, 123L);
        verify(clinicDao).getReferenceById((Long) any());
        verify(doctorRepo).save((Doctors) any());
        verify(successResponse).setMessage((String) any());
        assertSame(clinic, doctors1.getEngagedClinic());
    }

    /**
     * Method under test: {@link DiseaseService#deleteClinic(long, long)}
     * @throws Throwable 
     */
    @Test
    void testDeleteClinic() throws Throwable {
        Clinic clinic = new Clinic();
        clinic.setAddress("42 Main St");
        clinic.setClinicId(123L);
        clinic.setClinicName("Clinic Name");
        clinic.setContactUs(1L);
        clinic.setDays("Days");
        clinic.setDoctor(new ArrayList<>());
        clinic.setEndTime("End Time");
        clinic.setStartTime("Start Time");
        Optional<Clinic> ofResult = Optional.of(clinic);
        when(clinicDao.findById((Long) any())).thenReturn(ofResult);

        Disease disease = new Disease();
        disease.setClinics(new HashSet<>());
        disease.setId(123L);
        disease.setName("Name");
        Optional<Disease> ofResult1 = Optional.of(disease);

        Disease disease1 = new Disease();
        disease1.setClinics(new HashSet<>());
        disease1.setId(123L);
        disease1.setName("Name");
        when(diseaseDao.save((Disease) any())).thenReturn(disease1);
        when(diseaseDao.findById((Long) any())).thenReturn(ofResult1);
        doNothing().when(successResponse).setMessage((String) any());
        diseaseService.deleteClinic(123L, 123L);
        verify(clinicDao).findById((Long) any());
        verify(diseaseDao).save((Disease) any());
        verify(diseaseDao).findById((Long) any());
        verify(successResponse).setMessage((String) any());
    }

    /**
     * Method under test: {@link DiseaseService#deleteClinic(long, long)}
     * @throws Throwable 
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testDeleteClinic2() throws Throwable {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException
        //       at com.cts.userdetails.service.DiseaseService.deleteClinic(DiseaseService.java:100)
        //   In order to prevent deleteClinic(long, long)
        //   from throwing NullPointerException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   deleteClinic(long, long).
        //   See https://diff.blue/R013 to resolve this issue.

        when(clinicDao.findById((Long) any())).thenReturn(null);

        Disease disease = new Disease();
        disease.setClinics(new HashSet<>());
        disease.setId(123L);
        disease.setName("Name");
        Optional<Disease> ofResult = Optional.of(disease);

        Disease disease1 = new Disease();
        disease1.setClinics(new HashSet<>());
        disease1.setId(123L);
        disease1.setName("Name");
        when(diseaseDao.save((Disease) any())).thenReturn(disease1);
        when(diseaseDao.findById((Long) any())).thenReturn(ofResult);
        doNothing().when(successResponse).setMessage((String) any());
        diseaseService.deleteClinic(123L, 123L);
    }

    /**
     * Method under test: {@link DiseaseService#deleteClinic(long, long)}
     * @throws Throwable 
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testDeleteClinic3() throws Throwable {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException
        //       at com.cts.userdetails.service.DiseaseService.deleteClinic(DiseaseService.java:99)
        //   In order to prevent deleteClinic(long, long)
        //   from throwing NullPointerException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   deleteClinic(long, long).
        //   See https://diff.blue/R013 to resolve this issue.

        Clinic clinic = new Clinic();
        clinic.setAddress("42 Main St");
        clinic.setClinicId(123L);
        clinic.setClinicName("Clinic Name");
        clinic.setContactUs(1L);
        clinic.setDays("Days");
        clinic.setDoctor(new ArrayList<>());
        clinic.setEndTime("End Time");
        clinic.setStartTime("Start Time");
        Optional<Clinic> ofResult = Optional.of(clinic);
        when(clinicDao.findById((Long) any())).thenReturn(ofResult);

        Disease disease = new Disease();
        disease.setClinics(new HashSet<>());
        disease.setId(123L);
        disease.setName("Name");
        when(diseaseDao.save((Disease) any())).thenReturn(disease);
        when(diseaseDao.findById((Long) any())).thenReturn(null);
        doNothing().when(successResponse).setMessage((String) any());
        diseaseService.deleteClinic(123L, 123L);
    }

    /**
     * Method under test: {@link DiseaseService#addClinicToDisease(Clinic, long)}
     */
    @Test
    void testAddClinicToDisease() throws EmptyDataAccessException {
        Disease disease = new Disease();
        disease.setClinics(new HashSet<>());
        disease.setId(123L);
        disease.setName("Name");

        Disease disease1 = new Disease();
        disease1.setClinics(new HashSet<>());
        disease1.setId(123L);
        disease1.setName("Name");
        when(diseaseDao.save((Disease) any())).thenReturn(disease1);
        when(diseaseDao.getReferenceById((Long) any())).thenReturn(disease);
        doNothing().when(successResponse).setMessage((String) any());

        Clinic clinic = new Clinic();
        clinic.setAddress("42 Main St");
        clinic.setClinicId(123L);
        clinic.setClinicName("Clinic Name");
        clinic.setContactUs(1L);
        clinic.setDays("Days");
        clinic.setDoctor(new ArrayList<>());
        clinic.setEndTime("End Time");
        clinic.setStartTime("Start Time");
        diseaseService.addClinicToDisease(clinic, 123L);
        verify(diseaseDao).getReferenceById((Long) any());
        verify(diseaseDao).save((Disease) any());
        verify(successResponse).setMessage((String) any());
    }

    /**
     * Method under test: {@link DiseaseService#getAllClinic()}
     */
    @Test
    void testGetAllClinic() throws EmptyDataAccessException {
        ArrayList<Clinic> clinicList = new ArrayList<>();
        when(clinicDao.findAll()).thenReturn(clinicList);
        List<Clinic> actualAllClinic = diseaseService.getAllClinic();
        assertSame(clinicList, actualAllClinic);
        assertTrue(actualAllClinic.isEmpty());
        verify(clinicDao).findAll();
    }

    /**
     * Method under test: {@link DiseaseService#deleteClinicById(long)}
     */
    @Test
    void testDeleteClinicById() throws EmptyDataAccessException {
        doNothing().when(clinicDao).deleteById((Long) any());
        doNothing().when(successResponse).setMessage((String) any());
        diseaseService.deleteClinicById(123L);
        verify(clinicDao).deleteById((Long) any());
        verify(successResponse).setMessage((String) any());
    }

    /**
     * Method under test: {@link DiseaseService#updateClinicDetails(Clinic)}
     * @throws Throwable 
     */
    @Test
    void testUpdateClinicDetails() throws Throwable {
        Clinic clinic = new Clinic();
        clinic.setAddress("42 Main St");
        clinic.setClinicId(123L);
        clinic.setClinicName("Clinic Name");
        clinic.setContactUs(1L);
        clinic.setDays("Days");
        clinic.setDoctor(new ArrayList<>());
        clinic.setEndTime("End Time");
        clinic.setStartTime("Start Time");
        Optional<Clinic> ofResult = Optional.of(clinic);

        Clinic clinic1 = new Clinic();
        clinic1.setAddress("42 Main St");
        clinic1.setClinicId(123L);
        clinic1.setClinicName("Clinic Name");
        clinic1.setContactUs(1L);
        clinic1.setDays("Days");
        clinic1.setDoctor(new ArrayList<>());
        clinic1.setEndTime("End Time");
        clinic1.setStartTime("Start Time");
        when(clinicDao.save((Clinic) any())).thenReturn(clinic1);
        when(clinicDao.findById((Long) any())).thenReturn(ofResult);

        Clinic clinic2 = new Clinic();
        clinic2.setAddress("42 Main St");
        clinic2.setClinicId(123L);
        clinic2.setClinicName("Clinic Name");
        clinic2.setContactUs(1L);
        clinic2.setDays("Days");
        clinic2.setDoctor(new ArrayList<>());
        clinic2.setEndTime("End Time");
        clinic2.setStartTime("Start Time");
        assertEquals("Clinic is updated successfully", diseaseService.updateClinicDetails(clinic2));
        verify(clinicDao).save((Clinic) any());
        verify(clinicDao).findById((Long) any());
        verify(successResponse).setMessage((String) any());

    }

    /**
     * Method under test: {@link DiseaseService#updateClinicDetails(Clinic)}
     * @throws Throwable 
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testUpdateClinicDetails2() throws Throwable {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException
        //       at com.cts.userdetails.service.DiseaseService.updateClinicDetails(DiseaseService.java:158)
        //   In order to prevent updateClinicDetails(Clinic)
        //   from throwing NullPointerException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   updateClinicDetails(Clinic).
        //   See https://diff.blue/R013 to resolve this issue.

        Clinic clinic = new Clinic();
        clinic.setAddress("42 Main St");
        clinic.setClinicId(123L);
        clinic.setClinicName("Clinic Name");
        clinic.setContactUs(1L);
        clinic.setDays("Days");
        clinic.setDoctor(new ArrayList<>());
        clinic.setEndTime("End Time");
        clinic.setStartTime("Start Time");
        when(clinicDao.save((Clinic) any())).thenReturn(clinic);
        when(clinicDao.findById((Long) any())).thenReturn(null);

        Clinic clinic1 = new Clinic();
        clinic1.setAddress("42 Main St");
        clinic1.setClinicId(123L);
        clinic1.setClinicName("Clinic Name");
        clinic1.setContactUs(1L);
        clinic1.setDays("Days");
        clinic1.setDoctor(new ArrayList<>());
        clinic1.setEndTime("End Time");
        clinic1.setStartTime("Start Time");
        diseaseService.updateClinicDetails(clinic1);
        verify(successResponse).setMessage((String) any());

    }
    @Test
    void testDeleteClinicDoctorById() throws EmptyDataAccessException {
        doNothing().when(doctorRepo).deleteById((Long) any());
        doNothing().when(successResponse).setMessage((String) any());
        diseaseService.deleteClinicDoctorById(123L);
        verify(doctorRepo).deleteById((Long) any());
        verify(successResponse).setMessage((String) any());
    }
}

