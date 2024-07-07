package top.xbaoziplus.wechatpush;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import top.xbaoziplus.wechatpush.config.UserPropertyConfig;
import top.xbaoziplus.wechatpush.utils.TianJuApiUtils;
import top.xbaoziplus.wechatpush.utils.JiNianRiUtils;
import top.xbaoziplus.wechatpush.utils.WeatherUtils;
import top.xbaoziplus.wechatpush.web.Pusher;

@SpringBootTest
class WeixinPushApplicationTests {
    @Autowired
    WeatherUtils weatherUtils;

    @Autowired
    TianJuApiUtils tianJuApiUtils;

    @Autowired
    Pusher pusher;

    @Test
    public void testWeather() {
        System.out.println(weatherUtils.getWeather());
    }

    @Test
    public void testCaiHongPi() {
        System.out.println(tianJuApiUtils.getCaiHongPiData());
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
