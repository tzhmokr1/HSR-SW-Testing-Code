package ch.hsr.testing.unittest.helloworld;

import org.junit.jupiter.api.*;

public class JunitHelloWorld {

    public JunitHelloWorld() {
        System.out.println("Constructor");
    }

    @BeforeAll
    public static void beforeAll() {
        System.out.println("beforeAll");
    }

    @BeforeEach
    public void beforeEach(){
        System.out.println("beforeEach");
    }

    @AfterAll
    public static void afterAll(){
        System.out.println("afterAll");
    }

    @AfterEach
    public void afterEach(){
        System.out.println("afterEach");
    }

    @Test
    public void test1() {
        System.out.println("test1");
    }

    @Test
    public void test2() {
        System.out.println("test1");
    }

}
