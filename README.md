# wgt-sdk-test
wgt的java sdk ，java版本1.8+测试.</br>
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

使用此SDK，需要配套安装部署wgt的虚拟组件，可单独联系jian.hong@veeder.com申请此组件安装部署。</br>

基于此SDK,应用可以监听车环状态，或者主动读取车环信息，进行其他逻辑的开发。

## SDK 暂时提供两个接口:
 1.addVisListener(Consumer\<NozzState\> visProcessor), 监听车环的识别变化</br>
 2.NozzState getNozzState(String nozNr)，读取加油枪读头识别的车环，如果读不到，字段会为空</br>

NozzState 字段描述如下：</br>

名称 | 类型 |是否必须 |描述 
----|----|----|---
mid | String | 是 |消息的唯一标识，wgt产生
wgtId | String | 是 |wgt的唯一标识
nozNr | String | 是 |读头的唯一号
track1| String | 是,车环离开时，此字段为空 |车环号
