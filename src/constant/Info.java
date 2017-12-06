package constant;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Info {

	public static final int TOTAL_MACHINE_COUNT=15;
	
	public static final String JOBS_PATH = ".\\jobs";
	// 组件集合
	public static List<String> jobNames;

	public static final String PRODUCT_PATH = ".\\products";
	// 产品集合
	public static List<String> productNames;
	// 产品包含的组件集合
	public static List<List<String>> jobItemsOfPro;

	public static List<String> getJobNames() {
		if (jobNames == null) {
			jobNames = new ArrayList<>();
			File jobsFile = new File(JOBS_PATH);

			File[] files = jobsFile.listFiles();

			for (File file : files)
				jobNames.add(file.getName().split("\\.")[0]);
		}

		return jobNames;
	}

	public static List<String> getProductNames(boolean refresh) {
		if (refresh||productNames == null)
			initProducts();

		return productNames;
	}

	public static List<List<String>> getJobItemsOfPro() {
		if (jobItemsOfPro == null)
			initProducts();

		return jobItemsOfPro;
	}

	private static void initProducts() {

		File file = new File(PRODUCT_PATH);
		if (!file.exists())
			return;

		productNames = new ArrayList<String>();
		jobItemsOfPro = new ArrayList<>();

		File[] products = file.listFiles();

		for (int i = 0; i < products.length; i++) {
			productNames.add(products[i].getName().split("\\.")[0]);
			List<String> jobs = new ArrayList<>();
			BufferedReader reader = null;
			try {
				reader = new BufferedReader(new InputStreamReader(new FileInputStream(products[i].getAbsolutePath())));
				String line = null;
				while ((line = reader.readLine()) != null) {
					jobs.add(line.trim());
				}

				jobItemsOfPro.add(jobs);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				if (reader != null)
					try {
						reader.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			}
		}

	}

}
