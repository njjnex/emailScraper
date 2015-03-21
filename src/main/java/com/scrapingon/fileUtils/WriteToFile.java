package com.scrapingon.fileUtils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * @author njjnex Singleton class for synchronized file access. Writes founded
 *         results in a text file.
 */
public class WriteToFile {

	private static final WriteToFile inst = new WriteToFile();
	private static final String RESULT_FILE = "result.txt";
	private static String newLine = System.getProperty("line.separator");

	private WriteToFile() {
		File f = new File(RESULT_FILE);
		if (f.exists() && !f.isDirectory()) {
			f.delete();
		}
	}

	public static WriteToFile getInstance() {
		return inst;
	}

	public synchronized void writeToFile(List<String> pageResults) {
		try {

			PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(RESULT_FILE, true)));
			for (String email : pageResults) {
				out.write(email);
				out.write(newLine);
			}
			out.close();
		} catch (IOException e) {
		}
	}
}
