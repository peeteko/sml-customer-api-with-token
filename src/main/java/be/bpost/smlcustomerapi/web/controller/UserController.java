package be.bpost.smlcustomerapi.web.controller;

import be.bpost.smlcustomerapi.model.SmlUserDetails;
import be.bpost.smlcustomerapi.model.SmlUserDetailsWithToken;
import be.bpost.smlcustomerapi.security.jwt.JwtTokenUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.Date;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/customer/user")
public class UserController {

    @GetMapping("/me")
    public ResponseEntity<SmlUserDetailsWithToken> getCurrentUser() throws Exception {

        SmlUserDetails smlUserDetails = new SmlUserDetails();
        smlUserDetails.setFirstName("Koen");
        smlUserDetails.setLastName("Peeters");
        smlUserDetails.setContractAccountId("123456");

        SmlUserDetailsWithToken smlUserDetailsWithToken = new SmlUserDetailsWithToken();
        smlUserDetailsWithToken.setSmlUserDetails(smlUserDetails);
        Date expirationDate = Date.from(Instant.now().plusSeconds(3600));
        smlUserDetailsWithToken.setJwtTokenCustomized(JwtTokenUtil.getJwtTokenSignedWithPrivateKey(smlUserDetails, expirationDate));
        return ResponseEntity.ok(smlUserDetailsWithToken);
    }

}
