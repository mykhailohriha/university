package com.hriha.university;

import com.hriha.university.entity.Department;
import com.hriha.university.entity.Lector;
import com.hriha.university.repository.DepartmentRepository;
import com.hriha.university.repository.LectorRepository;
import com.hriha.university.service.UniversityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@SpringBootApplication
public class UniversityApplication implements CommandLineRunner {

    @Autowired
    private UniversityService service;

    public static void main(String[] args) {
        SpringApplication.run(UniversityApplication.class, args);
    }

    @Override
    public void run(String... args) {
        service.loadData(); // Загрузка захардкоженных данных в базу
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter the command. (write 'exit' if you want to close the application)");
        try {
            String string = bufferedReader.readLine();
            while (!string.equals("exit")) {
                service.executeCommand(string);
                try {
                    string = bufferedReader.readLine();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                bufferedReader.close();
                System.out.println("The application is closing...");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
