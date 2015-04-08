package wit.fcl.filehash;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Locale;

public class FileHashUtil {
	public static String getFileMD5(File file) throws Exception {
		return getFileHash(file, "MD5");
	}

	public static String getFileSha1(File file) throws Exception {
		return getFileHash(file, "sha1");
	}

	private static byte[] createChecksum(File file, String type)
			throws NoSuchAlgorithmException, IOException {
		InputStream fis = new FileInputStream(file);
		byte[] buffer = new byte[8*1024];
		MessageDigest complete = MessageDigest.getInstance(type);
		int readNum = 0;
		do {
			readNum = fis.read(buffer);
			if (readNum > 0) {
				complete.update(buffer, 0, readNum);
			}
		} while (readNum != -1);
		fis.close();
		return complete.digest();
	}

	private static String getFileHash(File file, String type) throws Exception {
		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
				'A', 'B', 'C', 'D', 'E', 'F' };

		byte[] md = createChecksum(file, type);
		int j = md.length;
		char str[] = new char[j * 2];
		int k = 0;
		for (int i = 0; i < j; i++) {
			byte byte0 = md[i];
			str[k++] = hexDigits[byte0 >>> 4 & 0xf];
			str[k++] = hexDigits[byte0 & 0xf];
		}
		return new String(str).toLowerCase(Locale.US);
	}
}
