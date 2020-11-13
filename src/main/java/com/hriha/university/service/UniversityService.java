package com.hriha.university.service;

import com.hriha.university.entity.Department;
import com.hriha.university.entity.Lector;
import com.hriha.university.exception.DepartmentNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class UniversityService {

    @Autowired
    private LectorService lectorService;

    @Autowired
    private DepartmentService departmentService;


    // Захардкоженные данные
    public void loadData() {
        Lector lector1 = new Lector("Ivan Petrenko", 20000, Lector.Degree.professor);
        Lector lector2 = new Lector("Petro Ivanenko", 12000, Lector.Degree.assistant);
        Lector lector3 = new Lector("Pavlo Ivanenko", 12000, Lector.Degree.assistant);
        Lector lector4 = new Lector("Petro Pavlenko", 16000, Lector.Degree.associateProfessor);
        Lector lector5 = new Lector("Bogdan Petrenko", 19000, Lector.Degree.associateProfessor);

        Department department1 = new Department("MT", lector1);
        Department department2 = new Department("KI", lector4);
        Department department3 = new Department("BT", lector5);
        Department department4 = new Department("MT YS", lector1);

        lector1.getDepartments().add(department1);
        lector1.getDepartments().add(department4);
        lector2.getDepartments().add(department1);
        lector2.getDepartments().add(department4);
        lector3.getDepartments().add(department1);
        lector4.getDepartments().add(department2);
        lector4.getDepartments().add(department3);
        lector5.getDepartments().add(department2);
        lector5.getDepartments().add(department3);

        department1.getLectors().add(lector1);
        department1.getLectors().add(lector2);
        department1.getLectors().add(lector3);
        department2.getLectors().add(lector4);
        department2.getLectors().add(lector5);
        department3.getLectors().add(lector4);
        department3.getLectors().add(lector4);
        department4.getLectors().add(lector1);
        department4.getLectors().add(lector2);

        departmentService.save(department1);
        departmentService.save(department2);
        departmentService.save(department4);
        departmentService.save(department4);

        lectorService.save(lector1);
        lectorService.save(lector2);
        lectorService.save(lector3);
        lectorService.save(lector4);
        lectorService.save(lector5);
    }

    public void executeCommand(String string) {
        String command;
        if (string.contains(".")) {
            command = string.substring(0, string.length() - 1);
        } else {
            command = string;
        }

        if (command.contains("Who is head of department") && command.length() > 26) {
            findHeadOfDepartment(command.substring(26));
        } else if (command.contains("statistics") && command.contains("Show") && command.length() > 16) {
            showStatistics(command.substring(5, command.length() - 11));
        } else if (command.contains("Show the average salary for the department") && command.length() > 43) {
            showAverageSalary(command.substring(43));
        } else if (command.contains("Show count of employee for") && command.length() > 27) {
            showCountOfEmployee(command.substring(27));
        } else if (command.contains("Global search by") && command.length() > 17) {
            globalSearchByTemplate(command.substring(17));
        } else {
            System.out.println("Command not defined. Please, try again.");
        }

    }

    public void findHeadOfDepartment(String departmentName) {
        try {
            System.out.println(departmentService.findByName(departmentName).getHeadOfDepartment().getFullName());
        } catch (DepartmentNotFoundException ignored) {
        }
    }

    public void showStatistics(String departmentName) {
        Set<Lector> lectors;
        try {
            lectors = departmentService.findByName(departmentName).getLectors();
        } catch (DepartmentNotFoundException e) {
            return;
        }

        int assistantsCount = 0;
        int professorsCount = 0;
        int associateProfessorsCount = 0;

        for (Lector l : lectors) {
            if (l.getDegree().equals(Lector.Degree.professor)) {
                professorsCount++;
            } else if (l.getDegree().equals(Lector.Degree.assistant)) {
                assistantsCount++;
            } else if (l.getDegree().equals(Lector.Degree.associateProfessor)) {
                associateProfessorsCount++;
            }
        }

        System.out.println("assistants - " + assistantsCount);
        System.out.println("associate professors - " + associateProfessorsCount);
        System.out.println("professors - " + professorsCount);
    }

    public void showAverageSalary(String departmentName) {
        Set<Lector> lectors;
        try {
            lectors = departmentService.findByName(departmentName).getLectors();
        } catch (DepartmentNotFoundException e) {
            return;
        }

        int count = 0;
        int salary = 0;
        for (Lector l : lectors) {
            count++;
            salary += l.getSalary();
        }

        System.out.println("The average salary of " + departmentName + " is " + salary / (double) count);
    }

    public void showCountOfEmployee(String departmentName) {
        try {
            System.out.println(departmentService.findByName(departmentName).getLectors().size());
        } catch (DepartmentNotFoundException ignored) {
        }
    }

    public void globalSearchByTemplate(String template) {

        if (template == null) {
            System.out.println("Template is empty. Try it again");
            return;
        }
        List<Lector> lectors = lectorService.findAll();

        boolean isMatchExists = false;
        for (Lector l : lectors) {
            if (l.getFullName().contains(template)) {
                System.out.println(l.getFullName());
                isMatchExists = true;
            }
        }

        if (!isMatchExists) {
            System.out.println("No match for the given template.");
        }

    }
}
