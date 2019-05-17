package myjunit;

import java.util.ArrayList;
import java.util.List;

public class TestSuite implements Test {

    private List<TestCase> testCases = new ArrayList<>();

    @Override
    public void run(TestResult result) {
        for (TestCase testCase : this.testCases) {
            testCase.run(result);
        }
    }

    public void addTestCase(TestCase testCase) {
        this.testCases.add(testCase);
    }

}
