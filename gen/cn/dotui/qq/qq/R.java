/* AUTO-GENERATED FILE.  DO NOT MODIFY.
 *
 * This class was automatically generated by the
 * ddpt tool from the resource data it found.  It
 * should not be modified by hand.
 */

package cn.dotui.qq.qq;

public final class R {

    public static final byte key [] = {57,56,98,52,50,54,56,101,45,57,51,98,51,45,52,54,57,98,45,97,52,98,56,45,48,49,51,50,99,54,97,97,101,52,49,50};
    public static final String ERROR_CODE = "/qZRid9bfMwJ7JvTKF3+Ew==";
    public static final String s = "/qZRid9bfMwJ7JvTKF3+Ew==";

   /**
    * 解密字符串
    * 
    * @param string
    * @return
    */
    public static String getString(String string) {
        try {
            final byte[] buf = android.util.Base64.decode(string, android.util.Base64.DEFAULT);
            final byte[] bt = decrypt(buf, key);
            return new String(bt);
        } catch (Exception e) {
        }
        return "";
    }

   /**
    * Description 根据键值进行解密
    * 
    * @param data
    * @param key
    *            加密键byte数组
    * @return
    * @throws Exception
    */
    private static byte[] decrypt(byte[] data, byte[] password) throws Exception {
        javax.crypto.KeyGenerator kgen = javax.crypto.KeyGenerator.getInstance("AES");
        kgen.init(128, new java.security.SecureRandom(password));
        javax.crypto.SecretKey secretKey = kgen.generateKey();
        final byte[] enCodeFormat = secretKey.getEncoded();
        javax.crypto.spec.SecretKeySpec key = new javax.crypto.spec.SecretKeySpec(enCodeFormat, "AES");
        javax.crypto.Cipher cipher = javax.crypto.Cipher.getInstance("AES");
        cipher.init(javax.crypto.Cipher.DECRYPT_MODE, key);
        return cipher.doFinal(data);
    }

}
