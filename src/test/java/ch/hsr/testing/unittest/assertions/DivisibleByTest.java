package ch.hsr.testing.unittest.assertions;

import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.hamcrest.MatcherAssert;
import org.hamcrest.TypeSafeDiagnosingMatcher;
import org.junit.jupiter.api.Test;

import static ch.hsr.testing.unittest.assertions.DivisibleByMatcher.isDivisibleBy;
import static ch.hsr.testing.unittest.assertions.EvenMatcher.isEven;

public class DivisibleByTest {

    @Test
    public void evenNumber() {
        MatcherAssert.assertThat(16, isDivisibleBy(4));
        MatcherAssert.assertThat(17, isDivisibleBy(4));
    }
}

class DivisibleByMatcher extends TypeSafeDiagnosingMatcher<Integer> {

    private int by;

    public DivisibleByMatcher(int by) {
        this.by = by;
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("An number divisible by ").appendValue(by);
    }

    @Override
    protected boolean matchesSafely(Integer item, Description mismatchDescription) {
        mismatchDescription
                .appendText(" was ")
                .appendValue(item)
                .appendText(" which is not divisible by ")
                .appendValue(by)
                .appendText("!");
        return item % by == 0;
    }

    @Factory
    public static DivisibleByMatcher isDivisibleBy(int by){
        return new DivisibleByMatcher(by);
    }
}