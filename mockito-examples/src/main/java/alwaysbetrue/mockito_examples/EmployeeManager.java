package alwaysbetrue.mockito_examples;

public interface EmployeeManager {
    SalaryStructure getSalaryStructure(Employee employee);
    
    void recordEvent(EmpEvent empEvent);
}
