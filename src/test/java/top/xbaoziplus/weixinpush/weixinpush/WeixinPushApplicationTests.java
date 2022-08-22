package top.xbaoziplus.weixinpush.weixinpush;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import top.xbaoziplus.weixinpush.weixinpush.config.UserPropertyConfig;
import top.xbaoziplus.weixinpush.weixinpush.utils.CaiHongPiUtils;
import top.xbaoziplus.weixinpush.weixinpush.utils.JiNianRiUtils;
import top.xbaoziplus.weixinpush.weixinpush.utils.WeatherUtils;
import top.xbaoziplus.weixinpush.weixinpush.web.Pusher;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
class WeixinPushApplicationTests {
    @Autowired
    WeatherUtils weatherUtils;

    @Autowired
    CaiHongPiUtils caiHongPiUtils;

    @Autowired
    Pusher pusher;

    @Test
    public void testWeather() {
        System.out.println(weatherUtils.getWeather());
    }

    @Test
    public void testCaiHongPi() {
        System.out.println(caiHongPiUtils.getCaiHongPi());
    }

    @Test
    public void testJiNianRiUtils() {
        JiNianRiUtils.test();
    }

    @Autowired
    UserPropertyConfig userPropertyConfig;

    @Test
    public void testPush() {
        userPropertyConfig.getMyUser().forEach(userId -> {
            pusher.push(userId);
        });
    }

}
