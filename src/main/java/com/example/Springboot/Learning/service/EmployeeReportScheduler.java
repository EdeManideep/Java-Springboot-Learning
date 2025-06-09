package com.example.Springboot.Learning.service;

import com.example.Springboot.Learning.model.Employee;
import com.example.Springboot.Learning.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class EmployeeReportScheduler {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private EmailService emailService;

    @Scheduled(cron = "0 */2 * * * ?")
    public void sendDailyEmployeeReport(){
        LocalDateTime last24Hours = LocalDateTime.now().minusHours(24);
        List<Employee> newEmployees = employeeRepository.findByCreatedAtAfter(last24Hours);

        if(!newEmployees.isEmpty()){
            StringBuilder emailContent = new StringBuilder("New Employees in last 24 hours:\n\n");

            for(Employee emp : newEmployees){
                emailContent.append("Name: ").append(emp.getName())
                        .append(", Email: ").append(emp.getEmail())
                        .append(", Created At: ").append(emp.getCreatedAt())
                        .append("\n");
            }

            emailService.sendEmail(
                    "manideepede9@gmail.com",
                    "Daily New Employees Report",
                    emailContent.toString()
            );
        }
    }
}
