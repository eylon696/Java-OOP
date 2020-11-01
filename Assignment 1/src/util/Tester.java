// 1279595253
package util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Tester {

	private String testDescr;
	private boolean errorExists;
	private boolean errorEver = false;
	private StringBuilder output = null;
	private String extraDescr = "";
	
	
	// -----------------------------------------------------------------------------
	// Error reporting and checking
	// -----------------------------------------------------------------------------

	public void setOutput(StringBuilder output) {
		this.output = output;
	}
	
	public boolean errorExists() {
		return errorExists;
	}
	
	public boolean errorEver() {
		return errorEver;
	}
	
	public void setErrorExists(boolean val) {
		errorExists = val;
		if (val)
			errorEver = true;
	}
	
	public void initTest(String descr) {
		this.testDescr = descr;
		setErrorExists(false);
	}
	
	public void setExtraDescr(String extraDescr) {
		this.extraDescr = extraDescr;
	}

	private void initPublishedTest0(String msg) {
		String name = new Throwable().getStackTrace()[2].getMethodName(); 
		initTest("From published test file, " + msg + ": " + name + "()");
	}

	public void initPublishedTest(Class<?> c, String msg) {
		initPublishedTest0(c.getSimpleName() + ", " + msg);
	}

	public void initPublishedTest(Class<?> c) {
		initPublishedTest0(c.getSimpleName());
	}
	
	public void initPublishedTest(String msg) {
		initPublishedTest0(msg);		
	}

	
	public void initRealTest(Class<?> c, String descr) {
		initTest(c.getSimpleName() + ": " + descr);
	}
	
	public void initRealTest(String descr) {
		initTest(descr);
	}

	public void done() {
		print("\n------------------------------------------------\n");
		if (errorEver) {
			print(this.getClass().getSimpleName() + " has failed tests! see above.\n");
		} else
			print(this.getClass().getSimpleName() + " all tests passed.\n");
	}
	
	private void print(String text) {
		if (output == null) 
			System.err.print(text);
		else
			output.append(text);
	}

	public void error(String msg) {
		if (!errorExists) {
			print(testDescr + " " + extraDescr + ":\n");
			setErrorExists(true);
		}
		print("    " + msg + ".\n");
	}

	public boolean check(boolean cond, String msg) {
		if (!cond) {
			error(msg);
			return false;
		}
		return true;
	}

	public boolean check(boolean cond) {
		return check(cond, "");
	}

	private static boolean eqObjects(Object o1, Object o2) {
		return o1 == o2 || (o1 != null && o1.equals(o2));
	}

	public boolean checkEq(Object got, Object expect, String msg) {
		return check(eqObjects(got, expect), String.format("%s: Expected '%s' but got '%s'", msg, expect, got));
	}

	public boolean checkEq(Object got, Object expect) {
		return check(eqObjects(got, expect), String.format("Expected '%s' but got '%s'", expect, got));
	}

	public boolean checkEqStr(Object got, Object expect) {
		return checkEq(got.toString(), expect.toString());
	}

	public boolean checkEqStr(Object got, Object expect, String msg) {
		return checkEq(got.toString(), expect.toString(), msg);
	}

	private static boolean eqDouble(double a, double b, double precision) {
		return Math.abs(a - b) < precision;
	}

	public boolean checkEqDouble(double got, double expect, String msg) {
		return check(eqDouble(got, expect, 0.0001), String.format("%s: Expected '%.4f' but got '%.4f'", msg, expect, got));
	}

	public boolean checkEqDouble(double got, double expect) {
		return check(eqDouble(got, expect, 0.0001), String.format("Expected '%.4f' but got '%.4f'", expect, got));
	}

	public interface Thrower {
		public void run() throws Exception;
	}

	public boolean checkThrows(Thrower thrower, Class<? extends Exception> ec, String msg) {
		try {
			thrower.run();
		} catch (Exception c) {
			if (ec.isAssignableFrom(c.getClass()))
				return true;
			error(String.format("%s: Should throw %s, but instead is throwing %s", msg, ec.getName(),
					c.getClass().getName()));
			return false;
		}
		error(msg + ", should throw exception " + ec.getName());
		return false;
	}

	// -----------------------------------------------------------------------------
	// Overriding stdin
	// -----------------------------------------------------------------------------

	private byte[] stdinBuf;
	private int stdinIndex;

	public void setInput(String str) {
		stdinBuf = (str + "\n").getBytes(Charset.forName("UTF-8"));
		stdinIndex = 0;
		InputStream inputStream = new InputStream() {
			@Override
			public int read() {
				if (stdinIndex >= stdinBuf.length)
					return -1;

				return stdinBuf[stdinIndex++];
			}
		};
		System.setIn(inputStream);
	}

	// -----------------------------------------------------------------------------
	// Java Reflection stuff
	// -----------------------------------------------------------------------------

	private static boolean isPrivate(Method m) {
		return Modifier.isPrivate(m.getModifiers());
	}

	private static boolean isPrivate(Constructor<?> m) {
		return Modifier.isPrivate(m.getModifiers());
	}

	private static boolean isPrivate(Field f) {
		return Modifier.isPrivate(f.getModifiers());
	}

	private static String descr(int modifier) {
		String res = Modifier.toString(modifier);
		return res.length() > 0 ? res + " " : res;
	}

	private static String descr(Class<?>[] ps) {
		StringBuilder b = new StringBuilder();
		b.append('(');
		for (int i = 0; i < ps.length; i++) {
			b.append(ps[i].getSimpleName());
			if (i != ps.length - 1)
				b.append(',');
		}
		b.append(')');
		return b.toString();
	}

	private static String descr(Method m) {
		return descr(m.getModifiers()) + m.getReturnType().getSimpleName() + " " + m.getName()
				+ descr(m.getParameterTypes());
	}

	private static String descr(Constructor<?> c) {
		return descr(c.getModifiers()) + c.getDeclaringClass().getSimpleName() + descr(c.getParameterTypes());
	}

	private static String descr(Field f) {
		return descr(f.getModifiers()) + f.getName();
	}

	private static boolean equalMethods(Method m1, Method m2) {
		if (!m1.getName().equals(m2.getName()))
			return false;
		if (!Arrays.equals(m1.getParameterTypes(), m2.getParameterTypes()))
			return false;
		if (!m1.getReturnType().equals(m2.getReturnType()))
			return false;
		if (!Arrays.equals(m1.getExceptionTypes(), m2.getExceptionTypes()))
			return false;
		if (m1.getModifiers() != m2.getModifiers())
			return false;
		return true;
	}

	private static boolean equalConstructors(Constructor<?> m1, Constructor<?> m2) {
		if (!Arrays.equals(m1.getParameterTypes(), m2.getParameterTypes()))
			return false;
		if (!Arrays.equals(m1.getExceptionTypes(), m2.getExceptionTypes()))
			return false;
		if (m1.getModifiers() != m2.getModifiers())
			return false;
		return true;
	}

	private static boolean equalFields(Field f1, Field f2) {
		if (f1.getName().equals(f2.getName()))
			return false;
		if (f1.getModifiers() != f2.getModifiers())
			return false;
		return true;
	}

	private static boolean contains(Constructor<?>[] ms, Constructor<?> m) {
		if (isPrivate(m))
			return true;
		for (Constructor<?> m2 : ms)
			if (equalConstructors(m, m2))
				return true;
		return false;
	}

	private static boolean contains(Method[] ms, Method m) {
		if (isPrivate(m))
			return true;
		for (Method m2 : ms)
			if (equalMethods(m, m2))
				return true;
		return false;
	}

	private static boolean contains(Field[] fs, Field f) {
		if (isPrivate(f))
			return true;
		for (Field f2 : fs)
			if (equalFields(f, f2))
				return true;
		return false;
	}

	public void testEqualClasses(Class<?> user, Class<?> ref) {
		initPublishedTest(user, "checking the structure of the class");
		Constructor<?>[] cU = user.getConstructors();
		Constructor<?>[] cR = ref.getConstructors();
		for (Constructor<?> c : cU)
			if (!contains(cR, c))
				error("Constructor " + descr(c)
						+ " should be private, or should not exist, or just has a wrong signature");
		for (Constructor<?> c : cR)
			if (!contains(cU, c))
				error("Constructor " + descr(c) + " is missing (or has wrong signature)");

		Method[] mU = user.getDeclaredMethods();
		Method[] mR = ref.getDeclaredMethods();
		for (Method m : mU)
			if (!contains(mR, m))
				error("Method " + descr(m) + " should be private, or should not exist, or just has a wrong signature");
		for (Method m : mR)
			if (!contains(mU, m))
				error("Method " + descr(m) + " is missing (or has wrong signature)");

		Field[] fU = user.getFields();
		Field[] fR = ref.getFields();
		for (Field f : fU)
			if (!contains(fR, f))
				error("Field " + descr(f) + " should be private, or should not exist, or just has a wrong name");
		for (Field f : fR)
			if (!contains(fU, f))
				error("Field " + descr(f) + " is missing (or has wrong signature)");

		if (user.getSuperclass() != ref.getSuperclass())
			error("should be a direct subclass of " + ref.getSuperclass().getSimpleName());

		if (!new HashSet<>(Arrays.asList(user.getInterfaces()))
				.equals(new HashSet<>(Arrays.asList(ref.getInterfaces()))))
			error("something wrong with implemented interfaces.");
	}

	// ---------------------------------------------------------------------------------
	// Zip stuff
	// ---------------------------------------------------------------------------------

	public static void zipIt(String zipFileName) throws IOException {
		String sourceFile = "src";
		FileOutputStream fos = new FileOutputStream(zipFileName);
		ZipOutputStream zipOut = new ZipOutputStream(fos);
		File fileToZip = new File(sourceFile);

		zipFile(fileToZip, fileToZip.getName(), zipOut);
		zipOut.close();
		fos.close();
	}

	private static void zipFile(File fileToZip, String fileName, ZipOutputStream zipOut) throws IOException {
		if (fileToZip.isHidden()) {
			return;
		}
		if (fileToZip.isDirectory()) {
			if (fileName.endsWith("/")) {
				zipOut.putNextEntry(new ZipEntry(fileName));
				zipOut.closeEntry();
			} else {
				zipOut.putNextEntry(new ZipEntry(fileName + "/"));
				zipOut.closeEntry();
			}
			File[] children = fileToZip.listFiles();
			for (File childFile : children) {
				zipFile(childFile, fileName + "/" + childFile.getName(), zipOut);
			}
			return;
		}
		FileInputStream fis = new FileInputStream(fileToZip);
		ZipEntry zipEntry = new ZipEntry(fileName);
		zipOut.putNextEntry(zipEntry);
		byte[] bytes = new byte[1024];
		int length;
		while ((length = fis.read(bytes)) >= 0) {
			zipOut.write(bytes, 0, length);
		}
		fis.close();
	}

	public static boolean isOneStudent() {
		ExDetails s1 = ExDetails.firstStudent();
		ExDetails s2 = ExDetails.secondStudent();
		return s2 == null || s1.equals(s2);
	}
	
	public static void generateZip() {
		ExDetails s1 = ExDetails.firstStudent();
		ExDetails s2 = ExDetails.secondStudent();
		String zipName;

		System.out.println("Check your personal details are correct here:");
		System.out.println();
		System.out.println(s1);
		if (isOneStudent()) {
			zipName = String.format("%s.zip", s1.getId());
		} else {
			System.out.println(s2);
			zipName = String.format("%s_%s.zip", s1.getId(), s2.getId());
		}
		System.out.println("--------------------------------------");

		try {
			zipIt(zipName);
			System.out.println("Your zip file was created automatically!, you can find it in the project directory.");
			System.out.println("Its name is " + zipName);
			System.out.println();
			
			System.out.println("   *** Submit this file only! don't create your own zip! ***");
		} catch (IOException e) {
			System.err.println("Problem creating zip file! " + e);
		}
	}
	
	

	// ---------------------------------------------------------------------------------
	// Signature stuff
	// ---------------------------------------------------------------------------------

	private List<String> readFile() {
		String fileName = "src/" + this.getClass().getPackage().getName() + "/" + this.getClass().getSimpleName()
				+ ".java";
		List<String> lines = new ArrayList<>();

		try (Stream<String> stream = Files.lines(Paths.get(fileName))) {
			lines = stream.collect(Collectors.toList());
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		return lines;
	}

	private int signature(List<String> lines) {
		int sum = 0;
		for (int i = 1; i < lines.size(); i++)
			sum += lines.get(i).hashCode();
		return Math.abs(sum);
	}

	public boolean checkSignature() {
		List<String> lines = readFile();
		int hardcoded = 0;
		try {
			 hardcoded = Integer.valueOf(lines.get(0).substring(3));
		} catch (Exception e) {
			System.err.format("You have probably changed %s.java."
					+ " Please download it again.%n", 
					this.getClass().getSimpleName());
			return false;
		}
		if (hardcoded != signature(lines)) {
			System.err.format("You have probably changed %s.java (%d)."
					+ " Please download it again.%n", 
					this.getClass().getSimpleName(), signature(lines));
			return false;
		}
		return true;
	}
	

	// ---------------------------------------------------------------------------------
	// used by specific Testers
	// ---------------------------------------------------------------------------------

	public void myTests() {
	}
	
	public void myMain() {
		myTests();
		done();
		checkSignature();
	}
	

	// ---------------------------------------------------------------------------------
	// main - for generating zip file.
	// ---------------------------------------------------------------------------------
	
	public static void main(String[] args) {
		Tester t = new Tester();
		if (t.checkSignature())
			generateZip();
		else
			t.print("Zip not generated.");
	}

}
