package edu.xihua.project.pms;

import edu.xihua.project.pms.util.AESUtils;
import org.apache.commons.codec.binary.Base64;

/**
 * @author intent <a>zzy.main@gmail.com</a>
 * @date 2021/5/30 12:16 上午
 * @since 1.0
 */
public class AESDemo {
    public static void main(String[] args) throws Exception {
        String appId = "appid";
        String sessionKey = "key";
        String encryptedData = "data";
        String iv = "iv";
        String data = AESUtils.decrypt(
                Base64.decodeBase64(encryptedData),
                Base64.decodeBase64(sessionKey),
                Base64.decodeBase64(iv));
        System.out.println(data);
    }
}
