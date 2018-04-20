package util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class TimeUtil {
	 public static String getCurDate(){
	        SimpleDateFormat sdf = new SimpleDateFormat("",Locale.SIMPLIFIED_CHINESE); 
	        sdf.applyPattern("ddMMyyyy HHmmss"); 
	        //
	        TimeZone zone = TimeZone.getTimeZone("GMT+8");
	        sdf.setTimeZone(zone);

	        String timeStr = sdf.format(new Date()); 
	        return timeStr;
	    }

	    public static String getCHCurDate(){
	        SimpleDateFormat sdf = new SimpleDateFormat("",Locale.SIMPLIFIED_CHINESE); 
	        sdf.applyPattern("ddMMyyyy HHmmss"); 
	        //
	        TimeZone zone = TimeZone.getTimeZone("GMT+8");
	        sdf.setTimeZone(zone);

	        String strTmp = "";
	        String timeStr = sdf.format(new Date()); 
	        String yy = timeStr.substring(4, 8);
	        String mm = timeStr.substring(2, 4);
	        String dd = timeStr.substring(0, 2);

	        strTmp += yy + mm + dd;

	        strTmp += timeStr.substring(9, 15);

	        return strTmp;
	    }

	    public static String getCHCurYYYYMM(){
	        SimpleDateFormat sdf = new SimpleDateFormat("",Locale.SIMPLIFIED_CHINESE); 
	        sdf.applyPattern("ddMMyyyy HHmmss"); 
	        //
	        TimeZone zone = TimeZone.getTimeZone("GMT+8");
	        sdf.setTimeZone(zone);

	        String strTmp = "";
	        String timeStr = sdf.format(new Date()); 
	        String yy = timeStr.substring(4, 8);
	        String mm = timeStr.substring(2, 4);
	        //String dd = timeStr.substring(0, 2);

	        strTmp += yy + mm;// + dd;

	        //strTmp += timeStr.substring(9, 15);

	        return strTmp;
	    }
		
		
	    public static String getCHCurMMDD(){
	        SimpleDateFormat sdf = new SimpleDateFormat("",Locale.SIMPLIFIED_CHINESE); 
	        sdf.applyPattern("ddMMyyyy HHmmss"); 
	        //
	        TimeZone zone = TimeZone.getTimeZone("GMT+8");
	        sdf.setTimeZone(zone);

	        String strTmp = "";
	        String timeStr = sdf.format(new Date()); 
	        //String yy = timeStr.substring(4, 8);
	        String mm = timeStr.substring(2, 4);
	        String dd = timeStr.substring(0, 2);

	        strTmp += mm + dd;

	        //strTmp += timeStr.substring(9, 15);

	        return strTmp;
	    }
	    
	    
	    
	    public static String getCHCurYYYYMMDD(){
	        SimpleDateFormat sdf = new SimpleDateFormat("",Locale.SIMPLIFIED_CHINESE); 
	        sdf.applyPattern("ddMMyyyy HHmmss"); 
	        //
	        TimeZone zone = TimeZone.getTimeZone("GMT+8");
	        sdf.setTimeZone(zone);

	        String strTmp = "";
	        String timeStr = sdf.format(new Date()); 
	        String yy = timeStr.substring(4, 8);
	        String mm = timeStr.substring(2, 4);
	        String dd = timeStr.substring(0, 2);

	        strTmp += yy + mm + dd;

	        //strTmp += timeStr.substring(9, 15);

	        return strTmp;
	    }
	    public static String getAllTime(){
	        SimpleDateFormat sdf = new SimpleDateFormat("",Locale.SIMPLIFIED_CHINESE); 
	        sdf.applyPattern("HHmmss SSS"); 
	        //
	        TimeZone zone = TimeZone.getTimeZone("GMT+8");
	        sdf.setTimeZone(zone);
	        
	     
	        String timeStr = sdf.format(new Date()); 
	   
	        
	        return timeStr;
	    }
	    public static void main(String[] args) {

			//timeCounter eggTimer = new timeCounter(1);
	    	System.out.println(util.TimeUtil.getCHCurYYYYMMDD());
			//eggTimer.start();
			//System.out.println("------------------!");
		}
}
