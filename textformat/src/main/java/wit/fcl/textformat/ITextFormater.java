package wit.fcl.textformat;

import java.io.File;

public interface ITextFormater {
	public void format(File inputFile,String inputfileCharset,File outputFile,String outfileCharset);
}
