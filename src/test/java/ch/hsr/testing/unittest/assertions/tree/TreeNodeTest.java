package ch.hsr.testing.unittest.assertions.tree;

import ch.hsr.testing.unittest.assertions.tree.TreeNode;
import org.hamcrest.*;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

public class TreeNodeTest {


    /**
     * Without Custom Matcher: All properties have to be checked one by one
     */
    @Test
    public void compareUsingStandardMatchers() {
        TreeNode actual = createTree1();
        TreeNode expected = createTree2();

        // Assert that those two trees are equal.
        MatcherAssert.assertThat(actual.getValue(), is(expected.getValue()));
        MatcherAssert.assertThat(actual.getLeft().getValue(), is(expected.getLeft().getValue()));
        MatcherAssert.assertThat(actual.getRight().getValue(), is(expected.getRight().getValue()));

        // and so on...
    }

    /**
     * Using Custom Matcher
     */
    @Test
    public void compareUsingCustomMatcher() {
        TreeNode actual = createTree1();
        TreeNode expected = createTree2();
        MatcherAssert.assertThat(actual, not(TreeNodeMatcher.isEqualToTreeNode(expected)));
    }


    private TreeNode createTree1() {
        TreeNode leaf1 = new TreeNode(13);
        TreeNode leaf2 = new TreeNode(42);
        TreeNode leaf3 = new TreeNode(87);
        TreeNode inner1 = new TreeNode(leaf1, leaf2, 27);
        TreeNode inner2 = new TreeNode(leaf3, null, 90);
        TreeNode root = new TreeNode(inner1, inner2, 60);
        return root;
    }

    private TreeNode createTree2() {
        TreeNode leaf1 = new TreeNode(13);
        TreeNode leaf2 = new TreeNode(42);
        TreeNode leaf3 = new TreeNode(87);
        TreeNode leaf4 = new TreeNode(92);
        TreeNode inner1 = new TreeNode(leaf1, leaf2, 27);
        TreeNode inner2 = new TreeNode(leaf3, leaf4, 90);
        TreeNode root = new TreeNode(inner1, inner2, 60);
        return root;
    }

    public static class TreeNodeMatcher
            extends TypeSafeMatcher<TreeNode> {

        private final TreeNode expected;

        TreeNodeMatcher(TreeNode expected) {
            this.expected = expected;
        }

        @Override
        protected boolean matchesSafely(TreeNode actual) {
            return isEqualRecursive(actual, expected);
        }

        private boolean isEqualRecursive(TreeNode t1, TreeNode t2) {
            boolean valueIsEqual = t1.getValue() == t2.getValue();
            boolean leftIsEqual = (t1.getLeft() == null) == (t2.getLeft() == null);
            boolean rightIsEqual = (t1.getRight() == null) == (t2.getRight() == null);

            if (t1.getLeft() != null && t2.getLeft() != null) {
                leftIsEqual = isEqualRecursive(t1.getLeft(), t2.getLeft());
            }
            if (t1.getRight() != null && t2.getRight() != null) {
                rightIsEqual = isEqualRecursive(t1.getRight(), t2.getRight());
            }

            return valueIsEqual && leftIsEqual && rightIsEqual;
        }

        @Override
        public void describeTo(Description description) {
            description.appendText("<" + expected.toString() + ">");
        }

        @Factory
        static Matcher<TreeNode> isEqualToTreeNode(TreeNode expected) {
            return new TreeNodeMatcher(expected);
        }

    }


}