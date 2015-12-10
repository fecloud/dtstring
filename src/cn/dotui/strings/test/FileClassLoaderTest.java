/**
 * FileClassLoaderTest.java Created on 2015-12-10
 */
package cn.dotui.strings.test;

import java.lang.reflect.Field;

import cn.dotui.strings.classes.FileClassLoader;

/**
 * The class <code>FileClassLoaderTest</code>
 * 
 * @author Feng OuYang
 * @version 1.0
 */
public class FileClassLoaderTest {

	public static void main(String[] args) throws ClassNotFoundException {
		final FileClassLoader classLoader = new FileClassLoader("tt");
		Class<?> loadClass = classLoader.loadClass("cn.dotui.strings.R");
		Field[] fields = loadClass.getFields();
		for (Field field : fields) {
			System.out.println(field);
		}
	}

}
