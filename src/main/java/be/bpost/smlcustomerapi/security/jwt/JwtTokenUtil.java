package be.bpost.smlcustomerapi.security.jwt;

import be.bpost.smlcustomerapi.model.SmlUserDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.spec.EncodedKeySpec;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.time.Instant;
import java.util.Base64;
import java.util.Date;

public class JwtTokenUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(JwtTokenUtil.class);

    public static String getJwtTokenSignedWithPrivateKey(SmlUserDetails smlUserDetails, Date expirationDate) throws Exception {

        Instant instant = Instant.now();
        String privateKeyString = getResourceContentAsString("/certificates/pkcs8-private.key");
        PrivateKey privateKey = getPrivateKey(privateKeyString);
        return Jwts.builder().setIssuer("https://sml-dv2.bpost.cloud")
                .setSubject("users/1300819380")
                .setExpiration(expirationDate)
                .claim("scope", "connections:read connections:write")
                .claim("client_id_name", "sml")
                .claim("email", smlUserDetails.getEmail())
                .claim("customerAccountIds", smlUserDetails.getContractAccountId() )
                .signWith(privateKey, SignatureAlgorithm.RS256)
                .compact();
    }

    private static PrivateKey getPrivateKey(String privateKeyString) throws NoSuchAlgorithmException, InvalidKeySpecException {
        String privateKeyFormatted =  privateKeyString
                .replace("-----BEGIN PRIVATE KEY-----","")
                .replaceAll("\n", "")
                .replaceAll("\r","")
                .replace("-----END PRIVATE KEY-----", "");
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        EncodedKeySpec privateKeySpec = new PKCS8EncodedKeySpec(Base64.getDecoder().decode(privateKeyFormatted.getBytes()));
        return keyFactory.generatePrivate(privateKeySpec);
    }

    private static String getResourceContentAsString(String resourceName) throws IOException {
        URL url = JwtTokenUtil.class.getResource(resourceName);
        File file = new File(url.getFile());
        return Files.readString(file.toPath());
    }

}
