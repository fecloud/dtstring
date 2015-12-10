/**
 * Test.java Created on 2015-12-8
 */
package cn.dotui.strings.test;

import cn.dotui.strings.Ddpt;
import cn.dotui.strings.out.JavaFileOut;
import cn.dotui.strings.out.SysteOut;
import cn.dotui.strings.parse.JavaFileParse;

/**
 * The class <code>Test</code>
 * 
 * @author Feng OuYang
 * @version 1.0
 */
public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String src = "test";
		String srcclz = "cn.dotui.strings.R";
		String dest = "gen";
		String destclz = "cn.dotui.qq.qq.R";
		Ddpt ddpt = new Ddpt(new JavaFileParse(src, srcclz), destclz,
				new SysteOut());
		ddpt.gen();

		ddpt = new Ddpt(new JavaFileParse(src, srcclz), destclz,
				new JavaFileOut(dest, destclz));
		ddpt.gen();

	}

}
