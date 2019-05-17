package myjunit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;


public class TestResult {

    private static final Logger logger = LoggerFactory.getLogger(TestResult.class);

    private int runTestCount;
    private List<TestFailure> failures;
    private List<TestError> errors;

    public TestResult() {
        this.runTestCount = 0;
        this.failures = new ArrayList<>();
        this.errors = new ArrayList<>();
    }

    public synchronized void startTest() {
        this.runTestCount++;
    }

    public synchronized void addFailure(TestCase testCase) {
        this.failures.add(new TestFailure(testCase));
    }

    public synchronized void addError(TestCase testCase, Exception e) {
        this.errors.add(new TestError(testCase, e));
    }

    public void printCount() {
        logger.info("Total Test Count: {}", runTestCount);
        logger.info("Total Test Success Count: {}", runTestCount - failures.size() - errors.size());
        logger.info("Total Failure Count: {}", failures.size());
        logger.info("Total Error Count: {}", errors.size());
    }
}
