package org.fyp.ems.questionSettingModule.Security;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;

public class password_incryptor {
	
	private static final Random RANDOM = new SecureRandom();
	
	public String md5(String input) {
		
		String md5 = null;
		
		if(null == input) return null;
		
		try {
			
		//Create MessageDigest object for MD5
		MessageDigest digest = MessageDigest.getInstance("MD5");
		
		//Update input string in message digest
		digest.update(input.getBytes(), 0, input.length());

		//Converts message digest value in base 16 (hex) 
		md5 = new BigInteger(1, digest.digest()).toString(16);

		} catch (NoSuchAlgorithmException e) {

			e.printStackTrace();
		}
		return md5;
	}
	
	public String generateSalt()
	{
		byte[] salt = new byte[16];
	    RANDOM.nextBytes(salt);
	    return salt.toString().replace("[", "");
	}
	public String generateimagePath()
	{
		final Random RANDOM = new SecureRandom();
		byte[] salt = new byte[16];
		byte[] salt2 = new byte[16];
		byte[] salt3 = new byte[16];
		byte[] salt4 = new byte[16];
	    RANDOM.nextBytes(salt);
	    System.out.println(salt);
	    String random = salt.toString();
	    RANDOM.nextBytes(salt2);
	    System.out.println(salt2);
	    random =  random + salt2.toString();
	    RANDOM.nextBytes(salt3);
	    System.out.println(salt3);
	    random = random + salt3.toString();
	    RANDOM.nextBytes(salt4);
	    System.out.println(salt4);
	    random = random + salt4.toString();

	    return random.replace("[", "");
	}

}
