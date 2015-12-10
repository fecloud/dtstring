/**
 * CompilerTest.java Created on 2015-12-10
 */
package cn.dotui.strings.test;

import cn.dotui.strings.compiler.JavaFileCompiler;

/**
 * The class <code>CompilerTest</code>
 * 
 * @author Feng OuYang
 * @version 1.0
 */
public class CompilerTest {

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		final JavaFileCompiler compiler = new JavaFileCompiler("tt",
				"test/cn/dotui/strings/R.java");
		System.out.println(compiler.compiler());

	}
}
