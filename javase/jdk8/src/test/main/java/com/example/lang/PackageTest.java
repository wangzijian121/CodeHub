package com.example.lang;

import org.junit.Test;

import static org.junit.Assert.*;

public class PackageTest {


    @Test
    public void getName() {
        System.out.println(Package.getPackage("java.lang").getName());

    }

    @Test
    public void getSpecificationTitle() {
        System.out.println(Package.getPackage("java.lang").getSpecificationTitle());//Java Platform API Specification
    }

    @Test
    public void getSpecificationVersion() {
        System.out.println(Package.getPackage("java.lang").getSpecificationTitle());
    }

    @Test
    public void getSpecificationVendor() {
    }

    @Test
    public void getImplementationTitle() {
    }

    @Test
    public void getImplementationVersion() {
    }

    @Test
    public void getImplementationVendor() {
    }

    @Test
    public void isSealed() {
    }

    @Test
    public void testIsSealed() {
    }

    @Test
    public void isCompatibleWith() {
    }

    @Test
    public void getPackage() {
    }

    @Test
    public void getPackages() {
    }

    @Test
    public void testGetPackage() {
    }

    @Test
    public void testHashCode() {
    }

    @Test
    public void testToString() {
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
    public void getSystemPackage() {
    }

    @Test
    public void getSystemPackages() {
    }
}