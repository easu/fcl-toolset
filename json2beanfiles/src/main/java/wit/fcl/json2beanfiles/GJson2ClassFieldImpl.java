package wit.fcl.json2beanfiles;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;

public class GJson2ClassFieldImpl {
	String packetName;
	List<ClassFileObject> list = new ArrayList<ClassFileObject>();

	public List<ClassFileObject> press(File jsonFie, String rootClassName,
			String packetName) throws Exception {
		JsonParser jsonParser = new JsonParser();
		// Gson gson = new GsonBuilder()
		// .setLongSerializationPolicy(LongSerializationPolicy.STRING)
		// .create();
		// GsonBuilder gsonBuilder = new GsonBuilder();
		// gsonBuilder.setLongSerializationPolicy(
		// LongSerializationPolicy.STRING );
		Reader reader = new InputStreamReader(new FileInputStream(jsonFie),
				"UTF-8");
		JsonElement je = jsonParser.parse(reader);
		this.packetName = packetName;
		if (je.isJsonObject()) {
			press(je, rootClassName);
		}
		return list;
	}

	/**
	 * <p>
	 * main press enter
	 * 
	 * @param je
	 * @param className
	 * @return
	 */
	private ClassFileObject press(JsonElement je, String className) {
		if (je.isJsonObject()) {
			return pressJsonObject(je.getAsJsonObject(), className);
		} else if (je.isJsonArray()) {
			return pressJsonArray(je.getAsJsonArray(), className);
		} else if (je.isJsonNull()) {
			return pressjsonNull(je.getAsJsonNull(), className);
		} else {
			throw new RuntimeException(className + " error type");
		}
	}

	private ClassFileObject pressjsonNull(JsonNull je, String className) {
		return null;
	}

	private ClassFileObject pressJsonArray(JsonArray je, String className) {
		int size = je.size();
		JsonElement jsonElement = size != 0 ? je.get(size - 1)
				: new JsonObject();
		// TODO ȡ���һ��
		press(jsonElement, className);
		// for (JsonElement jsonElement : je) {
		// press(jsonElement, className);
		// TODO δ����������У��ж��ԭʼ��һ�������
		// break;
		// }
		return null;
	}

	private ClassFileObject pressJsonObject(JsonObject je, String className) {
		ClassFileObject classFieldOject = new ClassFileObject();
		classFieldOject.setClassName(className);
		Set<Entry<String, JsonElement>> set = je.entrySet();
		Iterator<Entry<String, JsonElement>> iter = set.iterator();
		List<FieldInfo> fieldsList = classFieldOject.getFields();
		while (iter.hasNext()) {
			Entry<String, JsonElement> entry = iter.next();
			JsonElement value = entry.getValue();
			String key = entry.getKey();
			FieldInfo fieldInfo = null;
			if (value instanceof JsonObject) {
				fieldInfo = pressFieldOjbect((JsonObject) value, key);
			} else if (value instanceof JsonArray) {
				fieldInfo = pressFieldArray((JsonArray) value, key);
			} else if (value instanceof JsonPrimitive) {
				fieldInfo = pressFieldPrimitvie((JsonPrimitive) value, key);
			} else if (value instanceof JsonNull) {
				fieldInfo = pressFieldNull((JsonNull) value, key);
			}
			fieldsList.add(fieldInfo);
		}
		classFieldOject.setFields(fieldsList);
		classFieldOject.setPacketName(packetName);
		list.add(classFieldOject);
		return classFieldOject;
	}

	private FieldInfo pressFieldNull(JsonNull value, String key) {
		// TODO ��ô����
		throw new RuntimeException(key + " is JsonNull");
	}

	private FieldInfo pressFieldPrimitvie(JsonPrimitive value, String key) {
		FieldInfo info = new FieldInfo();
		if (value.isBoolean()) {
			info.setType("boolean");
			info.setFieldName(key);
			info.setDefualValue("" + value.getAsBoolean());
		} else if (value.isString()) {
			info.setType("String");
			info.setFieldName(key);
			info.setDefualValue("" + value.getAsString());
		} else {
			// qzone ��uin�����в���> int.max�����Ե�String ����
			if ("uin".equals(key)) {
				info.setType("String");
				info.setFieldName(key);
				info.setDefualValue("" + value.getAsString());
				return info;
			}
			// TODO �����ʣ�µ����Ͷ������ʹ����ˣ���Ҫ���ֿ�
			// ��ʱ���long ������
			long longValue = value.getAsLong();
			if (longValue > Integer.MAX_VALUE) {
				info.setType("Long");
				info.setFieldName(key);
				info.setDefualValue("" + value.getAsLong());
			} else {
				info.setType("Integer");
				info.setFieldName(key);
				info.setDefualValue("" + value.getAsInt());
			}
		}
		return info;
	}

	private FieldInfo pressFieldArray(JsonArray value, String key) {
		String className = fieldName2ArrayClassName(key);
		FieldInfo info = new FieldInfo();
		info.setFieldName(key);
		info.setType("List<" + className + ">");
		// TODO info.setDefualValue(defualValue)
		// �ݹ�����������е�Ԫ��
		pressJsonArray(value, className);
		return info;
	}

	private FieldInfo pressFieldOjbect(JsonObject value, String key) {
		String className = fieldName2ClassName(key);
		FieldInfo info = new FieldInfo();
		info.setFieldName(key);
		// TODO info.setDefualValue()
		info.setType(className);
		pressJsonObject(value, className);
		return info;
	}

	private String fieldName2ClassName(String key) {
		// ����ĸ��д
		String objectClassName = key.toUpperCase().substring(0, 1)
				+ key.subSequence(1, key.length());
		return objectClassName;
	}

	private String fieldName2ArrayClassName(String key) {
		// ����ĸ��д��������Element
		String arryClassName = key.toUpperCase().substring(0, 1)
				+ key.subSequence(1, key.length()) + "Element";
		return arryClassName;
	}
}
