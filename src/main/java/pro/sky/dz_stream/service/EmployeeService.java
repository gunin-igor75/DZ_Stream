package pro.sky.dz_stream.service;

import pro.sky.dz_stream.domain.Employee;

import java.util.List;
import java.util.Map;

public interface EmployeeService {
    Employee addEmployee(Employee employee);

    Employee removeEmployee(Employee employee);

    Employee findEmployee(Employee employee);


    String getMaxSalary(int departmentId);

    String getMinSalary(int departmentId);

    String getEmployeeDep(int departmentId);

   String getEmployeeAll();
}
