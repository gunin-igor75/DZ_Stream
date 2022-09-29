package pro.sky.dz_stream.domain;

import java.util.Objects;

public class Employee {
    private final String firstName;
    private final String lastName;
    private final String fullName;
    private double salary;
    private int departmentId;

    public Employee(String firstName, String lastName, int departments, double salary) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.departmentId = departments;
        this.salary = salary;
        this.fullName = String.format("%s %s", firstName, lastName);
    }

    public Employee(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.fullName = String.format("%s %s", firstName, lastName);
    }

    public String getFirstName() {
        return firstName;
    }


    public String getLastName() {
        return lastName;
    }

    public String getFullName() {
        return fullName;
    }

    public double getSalary() {
        return salary;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return Objects.equals(fullName, employee.fullName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fullName);
    }

    @Override
    public String toString() {
        return String.format("%s отдел № %d зарплата %.2f", getFullName(), getDepartmentId(), getSalary());
    }
}
