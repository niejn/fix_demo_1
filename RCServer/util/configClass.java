package util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class configClass {
	
	protected static String CURRENTPATH = System.getProperty("user.dir");
	private static String conffile = "property.txt";
	private static String writeFilePath = "";
	private static String SenderID = "";
	private static String serverIP = "";
	private static String serverPort = "";
	private static String startTime="";
	private static String endTime="";
	private static String title="";
	private static boolean isInitial =false;
	public static String getFilePath()
	{
		if(isInitial==false)
		{
			isInitial = true;
			initial();
		}
		return writeFilePath;
	}
	public static String getserverIP()
	{
		if(isInitial==false)
		{
			isInitial = true;
			initial();
		}
		return serverIP;
	}
	public static String getserverPort()
	{
		if(isInitial==false)
		{
			isInitial = true;
			initial();
		}
		return serverPort;
	}
	public static String getSenderID()
	{
		if(isInitial==false)
		{
			isInitial = true;
			initial();
		}
		return SenderID;
	}
	public static String getStartTime()
	{
		if(isInitial==false)
		{
			isInitial = true;
			initial();
		}
		return startTime;
	}
	public static String getEndTime()
	{
		if(isInitial==false)
		{
			isInitial = true;
			initial();
		}
		return endTime;
	}
	public static String getTitle()
	{
		if(isInitial==false)
		{
			isInitial = true;
			initial();
		}
		return title;
	}
	
	private static void initial()
	{
		String fileName = CURRENTPATH + "/" + conffile;
		File file = new File(fileName);
		BufferedReader reader = null;
		try {

			reader = new BufferedReader(new FileReader(file));
			String tempString = null;
			// 一次读入一行，直到读入null为文件结束
			while ((tempString = reader.readLine()) != null) {
				// 显示行号

				if (tempString.contains("#")) {
					continue;
				} else {
				 
					String[] ary2 = tempString.split("=");
					if(ary2.length==2)
					{
						String str1 = ary2[0];
						str1 = str1.replaceAll(" ", "");
						
						if(str1.equals("writeFilePath"))
						{
							writeFilePath = ary2[1];
						}else if (str1.equals("SenderID"))
						{
							SenderID = ary2[1];
							
						}else if (str1.equals("serverIP"))
						{
							serverIP = ary2[1];
						}else if (str1.equals("serverPort"))
						{
							serverPort = ary2[1];
						}else if(str1.equals("startTime"))
						{
							startTime= ary2[1];
						}else if(str1.equals("endTime"))
						{
							endTime= ary2[1];
						}else if(str1.equals("title"))
						{
							title = ary2[1];
						}
					}
				}
			}
			reader.close();
		} catch (IOException e) {

		}
	}

}
