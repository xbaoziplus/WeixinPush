package top.xbaoziplus.weixinpush.weixinpush.web;

import me.chanjar.weixin.mp.api.WxMpInMemoryConfigStorage;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateData;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import top.xbaoziplus.weixinpush.weixinpush.entity.Weather;
import top.xbaoziplus.weixinpush.weixinpush.utils.CaiHongPiUtils;
import top.xbaoziplus.weixinpush.weixinpush.utils.JiNianRiUtils;
import top.xbaoziplus.weixinpush.weixinpush.utils.WeatherUtils;

@Component
public class Pusher {

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
    CaiHongPiUtils caiHongPiUtils;

    public void push(String userId) {
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
        //Map<String, String> map = caiHongPiUtils.getEnsentence();
        templateMessage.addData(new WxMpTemplateData("riqi", weather.getDate() + "  " + weather.getWeek(), "#00BFFF"));
        templateMessage.addData(new WxMpTemplateData("tianqi", weather.getText_now(), "#00FFFF"));
        templateMessage.addData(new WxMpTemplateData("low", weather.getLow() + "", "#173177"));
        templateMessage.addData(new WxMpTemplateData("temp", weather.getTemp() + "", "#EE212D"));
        templateMessage.addData(new WxMpTemplateData("high", weather.getHigh() + "", "#FF6347"));
        templateMessage.addData(new WxMpTemplateData("windclass", weather.getWind_class() + "", "#42B857"));
        templateMessage.addData(new WxMpTemplateData("winddir", weather.getWind_dir() + "", "#B95EA3"));
        templateMessage.addData(new WxMpTemplateData("caihongpi", caiHongPiUtils.getCaiHongPi(), "#FF69B4"));
        templateMessage.addData(new WxMpTemplateData("lianai", JiNianRiUtils.getLianAi() + "", "#FF1493"));
        templateMessage.addData(new WxMpTemplateData("shengri", JiNianRiUtils.getBirthday() + "", "#FFA500"));
        //templateMessage.addData(new WxMpTemplateData("shengri2", JiNianRiUtils.getBirthday_Hui() + "", "#FFA500"));
        //templateMessage.addData(new WxMpTemplateData("enSentence", map.get("enSentence") + "", "#C71585"));
        //templateMessage.addData(new WxMpTemplateData("zhSentence", map.get("zhSentence") + "", "#C71585"));
        String beizhu = "❤";
        if (JiNianRiUtils.getLianAi() % 365 == 0) {
            beizhu = "今天是恋爱" + (JiNianRiUtils.getLianAi() / 365) + "周年纪念日！";
        }
        if (JiNianRiUtils.getBirthday() == 0) {
            beizhu = "今天是生日，生日快乐呀！";
        }
        templateMessage.addData(new WxMpTemplateData("beizhu", beizhu, "#FF0000"));

        try {
            System.out.println(templateMessage.toJson());
            System.out.println(wxMpService.getTemplateMsgService().sendTemplateMsg(templateMessage));
        } catch (Exception e) {
            System.out.println("推送失败：" + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * @description: 自定义信息替换彩虹屁
     * @method: push
     * @author: xbaozi
     * @date: 2022/8/22 19:44
     **/
    public void push(String userId, String msg) {
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
        //Map<String, String> map = caiHongPiUtils.getEnsentence();
        templateMessage.addData(new WxMpTemplateData("riqi", weather.getDate() + "  " + weather.getWeek(), "#00BFFF"));
        templateMessage.addData(new WxMpTemplateData("tianqi", weather.getText_now(), "#00FFFF"));
        templateMessage.addData(new WxMpTemplateData("low", weather.getLow() + "", "#173177"));
        templateMessage.addData(new WxMpTemplateData("temp", weather.getTemp() + "", "#EE212D"));
        templateMessage.addData(new WxMpTemplateData("high", weather.getHigh() + "", "#FF6347"));
        templateMessage.addData(new WxMpTemplateData("windclass", weather.getWind_class() + "", "#42B857"));
        templateMessage.addData(new WxMpTemplateData("winddir", weather.getWind_dir() + "", "#B95EA3"));
        templateMessage.addData(new WxMpTemplateData("caihongpi", msg, "#FF69B4"));
        templateMessage.addData(new WxMpTemplateData("lianai", JiNianRiUtils.getLianAi() + "", "#FF1493"));
        templateMessage.addData(new WxMpTemplateData("shengri", JiNianRiUtils.getBirthday() + "", "#FFA500"));
        //templateMessage.addData(new WxMpTemplateData("shengri2", JiNianRiUtils.getBirthday_Hui() + "", "#FFA500"));
        //templateMessage.addData(new WxMpTemplateData("enSentence", map.get("enSentence") + "", "#C71585"));
        //templateMessage.addData(new WxMpTemplateData("zhSentence", map.get("zhSentence") + "", "#C71585"));
        String beizhu = "❤";
        if (JiNianRiUtils.getLianAi() % 365 == 0) {
            beizhu = "今天是恋爱" + (JiNianRiUtils.getLianAi() / 365) + "周年纪念日！";
        }
        if (JiNianRiUtils.getBirthday() == 0) {
            beizhu = "今天是生日，生日快乐呀！";
        }
        templateMessage.addData(new WxMpTemplateData("beizhu", beizhu, "#FF0000"));

        try {
            System.out.println(templateMessage.toJson());
            System.out.println(wxMpService.getTemplateMsgService().sendTemplateMsg(templateMessage));
        } catch (Exception e) {
            System.out.println("推送失败：" + e.getMessage());
            e.printStackTrace();
        }
    }
}