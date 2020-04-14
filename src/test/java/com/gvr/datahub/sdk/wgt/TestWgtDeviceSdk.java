package com.gvr.datahub.sdk.wgt;


import com.alibaba.fastjson.JSON;
import com.gvr.datahub.sdk.wgt.vo.NozzState;
import com.gvr.datahub.sdk.wgt.vo.WgtKeepLive;
import com.gvr.datahub.sdk.wgt.vo.WgtSetupRequest;
import com.gvr.sdk.test.TestWgtSdkApplication;
import org.junit.FixMethodOrder;
import org.junit.Test;

import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.function.Consumer;


@SpringBootTest(classes = TestWgtSdkApplication.class)
@RunWith(SpringRunner.class)
@FixMethodOrder(MethodSorters.JVM)
public class TestWgtDeviceSdk {
    private final  static Logger logger = LoggerFactory.getLogger(TestWgtDeviceSdk.class);

    @Autowired
    private WgtDeviceBuilder wgtDeviceBuilder;

    private final String wgtId = "110";

    private final String wgtIp = "10.28.188.31";

    private final String wgtPort = "5000";

    private final String wgtAddr = "32";

    private final String dc = "ec2-52-82-4-65.cn-northwest-1.compute.amazonaws.com.cn";


    /***
     * 测试主动读头状态查询
     */
    @Test
    public void testGetNozzleStat1()  {
        logger.info("test testGetNozzleStat");
        try(WgtDevice wgt = wgtDeviceBuilder.buildWgtDevice("localhost")){
            NozzState nozzState = wgt.getNozzState(wgtId,1);
            logger.info("get nozzstate {}", JSON.toJSON(nozzState));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testGetNozzleStat2()  {
        logger.info("test testGetNozzleStat");
        try(WgtDevice wgt = wgtDeviceBuilder.buildWgtDevice("localhost")){
            NozzState nozzState = wgt.getNozzState(wgtId,1);
            logger.info("get nozzstate {}", JSON.toJSON(nozzState));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void setBridge(){
        logger.info("test setup bridge");
        try(WgtDevice wgt = wgtDeviceBuilder.buildWgtDevice("localhost")){
            //String rc = wgt.setupBridge(5000);
          //  logger.info("set up  bridge {}", rc);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void setupWgt(){
        logger.info("test setup wgt");
        try(WgtDevice wgt = wgtDeviceBuilder.buildWgtDevice("localhost")){
            WgtSetupRequest request = new WgtSetupRequest();
            request.setTimeout(2000);
            request.setWgtId(wgtId);
            request.setWgtIp(wgtIp);
            request.setWgtPort(wgtPort);
            request.setTimeout(2000);
            request.setWgtAddr(wgtAddr);
            String  rc =  wgt.setupWgt(request);
            logger.info("set up  wgt {}", rc);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /***
     * 测试监听读头状态变化
     */
    @Test
    public void testDataListener(){
        logger.info("test wdg device listener");
        Consumer<WgtKeepLive> nozzStateProcessor = (stat) -> {
           logger.info("heartbeat监听到wgt 心跳数据 {}",stat);
        };
        Consumer<NozzState> visListener = (stat) -> {
            logger.info("visListener 识别到车环 {}",stat);
        };
        try(WgtDevice wgt = wgtDeviceBuilder.buildWgtDevice("161.189.102.2")){
            wgt.addWgtHbConsumer(nozzStateProcessor);
            wgt.addVisListener(visListener);
            boolean test = true;
            while(test){
                logger.info("testDataListener ");
                Thread.sleep(10000);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 读取枪号绑定的VIS读头号
     */
    @Test
    public void testGunNrRead(){
        try(WgtDevice wgt = wgtDeviceBuilder.buildWgtDevice("161.189.102.2")){
            int nr = wgt.getNozNr(15);
            logger.info("15号枪的读头号{}",nr);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 通过枪号读取枪绑定的读头识别状态
     */
    @Test
    public void testGunVisStatByGunId(){
        try(WgtDevice wgt = wgtDeviceBuilder.buildWgtDevice("161.189.102.2")){
            NozzState nrStateByGun = wgt.getNrStateByGun(wgtId,15);
            logger.info("15号枪的读头识别状态{}",nrStateByGun);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
