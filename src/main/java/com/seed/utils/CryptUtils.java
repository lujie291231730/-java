package com.seed.utils;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@SuppressWarnings("restriction")
public class CryptUtils {

	// region AES Methods

	private static String AESKEY = "bA@rHaBqJI$QpCcR";

	public static String AesEncrypt(String str) throws Exception {
		return AesEncrypt(str, AESKEY);
	}

	public static String AesEncrypt(String str, String key) throws Exception {
		if (StringUtils.StringIsEmpty(str) || StringUtils.StringIsEmpty(key))
			return "";
		
		Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
		cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(key.getBytes("utf-8"), "AES"));
		byte[] bytes = cipher.doFinal(str.getBytes("utf-8"));
		return new BASE64Encoder().encode(bytes);
	}

	public static String AesDecrypt(String str) throws Exception {
		return AesDecrypt(str, AESKEY);
	}

	public static String AesDecrypt(String str, String key) throws Exception {
		if (StringUtils.StringIsEmpty(str) || StringUtils.StringIsEmpty(key))
			return "";
		
		Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
		cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(key.getBytes("utf-8"), "AES"));
		byte[] bytes = new BASE64Decoder().decodeBuffer(str);
		bytes = cipher.doFinal(bytes);
		return new String(bytes, "utf-8");
	}

	// endregion AES Methods
	
	// region SHA 256 Methods
	
	public static String CryptSHA256 (String in) {
  	
  	try {
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			
			byte[] hash = digest.digest(in.getBytes("UTF-8"));
			
			StringBuffer out = new StringBuffer();
			
			for (int i = 0; i < hash.length; i++) {
				String hex = Integer.toHexString(0xff & hash[i]);
        if(hex.length() == 1) out.append('0');
        out.append(hex);
			}
			
			return out.toString();
		} catch (Exception e) {
			// TODO: handle exception
		}
  	
  	return "";
  }

	// endregion SHA 256 Methods
	
	// region MD5 Methods
	
	public static String GetMD5(String str) {   
    MessageDigest messageDigest = null;   

    try {   
        messageDigest = MessageDigest.getInstance("MD5");   

        messageDigest.reset();   

        messageDigest.update(str.getBytes("UTF-8"));   
    } catch (NoSuchAlgorithmException e) {   
        System.out.println("NoSuchAlgorithmException caught!");   
        System.exit(-1);   
    } catch (UnsupportedEncodingException e) {   
        e.printStackTrace();   
    }   

    byte[] byteArray = messageDigest.digest();   

    StringBuffer md5StrBuff = new StringBuffer();   

    for (int i = 0; i < byteArray.length; i++) {               
        if (Integer.toHexString(0xFF & byteArray[i]).length() == 1)   
            md5StrBuff.append("0").append(Integer.toHexString(0xFF & byteArray[i]));   
        else   
            md5StrBuff.append(Integer.toHexString(0xFF & byteArray[i]));   
    }   

    return md5StrBuff.toString().toUpperCase();   
	}
	
	// endregion MD5 Methods
	
}
