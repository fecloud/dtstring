/**
 * JavaFileParse.java Created on 2015-12-10
 */
package cn.dotui.strings.parse;

import java.io.File;

import cn.dotui.strings.classes.FileClassLoader;
import cn.dotui.strings.compiler.JavaFileCompiler;

/**
 * The class <code>JavaFileParse</code>
 * 
 * @author Feng OuYang
 * @version 1.0
 */
public class JavaFileParse extends ClassParse {

	protected String className;

	protected String classFile;

	protected String sourcePath;

	protected String classOutDir;

	/**
	 * @param className
	 * @param sourcePath
	 */
	public JavaFileParse(String sourcePath, String className) {
		super(className);
		this.className = className;
		this.sourcePath = sourcePath;

		if (!new File(sourcePath).exists()) {
			throw new RuntimeException(String.format(
					"path %s not found please check", sourcePath));
		}

		classFile = sourcePath + File.separator
				+ replaceAll(className, ".", File.separator) + ".java";

		if (!new File(classFile).exists()) {
			throw new RuntimeException(String.format(
					"not found class %splease check", className));
		}
	}

	/**
	 * 编译java文件
	 * 
	 * @return
	 */
	protected boolean compileJava() {
		final File outDir = new File(System.getProperty("java.io.tmpdir"),
				"dtstring" + System.currentTimeMillis());
		if (!outDir.exists()) {
			outDir.mkdirs();
		}
		classOutDir = outDir.getAbsolutePath();

		final JavaFileCompiler compiler = new JavaFileCompiler(classOutDir,
				classFile);
		try {
			return compiler.compiler();
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println(classFile + " compiler fail");
		}
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.dotui.strings.parse.ClassParse#loadClass(java.lang.String)
	 */
	@Override
	protected Class<?> loadClass(String name) throws ClassNotFoundException {
		if (compileJava()) {
			final FileClassLoader classLoader = new FileClassLoader(classOutDir);
			return classLoader.loadClass(className);
		}
		return null;
	}

	private final static String replaceAll(String src, String regex,
			String replacement) {
		final StringBuilder builder = new StringBuilder();
		for (int i = 0; i < src.length(); i++) {
			final char c = src.charAt(i);
			if (("" + c).equals(regex)) {
				builder.append(replacement);
			} else {
				builder.append(c);
			}
		}
		return builder.toString();
	}
}
