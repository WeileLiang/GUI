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

	public static final int TOTAL_MACHINE_COUNT = 15;

	public static final String JOBS_PATH = ".\\jobs";
	public static final String JOBSHOPS_PATH = ".\\jobshops";
	// 组件集合
	public static List<String> jobNames;

	public static final String PRODUCT_PATH = ".\\products";
	// 产品集合
	public static List<String> productNames;
	// 产品包含的组件集合
	public static List<List<String>> jobItemsOfPro;

	// 所有设备名称集合
	public static List<String> machineNames;
	// 车间名称集合
	public static List<String> jobshopNames;
	// 车间的设备组成
	public static List<List<String>> machinesOfJobshop;

	public static List<String> getMachineNames() {
		if (machineNames == null) {
			machineNames = new ArrayList<>();
			for (int i = 1; i <= TOTAL_MACHINE_COUNT; i++)
				machineNames.add("Machine" + i);
		}
		return machineNames;
	}

	public static List<String> getJobshopNames(boolean refresh) {
		if (refresh||jobshopNames == null)
			initJobshops();
		return jobshopNames;
	}

	public static List<List<String>> getMachinesOfJobshop() {
		if (machinesOfJobshop == null)
			initJobshops();

		return machinesOfJobshop;
	}

	private static void initJobshops() {
		File file = new File(JOBSHOPS_PATH);
		if (!file.exists())
			return;

		jobshopNames = new ArrayList<>();
		machinesOfJobshop = new ArrayList<>();

		File[] jobShops = file.listFiles();

		for (int i = 0; i < jobShops.length; i++) {
			jobshopNames.add(jobShops[i].getName().split("\\.")[0]);
			List<String> machines = new ArrayList<>();
			BufferedReader reader = null;
			try {
				reader = new BufferedReader(new InputStreamReader(new FileInputStream(jobShops[i].getAbsolutePath())));
				String line = null;
				while ((line = reader.readLine()) != null) {
					machines.add(line.trim());
				}

				machinesOfJobshop.add(machines);
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
		if (refresh || productNames == null)
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
