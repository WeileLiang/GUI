package constant;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Info {

	public static final String JOBS_PATH = ".\\jobs";
	public static List<String> jobNames;

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
}
