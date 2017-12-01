package utils;

public class FileUtil {
	public static void openFile(String filePath) {
		final Runtime runtime = Runtime.getRuntime();
		Process process = null;//
		final String cmd = "rundll32 url.dll FileProtocolHandler file://" + filePath;
		try {
			process = runtime.exec(cmd);
		} catch (final Exception e) {
			System.out.println("Error exec!");
		}
	}
}
