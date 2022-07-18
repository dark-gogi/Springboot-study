package im.back.springboot;

import org.junit.jupiter.api.*;

public class TestLifeCycle {

    @BeforeAll
    static void beforeAll(){
        System.out.println("## BeforeAll Annotation Invoked ##");
        System.out.println();
    }

    @AfterAll
    static void afterAll(){
        System.out.println("## AfterAll Annotation Invoked ##");
        System.out.println();
    }

    @BeforeEach
    void beforeEach(){
        System.out.println("## BeforeEach Annotation Invoked ##");
        System.out.println();
    }

    @AfterEach
    void afterEach(){
        System.out.println("## AfterEach Annotation Invoked ##");
        System.out.println();
    }

    @Test
    void test1(){
        System.out.println("## test1 start ##");
        System.out.println();
    }

    @Test
    @DisplayName(value = "Test Case 2")
    void test2(){
        System.out.println("## test2 start ##");
        System.out.println();
    }

    @Test
    @Disabled
    void test3(){
        System.out.println("## test3 start ##");
        System.out.println();
    }


}
