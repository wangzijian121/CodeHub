package com.example.java.lang;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ClassTest {

    Class<?> objectClass;

    {
        try {
            objectClass = Class.forName("java.lang.Object");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }


    @Test
    public void testToString() {
    }

    @Test
    public void toGenericString() throws ClassNotFoundException {

        Class<?> objectClass = Class.forName("java.lang.Object");
        System.out.println(objectClass.toGenericString());//public class java.lang.Object
    }

    @Test
    public void forName() throws ClassNotFoundException {
        Class o;
        o = Class.forName("java.lang.Object");
        System.out.println(o);
    }

    @Test
    public void testForName() {
    }

    @Test
    public void newInstance() throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        String str = (String) Class.forName("java.lang.String").newInstance();
        str += "wangzijian";
        System.out.println(str);//wangzijian

    }

    @Test
    public void isInstance() {
    }

    @Test
    public void isAssignableFrom() {
    }

    @Test
    public void isInterface() {
        System.out.println(objectClass.isInterface());//false
    }

    @Test
    public void isArray() {

    }

    @Test
    public void isPrimitive() {
        System.out.println(objectClass.isPrimitive());//false
    }

    @Test
    public void isAnnotation() {
    }

    @Test
    public void isSynthetic() {
    }

    @Test
    public void getName() {
    }

    @Test
    public void getClassLoader() {
        System.out.println(objectClass.getClassLoader());//null
        String name = "wangzijian";
        System.out.println(name.getClass().getClassLoader());//null

        ClassTest classTest = new ClassTest();
        System.out.println(classTest.getClass().getClassLoader());//AppClassLoader
    }

    @Test
    public void getClassLoader0() {
    }

    @Test
    public void getTypeParameters() {
    }

    @Test
    public void getSuperclass() {

        System.out.println(objectClass.getSuperclass());//null
        ClassTest classTest = new ClassTest();
        System.out.println(classTest.getClass().getSuperclass());//java.lang.Object

    }

    @Test
    public void getGenericSuperclass() {
    }

    @Test
    public void getPackage() {
    }

    @Test
    public void getInterfaces() {
    }

    @Test
    public void getGenericInterfaces() {
    }

    @Test
    public void getComponentType() {
    }

    @Test
    public void getModifiers() {
    }

    @Test
    public void getSigners() {
        System.out.println(objectClass.getSigners());
    }

    @Test
    public void setSigners() {
    }

    @Test
    public void getEnclosingMethod() {
    }

    @Test
    public void getEnclosingConstructor() {
    }

    @Test
    public void getDeclaringClass() {
    }

    @Test
    public void getEnclosingClass() {
    }

    @Test
    public void getSimpleName() {
    }

    @Test
    public void getTypeName() {
    }

    @Test
    public void getCanonicalName() {
    }

    @Test
    public void isAnonymousClass() {
    }

    @Test
    public void isLocalClass() {
        System.out.println(String.class.isLocalClass());//false
        System.out.println(objectClass.isLocalClass());//false
        ClassTest classTest = new ClassTest();
        System.out.println(classTest.getClass().isLocalClass());//false
    }

    @Test
    public void isMemberClass() {
    }

    @Test
    public void getClasses() {
    }

    @Test
    public void getFields() {
    }

    @Test
    public void getMethods() {
    }

    @Test
    public void getConstructors() {
    }

    @Test
    public void getField() {
    }

    @Test
    public void getMethod() {
    }

    @Test
    public void getConstructor() {
    }

    @Test
    public void getDeclaredClasses() {
    }

    @Test
    public void getDeclaredFields() {
    }

    @Test
    public void getDeclaredMethods() {
    }

    @Test
    public void getDeclaredConstructors() {
    }

    @Test
    public void getDeclaredField() {
    }

    @Test
    public void getDeclaredMethod() {
    }

    @Test
    public void getDeclaredConstructor() {
    }

    @Test
    public void getResourceAsStream() {
    }

    @Test
    public void getResource() {
    }

    @Test
    public void getProtectionDomain() {
    }

    @Test
    public void getPrimitiveClass() {
    }

    @Test
    public void getRawAnnotations() {
    }

    @Test
    public void getRawTypeAnnotations() {
    }

    @Test
    public void getExecutableTypeAnnotationBytes() {
    }

    @Test
    public void getConstantPool() {
    }

    @Test
    public void desiredAssertionStatus() {
    }

    @Test
    public void isEnum() {
    }

    @Test
    public void getEnumConstants() {
    }

    @Test
    public void getEnumConstantsShared() {
    }

    @Test
    public void enumConstantDirectory() {
    }

    @Test
    public void cast() {
    }

    @Test
    public void asSubclass() {
    }

    @Test
    public void getAnnotation() {
    }

    @Test
    public void isAnnotationPresent() {
    }

    @Test
    public void getAnnotationsByType() {
    }

    @Test
    public void getAnnotations() {
    }

    @Test
    public void getDeclaredAnnotation() {
    }

    @Test
    public void getDeclaredAnnotationsByType() {
    }

    @Test
    public void getDeclaredAnnotations() {
    }

    @Test
    public void casAnnotationType() {
    }

    @Test
    public void getAnnotationType() {
    }

    @Test
    public void getDeclaredAnnotationMap() {
    }

    @Test
    public void getAnnotatedSuperclass() {
    }

    @Test
    public void getAnnotatedInterfaces() {
    }
}