/**
 * FileClassLoader.java Created on 2015-12-10
 */
package cn.dotui.strings.classes;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * The class <code>FileClassLoader</code>
 * 
 * @author Feng OuYang
 * @version 1.0
 */
public class FileClassLoader extends ClassLoader {

	private String dir;

	/**
	 * @param classFile
	 */
	public FileClassLoader(String dir) {
		super();
		this.dir = dir;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.ClassLoader#findClass(java.lang.String)
	 */
	@Override
	protected Class<?> findClass(String name) throws ClassNotFoundException {
		final byte[] classData = getClassData(name);
		if (classData == null) {
			throw new ClassNotFoundException();
		} else {
			return defineClass(name, classData, 0, classData.length);
		}
	}

	private byte[] getClassData(String className) {
		try {
			final String path = classNameToPath(className);
			final InputStream in = new FileInputStream(path);
			final ByteArrayOutputStream out = new ByteArrayOutputStream();
			final byte[] buffer = new byte[1024];
			int len = 0;
			while ((len = in.read(buffer)) != -1) {
				out.write(buffer, 0, len);
			}
			in.close();
			return out.toByteArray();
		} catch (IOException e) {
		}
		return null;
	}

	private String classNameToPath(String className) {
		return dir + File.separatorChar
				+ className.replace('.', File.separatorChar) + ".class";
	}

}
