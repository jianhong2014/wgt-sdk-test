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

### 使用SDK的步骤
1.springboot 注入 WgtDeviceBuilder，并在启动类里面添加componentScan注、比如</br>
    @ComponentScan({"com.gvr.datahub.sdk.wgt"}</br>
    public class TestWgtSdkApplication
    
    @Autowired
    private WgtDeviceBuilder wgtDeviceBuilder;

## SDK 接口如下:
 1.addVisListener(Consumer\<NozzState\> visProcessor), 监听车环的识别变化</br>
 NozzState 字段描述如下：</br>

名称 | 类型 |是否必须 |描述 
----|----|----|---
mid | String | 是 |消息的唯一标识，wgt产生
wgtId | String | 是 |wgt的唯一标识
nozNr | String | 是 |读头的唯一号
track1| String | 是,车环离开时，此字段为空 |车环号

 2.NozzState getNozzState(String nozNr)，读取加油枪读头识别的车环，如果读不到，字段会为空</br>
 
 
名称 | 类型 |是否必须 |描述 
----|----|----|---
mid | String | 是 |消息的唯一标识，wgt产生
wgtId | String | 是 |wgt的唯一标识
nozNr | String | 是 |读头的唯一号
track1| String | 是,车环离开时，此字段为空 |车环号

3.String setupWgt(WgtSetupRequest info)，设置wgt的信息</br>

名称 | 类型 |是否必须 |描述 
----|----|----|---
wgtId | String | 是 |wgt的标识
wgtIp | String | 是 |wgt的ip地址
timeout | int | 是 |超时时间
wgtAddr| String |否| wgt串口地址

返回的状态码如下：</br>

状态码 | 描述 
----|----
000 | 成功 
001 | 一般错误
002 | 消息id错误 
003 | 阻塞
004 | 错误消息格式
005 | 缺少参数
006 | 油枪离线
007 | 错误枪号 
008 | 错误wgtId 
009 | 认证失败
010 | 端口错误
999 | 超时无响应
</br>
4.addWgtHbConsumer(Consumer<WgtKeepLive> wgtDataProcessor),wgt bridge的心跳回调处理:</br>
WgtKeepLive 字段如下：</br>

名称 | 类型 |是否必须 |描述 
----|----|----|---
mid | String | 是 |消息的唯一标识，wgt产生
rc | String | 是 |状态码，定义如上
