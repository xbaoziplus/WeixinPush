package top.xbaoziplus.wechatpush.utils;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

/**
 * @author xbaoziplus
 */
@Component
public class TianJuApiUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(TianJuApiUtils.class);

    @Value("${baiduMap.tianApiKey}")
    private String tianApiKey;

    public String requestTianJuApi(String httpUrl) {
        BufferedReader reader = null;
        String result = null;
        StringBuilder sbf = new StringBuilder();

        try {
            URL url = new URL(httpUrl);
            HttpURLConnection connection = (HttpURLConnection) url
                    .openConnection();
            connection.setRequestMethod("GET");
            InputStream is = connection.getInputStream();
            reader = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
            String strRead = null;
            while ((strRead = reader.readLine()) != null) {
                sbf.append(strRead);
                sbf.append("\r\n");
            }
            reader.close();
            result = sbf.toString();
        } catch (Exception e) {
            LOGGER.error("获取天聚数据失败， url: {}", httpUrl, e);
        }
        JSONObject jsonObject = JSONObject.parseObject(result);
        assert jsonObject != null;
        return jsonObject.getJSONObject("result").getString("content");
    }

    public String getCaiHongPiData() {
        String httpUrl = String.format("https://apis.tianapi.com/%s/index?key=%s", "caihongpi", tianApiKey);
        return requestTianJuApi(httpUrl);
    }

    public String getSayLoveData() {
        String httpUrl = String.format("https://apis.tianapi.com/%s/index?key=%s", "saylove", tianApiKey);
        return requestTianJuApi(httpUrl);
    }

}