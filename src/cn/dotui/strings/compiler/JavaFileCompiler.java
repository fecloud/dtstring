/**
 * JavaFileCompiler.java Created on 2015-12-10
 */
package cn.dotui.strings.compiler;

import java.io.File;
import java.nio.charset.Charset;
import java.util.Arrays;

import javax.tools.Diagnostic;
import javax.tools.DiagnosticCollector;
import javax.tools.JavaCompiler;
import javax.tools.JavaFileManager.Location;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.StandardLocation;
import javax.tools.ToolProvider;

/**
 * The class <code>JavaFileCompiler</code>
 * 
 * @author Feng OuYang
 * @version 1.0
 */
public class JavaFileCompiler {

	private String classFile;

	private String outDir;

	/**
	 * @param javaFile
	 */
	public JavaFileCompiler(String outDir, String classFile) {
		super();
		this.classFile = classFile;
		this.outDir = outDir;
	}

	public boolean compiler() throws Exception {
		boolean bRet = false;
		final JavaCompiler oJavaCompiler = ToolProvider.getSystemJavaCompiler();

		if(oJavaCompiler == null){
			throw new IllegalArgumentException("not found SystemJavaCompiler");
		}
		final DiagnosticCollector<JavaFileObject> oDiagnosticCollector = new DiagnosticCollector<JavaFileObject>();

		final StandardJavaFileManager oStandardJavaFileManager = oJavaCompiler.getStandardFileManager(oDiagnosticCollector, null, Charset.forName("UTF-8"));
		Location oLocation = StandardLocation.CLASS_OUTPUT;

		oStandardJavaFileManager.setLocation(oLocation,
				Arrays.asList(new File[] { new File(outDir) }));

		Iterable<? extends JavaFileObject> oItJavaFileObject = oStandardJavaFileManager
				.getJavaFileObjectsFromFiles(Arrays.asList(new File(classFile)));
		bRet = oJavaCompiler.getTask(null, oStandardJavaFileManager,
				oDiagnosticCollector, null, null, oItJavaFileObject).call();
		for (Diagnostic<?> oDiagnostic : oDiagnosticCollector.getDiagnostics()) {
			System.out.println("Error on line: " + oDiagnostic.getLineNumber()
					+ "; URI: " + oDiagnostic.getSource().toString());
		}
		return bRet;
	}

}
