package cn.dotui.strings;

import java.io.IOException;
import java.util.UUID;

import org.apache.commons.codec.binary.Base64;

public class DESUtil {

	public static void main(String[] args) throws IOException, Exception {
		String time = UUID.randomUUID().toString();
		time = time.substring(0, 8);
		System.out.println(time);
		byte[] key = time.getBytes("UTF-8");
		String data = "123412341234123";
		final String string = encrypt(data, key);
		System.out.println(string);
		System.out.println(decrypt(string, key));
		final byte []keyssss = {54,99,102,98,53,100,48,97};
		System.out.println(decrypt("dNED0KlQRArzJzsMyVGxyA==", keyssss));
	}

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
			e.printStackTrace();
		}
		return "";
	}

	/**
	 * base
	 * 
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
	 * 
	 * @param data
	 * @param password
	 * @return
	 * 
	 *         public static String getString(String string) { try { final
	 *         byte[] buf = Base64.decodeBase64(data); final byte[] bt =
	 *         decrypt(buf, password); return new String(bt); } catch (Exception
	 *         e) { } return ""; }
	 */

	/**
	 * 根据键值进行加密
	 * 
	 * @param data
	 * @param key
	 *            加密键byte数组
	 * @return
	 * @throws Exception
	 */
	private static byte[] encrypt(byte[] data, byte[] password)
			throws Exception {
		final java.security.Key key = new javax.crypto.spec.SecretKeySpec(
				password, "DES");
		final javax.crypto.Cipher cipher = javax.crypto.Cipher.getInstance("DES");
		cipher.init(javax.crypto.Cipher.ENCRYPT_MODE, key);
		return cipher.doFinal(data);
	}

	/**
	 * 根据键值进行解密
	 * 
	 * @param data
	 * @param key
	 *            加密键byte数组
	 * @return
	 * @throws Exception
	 */
	private static byte[] decrypt(byte[] data, byte[] password)
			throws Exception {
		final java.security.Key key = new javax.crypto.spec.SecretKeySpec(
				password, "DES");
		final javax.crypto.Cipher cipher = javax.crypto.Cipher
				.getInstance("DES");
		cipher.init(javax.crypto.Cipher.DECRYPT_MODE, key);

		return cipher.doFinal(data);
	}
}