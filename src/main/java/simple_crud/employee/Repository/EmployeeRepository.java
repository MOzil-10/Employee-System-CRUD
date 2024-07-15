package simple_crud.employee.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import simple_crud.employee.Model.Employees;

import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employees,Long> {
    Optional<Employees> findByEmail(String email);
}
