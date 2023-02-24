package com.example.postgres.springbootpostgresdocker.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.example.postgres.springbootpostgresdocker.EmployeeRepository;

@Service
public class TransferServiceImpl implements TransferService {
 
    @Autowired
    private EmployeeRepository employeeRepository;
 
    /**
     * Crazy method to Transfer salary from one employee to another to check DB Transactional issues  
     * 
     * Depending on the underlying relational database system, this is how the Lost Update anomaly could be prevented using a higher isolation level:

        | Isolation Level | Oracle | SQL Server | PostgreSQL | MySQL |
        |-----------------|--------|------------|------------|-------|
        | Read Committed  | Yes    | Yes        | Yes        | Yes   |   Yes = problem/ lost update is possible 
        | Repeatable Read | N/A    | No         | No         | Yes   |
        | Serializable    | No     | No         | No         | No    |
        Since we are using PostgreSQL in our Spring example, let’s change the isolation level from the default, which is Read Committed to Repeatable Read.

     * @param fromEmail
     * @param toEmail
     * @param cents
     * @return bootlean success or not 
     */
    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public boolean transfer(
            String fromEmail, String toEmail, long cents) {
        boolean status = true;
 
        long fromBalance = employeeRepository.getSalary(fromEmail);
 
        if(fromBalance >= cents) {
            status &= employeeRepository.addBalance(
                fromEmail, (-1) * cents
            ) > 0;
             
            status &= employeeRepository.addBalance(
                toEmail, cents
            ) > 0;
        }
 
        return status;
    }
}