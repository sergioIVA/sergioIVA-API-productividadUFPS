package services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;

public class AuthService {
	
	final String clave="miClave";

	public AuthService() {
		// TODO Auto-generated constructor stub
	}

	
	public void validarToken(String token)throws JWTVerificationException {
		
		
    	    Algorithm algorithm = Algorithm.HMAC256(clave);
    	    JWTVerifier verifier = JWT.require(algorithm)
    	        .withIssuer("auth0")
    	        .build(); //Reusable verifier instance
    	        DecodedJWT jwt = verifier.verify(token);
    	
	}
	
}
