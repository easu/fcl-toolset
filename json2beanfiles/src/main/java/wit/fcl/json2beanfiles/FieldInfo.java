package wit.fcl.json2beanfiles;

/**
 * ×Ö¶Î¸ñÊ½
 * 
 * @author fcl
 * @Date 2014-12-26 ÏÂÎç4:43:46
 */
public class FieldInfo {

	public FieldInfo(String type, String fieldName, String defualValue) {
		this.type = type;
		this.fieldName = fieldName;
		this.defualValue = defualValue;
	}

	public FieldInfo() {
		super();
	}

	private String type;
	private String fieldName;
	private String defualValue;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public String getDefualValue() {
		if ("String".equals(type)) {
			return "\"" + defualValue + "\"";
		} else if ("Long".equals(type)) {
			return defualValue + "L";
		}
		return defualValue;
	}

	public void setDefualValue(String defualValue) {
		this.defualValue = defualValue;
	}
}
