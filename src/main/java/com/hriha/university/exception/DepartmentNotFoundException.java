package com.hriha.university.exception;

public class DepartmentNotFoundException extends Throwable {
    public DepartmentNotFoundException(String department_not_found) {
        System.out.println(department_not_found);
    }
}
