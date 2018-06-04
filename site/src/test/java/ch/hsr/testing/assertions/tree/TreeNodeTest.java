package ch.hsr.testing.assertions.tree;

import org.hamcrest.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.is;

class TreeNodeTest {


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
        
        // TODO: Implement this
        Assertions.fail("Implement Testcase");
        // MatcherAssert.assertThat(actual, TreeNodeMatcher.isEqualToTreeNode(expected));
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

}