package top.xbaoziplus.weixinpush.weixinpush.utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import top.xbaoziplus.weixinpush.weixinpush.entity.Weather;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class WeatherUtils {
    @Value("${baiduMap.AK}")
    private String AKCode;

    @Value("${baiduMap.localAdminCode}")
    private String LocalAdminCode;

    public Weather getWeather(){
        RestTemplate restTemplate = new RestTemplate();
        Map<String,String> map = new HashMap<String,String>();
        // 地方行政代码
        map.put("district_id", LocalAdminCode);
        // 返回全属性数据
        map.put("data_type","all");
        // 设置AK码
        map.put("ak", AKCode);
        // 获取天气信息
        String res = restTemplate.getForObject(
                "https://api.map.baidu.com/weather/v1/?district_id={district_id}&data_type={data_type}&ak={ak}",
                String.class,
                map);
        // 转换成天气对象
        JSONObject json = JSONObject.parseObject(res);
        JSONArray forecasts = json.getJSONObject("result").getJSONArray("forecasts");
        List<Weather> weathers = forecasts.toJavaList(Weather.class);
        JSONObject now = json.getJSONObject("result").getJSONObject("now");
        Weather weather = weathers.get(0);
        weather.setText_now(now.getString("text"));
        weather.setTemp(now.getString("temp"));
        weather.setWind_class(now.getString("wind_class"));
        weather.setWind_dir(now.getString("wind_dir"));
        return weather;
    }
}