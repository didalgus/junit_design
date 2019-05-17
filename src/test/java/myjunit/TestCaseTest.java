package myjunit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/*
* JUnit 만들어보기
* https://jojoldu.tistory.com/231
*/
public class TestCaseTest extends TestCase {

    private static final Logger logger = LoggerFactory.getLogger(TestCaseTest.class);

    private static long base;

    public TestCaseTest(String testCaseName) {
        super(testCaseName);
    }

    @Override
    public void before () { base = 10;}

    public void runTest() {
        long sum = 10 + base;
        Assert.assertTrue(sum == 40);
    }

    public void runTestMinus() {
        long minus = 100 - base;
        Assert.assertTrue(minus == 90);
    }

    public static void main(String[] args) {

        TestSuite testSuite = new TestSuite();
        testSuite.addTestCase(new TestCaseTest("runTest"));
        testSuite.addTestCase(new TestCaseTest("runTestMinus"));

        TestResult testResult = new TestResult();
        testSuite.run(testResult);

        testResult.printCount();
    }
}