package com.employee.data.repository;

import org.springframework.data.domain.Sort.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.util.Streamable;

import com.employee.data.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

//	Streamable<Order> findById(Double employeeSalary);

}
