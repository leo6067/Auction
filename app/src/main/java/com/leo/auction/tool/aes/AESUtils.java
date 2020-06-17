package com.leo.auction.tool.aes;

/**
 * Created by hhr on 2017/11/3 0003.
 */

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/**
 * http://www.cnblogs.com/whoislcj/p/5473030.html<br>
 * <pre>
 * 什么是aes加密？
 * 高级加密标准（英语：Advanced Encryption Standard，缩写：AES），在密码学中又称Rijndael加密法，是美国联邦政府采用的一种区块加密标准。这个标准用来替代原先的DES，已经被多方分析且广为全世界所使用。
 * </pre>
 */
public class AESUtils {
//    private final static String HEX = "0123456789ABCDEF";
//    private static final String CBC_PKCS5_PADDING = "AES";//AES是加密方式 CBC是工作模式 PKCS5Padding是填充模式
//    private static final String AES = "AES";//AES 加密
//    private static final String SHA1PRNG = "SHA1PRNG";//// SHA1PRNG 强随机种子算法, 要区别4.2以上版本的调用方法
//
//    /*
//     * 生成随机数，可以当做动态的密钥 加密和解密的密钥必须一致，不然将不能解密
//     */
//    public static String generateKey() {
//        try {
//            SecureRandom localSecureRandom = SecureRandom.getInstance(SHA1PRNG);
//            byte[] bytes_key = new byte[20];
//            localSecureRandom.nextBytes(bytes_key);
//            String str_key = toHex(bytes_key);
//            return str_key;
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//
//    // 对密钥进行处理
//    private static byte[] getRawKey(byte[] seed) throws Exception {
//
//
//        KeyGenerator kgen = KeyGenerator.getInstance(AES);
//        //for android
//        SecureRandom sr = null;
//
//        int sdk_version = android.os.Build.VERSION.SDK_INT;
//        if(sdk_version>23){  // Android  6.0 以上
//            sr = SecureRandom.getInstance(SHA1PRNG,new CryptoProvider());
//        }else if(android.os.Build.VERSION.SDK_INT >= 17){   //4.2及以上
//            sr = SecureRandom.getInstance(SHA1PRNG, "Crypto");
//        }else {
//            sr = SecureRandom.getInstance(SHA1PRNG);
//        }
//
//
////        // for Java
////        sr = SecureRandom.getInstance(SHA1PRNG);
//        sr.setSeed(seed);
//        kgen.init(128, sr); //256 bits or 128 bits,192bits
//        //AES中128位密钥版本有10个加密循环，192比特密钥版本有12个加密循环，256比特密钥版本则有14个加密循环。
//        SecretKey skey = kgen.generateKey();
//        byte[] raw = skey.getEncoded();
//        return raw;
//    }
//
//    /*
//     * 加密
//     */
//    public static String encrypt(String key, String cleartext) {
//        if (TextUtils.isEmpty(cleartext)) {
//            return cleartext;
//        }
//        try {
//            byte[] result = encrypt(key, cleartext.getBytes());
//            return Base64Encoder.encode(result);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//    /*
//     * 加密
//     */
//    private static byte[] encrypt(String key, byte[] clear) throws Exception {
//        byte[] raw = getRawKey(key.getBytes());
//        SecretKeySpec skeySpec = new SecretKeySpec(raw, AES);
//        Cipher cipher = Cipher.getInstance(CBC_PKCS5_PADDING);
//        cipher.init(Cipher.ENCRYPT_MODE, skeySpec, new IvParameterSpec(new byte[cipher.getBlockSize()]));
//        byte[] encrypted = cipher.doFinal(clear);
//        return encrypted;
//    }
//
//
//    /*
//    * 解密
//    */
//    public static String decrypt(String key, String encrypted) {
//        if (TextUtils.isEmpty(encrypted)) {
//            return encrypted;
//        }
//        try {
//            byte[] enc = Base64Decoder.decodeToBytes(encrypted);
//            byte[] result = decrypt(key, enc);
//            return new String(result);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//    /*
//     * 解密
//     */
//    private static byte[] decrypt(String key, byte[] encrypted) throws Exception {
//        byte[] raw = getRawKey(key.getBytes());
//        SecretKeySpec skeySpec = new SecretKeySpec(raw, AES);
//        Cipher cipher = Cipher.getInstance(CBC_PKCS5_PADDING);
//        cipher.init(Cipher.DECRYPT_MODE, skeySpec, new IvParameterSpec(new byte[cipher.getBlockSize()]));
//        byte[] decrypted = cipher.doFinal(encrypted);
//        return decrypted;
//    }
//
//
//    //二进制转字符
//    public static String toHex(byte[] buf) {
//        if (buf == null)
//            return "";
//        StringBuffer result = new StringBuffer(2 * buf.length);
//        for (int i = 0; i < buf.length; i++) {
//            appendHex(result, buf[i]);
//        }
//        return result.toString();
//    }
//
//    private static void appendHex(StringBuffer sb, byte b) {
//        sb.append(HEX.charAt((b >> 4) & 0x0f)).append(HEX.charAt(b & 0x0f));
//    }
//
//
//
//    // 增加  CryptoProvider  类
//
//    public static  class CryptoProvider extends Provider {
//        /**
//         * Creates a Provider and puts parameters
//         */
//        public CryptoProvider() {
//            super("Crypto", 1.0, "HARMONY (SHA1 digest; SecureRandom; SHA1withDSA signature)");
//            put("SecureRandom.SHA1PRNG",
//                    "org.apache.harmony.security.provider.crypto.SHA1PRNG_SecureRandomImpl");
//            put("SecureRandom.SHA1PRNG ImplementedIn", "Software");
//        }
//    }
//
//
//
//
//
//
//
//
//
//
//
//
//
////
//    public static void main(String[] args) {
//        String jsonData = "ia9NFBubYRse4CMIdVqC1g==";
//        System.out.println("AESUtils jsonData ---->" + jsonData);
//        System.out.println("AESUtils jsonData length ---->" + jsonData.length());
//
//        //生成key
//        String secretKey = AESUtils.generateKey();
//        System.out.println("AESUtils secretKey ---->" + secretKey);
//
//        //AES加密
//        long start = System.currentTimeMillis();
//        String encryStr = AESUtils.encrypt(secretKey, jsonData);
//        long end = System.currentTimeMillis();
//        System.out.println("AESUtils encrypt cost time---->" + (end - start));
//        System.out.println("AESUtils jsonData(encrypted) ---->" + encryStr);
//        System.out.println("AESUtils jsonData(encrypted) length ---->" + encryStr.length());
//
//        //AES解密
//        start = System.currentTimeMillis();
//        String decryStr = AESUtils.decrypt(secretKey, encryStr);
//        end = System.currentTimeMillis();
//        System.out.println("AESUtils decrypt cost time---->" + (end - start));
//        System.out.println("AESUtils jsonData(decrypted) ---->" + decryStr);
//    }


