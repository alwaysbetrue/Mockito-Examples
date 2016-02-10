package alwaysbetrue.mockito_examples;

public class EmpEvent {

	    private Employee emp;
	    private EventType eventType;
	    private Object data;
	    public EmpEvent(Employee employee, EventType eventType, Object data) {
	        this.emp = employee;
	        this.eventType = eventType;
	        this.data = data;
	    }
	    public Employee getEmp() {
	        return emp;
	    }
	    public EventType getEventType() {
	        return eventType;
	    }
	    public Object getData() {
	        return data;
	    }
}
