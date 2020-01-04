package ch.hsr.testing.unittest.weekenddiscount;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

class WeekendDiscountTimeSlotValidatorExceptionHandlingExampleTest {

    private WeekendDiscountTimeSlotValidator weekendDiscountValidator = new WeekendDiscountTimeSlotValidator();

    @Tag("negative")
    @Test
    void throwsExceptionIfNotInitialized() {
        org.junit.jupiter.api.Assertions.assertThrows(
                IllegalWeekendNumberException.class,
                () -> weekendDiscountValidator.isAuthorizedForDiscount(LocalDateTime.of(2019, 1, 1, 0, 0, 0)));

    }

    @Tag("negative")
    @Test
    void throwsExceptionMessageIsCorrectIfNotInitialized() {

        Throwable t = null;

        try {
            weekendDiscountValidator.isAuthorizedForDiscount(LocalDateTime.of(2019, 1, 1, 0, 0, 0));
        } catch (IllegalWeekendNumberException e) {
            t = e;
        }

        Assertions.assertThat(t).isNotNull();
        Assertions.assertThat(t.getLocalizedMessage()).contains("has not been initialized");

    }
}