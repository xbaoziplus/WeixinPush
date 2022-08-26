package top.xbaoziplus.weixinpush.weixinpush.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import top.xbaoziplus.weixinpush.weixinpush.web.Pusher;

/**
 * @ClassName TaskConfig
 * @BelongsProject WeixinPush
 * @BelongsPackage top.xbaoziplus.weixinpush.weixinpush.config
 * @Author xbaozi
 * @CreateTime 2022-08-22  18:58
 * @Description 定时任务
 * @Version 1.0
 */
@Component
public class TaskConfig {
    @Autowired
    private Pusher pusher;

    @Autowired
    UserPropertyConfig userPropertyConfig;

    /**
     * 表示每天早上的7:30:00执行任务
     */
    @Scheduled(cron = "0 30 7 * * ?")
    public void goodMorning(){
        // 获取配置文件中的用户集合，逐个发送
        userPropertyConfig.getMyUser().forEach(userId -> {
            pusher.push(userId);
        });
    }
}
