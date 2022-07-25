package com.employee.data.controller;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.employee.data.entity.Employee;
import com.employee.data.exception.CustomErrorResponse;
import com.employee.data.exception.EmployeeAlreadyExistsException;
import com.employee.data.repository.EmployeeService;


@RestController
@RequestMapping("/employees")
public class EmployeeController {

	
	@Autowired
	private EmployeeService employeeService;

    @GetMapping("")
    public List<Employee> list(){
		return employeeService.listAllEmployees();
	}

    @GetMapping("/{employeeId}")
    public ResponseEntity<Employee> get(@PathVariable Integer employeeId) {
        try {
            Employee employee = employeeService.getEmployee(employeeId);
            return new ResponseEntity<Employee>(employee, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<Employee>(HttpStatus.NOT_FOUND);
        }
    }
    
    
    @GetMapping("/sort/{field}")
    public List<Employee> getEmployeesWithSort(@PathVariable String field){
    	List<Employee> allEmployees= employeeService.findEmployeesWithSort(field);
		return allEmployees; 
    }
    
    
    @ExceptionHandler(value = EmployeeAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public CustomErrorResponse handleCustomerAlreadyExistsException(EmployeeAlreadyExistsException ex){
    		return new CustomErrorResponse(HttpStatus.CONFLICT.value(),
                       ex.getMessage());
    }
    
    
    
    @PostMapping("/")
    public void add(@RequestBody Employee employee) {
        employeeService.saveEmployee(employee);
    }
    @PutMapping("/{employeeSalary}")
    public ResponseEntity<?> update(@RequestBody Employee employee, @PathVariable Integer employeeSalary) {
        try {
        	Employee existEmployee = employeeService.getEmployee(employeeSalary);
        	employee.setEmployeeSalary(employeeSalary);            
        	employeeService.saveEmployee(employee);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/{employeeId}")
    public void delete(@PathVariable Integer employeeId) {

    	employeeService.deleteEmployee(employeeId);
    }

}

