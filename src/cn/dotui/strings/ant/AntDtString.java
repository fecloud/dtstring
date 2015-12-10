/**
 * AntDtString.java Created on 2015-12-9
 */
package cn.dotui.strings.ant;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Task;

import cn.dotui.strings.Ddpt;
import cn.dotui.strings.out.JavaFileOut;
import cn.dotui.strings.parse.JavaFileParse;

/**
 * The class <code>AntDtString</code>
 * 
 * @author Feng OuYang
 * @version 1.0 BuildException
 */
public class AntDtString extends Task {

	private String src;

	private String srclz;

	private String dest;

	private String destlz;

	public String getSrc() {
		return src;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.tools.ant.Task#execute()
	 */
	@Override
	public void execute() throws BuildException {
		System.out.println("ddpt");

		System.out.println("");
		System.out.println(String.format("\t%s:%s", "src", src));
		System.out.println(String.format("\t%s:%s", "srclz", srclz));
		System.out.println(String.format("\t%s:%s", "dest", dest));
		System.out.println(String.format("\t%s:%s", "destlz", destlz));
		try {
			final Ddpt ddpt = new Ddpt(new JavaFileParse(src, srclz), destlz,
					new JavaFileOut(dest, destlz));
			ddpt.gen();
			System.out.println("ddpt success");
			super.execute();
		} catch (RuntimeException e) {
			e.printStackTrace();
			System.out.println("ddpt fail");
		}

	}

	public void setSrc(String src) {
		this.src = src;
	}

	public String getSrclz() {
		return srclz;
	}

	public void setSrclz(String srclz) {
		this.srclz = srclz;
	}

	public String getDest() {
		return dest;
	}

	public void setDest(String dest) {
		this.dest = dest;
	}

	public String getDestlz() {
		return destlz;
	}

	public void setDestlz(String destlz) {
		this.destlz = destlz;
	}

}
