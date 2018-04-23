package util;

/**
 *  Class Name: timeCounter.java
 *  Function:
 *  
 *     Modifications:   
 *  
 *  @author JohnnyLiu  DateTime 2012-6-18 下午9:17:12    
 *  @version 1.0
 */

import java.util.Timer;

import java.util.TimerTask;

public class timeCounter {

	private final Timer timer = new Timer();

	private final int minutes;
	private TradeDataStruct tds;
	private String mytime1 = "0830";// for real 
	private String refreshTime = "0840";// for real
	 
//	private String mytime1 = "1028";// for demo
//	private String refreshTime = "1029";// for demo
	
	private int iswriteFlag = 0;// 0 not write yet,1  have writen 
	public timeCounter(int minutes,TradeDataStruct tds1) {

		this.minutes = minutes;
		tds =tds1;
		iswriteFlag= 0;
	}

	public void start() {

		timer.schedule(new TimerTask() {

			public void run() {

				exec();

				//timer.cancel();

			}

			private void exec() {

				String tmp = TimeUtil.getAllTime();
				String time1 = tmp.substring(0,4);
				System.out.println("Timer is Running:"+tmp);
				if(time1.equals(mytime1)&&iswriteFlag ==0)
				{
					//tds.newFile();
					//System.out.println("Reach 8:30,create new file");
					iswriteFlag =1;
				}else if(time1.equals(refreshTime))
				{
					iswriteFlag =0;
				}else
				{
					
				}
				// Start a new thread to play a sound...

			}
		},  minutes * 60 * 1000, minutes * 60 * 1000);// 使用毫秒计数
		//}, 2000,2000);// 使用毫秒计数

	}

	public static void main(String[] args) {

		//timeCounter eggTimer = new timeCounter(1);

		//eggTimer.start();
		//System.out.println("------------------!");
	}

}
