package me.clemado1.infllearnthejavatest;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.*;

import java.time.Duration;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumingThat;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class StudyTest {

    @FastTest
    @EnabledOnOs({OS.LINUX, OS.WINDOWS})
    @EnabledOnJre({JRE.JAVA_11, JRE.JAVA_13, JRE.JAVA_14})
    @DisplayName("스터디 만들기\uD83D\uDC4D")
    void create_new_study() {
        // assumeTrue("LOCAL".equalsIgnoreCase(System.getenv("TEST_ENV")));
        assumingThat("LOCAL".equalsIgnoreCase(System.getenv("TEST_ENV")), () -> System.out.println("local"));

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Study(-10));
        assertEquals("스터디 최대 참석 가능 인원은 0보다 커야한다.", exception.getMessage());
        assertTimeout(Duration.ofMillis(100), () -> {
            new Study(10);
            // Thread.sleep(300);
        });
        // TODO: ThreadLocal와 assertTimeoutPreemptively를 같이 사용할 시 예상하지 못한 결과가 나올 수 있음.
    }

    @SlowTest
    @EnabledIfEnvironmentVariable(named = "TEST_ENV", matches = "LOCAL")
    void create_new_study_again() {
        Study study = new Study(10);

        assertAll(
                () -> assertNotNull(study),
                () -> assertEquals(StudyStatus.DRAFT, study.getStudyStatus(), () -> "스터디를 처음 만들면 " + StudyStatus.DRAFT + " 상태이어야 한다."),
                () -> assertTrue(study.getLimit() > 0, "스터디 최대 참석 가능 인원은 0보다 커야한다.")
        );
    }

    @Test
    void studyTest(Optional<Study> opParam) {
        Study nullTdy = null;
        opParam.get();
        Optional<Study> studyOp = Optional.ofNullable(nullTdy);
        if (studyOp.isPresent()) {
            System.out.println("studyOp is present! - " + studyOp.get());
        } else {
            System.out.println("studyOp is empty! - " + studyOp);
        }
    }

    @BeforeAll
    static void beforeAll() {
        System.out.println("before all");
    }

    @AfterAll
    static void afterAll() {
        System.out.println("after all");
    }

    @BeforeEach
    void beforeEach() {
        System.out.println("before each");
    }

    @AfterEach
    void afterEach() {
        System.out.println("after each");
    }
}