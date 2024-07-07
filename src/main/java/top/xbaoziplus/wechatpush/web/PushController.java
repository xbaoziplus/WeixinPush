package top.xbaoziplus.wechatpush.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.xbaoziplus.wechatpush.config.UserPropertyConfig;

import java.util.HashMap;
import java.util.Map;

/**
 * @author xbaozi
 * @create 2022-08-22  19:37
 * @Version 1.0
 */
@RestController
public class PushController {
    @Autowired
    private Pusher pusher;

    @Autowired
    UserPropertyConfig userPropertyConfig;

    @RequestMapping("/immediatePush/param/{msg}")
    public Map<String, String> immediatePushWithParam(@PathVariable String msg) {
        // 获取配置文件中的用户集合，逐个发送
        userPropertyConfig.getMyUser().forEach(userId -> {
            pusher.push(userId, msg);
        });
        Map<String, String> map = new HashMap<String, String>(2);
        map.put("msg", "推送成功");
        map.put("code", "200");
        return map;
    }

    @RequestMapping("/immediatePush")
    public Map<String, String> immediatePush() {
        // 获取配置文件中的用户集合，逐个发送
        userPropertyConfig.getMyUser().forEach(userId -> {
            pusher.push(userId);
        });
        Map<String, String> map = new HashMap<String, String>(2);
        map.put("msg", "推送成功");
        map.put("code", "200");
        return map;
    }
}
