/**
 * DtString.java Created on 2015-12-8
 */
package cn.dotui.strings;

/**
 * The class <code>DtString</code>
 * 
 * @author Feng OuYang
 * @version 1.0
 */
public class DtString {

	private String key;

	private String value;

	/**
	 * 
	 */
	public DtString() {
		super();
	}

	/**
	 * @param key
	 * @param value
	 */
	public DtString(String key, String value) {
		super();
		this.key = key;
		this.value = value;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "DtString [key=" + key + ", value=" + value + "]";
	}

}
