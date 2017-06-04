/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package security;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.SecureRandom;

/**
 *
 * @author andy
 */
public class HashConvertor {
    
    private String hashType;
    
    public HashConvertor(String hashType){
        this.hashType=hashType;
    }
    
    public HashConvertor(){
        this("SHA-256");
    }
    
    public String convert(String str){
        try{
            MessageDigest digest = MessageDigest.getInstance( hashType);
            SecureRandom random = new SecureRandom();
            String salt = new BigInteger(0x32,random).toString(0x20);
            str+=salt;
            byte[] hash = digest.digest(str.getBytes("UTF-8"));
            StringBuffer hexString = new StringBuffer();
            int lg = hash.length;
            for(int i = 0; i < lg; i++){
                String hex = Integer.toHexString(0xff & hash[i]);
                if(hex.length()==1){
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            return (hexString.toString()+salt);
        }catch(Exception ex){
            throw new RuntimeException(ex);
        }
    }
/*    public String convert(String str, String salt){
        try{
            MessageDigest digest = MessageDigest.getInstance( hashType);
            SecureRandom random = new SecureRandom();
            str+=salt;
            byte[] hash = digest.digest(str.getBytes("UTF-8"));
            StringBuffer hexString = new StringBuffer();
            int lg = hash.length;
            for(int i = 0; i < lg; i++){
                String hex = Integer.toHexString(0xff & hash[i]);
                if(hex.length()==1){
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            return (hexString.toString()+salt);
        }catch(Exception ex){
            throw new RuntimeException(ex);
        }
    }*/
}
