package ch.hsr.testing.unittest.assertions;

import org.hamcrest.*;
import org.junit.jupiter.api.Test;

import static ch.hsr.testing.unittest.assertions.EvenMatcher.isEven;

public class EvenTest {

    @Test
    public void evenNumber() {
        MatcherAssert.assertThat(2, isEven());
        MatcherAssert.assertThat(3, isEven());
    }
}

class EvenMatcher extends TypeSafeDiagnosingMatcher<Integer> {

    @Override
    public void describeTo(Description description) {
        description.appendText("An even number");
    }

    @Override
    protected boolean matchesSafely(Integer item, Description mismatchDescription) {
        mismatchDescription.appendText(" was ").appendValue(item).appendText(" which is an odd number!");
        return item % 2 == 0;
    }

    @Factory
    public static EvenMatcher isEven(){
        return new EvenMatcher();
    }
}