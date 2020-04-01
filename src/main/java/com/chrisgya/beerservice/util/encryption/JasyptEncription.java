package com.chrisgya.beerservice.util.encryption;

import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;

public class JasyptEncription {
    /*
    NB: for java 8, remember to install Java Cryptography Extension (JCE) Unlimited Strength Jurisdiction Policy Files
     https://www.oracle.com/java/technologies/javase-jce8-downloads.html
     */

    private final static String secret = "4567FFGF@yrjdjd&%@sd#j";
    private final static String algorithm = "PBEWithMD5AndTripleDES";

    private static StandardPBEStringEncryptor getEncryptorInstance() {
        StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
        encryptor.setPassword(secret);
        encryptor.setAlgorithm(algorithm);
        return encryptor;
    }

    private static PooledPBEStringEncryptor getPooledPBEStringEncryptorInstance() {
        PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
        encryptor.setPoolSize(4); // set the size to match the number of CPC core on the server
        encryptor.setPassword(secret);
        encryptor.setAlgorithm(algorithm);
        return encryptor;
    }

    public static String encrypt(String privateData, Boolean usePool) {
        return usePool ? getPooledPBEStringEncryptorInstance().encrypt(privateData) :
                getEncryptorInstance().encrypt(privateData);
    }

    public static String decrypt(String encryptedData, Boolean usePool) {
        return usePool ? getPooledPBEStringEncryptorInstance().decrypt(encryptedData) :
                getEncryptorInstance().decrypt(encryptedData);
    }
}
