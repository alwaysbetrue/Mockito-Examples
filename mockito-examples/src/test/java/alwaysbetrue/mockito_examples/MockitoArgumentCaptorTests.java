package alwaysbetrue.mockito_examples;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

/**
 * This working example demonstrates the use of Mockito's ArgumentCaptor
 * 
 * Reference:  http://javarticles.com/2015/08/mockito-argumentcaptor-example.html
 *
 */

public class MockitoArgumentCaptorTests {
	   @Mock private EmployeeManager empManager;
	    @Mock private TaxManager taxManager;
	    private Employee emp;
	    private SalaryStructure salaryStructure;
	     
	    @Before
	    public void init() {
	        MockitoAnnotations.initMocks(this);
	        emp = new Employee("Joe");
	        emp.setEmpManager(empManager);
	        emp.setTaxManager(taxManager);
	         
	        salaryStructure = new SalaryStructure(12000L);               
	        when(empManager.getSalaryStructure(emp)).thenReturn(salaryStructure);
	        when(taxManager.calculateTax(emp, salaryStructure)).thenReturn(1000L);
	    }
	    @Test
	    public void argumentVerification() {                
	        Employee empJoe = new Employee("Joe");
	        empJoe.setEmpManager(empManager);
	        empJoe.setTaxManager(taxManager);
	        Long netPay = empJoe.calculateNetPay();
	         
	        assertEquals(netPay, new Long(11000));
	        verify(empManager).getSalaryStructure(emp);
	        verify(taxManager).calculateTax(empJoe, salaryStructure);
	        verify(empManager).recordEvent(any(EmpEvent.class));
	    }
	     
	    @Test
	    public void argumentValuesVerificationWithoutArgumentCapture() {   
	    	/*
	    	 * This will fail because actual EmpEvent argument is different than the one we passed in 
	    	 * on the verify step below.
	    	 */
	        Employee empJoe = new Employee("Joe");
	        empJoe.setEmpManager(empManager);
	        empJoe.setTaxManager(taxManager);
	        Long netPay = empJoe.calculateNetPay();
	         
	        assertEquals(netPay, new Long(11000));
	        verify(empManager).getSalaryStructure(emp);
	        verify(taxManager).calculateTax(empJoe, salaryStructure);
	        verify(empManager).recordEvent(new EmpEvent(empJoe, EventType.TAX_CALCULATED, 1000L));
	    }
	    
	    @Test
	    public void argumentValuesVerificationWithArgumentCapture() {     
	    	/*
	    	 * 
	    	 */
	        Employee empJoe = new Employee("Joe");
	        empJoe.setEmpManager(empManager);
	        empJoe.setTaxManager(taxManager);
	        empJoe.calculateNetPay();
	         
	        /*'
	         * Create an ArgumentCaptor object for the type of argument we're interested in.  Use factory method to create.
	         */
	        ArgumentCaptor<EmpEvent> empEventArgCaptor = ArgumentCaptor.forClass(EmpEvent.class);
	        
	        
	        /*
	         * Capture the ArgumentCaptor object - you'll need the object before you can start verifying.
	         * Internally it uses argument matcher and stores the argument value.
	         */
	        verify(empManager).recordEvent(empEventArgCaptor.capture());
	         
			/*
			 * Retrieve the captured argument value using ArgumentCaptor.getValue().	        
			 */
	        assertEquals(empEventArgCaptor.getValue().getData(), 1000L);
	        assertEquals(empEventArgCaptor.getValue().getEventType(), EventType.TAX_CALCULATED);
	        assertEquals(empEventArgCaptor.getValue().getEmp(), empJoe);
	    }
}
