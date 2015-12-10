/**
 * ClassParse.java Created on 2015-12-8
 */
package cn.dotui.strings.parse;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import cn.dotui.strings.DtString;
import cn.dotui.strings.SourceParse;

/**
 * The class <code>ClassParse</code>
 * 
 * @author Feng OuYang
 * @version 1.0
 */
public class ClassParse implements SourceParse {

	private String className;

	private Class<?> clz;

	private ArrayList<String> key = new ArrayList<String>();

	private Map<String, String> map = new HashMap<String, String>();

	private int index;

	/**
	 * @param className
	 */
	public ClassParse(String className) {
		super();
		this.className = className;
	}

	protected Class<?> loadClass(String name) throws ClassNotFoundException {
		return Class.forName(name);
	}

	public boolean parse(String name) throws ClassNotFoundException {
		clz = loadClass(name);
		if (clz == null) {
			throw new ClassNotFoundException(className
					+ " not found please check compiler is success");
		}
		Field[] fields = clz.getFields();
		if (null != fields) {
			String className = null;
			int modifiers = -1;
			for (Field f : fields) {
				className = f.getType().getName();
				if (className.equals(String.class.getName())) {
					modifiers = f.getModifiers();
					if (Modifier.isPublic(modifiers)
							&& Modifier.isStatic(modifiers)
							&& Modifier.isFinal(modifiers)) {
						key.add(f.getName());
						try {
							map.put(f.getName(), f.get(null).toString());
						} catch (Exception e) {
						}
					}
				}
			}
		}
		return true;
	}

	public DtString getDtString(String string) {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.dotui.strings.SourceParse#next()
	 */
	@Override
	public DtString next() {
		if (key.isEmpty() || index >= key.size()) {
			return null;
		}
		final String var = key.get(index);
		index++;
		final String value = map.get(var);
		return new DtString(var, value);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.dotui.strings.SourceParse#parse()
	 */
	@Override
	public boolean parse() {
		try {
			return parse(className);
		} catch (ClassNotFoundException e) {
		}
		return false;
	}
}
