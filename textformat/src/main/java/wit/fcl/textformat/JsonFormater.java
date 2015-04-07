package wit.fcl.textformat;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonIOException;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

public class JsonFormater implements ITextFormater {

	@Override
	public void format(File inputFile, String inputfileCharset,
			File outputFile, String outfileCharset) {

		try {
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			JsonParser jp = new JsonParser();
			InputStreamReader isr = new InputStreamReader(new FileInputStream(
					inputFile), inputfileCharset);
			JsonElement je = jp.parse(isr);
			String prettyJsonString = gson.toJson(je);
			FileOutputStream fos = new FileOutputStream(outputFile);
			fos.write(prettyJsonString.getBytes(outfileCharset));
			fos.close();
		} catch (JsonIOException e) {
			e.printStackTrace();
		} catch (JsonSyntaxException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
