package backend;

public class EmployeeUser implements Record{

    private String employeeId;
    private String name;
    private String email;
    private String address;
    private String phoneNumber;

    public EmployeeUser(String employeeId, String name, String email, String address, String phoneNumber) {
        setEmployeeId(employeeId);
        setName(name);
        setEmail(email);
        setAddress(address);
        setPhoneNumber(phoneNumber);
    }

    public void setEmployeeId(String employeeId) {

        this.employeeId = employeeId;

    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String lineRepresentation() {
        return (this.employeeId + ',' + this.name + ',' + this.email + ',' + this.address + ',' + this.phoneNumber);
    }

    public String getSearchKey() {
        return this.employeeId;
    }

}