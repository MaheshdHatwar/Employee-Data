package com.employee.data.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.employee.data.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

}
