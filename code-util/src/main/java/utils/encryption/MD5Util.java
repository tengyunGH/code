package utils.encryption;

import sun.misc.BASE64Encoder;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author tengyun
 * @date 2021/2/19 10:33
 **/
public class MD5Util {

    /**
     * @author dls
     * @Description: 返回MD5加密后在前边加上{MD5}的密码
     * @date 2018/9/17 17:13
     */
    public static String getMd5(String data) throws Exception {
        MessageDigest md = MessageDigest.getInstance("md5");
        byte[] md5 = md.digest(data.getBytes());
        String sData = new String(md5, "utf-8");
        BASE64Encoder be = new BASE64Encoder();
        String base64 = "{MD5}" + be.encode(md5);
        //下边一行是为了防止有时候在url传输过程中+号会变成空格的问题
        //base64 = base64.replace("+","%2B");
        return base64;
    }
    /**
     * MD5加密
     *
     * @param inStr
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String MD5(String inStr) throws UnsupportedEncodingException {
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
            md5.reset();
            md5.update(inStr.getBytes("UTF-8"));
        } catch (NoSuchAlgorithmException e) {
            System.out.println("NoSuchAlgorithmException caught!");
            System.exit(-1);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        byte[] byteArray = md5.digest();

        StringBuffer md5StrBuff = new StringBuffer();

        for (int i = 0; i < byteArray.length; i++) {
            if (Integer.toHexString(0xFF & byteArray[i]).length() == 1) {
                md5StrBuff.append("0").append(Integer.toHexString(0xFF & byteArray[i]));
            } else {
                md5StrBuff.append(Integer.toHexString(0xFF & byteArray[i]));
            }
        }
        return String.valueOf(md5StrBuff);
    }
}
