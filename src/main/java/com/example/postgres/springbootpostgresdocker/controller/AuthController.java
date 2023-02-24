package com.example.postgres.springbootpostgresdocker.controller;

import java.util.List;

import com.example.postgres.springbootpostgresdocker.EmployeeRepository;
import com.example.postgres.springbootpostgresdocker.Model.Employee;
import com.example.postgres.springbootpostgresdocker.utils.JWTUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;

@RestController
@RequestMapping("/api")
public class AuthController {

    @Autowired
    private EmployeeRepository employeeRepo;

    private JWTUtil jwtUtil = new JWTUtil();

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@RequestBody Employee userExternal) {

        Employee loggedUser = getUserByCredentials(userExternal);
        if (loggedUser != null) {
            String tokenJwt = jwtUtil.create(String.valueOf(loggedUser.getId()), loggedUser.getEmail());
            return tokenJwt;
        }
        return "FAIL";
    }

    
    private Employee getUserByCredentials(Employee userExternal) {

        //query by email 
        List<Employee> listFound = employeeRepo.findAllByEmail(userExternal.getEmail());


        if (listFound.isEmpty()) {
            return null;
        }

        String passwordHashed = listFound.get(0).getPassword();

        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        if (argon2.verify(passwordHashed, userExternal.getPassword())) {
            return listFound.get(0);
        }
        
        return null;
    }

}
