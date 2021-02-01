package com.tengyun.redis.verifytest.common;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.SecureRandom;

/**
 * @author tengyun
 * @date 2021/1/31 16:37
 **/
@Component
public class AesSupport implements InitializingBean {

    private static final String KEY_ALGORITHM = "AES";

    @Value("${aes.secret}")
    private String secret;

    private Cipher decryptCipher;

    private Cipher encryptCipher;

    private static SecretKey getSecretKey(String password) throws Exception {
        SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
        random.setSeed(password.getBytes());
        KeyGenerator kg = KeyGenerator.getInstance(KEY_ALGORITHM);
        kg.init(128, random);
        SecretKey secretKey = kg.generateKey();
        return secretKey;
    }

    public String decrypt(String content) throws Exception {
        byte[] source = Base64.decodeBase64(content);
        byte[] result = decryptCipher.doFinal(source);
        return new String(result, "utf-8");
    }

    public String encrypt(String content) throws Exception {
        byte[] byteContent = content.getBytes("utf-8");
        byte[] result = encryptCipher.doFinal(byteContent);
        String text = Base64.encodeBase64String(result);
        return text;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        decryptCipher = Cipher.getInstance(KEY_ALGORITHM);
        decryptCipher.init(Cipher.DECRYPT_MODE, getSecretKey(secret));
        encryptCipher = Cipher.getInstance(KEY_ALGORITHM);
        encryptCipher.init(Cipher.ENCRYPT_MODE, getSecretKey(secret));
    }

}
