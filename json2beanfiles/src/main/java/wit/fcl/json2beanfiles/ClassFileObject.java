package wit.fcl.json2beanfiles;

import java.util.ArrayList;
import java.util.List;

/**
 * 类的格式
 * 
 * @author fcl
 * @Date 2014-12-26 下午4:42:58
 */
public class ClassFileObject {
	private String packetName = "default";
	private String className = "DefaultClass";
	private List<FieldInfo> fields = new ArrayList<FieldInfo>();

	public String getPacketName() {
		return packetName;
	}

	public void setPacketName(String packetName) {
		this.packetName = packetName;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public List<FieldInfo> getFields() {
		return fields;
	}

	public void setFields(List<FieldInfo> fields) {
		this.fields = fields;
	}
}
