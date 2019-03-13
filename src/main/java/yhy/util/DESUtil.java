package yhy.util;


import org.apache.commons.codec.binary.Base64;
import yhy.exception.ServiceException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class DESUtil {

    public static String encrypt(String encryptString, String encryptKey) throws ServiceException {
        try {
            SecretKeySpec key = new SecretKeySpec(encryptKey.getBytes("utf-8"), "DES");
            Cipher cipher = Cipher.getInstance("DES");
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] encryptedData = cipher.doFinal(encryptString.getBytes("utf-8"));
            return Base64.encodeBase64String(encryptedData);
        } catch (UnsupportedEncodingException | NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException e) {
//            logger.error("DES encrypt failure", e);
            throw new ServiceException("DES-加密失败", e);
        }
    }

    public static String decrypt(String decryptString, String decryptKey) throws ServiceException {
        try {
            byte[] byteMi = Base64.decodeBase64(decryptString);
            SecretKeySpec key = new SecretKeySpec(decryptKey.getBytes("utf-8"), "DES");
            Cipher cipher;
            cipher = Cipher.getInstance("DES");
            cipher.init(Cipher.DECRYPT_MODE, key);
            byte decryptedData[] = cipher.doFinal(byteMi);
            return new String(decryptedData, "utf-8");
        } catch (UnsupportedEncodingException | NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException e) {
//            logger.error("DES decryption failure", e);
            throw new ServiceException("DES-解密失败", e);
        }
    }

    public static void main(String[] args) {

    }
}
