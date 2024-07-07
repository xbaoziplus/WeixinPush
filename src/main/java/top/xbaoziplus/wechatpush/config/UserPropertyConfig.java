package top.xbaoziplus.wechatpush.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * @description 微信用户配置
 * @author xbaoziplus
 * @create 2024/6/10 16:14 */
@Configuration
@ConfigurationProperties("wx-user")
@Data
public class UserPropertyConfig {
  private List<String> myUser;
}