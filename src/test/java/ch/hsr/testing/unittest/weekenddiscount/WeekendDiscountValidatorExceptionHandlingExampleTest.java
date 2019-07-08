package ch.hsr.testing.unittest.weekenddiscount;
import ch.hsr.testing.unittest.util.DateFactory;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class WeekendDiscountValidatorExceptionHandlingExampleTest {

    WeekendDiscountValidator weekendDiscountValidator = new WeekendDiscountValidator();

    @Test
    void isInWeekend() throws ValidatorNotYetInitializedException {
        weekendDiscountValidator.initializeWithWeekendNumber(4);
        boolean authorizedForDiscount = weekendDiscountValidator.isAuthorizedForDiscount(DateFactory.createDate(2019, 6, 23, 0, 0, 0));
        Assertions.assertThat(authorizedForDiscount).isTrue();
    }

    @Tag("negative")
    @Test
    void throwsExceptionIfNotInitialized() {
        org.junit.jupiter.api.Assertions.assertThrows(
                ValidatorNotYetInitializedException.class,
                () -> weekendDiscountValidator.isAuthorizedForDiscount(DateFactory.createDate(2019, 1, 1, 0, 0, 0)));

    }


    @Tag("negative")
    @Test
    void throwsExceptionMessageIsCorrectIfNotInitialized() {

        Throwable t = null;

        try {
            weekendDiscountValidator.isAuthorizedForDiscount(DateFactory.createDate(2019, 1, 1, 0, 0, 0));
        } catch (ValidatorNotYetInitializedException e) {
            t = e;
        }

        Assertions.assertThat(t).isNotNull();
        Assertions.assertThat(t.getLocalizedMessage()).contains("not initialized");

    }

}