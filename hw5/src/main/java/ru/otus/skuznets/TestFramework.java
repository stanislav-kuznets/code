package ru.otus.skuznets;

import com.google.common.collect.ImmutableSet;
import com.google.common.reflect.ClassPath;
import ru.otus.skuznets.Annotations.After;
import ru.otus.skuznets.Annotations.Before;
import ru.otus.skuznets.Annotations.Test;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class TestFramework {

    static void start(Class test) throws Exception {
        startTests(test);

    }

    public static void startTests(Class c1) throws Exception {
        ArrayList<Method> methodsTest = new ArrayList<Method>();
        Method methodWithAfter = null;
        Method methodWithBefore = null;

        int afterCounter = 0;
        int beforeCounter = 0;
        int testCounter = 0;

        for (Method method : c1.getDeclaredMethods()) {
            if (ReflectionHelper.getAnnotation(method).annotationType().equals(After.class)) {
                methodWithAfter = method;
                afterCounter++;
            }
            if (ReflectionHelper.getAnnotation(method).annotationType().equals(Before.class)) {
                methodWithBefore = method;
                beforeCounter++;
            }
            if (ReflectionHelper.getAnnotation(method).annotationType().equals(Test.class)) {
                methodsTest.add(method);
                testCounter++;
            }
        }

        if (afterCounter > 1 || beforeCounter > 1 || testCounter == 0) {
            throw new Exception("Error");
        }


        for (Method method : methodsTest) {
            Object instance = ReflectionHelper.instantiate(c1);
            if (methodWithAfter != null) {
                methodWithAfter.invoke(instance);
            }

            method.invoke(instance);

            if (methodWithBefore != null) {
                methodWithBefore.invoke(instance);
            }
        }
    }

    public static void start(String packageName) throws Exception {
        Set<String> classNamesSet = getClassNamesFromPackage(packageName);
        Set<Class> classList = new HashSet<>();
        for (String className : classNamesSet) {
            classList.add(Class.forName(className));
        }
        int classesWithTest = 0;
        for (Class c1 : classList) {
            if (classHaveMethodWithTestAnnotation(c1)) {
                start(c1);
                classesWithTest++;
            }
        }
        if (classesWithTest == 0) {
            throw new Exception("No methods at this package have an annotation @Test");
        }
    }

    private static boolean classHaveMethodWithTestAnnotation(Class className) {
        Method[] methods = className.getDeclaredMethods();
        for (Method method : methods) {
            if (ReflectionHelper.haveAnyAnnotation(method, Test.class)) {
                return true;
            }
        }
        return false;
    }

    private static Set<String> getClassNamesFromPackage(String packageName) throws Exception {
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        ClassPath currentClassPath = ClassPath.from(loader);
        ImmutableSet<ClassPath.ClassInfo> classInfoSet = currentClassPath.getTopLevelClasses(packageName);
        Set<String> setOfClassNames = new HashSet<>();
        for (ClassPath.ClassInfo classInfo : classInfoSet) {
            setOfClassNames.add(classInfo.getName());
        }
        return setOfClassNames;
    }
}
