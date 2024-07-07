package top.xbaoziplus.wechatpush.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author xbaozi
 * @create 2022-08-22  17:21
 * @description 对应官网的天气实体类
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Weather {
    String wd_night;
    String date;
    String high;
    String week;
    String text_night;
    String wd_day;
    String low;
    String wc_night;
    String text_day;
    String wc_day;
    // 当前天气
    String text_now;
    // 当前温度
    String temp;
    // 风级大小
    String wind_class;
    // 风向
    String wind_dir;
}
