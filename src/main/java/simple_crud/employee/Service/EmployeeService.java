package simple_crud.employee.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import simple_crud.employee.Model.Employees;
import simple_crud.employee.Repository.EmployeeRepository;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class EmployeeService {

    private final EmployeeRepository repository;

    public Employees createEmployee(Employees employees) {
        try {
            Optional<Employees> existingEmployee = repository.findByEmail(employees.getEmail());
            if (existingEmployee.isPresent()) {
                throw new Exception("Employee with email " + employees.getEmail() + " already exists.");
            }
            return repository.save(employees);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public List<Employees> getEmployees() {
        try {
            return repository.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Error retrieving a list of employees");
        }
    }

    public Employees getEmployeeById(Long id) {
        try {
            return repository.findById(id).orElseThrow(() -> new Exception("Employee not found with id " + id));
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public Employees updateEmployees(Long id, Employees employeesDetails) {
        try {
            Employees employees = repository.findById(id).orElseThrow(() -> new Exception("Employee not found with ID " + id));
            employees.setName(employeesDetails.getName());
            employees.setEmail(employeesDetails.getEmail());
            employees.setRole(employeesDetails.getRole());
            return repository.save(employees);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public void deleteEmployee(Long id) {
        try {
            Employees employee = repository.findById(id).orElseThrow(() -> new Exception("Employee with Id " + id + " is not found, please try again"));
            repository.delete(employee);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
