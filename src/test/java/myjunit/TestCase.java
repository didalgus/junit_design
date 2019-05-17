package myjunit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public abstract class TestCase implements Test {

    private static final Logger logger = LoggerFactory.getLogger(TestCaseTest.class);
    protected String testCaseName;

    public TestCase(String testCaseName)  {
        this.testCaseName = testCaseName;
    }

    public TestResult run() {
        TestResult testResult = createTestResult();
        run(testResult);

        return testResult;
    }

    public void run(TestResult testResult) {
        testResult.startTest();
        before();

        try {
            runTestCase();
        } catch (InvocationTargetException ite) {

            if(isAssertionFailed(ite)) {
                testResult.addFailure(this);
            } else {
                testResult.addError(this, ite);
            }

        } catch (Exception e) {
            testResult.addError(this, e);
        } finally {
            after();
        }

    }

    /*
    *
    * InvocationTargetException :
    * method invoke시 호출한 메소드 내에서 Exception이 발생했을때 해당 wrapping 해주는 Exception 클래스
    * isAssertionFailed 메소드에서 AssertionFailedError 인지 InvocationTargetException 인지 구별
    * */
    private boolean isAssertionFailed(InvocationTargetException ite) {
        /* 객체타입을 확인, 참조변수(객체) + instanceof + 타입(클래스명), 결과 boolean (true/false) */
        return ite.getTargetException() instanceof AssertionFailedError;
    }

    private TestResult createTestResult() {
        return new TestResult();
    }

    protected void before() {}

    private void runTestCase() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        logger.info("{} execute ", testCaseName);       // test case name
        Method method = this.getClass().getMethod(testCaseName, null);  // 동적으로 해당클래스의 메서드에 접
        method.invoke(this, null);   // Method 클래스의 메서드, 동적 호촐된 메서드의 결과 리턴

    }

    protected void after(){}

    public String getTestCaseName() {
        return "";
    }
}
