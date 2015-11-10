package org.teamhad.qa.listeners;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;
import org.testng.internal.BaseTestMethod;
import org.testng.internal.TestResult;

import java.lang.reflect.Field;

/**
 * @author UJOSSJA
 * @date Jan 13, 2014
 */

public class TestNGMethodRenamer extends TestListenerAdapter {

    private static final Logger logger = LoggerFactory.getLogger(TestNGMethodRenamer.class);

    @Override
    public void onTestSuccess(ITestResult testResult) {
        logger.info("Test listner status: test PASSED!");
        setTestNameInXml(testResult);
        super.onTestSuccess(testResult);
    }

    @Override
    public void onTestSkipped(ITestResult testResult) {
        logger.warn("Test listner status: test SKIPPED!");
        setTestNameInXml(testResult);
        super.onTestSuccess(testResult);
    }

    @Override
    public void onTestFailure(ITestResult testResult) {
        logger.error("Test listner status: test FAILED!");
        setTestNameInXml(testResult);
        super.onTestSuccess(testResult);
    }

    private void setTestNameInXml(ITestResult tr) {
        try {
            Field method = TestResult.class.getDeclaredField("m_method");
            method.setAccessible(true);
            method.set(tr, tr.getMethod().clone());
            Field methodName = BaseTestMethod.class.getDeclaredField("m_methodName");
            methodName.setAccessible(true);
            if (tr.getParameters().length > 0) {
                methodName.set(tr.getMethod(), (Object) tr.getMethod().getMethodName().concat("(")
                        .concat(tr.getParameters()[0].toString()).concat(")"));
            }
        } catch (IllegalAccessException e) {
            logger.error("Illegal Access Exception renaming test method", e);
        } catch (NoSuchFieldException e) {
            logger.error("No Such Field Exception renaming test method", e);
        }
    }
}
