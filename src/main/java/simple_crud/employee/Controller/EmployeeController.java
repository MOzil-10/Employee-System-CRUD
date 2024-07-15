package simple_crud.employee.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import simple_crud.employee.Model.Employees;
import simple_crud.employee.Service.EmployeeService;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/employee")
public class EmployeeController {

    private final EmployeeService service;

    @PostMapping
    public ResponseEntity<Employees> createEmployee(@RequestBody Employees employees) {
        try {
            Employees createdEmployee = service.createEmployee(employees);
            return new ResponseEntity<>(createdEmployee, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity<List<Employees>> getAllEmployees() {
        try {
            List<Employees> employees = service.getEmployees();
            return new ResponseEntity<>(employees, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employees> getEmployeeById(@PathVariable Long id) {
        try {
            Employees employee = service.getEmployeeById(id);
            return new ResponseEntity<>(employee, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Employees> updateEmployee(@PathVariable Long id, @RequestBody Employees employeeDetails) {
        try {
            Employees updatedEmployee = service.updateEmployees(id, employeeDetails);
            return new ResponseEntity<>(updatedEmployee, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteEmployee(@PathVariable Long id) {
        try {
            service.deleteEmployee(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
