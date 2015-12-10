package cn.dotui.strings;

import java.io.IOException;

import org.apache.commons.codec.binary.Base64;

public class AESUtil {

	public static void main(String[] args) throws IOException, Exception {
		byte[] key = { 12, 13 };
		String data = "123412341234123";
		final String string = encrypt(data, key);
		System.out.println(string);
		System.out.println(decrypt(string, key));
	}

	private final static String AES = "AES";

	/**
	 * Description 根据键值进行加密
	 * 
	 * @param data
	 * @param key
	 *            加密键byte数组
	 * @return
	 * @throws Exception
	 */
	public static String encrypt(String data, byte[] password) {
		try {
			final byte[] bt = encrypt(data.getBytes("UTF-8"), password);
			return Base64.encodeBase64String(bt);
		} catch (Exception e) {
		}
		return "";
	}

	/**
	 * base
	 * @param data
	 * @param password
	 * @return
	 */
	public static String decrypt(String data, byte[] password) {
		try {
			final byte[] buf = Base64.decodeBase64(data);
			final byte[] bt = decrypt(buf, password);
			return new String(bt);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}
	
	/**
	 * 解密字符串
	 * @param data
	 * @param password
	 * @return
	 
	public static String getString(String string)  {
		try {
			final byte[] buf = Base64.decodeBase64(data);
			final byte[] bt = decrypt(buf, password);
			return new String(bt);
		} catch (Exception e) {
		}
		return "";
	}
	*/

	/**
	 * Description 根据键值进行加密
	 * 
	 * @param data
	 * @param key
	 *            加密键byte数组
	 * @return
	 * @throws Exception
	 */
	private static byte[] encrypt(byte[] data, byte[] password)
			throws Exception {
		javax.crypto.KeyGenerator kgen = javax.crypto.KeyGenerator
				.getInstance(AES);
		kgen.init(128, new java.security.SecureRandom(password));
		javax.crypto.SecretKey secretKey = kgen.generateKey();
		byte[] enCodeFormat = secretKey.getEncoded();
		javax.crypto.spec.SecretKeySpec key = new javax.crypto.spec.SecretKeySpec(
				enCodeFormat, AES);
		javax.crypto.Cipher cipher = javax.crypto.Cipher.getInstance(AES);// 创建密码器
		cipher.init(javax.crypto.Cipher.ENCRYPT_MODE, key);// 初始化
		return cipher.doFinal(data);
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
	private static byte[] decrypt(byte[] data, byte[] password)
			throws Exception {
		javax.crypto.KeyGenerator kgen = javax.crypto.KeyGenerator
				.getInstance(AES);
		kgen.init(128, new java.security.SecureRandom(password));
		javax.crypto.SecretKey secretKey = kgen.generateKey();
		byte[] enCodeFormat = secretKey.getEncoded();
		javax.crypto.spec.SecretKeySpec key = new javax.crypto.spec.SecretKeySpec(
				enCodeFormat, AES);
		javax.crypto.Cipher cipher = javax.crypto.Cipher.getInstance(AES);// 创建密码器
		cipher.init(javax.crypto.Cipher.DECRYPT_MODE, key);// 初始化

		return cipher.doFinal(data);
	}
}