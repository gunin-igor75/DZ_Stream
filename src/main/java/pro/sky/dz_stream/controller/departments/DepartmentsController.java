package pro.sky.dz_stream.controller.departments;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pro.sky.dz_stream.service.EmployeeService;


@RestController
@RequestMapping("/departments")
public class DepartmentsController {
    private final EmployeeService employeeService;

    public DepartmentsController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/max-salary")
    String getMaxSalary(@RequestParam(value = "departmentId", required = false) int departmentId) {
        return employeeService.getMaxSalary(departmentId);
    }

    @GetMapping("/min-salary")
    String getMinSalary(@RequestParam(value = "departmentId", required = false) int departmentId) {
        return employeeService.getMaxSalary(departmentId);
    }

    @GetMapping("/allDep")
    String getEmployeeDep(@RequestParam(value = "departmentId", required = false) int departmentId) {
        return employeeService.getEmployeeDep(departmentId);
    }

    @GetMapping("/all")
    String getEmployeeAll() {
        return employeeService.getEmployeeAll();
    }
}
