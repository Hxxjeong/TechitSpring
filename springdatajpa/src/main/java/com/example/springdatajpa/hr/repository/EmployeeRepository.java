package com.example.springdatajpa.hr.repository;

import com.example.springdatajpa.hr.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    List<Employee> findByLastName(String lastName);

    // JOIN FETCH 사용 (Employee와 Department 사이 관계가 즉시 로드(Eager Fetching))
    @Query("SELECT e FROM Employee e JOIN FETCH e.department d WHERE d.departmentId IN :departmentIds AND e.salary BETWEEN :minSalary AND :maxSalary")
    List<Employee> findByDepartmentIdInAndSalaryBetween(@Param("departmentIds") List<Integer> departmentIds, @Param("minSalary") Double minSalary, @Param("maxSalary")Double maxSalary);

}
