/**
 * Ddpt.java Created on 2015-12-8
 */
package cn.dotui.strings;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.UUID;

/**
 * The class <code>Ddpt</code>
 * 
 * @author Feng OuYang
 * @version 1.0
 */
public final class Ddpt {

	private String className;

	private SourceParse parse;

	private OutputStream outputStream;

	private byte[] key;

	private static final String line_separator = System
			.getProperty("line.separator");

	/**
	 * @param parse
	 */
	public Ddpt(SourceParse parse, String className, OutputStream outputStream) {
		super();
		this.parse = parse;
		this.className = className;
		this.outputStream = outputStream;
		try {
			key = UUID.randomUUID().toString().getBytes("UTF-8");
		} catch (UnsupportedEncodingException e) {
		}
	}

	public SourceParse getParse() {
		return parse;
	}

	public void setParse(SourceParse parse) {
		this.parse = parse;
	}

	private void writeln() {
		final StringBuilder builder = new StringBuilder();
		builder.append(line_separator);
		write(builder.toString());
	}

	private void writeln(String string) {
		final StringBuilder builder = new StringBuilder(string);
		builder.append(line_separator);
		write(builder.toString());
	}

	private void write(String string) {
		try {
			outputStream.write(string.getBytes("UTF-8"));
			outputStream.flush();
		} catch (IOException e) {
		}
	}

	private void close() {
		try {
			outputStream.close();
		} catch (IOException e) {
		}
	}

	public boolean gen() {
		if (null != parse) {

			boolean parsed = parse.parse();
			if (!parsed) {
				return false;
			}

			DtString dtString = null;

			writeln(getHead());

			writeln(getDelarePackage());
			writeln();
			writeln(getDelareClass());

			writeln("");

			writeln(String.format("    public static final byte %s [] = {%s};",
					"key", join(key, ",")));

			while (null != (dtString = parse.next())) {
				writeln(String.format(
						"    public static final String %s = \"%s\";",
						dtString.getKey(),
						AESUtil.encrypt(dtString.getValue(), key)));
			}

			writeln("");

			writeln(getStringMethod());

			writeln(getFoot());

			writeln("}");

			close();
			return true;
		}

		return false;
	}

	private String getHead() {
		final StringBuilder builder = new StringBuilder();
		builder.append("/* AUTO-GENERATED FILE.  DO NOT MODIFY.").append(
				line_separator);
		builder.append(" *").append(line_separator);
		builder.append(" * This class was automatically generated by the")
				.append(line_separator);
		builder.append(" * ddpt tool from the resource data it found.  It")
				.append(line_separator);
		builder.append(" * should not be modified by hand.").append(
				line_separator);
		builder.append(" */").append(line_separator);
		return builder.toString();
	}

	private String getDelarePackage() {
		final StringBuilder builder = new StringBuilder();
		if (!getPackageName().equals("")) {
			builder.append(String.format("package %s;", getPackageName()));

		}
		return builder.toString();
	}

	private String getDelareClass() {
		final StringBuilder builder = new StringBuilder();
		if (!getClassName().equals("")) {
			builder.append(String.format("public final class %s {",
					getClassName()));
		}
		return builder.toString();
	}

	private String getClassName() {
		if (className != null) {
			final int index = className.lastIndexOf(".");
			if (index > 0 && index < className.length() - 1) {
				return className.substring(index + 1);
			}
		}
		return "";
	}

	private String getPackageName() {
		if (className != null) {
			final int index = className.lastIndexOf(".");
			if (index > 0 && index < className.length() - 1) {
				return className.substring(0, index);
			}
		}
		return "";
	}

	private String getStringMethod() {
		final StringBuilder builder = new StringBuilder();
		builder.append("   /**").append(line_separator);
		builder.append("    * 解密字符串").append(line_separator);
		builder.append("    * ").append(line_separator);
		builder.append("    * @param string").append(line_separator);
		builder.append("    * @return").append(line_separator);
		builder.append("    */").append(line_separator);
		builder.append("    public static String getString(String string) {")
				.append(line_separator);
		builder.append("        try {").append(line_separator);
		builder.append(
				"            final byte[] buf = android.util.Base64.decode(string, android.util.Base64.DEFAULT);")
				.append(line_separator);
		builder.append("            final byte[] bt = decrypt(buf, key);")
				.append(line_separator);
		builder.append("            return new String(bt);").append(
				line_separator);
		builder.append("        } catch (Exception e) {")
				.append(line_separator);
		builder.append("        }").append(line_separator);
		builder.append("        return \"\";").append(line_separator);
		builder.append("    }").append(line_separator);
		return builder.toString();
	}

	private String getFoot() {
		final StringBuilder builder = new StringBuilder();
		builder.append("   /**").append(line_separator);
		builder.append("    * Description 根据键值进行解密").append(line_separator);
		builder.append("    * ").append(line_separator);
		builder.append("    * @param data").append(line_separator);
		builder.append("    * @param key").append(line_separator);
		builder.append("    *            加密键byte数组").append(line_separator);
		builder.append("    * @return").append(line_separator);
		builder.append("    * @throws Exception").append(line_separator);
		builder.append("    */").append(line_separator);
		builder.append(
				"    private static byte[] decrypt(byte[] data, byte[] password) throws Exception {")
				.append(line_separator);
		builder.append(
				"        javax.crypto.KeyGenerator kgen = javax.crypto.KeyGenerator.getInstance(\"AES\");")
				.append(line_separator);
		builder.append(
				"        kgen.init(128, new java.security.SecureRandom(password));")
				.append(line_separator);
		builder.append(
				"        javax.crypto.SecretKey secretKey = kgen.generateKey();")
				.append(line_separator);
		builder.append(
				"        final byte[] enCodeFormat = secretKey.getEncoded();")
				.append(line_separator);
		builder.append(
				"        javax.crypto.spec.SecretKeySpec key = new javax.crypto.spec.SecretKeySpec(enCodeFormat, \"AES\");")
				.append(line_separator);
		builder.append(
				"        javax.crypto.Cipher cipher = javax.crypto.Cipher.getInstance(\"AES\");")
				.append(line_separator);
		builder.append(
				"        cipher.init(javax.crypto.Cipher.DECRYPT_MODE, key);")
				.append(line_separator);
		builder.append("        return cipher.doFinal(data);").append(
				line_separator);
		builder.append("    }").append(line_separator);
		return builder.toString();
	}

	private static final String join(byte[] bytes, String join) {
		final StringBuilder builder = new StringBuilder();
		if (bytes != null && bytes.length > 0) {
			for (int i = 0; i < bytes.length; i++) {
				if (i > 0) {
					builder.append(join);
				}
				builder.append(bytes[i]);
			}
		}
		return builder.toString();
	}
}
