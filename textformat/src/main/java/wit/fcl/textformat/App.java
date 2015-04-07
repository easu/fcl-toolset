package wit.fcl.textformat;

import java.io.File;

public class App {
	public static void main(String[] args) {
		if (args.length != 4) {
			usage();
			return;
		}
		String type = args[0];
		String inputfile = args[1];
		String outputfile = args[2];
		String charset = args[3];
		if ("json".equalsIgnoreCase(type)) {
			ITextFormater formater = new JsonFormater();
			formater.format(new File(inputfile), charset, new File(outputfile),
					charset);
		} else if ("html".equalsIgnoreCase(type)) {
			ITextFormater formater = new HtmlFormater();
			formater.format(new File(inputfile), charset, new File(outputfile),
					charset);
		} else {
			System.out.println("unsupport type :" + type);
		}
	}

	public static void usage() {
		System.out.println("Usage:");
		System.out
				.println("textformat <type> <inputfile> <outputfile> <charset>");
		System.out.println("eg: textformat json c:/in.txt c:/out.txt utf8");
	}
}
