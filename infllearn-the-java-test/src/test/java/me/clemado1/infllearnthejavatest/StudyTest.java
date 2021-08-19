package me.clemado1.infllearnthejavatest;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.*;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.aggregator.AggregateWith;
import org.junit.jupiter.params.aggregator.ArgumentsAccessor;
import org.junit.jupiter.params.aggregator.ArgumentsAggregationException;
import org.junit.jupiter.params.aggregator.ArgumentsAggregator;
import org.junit.jupiter.params.converter.ArgumentConversionException;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.converter.SimpleArgumentConverter;
import org.junit.jupiter.params.provider.*;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumingThat;

// @ExtendWith(FindSlowTestExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class StudyTest {

    int value = 1;

    @RegisterExtension
    static FindSlowTestExtension findSlowTestExtension = new FindSlowTestExtension(1000L);

    @Order(1)
    @FastTest
    @EnabledOnOs({OS.LINUX, OS.WINDOWS})
    @EnabledOnJre({JRE.JAVA_11, JRE.JAVA_13, JRE.JAVA_14})
    @DisplayName("스터디 만들기 1")
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

    @Test
    void slow_test_again() throws InterruptedException {
        Thread.sleep(1005L);
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

    @Order(2)
    @DisplayName("스터디 만들기 2")
    @RepeatedTest(value = 10, name = "{displayName}, {currentRepetition}/{totalRepetitions}")
    void create_study(RepetitionInfo repetitionInfo) {
        System.out.println("test " + repetitionInfo.getCurrentRepetition() + "/" + repetitionInfo.getTotalRepetitions());
    }

    @DisplayName("스터디 만들기 3")
    @ParameterizedTest(name = "{index} {displayName} message={0}")
    @ValueSource(strings = {"1", "2", "3", "4"})
    @NullAndEmptySource
    void parameterizedTest(String message) {
        System.out.println(message);
    }

    @DisplayName("스터디 만들기 4")
    @ParameterizedTest(name = "{index} {displayName} message={0}")
    @ValueSource(ints = {1, 2, 3, 4})
    void parameterizedTest(@ConvertWith(StudyConverter.class) Study message) {
        System.out.println(message.getLimit());
    }

    @DisplayName("스터디 만들기 5")
    @ParameterizedTest(name = "{index} {displayName} message={0}")
    @CsvSource({"10, 'java study", "20, spring study"})
    void csvSourceTest(@AggregateWith(StudyAggregator.class) Study study) {
        System.out.println(study);
    }

    static class StudyConverter extends SimpleArgumentConverter {
        @Override
        protected Object convert(Object o, Class<?> aClass) throws ArgumentConversionException {
            assertEquals(Study.class, aClass, "Can only convert to Study");
            return new Study(Integer.parseInt(o.toString()));
        }
    }

    static class StudyAggregator implements ArgumentsAggregator {
        @Override
        public Object aggregateArguments(ArgumentsAccessor argumentsAccessor, ParameterContext parameterContext) throws ArgumentsAggregationException {
            return new Study(argumentsAccessor.getInteger(0), argumentsAccessor.getString(1));
        }
    }

    @BeforeAll
    void beforeAll() {
        System.out.println("before all");
    }

    @AfterAll
    void afterAll() {
        System.out.println("after all");
    }

    @BeforeEach
    void beforeEach() {
        System.out.println("before each - " + value++ + " / " + this);
    }

    @AfterEach
    void afterEach() {
        System.out.println("after each");
    }
}