package top.xbaoziplus.weixinpush.weixinpush.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@ConfigurationProperties("wx-user")
@Data
public class UserPropertyConfig {
  private List<String> myUser;
}