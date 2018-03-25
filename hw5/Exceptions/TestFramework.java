package ru.otus.skuznets;

import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;
import ru.otus.skuznets.Annotations.After;
import ru.otus.skuznets.Annotations.Before;
import ru.otus.skuznets.Annotations.Test;
import ru.otus.skuznets.Exceptions.MoreOneAfterAnnotationException;
import ru.otus.skuznets.Exceptions.MoreOneBeforeAnnotationException;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Set;

import static ru.otus.skuznets.ReflectionHelper.callMethod;

public class TestFramework {
    public static void runTest(Class c1) throws Exception {
        ArrayList<Method> methodsWithTestAnnotations = new ArrayList<Method>();
        Method methodWithAfter = null;
        Method methodWithBefore = null;

        for (Method method: c1.getDeclaredMethods()) {
            Annotation[] annotations = method.getDeclaredAnnotations();
            for (Annotation annotation: annotations) {
                if (annotation instanceof After) {
                    if (methodWithAfter == null) {
                        methodWithAfter = method;
                    } else {
                        throw new MoreOneAfterAnnotationException();
                    }
                }
                if (annotation instanceof Before) {
                    if (methodWithBefore == null) {
                        methodWithBefore = method;
                    } else {
                        throw new MoreOneBeforeAnnotationException();
                    }
                }
                if (annotation instanceof Test) {
                    methodsWithTestAnnotations.add(method);
                }
            }
        }
        for (Method method: methodsWithTestAnnotations) {
            Object instance = ReflectionHelper.instantiate(c1);
            if (methodWithAfter != null) {
                callMethod(instance, methodWithAfter.getName());
                System.out.println("Call method with After Annotation");
            }
            if (methodWithBefore != null) {
                callMethod(instance, methodWithBefore.getName());
                System.out.println("Call method with Before Annotation");
            }
            callMethod(instance, method.getName());
            System.out.println("Call method with Test Annotation");
        }
    }

    public static void runTest(String c2) throws Exception {
        Reflections reflections = new Reflections(c2, new SubTypesScanner(false));
        Set<Class<?>> list = reflections.getSubTypesOf(Object.class);
        for (Class c3: list) {
            runTest(c3);
        };
    }
}
