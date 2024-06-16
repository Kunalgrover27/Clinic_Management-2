package com.cts.userdetails.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.cts.userdetails.exception.EmptyDataAccessException;
import com.cts.userdetails.model.Login;
import com.cts.userdetails.model.Patient;
import com.cts.userdetails.model.PatientDto;
import com.cts.userdetails.repository.PatientDao;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import java.io.UnsupportedEncodingException;
import java.util.Optional;

import org.junit.jupiter.api.Disabled;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {PatientService.class})
@ExtendWith(SpringExtension.class)
class PatientServiceTest {
    @MockBean
    private PatientDao patientDao;

    @Autowired
    private PatientService patientService;

    /**
     * Method under test: {@link PatientService#registerPatient(PatientDto)}
     */
    @Test
    void testRegisterPatient() throws EmptyDataAccessException, UnsupportedEncodingException {
        Patient patient = new Patient();
        patient.setConfirmPassword("iloveyou");
        patient.setDob("Dob");
        patient.setEmail("jane.doe@example.org");
        patient.setFirstName("Jane");
        patient.setGender("Gender");
        patient.setId(123L);
        patient.setImage("AAAAAAAA".getBytes("UTF-8"));
        patient.setLastName("Doe");
        patient.setPassword("iloveyou");
        patient.setUserName("janedoe");
        when(patientDao.save((Patient) any())).thenReturn(patient);
        assertEquals("Patient is registered successfully", patientService.registerPatient(new PatientDto()));
        verify(patientDao).save((Patient) any());
    }

    /**
     * Method under test: {@link PatientService#registerPatient(PatientDto)}
     */
    @Test
    void testRegisterPatient2() throws EmptyDataAccessException, UnsupportedEncodingException {
        Patient patient = new Patient();
        patient.setConfirmPassword("iloveyou");
        patient.setDob("Dob");
        patient.setEmail("jane.doe@example.org");
        patient.setFirstName("Jane");
        patient.setGender("Gender");
        patient.setId(123L);
        patient.setImage("AAAAAAAA".getBytes("UTF-8"));
        patient.setLastName("Doe");
        patient.setPassword("iloveyou");
        patient.setUserName("janedoe");
        when(patientDao.save((Patient) any())).thenReturn(patient);
        assertThrows(EmptyDataAccessException.class, () -> patientService.registerPatient(null));
    }

    /**
     * Method under test: {@link PatientService#registerPatient(PatientDto)}
     */
    @Test
    void testRegisterPatient3() throws EmptyDataAccessException, IOException {
        Patient patient = new Patient();
        patient.setConfirmPassword("iloveyou");
        patient.setDob("Dob");
        patient.setEmail("jane.doe@example.org");
        patient.setFirstName("Jane");
        patient.setGender("Gender");
        patient.setId(123L);
        patient.setImage("AAAAAAAA".getBytes("UTF-8"));
        patient.setLastName("Doe");
        patient.setPassword("iloveyou");
        patient.setUserName("janedoe");
        when(patientDao.save((Patient) any())).thenReturn(patient);
        assertEquals("Patient is registered successfully",
                patientService.registerPatient(
                        new PatientDto(123L, "Jane", "Doe", "janedoe", "iloveyou", "iloveyou", "jane.doe@example.org",
                                "Patient is registered successfully", "Patient is registered successfully", new MockMultipartFile(
                                "Patient is registered successfully", new ByteArrayInputStream("AAAAAAAA".getBytes("UTF-8"))))));
        verify(patientDao).save((Patient) any());
    }

    /**
     * Method under test: {@link PatientService#loadUserByName(String)}
     */
    @Test
    void testLoadUserByName() throws UnsupportedEncodingException {
        Patient patient = new Patient();
        patient.setConfirmPassword("iloveyou");
        patient.setDob("Dob");
        patient.setEmail("jane.doe@example.org");
        patient.setFirstName("Jane");
        patient.setGender("Gender");
        patient.setId(123L);
        patient.setImage("AAAAAAAA".getBytes("UTF-8"));
        patient.setLastName("Doe");
        patient.setPassword("iloveyou");
        patient.setUserName("janedoe");
        when(patientDao.findByEmail((String) any())).thenReturn(patient);
        Login actualLoadUserByNameResult = patientService.loadUserByName("User");
        assertEquals("iloveyou", actualLoadUserByNameResult.getPassword());
        assertEquals("jane.doe@example.org", actualLoadUserByNameResult.getUserName());
        verify(patientDao).findByEmail((String) any());
    }

    /**
     * Method under test: {@link PatientService#getPatientDetails(String)}
     */
    @Test
    void testGetPatientDetails() throws EmptyDataAccessException, UnsupportedEncodingException {
        Patient patient = new Patient();
        patient.setConfirmPassword("iloveyou");
        patient.setDob("Dob");
        patient.setEmail("jane.doe@example.org");
        patient.setFirstName("Jane");
        patient.setGender("Gender");
        patient.setId(123L);
        patient.setImage("AAAAAAAA".getBytes("UTF-8"));
        patient.setLastName("Doe");
        patient.setPassword("iloveyou");
        patient.setUserName("janedoe");
        when(patientDao.findByEmail((String) any())).thenReturn(patient);
        assertSame(patient, patientService.getPatientDetails("jane.doe@example.org"));
        verify(patientDao).findByEmail((String) any());
    }

