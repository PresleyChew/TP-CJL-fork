package seedu.address.model.appointment;

import static seedu.address.logic.commands.CommandTestUtil.INVALID_APPOINTMENT_DATE_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_APPOINTMENT_DATE_NONEXISTANT;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_APPOINTMENT_NAME;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_APPOINTMENT_TIMEPERIOD_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_APPOINTMENT_TIMEPERIOD_ORDER;
import static seedu.address.logic.commands.CommandTestUtil.VALID_APPOINTMENT_DATE_DENTAL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_APPOINTMENT_NAME_DENTAL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_APPOINTMENT_TIMEPERIOD_DENTAL;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;

class AppointmentTest {

    @Test
    public void constructorNullThrowsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Appointment(null, VALID_APPOINTMENT_DATE_DENTAL,
                                                                       VALID_APPOINTMENT_TIMEPERIOD_DENTAL));
        assertThrows(NullPointerException.class, () -> new Appointment(VALID_APPOINTMENT_NAME_DENTAL, null,
                                                                       VALID_APPOINTMENT_TIMEPERIOD_DENTAL));
        assertThrows(NullPointerException.class, () -> new Appointment(VALID_APPOINTMENT_NAME_DENTAL,
                                                                       VALID_APPOINTMENT_DATE_DENTAL, null));
    }

    @Test
    public void constructorInvalidApptNameThrowsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Appointment(INVALID_APPOINTMENT_NAME,
                                                                           VALID_APPOINTMENT_DATE_DENTAL,
                                                                           VALID_APPOINTMENT_TIMEPERIOD_DENTAL));
    }

    @Test
    public void constructorInvalidApptDateThrowsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Appointment(VALID_APPOINTMENT_NAME_DENTAL,
                                                                           INVALID_APPOINTMENT_DATE_FORMAT,
                                                                           VALID_APPOINTMENT_TIMEPERIOD_DENTAL));
        assertThrows(IllegalArgumentException.class, () -> new Appointment(VALID_APPOINTMENT_NAME_DENTAL,
                                                                           INVALID_APPOINTMENT_DATE_NONEXISTANT,
                                                                           VALID_APPOINTMENT_TIMEPERIOD_DENTAL));
    }

    @Test
    public void constructorInvalidApptTimePeriodThrowsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Appointment(VALID_APPOINTMENT_NAME_DENTAL,
                                                                           VALID_APPOINTMENT_DATE_DENTAL,
                                                                           " "));
        assertThrows(IllegalArgumentException.class, () -> new Appointment(VALID_APPOINTMENT_NAME_DENTAL,
                                                                           VALID_APPOINTMENT_DATE_DENTAL,
                                                                           "5512"));
        assertThrows(IllegalArgumentException.class, () -> new Appointment(VALID_APPOINTMENT_NAME_DENTAL,
                                                                           VALID_APPOINTMENT_DATE_DENTAL,
                                                                           "asd1200-1223asd"));
        assertThrows(IllegalArgumentException.class, () -> new Appointment(VALID_APPOINTMENT_NAME_DENTAL,
                                                                           VALID_APPOINTMENT_DATE_DENTAL,
                                                                           INVALID_APPOINTMENT_TIMEPERIOD_FORMAT));
        assertThrows(IllegalArgumentException.class, () -> new Appointment(VALID_APPOINTMENT_NAME_DENTAL,
                                                                           VALID_APPOINTMENT_DATE_DENTAL,
                                                                           INVALID_APPOINTMENT_TIMEPERIOD_ORDER));
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    @Test
    public void isValidAppointmentName() {
        // null appointment name
        assertThrows(NullPointerException.class, () -> Appointment.isValidAppointmentName(null));
    }

    @Test
    public void checkIsTimePeriodValid() {
        // null appointment name
        assertThrows(NullPointerException.class, () -> Appointment.checkIsTimePeriodValid(null));
    }

    @Test
    public void testCompareTo() throws IllegalValueException {
        Appointment a = new Appointment(VALID_APPOINTMENT_NAME_DENTAL, "2024-10-25", "1000-1235");
        Appointment sameDateBefore = new Appointment(VALID_APPOINTMENT_NAME_DENTAL, "2024-10-25", "0900-1235");
        Appointment sameDateTime = new Appointment(VALID_APPOINTMENT_NAME_DENTAL, "2024-10-25", "1000-1235");
        Appointment sameDateAfter = new Appointment(VALID_APPOINTMENT_NAME_DENTAL, "2024-10-25", "1100-1235");
        Appointment earlyDateBefore = new Appointment(VALID_APPOINTMENT_NAME_DENTAL, "2024-10-20", "0900-1235");
        Appointment earlyDateSame = new Appointment(VALID_APPOINTMENT_NAME_DENTAL, "2024-10-20", "1000-1235");
        Appointment earlyDateAfter = new Appointment(VALID_APPOINTMENT_NAME_DENTAL, "2024-10-20", "1100-1235");
        Appointment laterDateBefore = new Appointment(VALID_APPOINTMENT_NAME_DENTAL, "2024-11-25", "0900-1235");
        Appointment laterDateSame = new Appointment(VALID_APPOINTMENT_NAME_DENTAL, "2024-11-25", "1000-1235");
        Appointment laterDateAfter = new Appointment(VALID_APPOINTMENT_NAME_DENTAL, "2024-11-25", "1100-1235");

        Assertions.assertTrue(0 < a.compareTo(sameDateBefore));
        Assertions.assertEquals(0, a.compareTo(sameDateTime));
        Assertions.assertTrue(0 > a.compareTo(sameDateAfter));
        Assertions.assertTrue(0 < a.compareTo(earlyDateBefore));
        Assertions.assertTrue(0 < a.compareTo(earlyDateSame));
        Assertions.assertTrue(0 < a.compareTo(earlyDateAfter));
        Assertions.assertTrue(0 > a.compareTo(laterDateBefore));
        Assertions.assertTrue(0 > a.compareTo(laterDateSame));
        Assertions.assertTrue(0 > a.compareTo(laterDateAfter));
    }

    @Test
    public void testToString() throws IllegalValueException {
        Appointment a = new Appointment(VALID_APPOINTMENT_NAME_DENTAL, VALID_APPOINTMENT_DATE_DENTAL,
                                        VALID_APPOINTMENT_TIMEPERIOD_DENTAL);
        String expected = VALID_APPOINTMENT_NAME_DENTAL + " [ " + VALID_APPOINTMENT_DATE_DENTAL + " @ "
                          + VALID_APPOINTMENT_TIMEPERIOD_DENTAL + " ]";
        Assertions.assertEquals(expected, a.toString());
    }

    @SuppressWarnings({
        "ConstantValue",
        "EqualsWithItself",
        "SimplifiableAssertion"})
    @Test
    public void testEquals() throws IllegalValueException {
        Appointment a = new Appointment(VALID_APPOINTMENT_NAME_DENTAL, "2024-10-25", "1000-1235");
        Appointment sameDateBefore = new Appointment(VALID_APPOINTMENT_NAME_DENTAL, "2024-10-25", "0900-1235");
        Appointment sameDateTime = new Appointment(VALID_APPOINTMENT_NAME_DENTAL, "2024-10-25", "1000-1235");
        Appointment sameDateAfter = new Appointment(VALID_APPOINTMENT_NAME_DENTAL, "2024-10-25", "1100-1235");
        Appointment earlyDateBefore = new Appointment(VALID_APPOINTMENT_NAME_DENTAL, "2024-10-20", "0900-1235");
        Appointment earlyDateSame = new Appointment(VALID_APPOINTMENT_NAME_DENTAL, "2024-10-20", "1000-1235");
        Appointment earlyDateAfter = new Appointment(VALID_APPOINTMENT_NAME_DENTAL, "2024-10-20", "1100-1235");
        Appointment laterDateBefore = new Appointment(VALID_APPOINTMENT_NAME_DENTAL, "2024-11-25", "0900-1235");
        Appointment laterDateSame = new Appointment(VALID_APPOINTMENT_NAME_DENTAL, "2024-11-25", "1000-1235");
        Appointment laterDateAfter = new Appointment(VALID_APPOINTMENT_NAME_DENTAL, "2024-11-25", "1100-1235");

        Assertions.assertFalse(a.equals(null));
        Assertions.assertTrue(a.equals(a));
        Assertions.assertFalse(a.equals(sameDateBefore));
        Assertions.assertTrue(a.equals(sameDateTime));
        Assertions.assertFalse(a.equals(sameDateAfter));
        Assertions.assertFalse(a.equals(earlyDateBefore));
        Assertions.assertFalse(a.equals(earlyDateSame));
        Assertions.assertFalse(a.equals(earlyDateAfter));
        Assertions.assertFalse(a.equals(laterDateBefore));
        Assertions.assertFalse(a.equals(laterDateSame));
        Assertions.assertFalse(a.equals(laterDateAfter));
    }

    @Test
    public void testIsClashing() throws IllegalValueException {
        Appointment a = new Appointment(VALID_APPOINTMENT_NAME_DENTAL, "2024-10-25", "1000-1235");
        Assertions.assertFalse(a.isClashing("2024-10-25", "0900-0935")); // "sameDateBefore"
        Assertions.assertTrue(a.isClashing("2024-10-25", "0900-1235")); // "sameDateBefore"
        Assertions.assertTrue(a.isClashing("2024-10-25", "0845-1300")); // "sameDateTimeEnclose"
        Assertions.assertTrue(a.isClashing("2024-10-25", "1000-1235")); // "sameDateTimeInside"
        Assertions.assertTrue(a.isClashing("2024-10-25", "1045-1200")); // "sameDateTime"
        Assertions.assertTrue(a.isClashing("2024-10-25", "1100-1235")); // "sameDateAfter"
        Assertions.assertFalse(a.isClashing("2024-10-25", "1235-1345")); // "sameDateAfter"

        Assertions.assertFalse(a.isClashing("2024-09-25", "0900-0935")); // "earlierDateBefore"
        Assertions.assertFalse(a.isClashing("2024-09-25", "0900-1235")); // "earlierDateBefore"
        Assertions.assertFalse(a.isClashing("2024-09-25", "0845-1300")); // "earlierDateTimeEnclose"
        Assertions.assertFalse(a.isClashing("2024-09-25", "1000-1235")); // "earlierDateTimeInside"
        Assertions.assertFalse(a.isClashing("2024-09-25", "1045-1200")); // "earlierDateTime"
        Assertions.assertFalse(a.isClashing("2024-09-25", "1100-1235")); // "earlierDateAfter"
        Assertions.assertFalse(a.isClashing("2024-09-25", "1235-1345")); // "earlierDateAfter"

        Assertions.assertFalse(a.isClashing("2024-11-25", "0900-0935")); // "laterDateBefore"
        Assertions.assertFalse(a.isClashing("2024-11-25", "0900-1235")); // "laterDateBefore"
        Assertions.assertFalse(a.isClashing("2024-11-25", "0845-1300")); // "laterDateTimeEnclose"
        Assertions.assertFalse(a.isClashing("2024-11-25", "1000-1235")); // "laterDateTimeInside"
        Assertions.assertFalse(a.isClashing("2024-11-25", "1045-1200")); // "laterDateTime"
        Assertions.assertFalse(a.isClashing("2024-11-25", "1100-1235")); // "laterDateAfter"
        Assertions.assertFalse(a.isClashing("2024-11-25", "1235-1345")); // "laterDateAfter"
    }
}