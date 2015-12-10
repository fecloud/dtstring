/**
 * JavaFileOut.java Created on 2015-12-8
 */
package cn.dotui.strings.out;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * The class <code>JavaFileOut</code>
 * 
 * @author Feng OuYang
 * @version 1.0
 */
public class JavaFileOut extends OutputStream {

	private String dirPath;

	private String className;

	private FileOutputStream fileOutputStream;

	/**
	 * @param dirPath
	 * @param className
	 */
	public JavaFileOut(String dirPath, String className) {
		super();
		this.dirPath = dirPath;
		this.className = className;
		checkDir();
		createFile();
	}

	private void checkDir() {
		if (null == dirPath) {
			throw new RuntimeException("dirPath is null");
		}

		if (null == className) {
			throw new RuntimeException("className is null");
		}

		final File file = new File(dirPath);
		if (!file.exists()) {
			final boolean create = file.mkdirs();
			if (!create) {
				throw new RuntimeException(String.format("%s mkdir error",
						dirPath));
			}
		}
	}

	private void createFile() {
		final String filename = replaceAll(className, ".", File.separator)
				+ ".java";
		final File file = new File(dirPath, filename);
		try {
			if (!file.exists()) {
				file.getParentFile().mkdirs();
				file.createNewFile();
			}
			fileOutputStream = new FileOutputStream(file);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getDirPath() {
		return dirPath;
	}

	public void setDirPath(String dirPath) {
		this.dirPath = dirPath;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.io.OutputStream#write(int)
	 */
	@Override
	public void write(int b) throws IOException {
		fileOutputStream.write(b);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.io.OutputStream#close()
	 */
	@Override
	public void close() throws IOException {
		fileOutputStream.close();
		super.close();
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
