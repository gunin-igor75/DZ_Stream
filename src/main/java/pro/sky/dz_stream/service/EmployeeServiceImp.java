package pro.sky.dz_stream.service;

import org.springframework.stereotype.Service;
import pro.sky.dz_stream.domain.Employee;
import pro.sky.dz_stream.exeptions.EmployeeAlreadyAddedException;
import pro.sky.dz_stream.exeptions.EmployeeNotFoundException;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImp implements EmployeeService {

    private Map<String, Employee> employees = new HashMap<>();

    {
        loadEmployee();
    }

    public Map<String, Employee> getEmployees() {
        return employees;
    }

    @Override
    public Employee addEmployee(Employee employee) {
        if (employee == null || employee.getFirstName() == null || employee.getLastName() == null) {
            throw new EmployeeAlreadyAddedException("Не правильный запрос");
        }
        if (employees.containsKey(employee.getFullName())) {
            throw new EmployeeAlreadyAddedException("Сотрудник уже существует");
        }
        employees.put(employee.getFullName(), employee);
        return employee;
    }

    @Override
    public Employee removeEmployee(Employee employee) {
        if (employee == null) {
            throw new EmployeeAlreadyAddedException("Не правильный запрос");
        }
        if (!employees.containsKey(employee.getFullName())) {
            throw new EmployeeNotFoundException("Сотрудник не найден");
        }
        employees.remove(employee.getFullName());
        return employee;
    }

    @Override
    public Employee findEmployee(Employee employee) {
        if (employee == null) {
            throw new EmployeeAlreadyAddedException("Не правильный запрос");
        }
        if (!employees.containsKey(employee.getFullName())) {
            throw new EmployeeNotFoundException("Сотрудник не найден");
        }
        return employees.get(employee.getFullName());
    }

    @Override
    public String getMaxSalary(int departmentId) {
        Employee employee = employees.values().stream()
                .filter(e -> e.getDepartmentId() == departmentId)
                .max((x, y) -> (int) (y.getSalary() - x.getSalary()))
                .orElse(null);
        if (employee == null) {
            throw new EmployeeNotFoundException("Сотрудники отсутствуют");
        }
        return employee.toString();
    }

    @Override
    public String getMinSalary(int departmentId) {
        Employee employee = employees.values().stream()
                .filter(e -> e.getDepartmentId() == departmentId)
                .min((x, y) -> (int) (x.getSalary() - y.getSalary()))
                .orElse(null);
        if (employee == null) {
            throw new EmployeeNotFoundException("Сотрудники отсутствуют");
        }
        return employee.toString();
    }

    @Override
    public String getEmployeeDep(int departmentId) {
        List<Employee> list = employees.values().stream()
                .filter(e -> e.getDepartmentId() == departmentId)
                .toList();
        if (list.isEmpty()) {
            throw new EmployeeNotFoundException("Сотрудники отсутствуют");
        }
        String result = list.stream().map(Employee::toString).collect(Collectors.joining("<br>"));
        return String.format("Работники по отделу № %d<br>%s", list.get(0).getDepartmentId(), result);
    }

    @Override
    public String getEmployeeAll() {
        Map<Integer, List<Employee>> map = employees.values().stream()
                .collect(Collectors.groupingBy(Employee::getDepartmentId));
        if (map.isEmpty()) {
            throw new EmployeeNotFoundException("Сотрудники отсутствуют");
        }
        return map.values()
                .stream()
                .flatMap(Collection::stream)
                .map(Employee::toString)
                .collect(Collectors.joining("<br>"));
    }


    private void loadEmployee() {
        Employee employee1 = new Employee("Igor", "Pupkin", 1, 50000);
        Employee employee2 = new Employee("Alena", "Sidorova", 1, 45000);
        Employee employee3 = new Employee("Max", "Ivanov", 2, 48000);
        Employee employee4 = new Employee("Petr", "Petrov", 2, 25000);
        employees.put(employee1.getFullName(), employee1);
        employees.put(employee2.getFullName(), employee2);
        employees.put(employee3.getFullName(), employee3);
        employees.put(employee4.getFullName(), employee4);
    }
}
