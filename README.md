# wgt-sdk-test
wgt的java sdk 测试
sdk放在github 上的maven个人仓库上，应用的POM 里面增加仓库地址：
```xml
    <repositories>
        <repository>
            <id>gvr-mvn-repo</id>
            <url>https://raw.githubusercontent.com/jianhong2014/mvn-repo/master</url>
            <snapshots>
                <enabled>true</enabled>
                <updatePolicy>always</updatePolicy>
            </snapshots>
        </repository>
    </repositories>
```

dependency增加如下内容：
```xml
        <dependency>
            <groupId>com.gvr.datahub</groupId>
            <artifactId>wgt-dev-sdk</artifactId>
            <version>1.0-RELEASE</version>
        </dependency>
```

使用SDK，需要配套安装部署wgt的虚拟组件，可单独联系jian.hong@veeder.com申请此组件安装部署。

## SDK 暂时提供两个接口:
 1.addVisListener(Consumer<NozzState> visProcessor), 监听车环的识别变化</br>
 2.NozzState getNozzState(String nozNr)，读取加油枪读头识别的车环</br>
