package com.util;
import java.io.IOException;
import java.io.UnsupportedEncodingException;


import com.service.LoginService;
import org.apache.commons.lang3.StringUtils;
import org.jfree.util.Log;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import sun.misc.*;

/**
 * basic Auth 认证方式
 *
 */
public class BasicAuthenticationUtil {
    private static final String KEY_MAC_SHA1 = "HmacSHA1";
    private static final String CHARSET_UTF8 = "UTF-8";
    @Autowired
    private static LoginService loginService;

    public static boolean checkHeaderAuth(HttpServletRequest request) {
        //获取时间Date和密文A
        String date_header = request.getHeader("date_header");
        String cyphertextA =request.getHeader("cyphertext");
        if (date_header != null) {
            //base64解密A得到用户名和密文B
            String decodedAuth = base64Decode(cyphertextA);
            String []StrB=decodedAuth.split(":");
            if(StrB.length==2){
                String userName=StrB[0];
                //用户名去数据库查密码 loginService.getPasswd(name);
                String passwd=loginService.getPasswd(userName);
                //用得到的 密码 Date,用hmacSha1Encrypt 进行加密，得到密文 与密文B比较
                try {
                    String hmac = hmacSha1Encrypt(passwd,date_header);
                    System.out.println(hmac.equals(StrB[1]));
                    return hmac.equals(base64Decode(StrB[1]));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return false;
        }
        return false;
    }

    public static boolean checkUserAuth(HttpServletRequest request, String sessionName) {
        String sessionAuth = null;
        if (StringUtils.isNotBlank(sessionName)) {
            sessionAuth = (String) request.getSession().getAttribute(sessionName);
        }
        if (sessionAuth != null) {
            Log.info("this is next step");
            return true;
        }
        return false;
    }

    /**
     * base64解码
     *
     */
    public static String base64Decode(String str) {
        if (StringUtils.isBlank(str)) {
            return null;
        }
        String s = null;
        try {
            BASE64Decoder decoder = new BASE64Decoder();
            byte[] b = decoder.decodeBuffer(str);
            s = new String(b, CHARSET_UTF8);
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            s = null;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            s = null;
        }
        return s;
    }
    /**
     * hmacsha1 加密  用DATE和用户密码进行加密 得到HMAC（基于哈希的消息验证代码）
     *
     */
    public static String hmacSha1Encrypt(String encryptText, String encryptKey) throws Exception {
        byte[] text = encryptText.getBytes(CHARSET_UTF8);
        byte[] keyData = encryptKey.getBytes(CHARSET_UTF8);
        SecretKeySpec secretKey = new SecretKeySpec(keyData, KEY_MAC_SHA1);
        Mac mac = Mac.getInstance(secretKey.getAlgorithm());
        mac.init(secretKey);
        return new String(mac.doFinal(text));
    }

}