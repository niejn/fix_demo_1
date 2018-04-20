package util; 

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/** 
 * @author 
 * @version create time��2011-5-4 ����03:34:10 
 * @Title: UtilFun.java 
 * @Package quickfix 
 * @Description: TODO
 * @version V1.0 
 * @ ..
 */
public class UtilFun {

	public static String getCurDate(){
            SimpleDateFormat sdf = new SimpleDateFormat("",Locale.SIMPLIFIED_CHINESE); 
	    sdf.applyPattern("ddMMyyyy HHmmss"); 
	    //
	    TimeZone zone = TimeZone.getTimeZone("GMT+8");
	    sdf.setTimeZone(zone);

	    String timeStr = sdf.format(new Date()); 
	    return timeStr;
	}
	

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(getCurDate().substring(0,8));
        System.out.println(getCurDate().substring(9,11));
        double i = 1.000005;
        DecimalFormat df1 = new DecimalFormat("0.##");
        System.out.println(df1.format(i));
	}

}
 
