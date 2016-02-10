package alwaysbetrue.mockito_examples;

public class Employee {

	    private TaxManager taxManager;
	    private EmployeeManager empManager;
	    private String name;
	     
	    public Employee(String name) {
	       this.name = name;
	    }   
	     
	    public void setTaxManager(TaxManager taxManager) {
	        this.taxManager = taxManager;
	    }
	 
	    public void setEmpManager(EmployeeManager empManager) {
	        this.empManager = empManager;
	    }
	 
	    public Long calculateNetPay() {
	        SalaryStructure salaryStructure = empManager.getSalaryStructure(this);
	        Long tax = taxManager.calculateTax(this, salaryStructure);
	        empManager.recordEvent(new EmpEvent(this, EventType.TAX_CALCULATED, tax));
	        return salaryStructure.getGross() - tax;
	    }
	     
	    public String toString() {
	        return "Employee (" + name + ")";
	    }       
	     
	    public String getName() {
	        return name;
	    }
	 
	    public boolean equals(Object o) {
	        if (o == null) {
	            return false;
	        }
	        if (!(o instanceof Employee)) {
	            return false;
	        }
	        Employee emp = (Employee) o;
	        return name.equals(emp.getName());
	    }

}
