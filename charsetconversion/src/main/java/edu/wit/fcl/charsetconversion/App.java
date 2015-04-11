package edu.wit.fcl.charsetconversion;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class App {
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
		String Icharset = args[1];
		String Ocharset = args[2];
		if (!targetFile.isDirectory()) {
			System.out.println("target file must be directory");
			return;
		}
		if (srcFile.isDirectory()) {
			System.out.println(srcFile.getAbsolutePath());
			System.out.println("[" + Icharset + "]---dir---->[" + Ocharset
					+ "]");
			System.out.println(targetFile.getAbsolutePath());
			FileCharsetConversion.tranlsteDir(srcFile, Icharset, targetFile,
					Ocharset);
		} else if (srcFile.isFile()) {
			File dstFile = new File(targetFile.getAbsolutePath() + "/" + srcFile.getName());
			System.out.println(srcFile.getAbsolutePath());
			System.out.println("[" + Icharset + "]---file---->[" + Ocharset
					+ "]");
			System.out.println(dstFile.getAbsolutePath());
			FileCharsetConversion.tranlste(srcFile, Icharset, dstFile,Ocharset);
		} else {
			System.out.println("error");
		}

	}

	public static void usage() {
		System.out.println("usage:");
		System.out
				.println("charsetcoversion <configFilePath> <inputfilecharset> <ouputfilecharset>");
		System.out.println("  configFile contain two line");
		System.out
				.println("  first line contain Source file or directory path");
		System.out.println("  second line target dir");
		System.out.println("    eg: charsetcoversion C:/config.txt utf8 gbk");
		System.out.println("       config.txt :");
		System.out.println("        C:/source");
		System.out.println("        C:/target");
	}
}
