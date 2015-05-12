package wit.fcl.bookmark;

import java.util.Comparator;

/**
 * @author Administrator 
 * ��ǩ����ıȽ������Ȱ����ͱȣ��ٰ�uri��
 * ������ͬ����վ�����ݿ��Է�һ��
 */
public class BookMarkComparator implements Comparator<ChildrenElement> {

	@Override
	public int compare(ChildrenElement o1, ChildrenElement o2) {
		if (o1.getType().compareTo(o2.getType()) == 0) {
			// String type = "text/x-moz-place-container";
			// String type2 = "text/x-moz-place-separator";
			String type3 = "text/x-moz-place";
			if (type3.equals(o1.getType())) {
				return o1.getUri().compareTo(o2.getUri());
			}
			return 0;
		} else if (o1.getType().compareTo(o2.getType()) > 0) {
			return 1;
		} else {
			return -1;
		}
	}
}
