package backend;
public class EmployeeUserDatabase extends Database<EmployeeUser>{
    
    public EmployeeUserDatabase(String filename) {
        super(filename);
    }

    @Override
    public EmployeeUser createRecordFrom(String line) {
    	EmployeeUser employee = null;
    	if(line != null) {
    		
    		String[] data = line.split(",");
    		if(data.length == 5) {
    			if(data[0] == null || data[1] == null || data[2] == null || data[3] == null || data[4] == null)
    				System.out.println("Can't have null input");
    			else
    				employee = new EmployeeUser(data[0], data[1], data[2], data[3], data[4]);
    		}
    			
    	}
        
        return employee;
    }
    
}