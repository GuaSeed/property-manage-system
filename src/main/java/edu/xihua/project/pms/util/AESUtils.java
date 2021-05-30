package edu.xihua.project.pms.util;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.AlgorithmParameters;
import java.security.Key;
import java.security.Security;

/**
 * @author intent <a>zzy.main@gmail.com</a>
 * @date 2021/5/30 12:47 上午
 * @since 1.0
 */
public class AESUtils {
    //算法名
    public static final String KEY_ALGORITHM = "AES";
    //加解密算法/模式/填充方式
    //可以任意选择，为了方便后面与iOS端的加密解密，采用与其相同的模式与填充方式
    //ECB模式只用密钥即可对数据进行加密解密，CBC模式需要添加一个参数iv
    public static final String CIPHER_ALGORITHM = "AES/CBC/PKCS7Padding";

    static {
        Security.addProvider(new BouncyCastleProvider());
    }

    //生成密钥

    private static byte[] generateKey(byte[] aesKeyData) throws Exception {
        Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
        /*
        KeyGenerator kgen =KeyGenerator.getInstance(KEY_ALGORITHM);
        kgen.init(128, new SecureRandom(aesKey.getBytes()));
SecretKey secretKey = kgen.generateKey();
byte[] encodeFormat = secretKey.getEncoded();
SecretKeySpec keySpec = new SecretKeySpec(encodeFormat, "AES");
        return keySpec.getEncoded();
        */
        return aesKeyData;
    }

    //生成iv
    private static AlgorithmParameters generateIV(byte[] iv) throws Exception {
        AlgorithmParameters params = AlgorithmParameters.getInstance(KEY_ALGORITHM);
        params.init(new IvParameterSpec(iv));
        return params;
    }

    //解密
    public static String decrypt(byte[] encryptedData, byte[] aesKeyData, byte[] ivBytes) throws Exception {
//        byte[] encryptedData = hexStringToByte(encryptedStr);
        byte[] keyBytes = generateKey(aesKeyData);
        Key key = convertToKey(keyBytes);
        Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
        AlgorithmParameters iv = generateIV(ivBytes);
        //设置为解密模式
        cipher.init(Cipher.DECRYPT_MODE, key, iv);
        byte[] decryptData = cipher.doFinal(encryptedData);
        return new String(decryptData);
    }

    //转化成JAVA的密钥格式

    private static Key convertToKey(byte[] keyBytes) {
        return new SecretKeySpec(keyBytes, KEY_ALGORITHM);
    }

    /**
     * 十六进制字符串转换成数组
     *
     * @param hex
     * @return
     */
    private static byte[] hexStringToByte(String hex) {
        int len = (hex.length() / 2);
        byte[] result = new byte[len];
        char[] achar = hex.toCharArray();
        for (int i = 0; i < len; i++) {
            int pos = i * 2;
            result[i] = (byte) (toByte(achar[pos]) << 4 | toByte(achar[pos + 1]));
        }
        return result;
    }

    private static byte toByte(char c) {
        return (byte) "0123456789abcdef".indexOf(c);
    }

    /**
     * 把字节数组转换成16进制字符串
     *
     * @param bArray
     * @return
     */
    private static final String bytesToHexString(byte[] bArray) {
        StringBuffer sb = new StringBuffer(bArray.length);
        String sTemp;
        for (int i = 0; i < bArray.length; i++) {
            sTemp = Integer.toHexString(0xFF & bArray[i]);
            if (sTemp.length() < 2) {
                sb.append(0);
            }
            sb.append(sTemp.toLowerCase());
        }
        return sb.toString();
    }
}