    // 加密
    public static String Encrypt(String sSrc, String sKey) throws Exception {

        byte[] raw = sKey.getBytes("utf-8");
        SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");//"算法/模式/补码方式"
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
        byte[] encrypted = cipher.doFinal(sSrc.getBytes("utf-8"));

        return Base64Encoder.encode(encrypted);//此处使用BASE64做转码功能，同时能起到2次加密的作用。
    }

    // 解密
    public static String Decrypt(String sSrc, String sKey) throws Exception {
        try {
            // 判断Key是否正确
            if (sKey == null) {
                System.out.print("Key为空null");
                return null;
            }
            // 判断Key是否为16位
            if (sKey.length() != 16) {
                System.out.print("Key长度不是16位");
                return null;
            }
            byte[] raw = sKey.getBytes("utf-8");
            SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, skeySpec);
            byte[] encrypted1 = Base64Decoder.decodeToBytes(sSrc);//先用base64解密
            try {
                byte[] original = cipher.doFinal(encrypted1);
                String originalString = new String(original, "utf-8");
                return originalString;
            } catch (Exception e) {
                System.out.println(e.toString());
                return null;
            }
        } catch (Exception ex) {
            System.out.println(ex.toString());
            return null;
        }
    }




    // 压缩
    public static String compress(String str) throws IOException {
        if (str == null || str.length() == 0) {
            return str;
        }
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        GZIPOutputStream gzip = new GZIPOutputStream(out);
        gzip.write(str.getBytes());
        gzip.close();
        return out.toString("ISO-8859-1");
    }

    // 解压缩
    public static String uncompress(String str) throws IOException {
        if (str == null || str.length() == 0) {
            return str;
        }
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ByteArrayInputStream in = new ByteArrayInputStream(str
                .getBytes("ISO-8859-1"));
        GZIPInputStream gunzip = new GZIPInputStream(in);
        byte[] buffer = new byte[256];
        int n;
        while ((n = gunzip.read(buffer)) >= 0) {
            out.write(buffer, 0, n);
        }
        // toString()使用平台默认编码，也可以显式的指定如toString("GBK")
        return out.toString();
    }



}
