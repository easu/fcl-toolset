## json到javabean文件的转化工具
###简介
以前时常要碰到json与javabean的转化。

如：

> Gson g = new GsonBuilder().create();
> 
> EmployeeBean em = g.fromJson(json, EmployeeBean.class);

能这样转化的前提是EmployeeBean.java已经创建好了。

但考虑一种情况，json 结构已经定义好了，需要了按json的格式，编写EmployeeBean.java文件。

这个工具的作用，就是根据json的结构来生成EmployeeBean.java。

###示例
``` json
{
  "key1": "value1",
  "key2": "value2",
  "key3": "value3",
  "element": [
    {
      "key": "1",
      "value": "2"
    },
    {
      "key": "3",
      "value": "4"
    }
  ]
}
```

这样一个json文件经过转化后，就会生成下面两个java文件。
JsonDemo.java
```java

public class JsonDemo {
  private String key1;
  private String key2;
  private String key3;
  private List<ElementElement> element;

  public void init() {
    key1 = "value1";
    key2 = "value2";
    key3 = "value3";
    element = null;
  }

  public void setKey1(String key1) {
    this.key1 = key1;
  }

  public String getKey1() {
    return this.key1;
  }
// other get and set method
...

}

```

ElementElement.java

``` java

public class ElementElement {
  private String key;
  private String value;

  public void init() {
    key = "3";
    value = "4";
  }

  public void setKey(String key) {
    this.key = key;
  }

  public String getKey() {
    return this.key;
  }

  public void setValue(String value) {
    this.value = value;
  }

  public String getValue() {
    return this.value;
  }

}
```


###编译
需要`maven`

在命令行窗口

`mvn clean compile assembly:single`

编译好的jar包在taget目录下。

###运行
`json2beanfiles <file> [packageName] [OutputCharset]`

如：
`java -jar json2beanfiles.jar C:/Text.json edu.fcl.bean utf8`
###实现思路
1. 从文件中读取json,用gson遍历。
2. 遍历时，记录key的相关信息（java文件的字段名），生成中间对象。
3. 中间对象中有字段名列表等，遍历中间对象，按java文件织组方式输出到文件。


### 未完善的
1. 如果json数组中的json对象格式不一致，可能就只按数组中最后一个对象的结构来处理的。
2. 如果数值类型的数值小于int.max，就映射成的int。但该数值类型在后面的序列化时，有的数据可能会大于int.max，无法处理。


