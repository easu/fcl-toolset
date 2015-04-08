package wit.fcl.filehash;

import java.io.File;

import org.junit.Test;

public class FileHashTest {

	@Test
	public void test() throws Exception {
		String filepath = "C:\\Users\\Administrator\\Desktop\\test.html";
		long t = System.currentTimeMillis();
//		String str = FileHashUtil.getFileSha1(new File(filepath));
		String str = FileHashUtil.getFileMD5(new File(filepath));
		System.out.println(System.currentTimeMillis()-t);
		System.out.println(str);
	}

}
