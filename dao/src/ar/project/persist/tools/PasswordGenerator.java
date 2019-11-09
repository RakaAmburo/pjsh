package ar.project.persist.tools;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordGenerator {
	
	public static void main(String[] args) throws Exception {
		 
		int i = 0;
		while (i < 10) {
			String password = "123456";
			System.out.println(generatePassword(password));
			i++;
		}
		
		System.exit(0);
		
		int x = 0;
		List<String> lista = new ArrayList<String>();
		//lista.add("AAAA");
		while (x < 100000){
			String clave = generateUser().toUpperCase();
			if (!lista.contains(clave)){
				lista.add(clave);
			} else {
				System.out.println(lista.size());
				throw new Exception("Numero repetido");
			}
			
			System.out.println(clave);
			x++;
		}
		
		/*if (lista.contains("AAAA")){
			throw new Exception("Numero repetido");
		}*/
	 
	  }
	
	public static String generatePassword(String password){
		
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String hashedPassword = passwordEncoder.encode(password);
		
		return hashedPassword;
	}
	
	
	public static String generateUser(){
		
		SecureRandom random = new SecureRandom();
		String num = new BigInteger(50 , random).toString(32).toUpperCase();
		return num;
		
	}

}
