/**
 * 
 */
package deyin;

import org.jose4j.jwa.AlgorithmConstraints;
import org.jose4j.jwa.AlgorithmConstraints.ConstraintType;
import org.jose4j.jwk.HttpsJwks;
import org.jose4j.jws.AlgorithmIdentifiers;
import org.jose4j.jwt.JwtClaims;
import org.jose4j.jwt.consumer.InvalidJwtException;
import org.jose4j.jwt.consumer.JwtConsumer;
import org.jose4j.jwt.consumer.JwtConsumerBuilder;
import org.jose4j.keys.resolvers.HttpsJwksVerificationKeyResolver;

/**
 * @author dxu
 *
 */
public class jwtTtest {
    /*
{
  "alg": "RS256",
  "typ": "JWT",
  "kid", "deyin-key"
}
{
  "iss": "https://jwt-idp.example.com",
  "sub": "mailto:mike@example.com",
  "nbf": 1500831050,
  "exp": 1500934650,
  "iat": 1500831050,
  "jti": "id123456",
  "typ": "https://example.com/register"
}
     */
    /* generated at https://jwt.io */
    
    //with KID
     //private static final String jwt="eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCIsImtpZCI6ImRleWluLWtleSJ9.eyJpc3MiOiJodHRwczovL2p3dC1pZHAuZXhhbXBsZS5jb20iLCJzdWIiOiJtYWlsdG86bWlrZUBleGFtcGxlLmNvbSIsIm5iZiI6MTUwMDgzMTA1MCwiZXhwIjoxNTAwOTM0NjUwLCJpYXQiOjE1MDA4MzEwNTAsImp0aSI6ImlkMTIzNDU2IiwidHlwIjoiaHR0cHM6Ly9leGFtcGxlLmNvbS9yZWdpc3RlciJ9.sJf2DFTUjDJom19usHKCh31OEA8PIsnMrRbqks7xf6Km_RPrkRPaudx6HsHOz0uveB09cv2OYfpngm-zVr43n4uCtoE4xdNtO5NSb3Rv5QDILsDOLikTxkVBX4laErG2CK52i1reTHI_aVAsi9yrragT9rZgk1FpQ8MAzrfkopM_jccei5ejdEpTy75plixK8Zc05s17DpVr9Vcp8ybkhuZh0tN4hLjcL6IP3-aA8XLluOCG09QhBSpM1b3QDz2HoSPLUfyhfGMGChepKtbzNONT9_1Gpo0awJwsC-1LTfiqCvE-fOsBt_qVE1f9oPN0jrQU3pYAir7NaZZLth9YAA";  
    
    //without KID
    private static final String jwt = "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJodHRwczovL2p3dC1pZHAuZXhhbXBsZS5jb20iLCJzdWIiOiJtYWlsdG86bWlrZUBleGFtcGxlLmNvbSIsIm5iZiI6MTUwMDgzMTA1MCwiZXhwIjoxNTAwOTM0NjUwLCJpYXQiOjE1MDA4MzEwNTAsImp0aSI6ImlkMTIzNDU2IiwidHlwIjoiaHR0cHM6Ly9leGFtcGxlLmNvbS9yZWdpc3RlciJ9.R3-xMUwZK2s3f98GXp14faNplvk4ES0975NqAigDqrJaNIuXO6sW3kUByyPrxbCL5bs0bRpPQndlkpGn5bLmex_TS1OMIlyvEU4BmefDJQSGlR4mf-NQXUoc4zCKQMu7igaKSYXbgrKxkSXonrwjMUjoaGO485R3AJMfd39uo0-5xJDWDvK41DZfMDj3LMl-X7NrRi7mJw8f-STs2BptnVoI63D2LLO9KF2JcmiruPFcFAFW7y40KB6aKccnlufhajrjDM_ZrxJMCYYlcSAnztQ4IaHqixWEyukh_os_Wusxob78gnVcABwOsrZnPLVZi3Ixhj376zYHxvc6LiP9sg";
    
    private static final String jwksUrl="http://nwea-sandbox-sandbox.apigee.net/deyin-0519/jwks";
    
    /**
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        System.out.println("test");
        
        HttpsJwks httpsJkws = new HttpsJwks(jwksUrl);
        HttpsJwksVerificationKeyResolver httpsJwksKeyResolver = new HttpsJwksVerificationKeyResolver(httpsJkws);
        
        JwtConsumer jwtConsumer = new JwtConsumerBuilder()
                //.setRequireExpirationTime() // the JWT must have an expiration time
                //.setAllowedClockSkewInSeconds(30) // allow some leeway in validating time based claims to account for clock skew
                .setRequireSubject() // the JWT must have a subject claim
                .setExpectedIssuer("https://jwt-idp.example.com") // whom the JWT needs to have been issued by
                //.setExpectedAudience("Audience") // to whom the JWT is intended for
                .setVerificationKeyResolver(httpsJwksKeyResolver) // verify the signature with the public key
                .setJwsAlgorithmConstraints( // only allow the expected signature algorithm(s) in the given context
                        new AlgorithmConstraints(ConstraintType.WHITELIST, // which is only RS256 here
                                AlgorithmIdentifiers.RSA_USING_SHA256))
                .build(); // create the JwtConsumer instance

        try
        {
            //  Validate the JWT and process it to the Claims
            JwtClaims jwtClaims = jwtConsumer.processToClaims(jwt);
            System.out.println("JWT validation succeeded! " + jwtClaims);
        }
        catch (InvalidJwtException e)
        {
            // InvalidJwtException will be thrown, if the JWT failed processing or validation in anyway.
            // Hopefully with meaningful explanations(s) about what went wrong.
            System.out.println("Invalid JWT! " + e);
        }
    }

}
