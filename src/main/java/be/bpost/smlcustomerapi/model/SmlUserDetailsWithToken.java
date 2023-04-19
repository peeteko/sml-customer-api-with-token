package be.bpost.smlcustomerapi.model;

public class SmlUserDetailsWithToken {

    private SmlUserDetails smlUserDetails;

    private String jwtTokenCustomized;

    public SmlUserDetails getSmlUserDetails() {
        return smlUserDetails;
    }

    public void setSmlUserDetails(SmlUserDetails smlUserDetails) {
        this.smlUserDetails = smlUserDetails;
    }

    public String getJwtTokenCustomized() {
        return jwtTokenCustomized;
    }

    public void setJwtTokenCustomized(String jwtTokenCustomized) {
        this.jwtTokenCustomized = jwtTokenCustomized;
    }
}
