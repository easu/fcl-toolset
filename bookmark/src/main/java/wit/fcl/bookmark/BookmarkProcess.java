package wit.fcl.bookmark;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class BookmarkProcess {

	public static void main(String[] args) {

//		String filePath = "D:\\fcl\\firefox_back\\bookmarks-2015-05-11_new.json";
//		String dstfilePath = "D:\\fcl\\firefox_back\\other.json";
		// String selfDefFile= "D:\\fcl\\firefox_back\\selfDef.json";
//		mainwork(filePath, dstfilePath);
		// getOutLine(filePath,selfDefFile);

	}

	/**
	 * �õ���ǩ�ļ��Ļ������ for test
	 * 
	 * @param filePath
	 * @param selfDefFile
	 */
	public static void getOutLine(String filePath, String selfDefFile) {
		FirefoxBookmarkBean bean = getBean(filePath);
		bean.getChildren().get(0).getChildren().clear();
		bean.getChildren().get(1).getChildren().clear();
		bean.getChildren().get(2).getChildren().clear();
		saveToFile(bean, selfDefFile);
	}

	static private Gson gson = new GsonBuilder().disableHtmlEscaping()
			.setPrettyPrinting().create();

	/**
	 * ���ļ������л���bean
	 * 
	 * @param path
	 * @return
	 */
	public static FirefoxBookmarkBean getBean(String path) {
		// TODO defautl is utf8
		FirefoxBookmarkBean res = null;
		try {
			res = gson.fromJson(new InputStreamReader(new FileInputStream(
					new File(path)), "UTF-8"), FirefoxBookmarkBean.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	/**
	 * ����������
	 * 
	 * @param list
	 */
	public static void sort(List<ChildrenElement> list) {
		Collections.sort(list, new BookMarkComparator());
	}

	/**
	 * ��bean�ļ����浽�ļ���
	 * 
	 * @param bean
	 * @param filePath
	 */
	public static void saveToFile(FirefoxBookmarkBean bean, String filePath) {
		String jsonStr = gson.toJson(bean);
		try {
			FileOutputStream fos = new FileOutputStream(filePath);
			fos.write(jsonStr.getBytes("UTF-8"));
			fos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		// TODO why this method cant do when file more then 1M
		// gson.toJson(res,new FileWriter(new File(dstfilePath)));
	}

	/**
	 * ����ǩ�е�Ԫ�ؽ��з��࣬ȥ�أ�����
	 * 
	 * @param srcFilePath
	 * @param dstFilePath
	 */
	public static void mainwork(String srcFilePath, String dstFilePath) {

		FirefoxBookmarkBean bean = getBean(srcFilePath);
		List<ChildrenElement> list = bean.getChildren().get(0).getChildren();
		sort(list);
		removeDuplicateWithOrder(list);
		modifyIndex(list);

		// removeRecentUse(list);
		saveToFile(bean, dstFilePath);
	}

	/**
	 * <p>
	 * ȥ�����ʹ�ñ�ǩ,for test
	 * 
	 * @param list
	 */
	public static void removeRecentUse(List<ChildrenElement> list) {
		String guid = "ir9Od1_sYqcm";
		String guid2 = "rWBlcfdr-NvR";
		ChildrenElement c1 = null;
		ChildrenElement c2 = null;
		for (ChildrenElement childrenElement : list) {
			String guidtag = childrenElement.getGuid();
			if (guidtag.equals(guid)) {
				c1 = childrenElement;
			}
			if (guidtag.equals(guid2)) {
				c2 = childrenElement;
			}
		}
		list.remove(c1);
		list.remove(c2);

	}

	/**
	 * �޸��б��е�index��˳��
	 * 
	 * @param list
	 */
	public static void modifyIndex(List<ChildrenElement> list) {
		String type = "text/x-moz-place";
		int index = 0;
		for (ChildrenElement childrenElement : list) {
			if (!type.equals(childrenElement.getType())) {
				childrenElement.setIndex(index++);
			}
		}
		System.out.println("floder" + index);
		for (ChildrenElement childrenElement : list) {
			if (type.equals(childrenElement.getType())) {
				childrenElement.setIndex(index++);
			}
		}
	}

	/**
	 * �Ƴ������е��ظ�Ԫ��
	 * 
	 * @param list
	 */
	public static <T> void removeDuplicateWithOrder(List<T> list) {
		Set<T> set = new HashSet<T>();
		List<T> newList = new ArrayList<T>();
		for (Iterator<T> iter = list.iterator(); iter.hasNext();) {
			T element = (T) iter.next();
			if (set.add(element))
				newList.add(element);
		}
		list.clear();
		list.addAll(newList);
	}
}
