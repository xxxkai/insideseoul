package com.example.insideseoul;

import android.util.Base64;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Cipher {
    private static byte[] salt;

    public static void setSalt(byte[] s){
        salt = s;
    }

    public static byte[] doCipher(byte[] pt){
        try{
            MessageDigest sh = MessageDigest.getInstance("SHA-256");
            if(salt != null)
                sh.update(salt);
            sh.update(pt);
            return sh.digest();
        }catch(NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getBase64(byte[] data){
        return Base64.encodeToString(data, 0);
    }

}
