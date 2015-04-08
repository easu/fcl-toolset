package wit.fcl.json2beanfiles;

import java.io.File;
import java.util.List;

public class App {
	public static void main(String[] args) throws Exception {
		if (!(args.length == 2 || args.length == 3 || args.length == 1)) {
			usage();
			return;
		}
		String filePath = args[0];
		String workDir = "";// 默认当前目录
		String fileName = null;
		if (filePath.contains(":")) {// full path
			int pos = filePath.lastIndexOf("\\");
			workDir = filePath.substring(0, pos + 1);
			fileName = filePath.substring(pos + 1, filePath.length());
		} else {
			fileName = filePath;
		}
		//
		String className = null;
		int posf = fileName.indexOf(".");
		className = posf != -1 ? fileName.substring(0, posf) : fileName;
		File tagerdir = new File(workDir + "bean");
		if (!tagerdir.exists() || !tagerdir.isDirectory()) {
			tagerdir.mkdir();
		}
		String packageName = "defualt";
		String outputcharset = "utf-8";
		if (args.length > 1) {
			packageName = args[1];
			if (args.length == 3) {
				outputcharset = args[2];
			}
		}

		GJson2ClassFieldImpl g = new GJson2ClassFieldImpl();
		List<ClassFileObject> list = g.press(new File(filePath), className,
				packageName);
		for (ClassFileObject classFileObject : list) {
			ObjectPress op = new ObjectPress(classFileObject);
			String dstFilePath = tagerdir.getAbsolutePath() + "\\"
					+ classFileObject.getClassName() + ".java";
			op.outputFile(new File(dstFilePath), outputcharset);
			System.out.println("generate file:" + dstFilePath);
		}
		/*
		 * System.out.println(tagerdir.getAbsolutePath());
		 * System.out.println(className); System.out.println(fileName);
		 * System.out.println(workDir);
		 */
		System.out.println("success");
	}

	private static void usage() {
		System.out.println("Usage:");
		System.out
				.println("json2beanfiles <file> [packageName] [OutputCharset]");
		System.out.println("package name defualt is defualt");
		System.out.println("outputcharset defualt is utf-8");
	}
}
