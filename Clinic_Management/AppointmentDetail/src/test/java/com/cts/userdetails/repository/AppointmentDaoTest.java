package com.cts.userdetails.repository;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

class AppointmentDaoTest {
    /**
     * Method under test: {@link AppointmentDao#findByPatientId(long)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testFindByPatientId() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException
        //   In order to prevent findByPatientId(long)
        //   from throwing NullPointerException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   findByPatientId(long).
        //   See https://diff.blue/R013 to resolve this issue.

        AppointmentDao appointmentDao = null;
        appointmentDao.findByPatientId(1L);
    }
}