    /**
     * Method under test: {@link PatientService#updatePatientDetails(PatientDto)}
     */
    @Test
    void testUpdatePatientDetails() throws EmptyDataAccessException, UnsupportedEncodingException {
        Patient patient = new Patient();
        patient.setConfirmPassword("iloveyou");
        patient.setDob("Dob");
        patient.setEmail("jane.doe@example.org");
        patient.setFirstName("Jane");
        patient.setGender("Gender");
        patient.setId(123L);
        patient.setImage("AAAAAAAA".getBytes("UTF-8"));
        patient.setLastName("Doe");
        patient.setPassword("iloveyou");
        patient.setUserName("janedoe");
        Optional<Patient> ofResult = Optional.of(patient);

        Patient patient1 = new Patient();
        patient1.setConfirmPassword("iloveyou");
        patient1.setDob("Dob");
        patient1.setEmail("jane.doe@example.org");
        patient1.setFirstName("Jane");
        patient1.setGender("Gender");
        patient1.setId(123L);
        patient1.setImage("AAAAAAAA".getBytes("UTF-8"));
        patient1.setLastName("Doe");
        patient1.setPassword("iloveyou");
        patient1.setUserName("janedoe");
        when(patientDao.save((Patient) any())).thenReturn(patient1);
        when(patientDao.findById((Long) any())).thenReturn(ofResult);
        assertEquals("Patient is updated successfully", patientService.updatePatientDetails(new PatientDto()));
        verify(patientDao).save((Patient) any());
        verify(patientDao).findById((Long) any());
    }

    /**
     * Method under test: {@link PatientService#updatePatientDetails(PatientDto)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testUpdatePatientDetails2() throws EmptyDataAccessException, UnsupportedEncodingException {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException
        //       at com.cts.userdetails.service.PatientService.updatePatientDetails(PatientService.java:69)
        //   In order to prevent updatePatientDetails(PatientDto)
        //   from throwing NullPointerException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   updatePatientDetails(PatientDto).
        //   See https://diff.blue/R013 to resolve this issue.

        Patient patient = new Patient();
        patient.setConfirmPassword("iloveyou");
        patient.setDob("Dob");
        patient.setEmail("jane.doe@example.org");
        patient.setFirstName("Jane");
        patient.setGender("Gender");
        patient.setId(123L);
        patient.setImage("AAAAAAAA".getBytes("UTF-8"));
        patient.setLastName("Doe");
        patient.setPassword("iloveyou");
        patient.setUserName("janedoe");
        when(patientDao.save((Patient) any())).thenReturn(patient);
        when(patientDao.findById((Long) any())).thenReturn(null);
        patientService.updatePatientDetails(new PatientDto());
    }

    /**
     * Method under test: {@link PatientService#updatePatientDetails(PatientDto)}
     */
    @Test
    void testUpdatePatientDetails3() throws EmptyDataAccessException, UnsupportedEncodingException {
        Patient patient = new Patient();
        patient.setConfirmPassword("iloveyou");
        patient.setDob("Dob");
        patient.setEmail("jane.doe@example.org");
        patient.setFirstName("Jane");
        patient.setGender("Gender");
        patient.setId(123L);
        patient.setImage("AAAAAAAA".getBytes("UTF-8"));
        patient.setLastName("Doe");
        patient.setPassword("iloveyou");
        patient.setUserName("janedoe");
        Optional<Patient> ofResult = Optional.of(patient);

        Patient patient1 = new Patient();
        patient1.setConfirmPassword("iloveyou");
        patient1.setDob("Dob");
        patient1.setEmail("jane.doe@example.org");
        patient1.setFirstName("Jane");
        patient1.setGender("Gender");
        patient1.setId(123L);
        patient1.setImage("AAAAAAAA".getBytes("UTF-8"));
        patient1.setLastName("Doe");
        patient1.setPassword("iloveyou");
        patient1.setUserName("janedoe");
        when(patientDao.save((Patient) any())).thenReturn(patient1);
        when(patientDao.findById((Long) any())).thenReturn(ofResult);
        assertThrows(EmptyDataAccessException.class, () -> patientService.updatePatientDetails(null));
    }

    /**
     * Method under test: {@link PatientService#updatePatientDetails(PatientDto)}
     */
    @Test
    void testUpdatePatientDetails4() throws EmptyDataAccessException, IOException {
        Patient patient = new Patient();
        patient.setConfirmPassword("iloveyou");
        patient.setDob("Dob");
        patient.setEmail("jane.doe@example.org");
        patient.setFirstName("Jane");
        patient.setGender("Gender");
        patient.setId(123L);
        patient.setImage("AAAAAAAA".getBytes("UTF-8"));
        patient.setLastName("Doe");
        patient.setPassword("iloveyou");
        patient.setUserName("janedoe");
        Optional<Patient> ofResult = Optional.of(patient);

        Patient patient1 = new Patient();
        patient1.setConfirmPassword("iloveyou");
        patient1.setDob("Dob");
        patient1.setEmail("jane.doe@example.org");
        patient1.setFirstName("Jane");
        patient1.setGender("Gender");
        patient1.setId(123L);
        patient1.setImage("AAAAAAAA".getBytes("UTF-8"));
        patient1.setLastName("Doe");
        patient1.setPassword("iloveyou");
        patient1.setUserName("janedoe");
        when(patientDao.save((Patient) any())).thenReturn(patient1);
        when(patientDao.findById((Long) any())).thenReturn(ofResult);
        assertEquals("Patient is updated successfully", patientService.updatePatientDetails(new PatientDto(123L, "Jane",
                "Doe", "janedoe", "iloveyou", "iloveyou", "jane.doe@example.org", "User data is not Found",
                "User data is not Found",
                new MockMultipartFile("User data is not Found", new ByteArrayInputStream("AAAAAAAA".getBytes("UTF-8"))))));
        verify(patientDao).save((Patient) any());
        verify(patientDao).findById((Long) any());
    }
}

