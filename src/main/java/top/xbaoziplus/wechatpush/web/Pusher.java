package top.xbaoziplus.wechatpush.web;

import me.chanjar.weixin.mp.api.WxMpInMemoryConfigStorage;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateData;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import top.xbaoziplus.wechatpush.entity.Weather;
import top.xbaoziplus.wechatpush.utils.JiNianRiUtils;
import top.xbaoziplus.wechatpush.utils.TianJuApiUtils;
import top.xbaoziplus.wechatpush.utils.WeatherUtils;

@Component
public class Pusher {
    private static final Logger logger = LoggerFactory.getLogger(Pusher.class);

    private static String appId;
    private static String secret;
    private static String templateId;

    @Value("${MyWeiXin.appId}")
    public void setAppId(String appId) {
        Pusher.appId = appId;
    }

    @Value("${MyWeiXin.appsecret}")
    public void setSecret(String secret) {
        Pusher.secret = secret;
    }

    @Value("${MyWeiXin.templateId}")
    public void setTemplateId(String templateId) {
        Pusher.templateId = templateId;
    }

    @Autowired
    private WeatherUtils weatherUtils;

    @Autowired
    TianJuApiUtils tianJuApiUtils;

    public void push(String userId, String sayLoveMsg) {
        // 配置微信公众号平台
        WxMpInMemoryConfigStorage wxStorage = new WxMpInMemoryConfigStorage();
        wxStorage.setAppId(appId);
        wxStorage.setSecret(secret);
        WxMpService wxMpService = new WxMpServiceImpl();
        wxMpService.setWxMpConfigStorage(wxStorage);
        // 推送消息
        WxMpTemplateMessage templateMessage = WxMpTemplateMessage.builder()
                .toUser(userId)
                .templateId(templateId)
                .build();
        // 获取天气信息
        Weather weather = weatherUtils.getWeather();
        String dateData = String.format("%s %s", weather.getDate(), weather.getWeek());
        String weatherData = String.format("%s(白) / %s(夜)", weather.getText_day(), weather.getText_night());
        String temperatureData = String.format("%s°C / %s°C", weather.getHigh(), weather.getLow());
        String besidesData = weatherData.contains("雨") ? "! ! ! 今天有雨记得带伞 ! ! !" : "! ! ! 今天天气应该不错 ! ! !";
        templateMessage.addData(new WxMpTemplateData("date", dateData));
        templateMessage.addData(new WxMpTemplateData("weather", weatherData));
        templateMessage.addData(new WxMpTemplateData("temperature", temperatureData));
        templateMessage.addData(new WxMpTemplateData("remember", "! ! ! 爱你也爱我 ! ! !"));
        templateMessage.addData(new WxMpTemplateData("besides", besidesData));
        templateMessage.addData(new WxMpTemplateData("anniversary", String.valueOf(JiNianRiUtils.getLianAi())));
        templateMessage.addData(new WxMpTemplateData("loveLetter", sayLoveMsg));
        templateMessage.addData(new WxMpTemplateData("encourage", tianJuApiUtils.getCaiHongPiData()));

        try {
            logger.info("templateMessage: {}", templateMessage.toJson());
            wxMpService.getTemplateMsgService().sendTemplateMsg(templateMessage);
        } catch (Exception e) {
            logger.error("推送失败", e);
        }
    }

    public void push(String userId) {
        push(userId, tianJuApiUtils.getSayLoveData());
    }
}