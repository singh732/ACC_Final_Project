package project.web;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

import project.utility.In;

public class Cache {
	
	public static Boolean isUrlAvailable(String Url) {
		Boolean flag = false;
		try {
			In in = new In("Cache.txt");

			while (!in.isEmpty()) {
				String s = in.readLine();
				String stringArray[]= s.split(" ");
				if (stringArray[0].equals(Url))
					flag = true;
			}
			// System.out.println();
		} catch (Exception e) {
			System.out.println(e);
		}
		return flag;
	}

		
		public static void addCache(String cache) throws IOException {

			File fstream = new File("Cache.txt");
			if(!fstream.exists())
			{
			fstream.createNewFile();
			}
			BufferedWriter out = new BufferedWriter(new FileWriter(fstream, true));
			out.append(cache);
			out.newLine();
			out.flush();
			out.close();

		}
		
		public static void clearCache() throws IOException {
			File fstream = new File("Cache.txt");
			
			if (fstream.exists()) {
				//fstream.delete();
				BufferedWriter out = new BufferedWriter(new FileWriter(fstream, false));
				out.write("");
				out.flush();
				out.close();
				System.out.println("Files Deleted.");
			} else {
				System.out.println("Failed to delete the file.");
			}
			File f = new File("TextFiles//");
			if(f.exists())
			{
			FileUtils.cleanDirectory(f);
			f.delete();
			}
			System.out.println("Cache has been cleared sucesfully");
		}

}
