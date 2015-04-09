package edu.wit.fcl.charsetconversion;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class App{
	public static void main(String[] args) throws IOException {
		if (args.length != 3) {
			usage();
			return;
		}
		BufferedReader br = new BufferedReader(new InputStreamReader(
				new FileInputStream(args[0]), "utf8"));
		String line = null;
		line = br.readLine();
		File srcFile = new File(line);
		line = br.readLine();
		File targetFile = new File(line);
		br.close();
		if (!targetFile.isDirectory()) {
			System.out.println("target file must be directory");
			return;
		}
		if (srcFile.isDirectory()) {
			FileCharsetConversion.tranlsteDir(srcFile, args[1], targetFile,
					args[2]);
		} else if (srcFile.isFile()) {
			FileCharsetConversion.tranlsteDir(srcFile, args[1], new File(
					targetFile.getAbsolutePath() + "/" + srcFile.getName()),
					args[2]);
		}

	}

	public static void usage() {
		System.out.println("usage:");
		System.out
				.println("charsetcoversion <configFilePath> <inputfilecharset> <ouputfilecharset>");
		System.out.println("  configFile contain two line");
		System.out.println("  first line contain Source file or directory path");
		System.out.println("  second line target dir");
		System.out.println("    eg: charsetcoversion C:/config.txt utf8 gbk");
		System.out.println("       config.txt :");
		System.out.println("        C:/source");
		System.out.println("        C:/target");
	}
}
