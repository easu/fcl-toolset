package wit.fcl.textformat;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class HtmlFormater implements ITextFormater {

	@Override
	public void format(File inputFile, String inputfileCharset,
			File outputFile, String outfileCharset) {
		try {
			Document doc = Jsoup.parse(inputFile, inputfileCharset);
			FileOutputStream fos = new FileOutputStream(outputFile);
			fos.write(doc.html().getBytes(outfileCharset));
			fos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
