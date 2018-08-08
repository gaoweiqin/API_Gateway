package com.util;
import java.util.Date;
import java.text.SimpleDateFormat;
public class Certification {
    public static void main(String[] args) {
        //24小时 = 86400000毫秒，比较时间戳的差
        getTime("1533634450582");
        //获取加密后的文件
        try{
            String strA=  BasicAuthenticationUtil.hmacSha1Encrypt("123456","1533634450582");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    /**
     * 获取当前系统时间
     */
    public static void getTime(String Date){
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        //比较Date和 df
        System.out.println(df.format(new Date()));// new Date()为获取当前系统时间
        long timenum=System.currentTimeMillis();

        Long now=System.currentTimeMillis();
        System.out.println(now);
        //24小时 = 86400000毫秒
        long t=now-Long.parseLong(Date);
        System.out.println(t);

    }
}
