package wit.fcl.json2beanfiles;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

public class App2 {

	public static void main(String[] args) throws IOException {
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
		String content = readLine(new File(filePath));
		work(content,tagerdir,packageName,outputcharset);
	}
	
	private static void usage() {
		System.out.println("Usage:");
		System.out
				.println("json2beanfiles <file> [packageName] [OutputCharset]");
		System.out.println("package name defualt is defualt");
		System.out.println("outputcharset defualt is utf-8");
	}
	public static String readLine(File tagetFile)
	{
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(tagetFile),"UTF-8"));
			String line = br.readLine();
			br.close();
			if(line.startsWith("GET"))
			{
				return line.split(" ")[1];
			}
			return line;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	public static String generRequestName(String header) throws IOException {
		String[] realut = header.split("/");
		String res = "";
		for (int i = 1; i < realut.length; i++) {
			res+=upperFirstChar(realut[i]);
		}
		res+="Request";
		return res;
	}
	public static String upperFirstChar(String field) {
		return field.toUpperCase().charAt(0)+field.substring(1,field.length());
	}
	public static void work(String content,File tagerdir,String packetName,String outputcharset) throws IOException {
		int pos = content.indexOf("?");
		String className = "Default";
		if(pos != -1)
		{
			String uri = content.substring(0,pos);
			className = generRequestName(uri);
			content = content.substring(pos+1, content.length());
//			System.out.println(content);
		}
		ClassFileObject ss = new ClassFileObject();
		ss.setClassName(className);
		ss.setPacketName(packetName);
		List<FieldInfo> fieldList = new ArrayList<FieldInfo>();
		String[] keyvlauelist = content.split("&");
		for (String string : keyvlauelist) {
			// 过滤空键
			if(string.indexOf("=")==-1)
			{
				System.err.println("null key: "+string);
				continue;
			}
			String[] pair = string.split("=");
			FieldInfo info = new FieldInfo();
			info.setFieldName(pair[0]);
			info.setDefualValue(pair.length >1 ? URLDecoder.decode(pair[1],"UTF-8"):"");
			info.setType("String");
			fieldList.add(info);
		}
		ss.setFields(fieldList);
		ObjectPress op = new ObjectPress(ss,true);
		String dstFilePath = tagerdir.getAbsolutePath() + "\\"
				+ ss.getClassName() + ".java";
//		op.outputFile(new File("C:\\Users\\Administrator\\Desktop\\CheckRequest.java"),"UTF-8");
		op.outputFile(new File(dstFilePath),outputcharset);
		System.out.println("generate file:" + dstFilePath);
	}
}
