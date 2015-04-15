## 文本格式化工具
主要做为UE的插件。工作中会时常会编辑一些json数据,每次都要在线格式化一化，十分不便，所以这个工具，把在ue里的数据，直接格式化。

目前支持`json`,`html`。

###编译
需要`maven`

在命令行窗口

`mvn clean compile assembly:single`

编译好的jar包在taget目录下。


###UE中的配置

UE菜单 -> 高级 -> 工具配置

点插入

菜单名：随意


命令行：
`java -jar "C:\Program Files (x86)\UE中文版编辑器\plugin\textformat-1.0-jar-with-dependencies.jar" "json" "%n%e" "%n%e" "gbk"`

其中`-jar` 后面是textformat jar包的具体目录
`%n%e`是文件路径。

工作目录：
`%p`

这样按快捷键直接就可以格式化UE中的json或者html

###实现
json格式化使用的是gson库。

html格式化使用的是jsoup库。

###TODO
js格式化

发现了一个python版本的
[js-beautify](https://github.com/beautify-web/js-beautify)



