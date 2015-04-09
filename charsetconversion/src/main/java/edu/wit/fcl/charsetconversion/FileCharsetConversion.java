package edu.wit.fcl.charsetconversion;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class FileCharsetConversion {
	static public void tranlsteDir(File inputDir, String inputfileCharset,
			File outputDir, String outputCharset) throws IOException {
		if (!inputDir.isDirectory()) {
			return;
		}
		if(!outputDir.isDirectory())
		{
			outputDir.mkdir();
		}
		File[] files = inputDir.listFiles();
		for (int i = 0; i < files.length; i++) {
			File tagetfile = files[i];
			if (tagetfile.isDirectory()) {
				if (!outputDir.isDirectory()) {
					outputDir.mkdir();
				}
				String path = tagetfile.getCanonicalPath();
				int pos = path.lastIndexOf("\\");
				String suffexstr = (path.substring(pos, path.length()));
				tranlsteDir(tagetfile, inputfileCharset,
						new File(outputDir.getAbsolutePath() + suffexstr),
						outputCharset);
			} else if (tagetfile.isFile()) {
				tranlste(
						tagetfile,
						inputfileCharset,
						new File(outputDir.getAbsolutePath() + "/"
								+ tagetfile.getName()), outputCharset);
			}
		}
	}

	static public void tranlste(File inputFile, String inputfileCharset,
			File outputFile, String outputCharset) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(
				new FileInputStream(inputFile), inputfileCharset));
		String line = null;
		StringBuffer sb = new StringBuffer();
		while ((line = br.readLine()) != null) {
			line += "\r\n";
			sb.append(line);
		}
		br.close();

		FileOutputStream fos = new FileOutputStream(outputFile);
		fos.write(sb.toString().getBytes(outputCharset));
		fos.close();
	}
}
