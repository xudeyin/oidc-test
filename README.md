# oidc-test

## notes:

### key gen commands:
```
openssl genrsa -out private.pem 2048
openssl rsa -in private.pem -pubout -outform PEM -out public.pem
```

### kid
https://stackoverflow.com/questions/43867440/whats-the-meaning-of-the-kid-claim-in-a-jwt-token
```
4.1.4. "kid" (Key ID) Header Parameter

The kid (key ID) Header Parameter is a hint indicating which key was used to secure the JWS. This parameter allows originators to explicitly signal a change of key to recipients. The structure of the  kid value is unspecified. Its value MUST be a case-sensitive string. Use of this Header Parameter is OPTIONAL.

When used with a JWK, the kid value is used to match a JWK kid parameter value.
```
* If JWT header has "kid", the JWK public key MUST also have "kid"
* If JWT header does not have "kid", the "kid" field is optional in JWK.

JWT (use https://jwt.io to generate the signed string.)
```
{
  "alg": "RS256",
  "typ": "JWT",
  "kid": "deyin-key"
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
```

Use the tool here to convert public key to JWK: https://www.npmjs.com/package/rsa-pem-to-jwk
```
{
  "keys": [
    { "kty": "RSA", 
      "alg": "RS256", 
      "use": "sig", 
      "kid": "deyin-key",
      "n": "ANKOwP7lVsZFtPUNP_HZIdocPsnGaso075LgdACeU4jgpxJs6MQfyRkB1twvQ4886O1HRm3aT-f_X3bJEL7xKX1l61ac29Jsdw-iJa50PCuMdbHQBAeHB2kQ_GyirjCEhI1NkjccvoLXfhK-fiiCxVI8RJcxhaZZtc-QWe4ypT-U7sWeLqlSpZk80apYD9-HdI5YpGH7cGW6-wjFsKYZTZsvyGuqrehFb0CFgUtS71dC-rVBN-VZu043y8ukcjX99EWnlC-etH7WYNUMTIHBS4OetQRj-JxUpXReiIMJvivHl1jEyM8Fpqf5-RNcptci9WSQBUsLoWe1Zs-f4q5BVNk",
      "e": "AQAB" }]
}
```