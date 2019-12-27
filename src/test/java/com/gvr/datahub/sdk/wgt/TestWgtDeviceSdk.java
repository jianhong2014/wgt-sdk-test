package com.gvr.datahub.sdk.wgt;


import com.alibaba.fastjson.JSON;
import com.gvr.datahub.sdk.wgt.vo.NozzState;
import com.gvr.datahub.sdk.wgt.vo.WgtKeepLive;
import com.gvr.sdk.test.TestWgtSdkApplication;
import org.junit.Test;

import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.function.Consumer;


@SpringBootTest(classes = TestWgtSdkApplication.class)
@RunWith(SpringRunner.class)
public class TestWgtDeviceSdk {
    private final  static Logger logger = LoggerFactory.getLogger(TestWgtDeviceSdk.class);

    @Autowired
    private WgtDeviceBuilder wgtDeviceBuilder;

    @Test
    public void testRpc() throws InterruptedException {
        logger.info("test hongjn2 ");
        try(WgtDevice wgt = wgtDeviceBuilder.buildWgtDevice("localhost","110")){
            NozzState nozzState = wgt.getNozzState("01");
            logger.info("get nozzstate {}", JSON.toJSON(nozzState));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testDataListener(){
        logger.info("test wdg device listener");
        Consumer<WgtKeepLive> nozzStateProcessor = (stat) -> {
           logger.info("heartbeat监听到wgt 心跳数据 {}",stat);
        };
        Consumer<NozzState> visListener = (stat) -> {
            logger.info("visListener 识别到车环 {}",stat);
        };
        try(WgtDevice wgt = wgtDeviceBuilder.buildWgtDevice("localhost","110")){
           // wgt.addWgtHbConsumer(nozzStateProcessor);
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

}
