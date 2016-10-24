## 日常使用的Java工具整理

### 日期

- [DateUtil](https://github.com/tomoya92/java-utils/blob/master/src/main/java/com/github/javautils/date/DateUtil.java)

### 加密

- [BASE64Decoder](https://github.com/tomoya92/java-utils/blob/master/src/main/java/com/github/javautils/encrypt/BASE64Decoder.java)
- [BASE64Encoder](https://github.com/tomoya92/java-utils/blob/master/src/main/java/com/github/javautils/encrypt/BASE64Encoder.java)
- [CharacterDecoder](https://github.com/tomoya92/java-utils/blob/master/src/main/java/com/github/javautils/encrypt/CharacterDecoder.java)
- [CharacterEncoder](https://github.com/tomoya92/java-utils/blob/master/src/main/java/com/github/javautils/encrypt/CharacterEncoder.java)
- [EncryptionUtil](https://github.com/tomoya92/java-utils/blob/master/src/main/java/com/github/javautils/encrypt/EncryptionUtil.java)

### 图片处理

- [ImageCompressUtil](https://github.com/tomoya92/java-utils/blob/master/src/main/java/com/github/javautils/image/ImageCompressUtil.java)
- [ImageUtil](https://github.com/tomoya92/java-utils/blob/master/src/main/java/com/github/javautils/image/ImageUtil.java)

### IO

- [FileUtil](https://github.com/tomoya92/java-utils/blob/master/src/main/java/com/github/javautils/io/FileUtil.java)

### 计算

- [BigDecimalUtil](https://github.com/tomoya92/java-utils/blob/master/src/main/java/com/github/javautils/math/BigDecimalUtil.java)

### 网络

- [HttpUtil](https://github.com/tomoya92/java-utils/blob/master/src/main/java/com/github/javautils/net/HttpUtil.java)
- [IpUtil](https://github.com/tomoya92/java-utils/blob/master/src/main/java/com/github/javautils/net/IpUtil.java)
- [RequestUtil](https://github.com/tomoya92/java-utils/blob/master/src/main/java/com/github/javautils/net/RequestUtil.java)

### 正则

- [Regular](https://github.com/tomoya92/java-utils/blob/master/src/main/java/com/github/javautils/regular/Regular.java)

### 字符串

- [StringUtil](https://github.com/tomoya92/java-utils/blob/master/src/main/java/com/github/javautils/string/StringUtil.java)

### 常量

- [Constants](https://github.com/tomoya92/java-utils/blob/master/src/main/java/com/github/javautils/Constants.java)

## 本地安装使用

克隆并编译安装

windows电脑还需要修改一下pom.xml里的插件配置

```
<!--如果是windows,请将bootclasspath里的:改成;-->
<bootclasspath>${java.home}/lib/rt.jar:${java.home}/lib/jce.jar</bootclasspath>
```

然后安装下面命令操作就可以将 `java-utils` 安装到本地了

```
git clone https://github.com/tomoya92/java-utils.git
cd java-utils
mvn install -Dmaven.test.skip=true
```

在个人项目里的`pom.xml`里添加依赖

```xml
<dependency>
    <groupId>com.github</groupId>
    <artifactId>java-utils</artifactId>
    <version>1.0</version>
</dependency>
```

## 贡献

欢迎提pr

## 感谢

[@qq347447474](https://github.com/qq347447474)
[JFinal工具类](http://git.oschina.net/jfinal/jfinal/tree/master/src/com/jfinal/kit?dir=1&filepath=src%2Fcom%2Fjfinal%2Fkit&oid=3712944c6c6eaa8531193b50681913617c0de33f&sha=15064f54a9d73939bd72a56f698ad95972654f09)

## 免责声明

本开源仓库内的工具类代码来自网络收集以及码农朋友提供,如果涉及侵权,请告知删除

## 开源协议

MIT