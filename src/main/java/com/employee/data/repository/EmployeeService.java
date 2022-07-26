package com.employee.data.repository;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.employee.data.entity.Employee;
import com.employee.data.exception.EmployeeAlreadyExistsException;

@Service
public class EmployeeService {
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
//	@PostConstruct
	public void initLoadDataToDB() {
		List<Employee> employees= (List<Employee>) IntStream.rangeClosed(1, 40).mapToObj(i->new Employee(new Random().nextInt(100),"Employee"+i,new Random().nextInt(200000),"Engineer"+i)).collect(Collectors.toList());
		employeeRepository.saveAll(employees);
	}
	
	//Sort the data in descending order
	
	public List<Employee> findEmployeesWithSort(String field){
		
		return employeeRepository.findAll(Sort.by(Sort.Direction.DESC, field));
	}
	
		
//	printing all data from db
	
	public List<Employee> listAllEmployees(){
		return employeeRepository.findAll();
	}
	

	
	
//	Get Employee data by providing id
	public Employee getEmployee(Integer employeeId) {
		return employeeRepository.findById(employeeId).get();
	}

	public Employee getEmployeeData(Integer employeeSalary) {
		return employeeRepository.findById(employeeSalary).get();
	}
    
	//deleting employee data
	public void deleteEmployee(Integer employeeId) {
		employeeRepository.deleteById(employeeId);
	}	
	

	public void updateEmployee(Employee employee) {
		employeeRepository.save(employee);
	}

	public String saveEmployee(Employee employee)
    {
        Employee existingEmployee
            = employeeRepository.findById(employee.getEmployeeId())
                  .orElse(null);
        if (existingEmployee == null) {
            employeeRepository.save(employee);
            return "Customer added successfully";
        }
        else
            throw new EmployeeAlreadyExistsException(
                "Employee already exists in the database");
    }
}
