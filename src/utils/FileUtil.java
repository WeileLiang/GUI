package utils;

import java.io.File;

public class FileUtil {
	public static void openFile(String filePath) {
		File file=new File(filePath);
		if(!file.exists()) return;
		
		final Runtime runtime = Runtime.getRuntime();
		Process process = null;//
		final String cmd = "rundll32 url.dll FileProtocolHandler file://" + file.getAbsolutePath();
		try {
			process = runtime.exec(cmd);
		} catch (final Exception e) {
			System.out.println("Error exec!");
		}
	}
}
