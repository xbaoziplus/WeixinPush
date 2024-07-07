package top.xbaoziplus.wechatpush.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

@Component
public class JiNianRiUtils {
    /** 牵手日期 */
    private static String LoveTime;
    /** 另一半生日 */
    private static String LoverBirthday;

    @Value("${MyTime.LoveTime}")
    public void setLoveTime(String loveTime) {
        LoveTime = loveTime;
    }

    @Value("${MyTime.LoverBirthday}")
    public void setLoverBirthday(String loverBirthday) {
        LoverBirthday = loverBirthday;
    }

    public static void test() {
        System.out.println(LoveTime + "===" + LoverBirthday);
    }

    /**
     * @description: 获取恋爱天数
     * @method: getLianAi
     * @author: xbaozi 
     * @date: 2022/8/22 18:03  
     **/
    public static int getLianAi(){
        return calculationLianAi("2019-03-05");
    }

    /**
     * @description: 获取生日倒计时
     * @method: getBirthday_Jo
     * @author: xbaozi
     * @date: 2022/8/22 18:03
     **/
    public static int getBirthday(){
        try {
            return calculationBirthday("2000-07-18");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }

	/**
	 * @description: 计算生日天数
	 * @method: calculationBirthday
	 * @author: xbaozi
	 * @date: 2022/8/22 18:04 
	 **/
    public static int calculationBirthday(String birthDate) throws ParseException {
        SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM-dd");
        // 存今天
        Calendar cToday = Calendar.getInstance();
        // 存生日
        Calendar cBirth = Calendar.getInstance();
        // 设置生日
        cBirth.setTime(myFormatter.parse(birthDate));
        // 修改为本年
        cBirth.set(Calendar.YEAR, cToday.get(Calendar.YEAR));
        int days;
        if (cBirth.get(Calendar.DAY_OF_YEAR) < cToday.get(Calendar.DAY_OF_YEAR)) {
            // 生日已经过了，要算明年的了
            days = cToday.getActualMaximum(Calendar.DAY_OF_YEAR) - cToday.get(Calendar.DAY_OF_YEAR);
            days += cBirth.get(Calendar.DAY_OF_YEAR);
        } else {
            // 生日还没过
            days = cBirth.get(Calendar.DAY_OF_YEAR) - cToday.get(Calendar.DAY_OF_YEAR);
        }
        // 输出结果
        if (days == 0) {
            return 0;
        } else {
            return days;
        }
    }

    /**
     * @description: 计算天数
     * @method: calculationLianAi
     * @author: xbaozi
     * @date: 2022/8/22 18:01
     **/
    public static int calculationLianAi(String date) {
        DateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        int day = 0;
        try {
            long time = System.currentTimeMillis() - simpleDateFormat.parse(date).getTime();
            day = (int) (time / 86400000L);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return day;
    }
}