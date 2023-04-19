package be.bpost.smlcustomerapi.model;

public class SmlUserDetails {

    private String firstName;
    private String lastName;
    private String contractAccountId;

    private String email;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getContractAccountId() {
        return contractAccountId;
    }

    public void setContractAccountId(String contractAccountId) {
        this.contractAccountId = contractAccountId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
