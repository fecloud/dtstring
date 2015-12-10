/**
 * SysteOut.java Created on 2015-12-8
 */
package cn.dotui.strings.out;

import java.io.PrintStream;

/**
 * The class <code>SysteOut</code>	
 * @author Feng OuYang
 * @version 1.0
 */
public class SysteOut extends PrintStream{

	/**
	 * @param out
	 */
	public SysteOut() {
		super(System.out);
	}

}
