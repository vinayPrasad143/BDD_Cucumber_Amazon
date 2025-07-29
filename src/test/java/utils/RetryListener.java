package utils;

import org.testng.IAnnotationTransformer;
import org.testng.annotations.ITestAnnotation;

import java.lang.reflect.Method;

public class RetryListener implements IAnnotationTransformer {

    public void transform(ITestAnnotation annotation, Method testMethod) {

        if (annotation.getRetryAnalyzerClass() == null) {
            annotation.setRetryAnalyzer(RetryAnalyzer.class);
        }
    }
}
