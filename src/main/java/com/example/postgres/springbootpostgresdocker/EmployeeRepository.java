package com.example.postgres.springbootpostgresdocker;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.postgres.springbootpostgresdocker.Model.Employee;

@Repository 
@Transactional(readOnly = true)
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
  @Query(
    value = "SELECT * from employee WHERE email = :mail",
    nativeQuery = true
  )
  public List<Employee> findAllByEmail(@Param("mail") String email);

  @Query(
    value = "SELECT salary from employee WHERE email = :mail",
    nativeQuery = true
  )
  long getSalary(@Param("mail") String email);

  @Query(
    value = " UPDATE employee SET salary = salary + :cents WHERE email = :mail",
    nativeQuery = true
  )
  @Modifying
  @Transactional
  int addBalance(@Param("mail") String mail, @Param("cents") long cents);
  
  /* @Query("SELECT u FROM employee u WHERE u.salary > 0")
    public Collection<Employee> findAllActiveEmployees();
    
    @Query(value = "SELECT * FROM EMPLOYEE u WHERE u.salary > 0", nativeQuery = true)
    public Collection<Employee> findAllActiveEmployeesNative();
    */
}
