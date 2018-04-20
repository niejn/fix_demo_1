package util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import MyQuickfix.ApplicationTrade;



public class TradeDataStruct {
	
	private  String Date="";
	private   String Account="";
	private   String Currency = "CNY";
	private   String Exchange ="";
	private   String ProductCurrency ="CNY";
	private   String Description="";
	private   String Symbol="";
	private   String ContractYear="";
	private   String ContractMonth="";
	private   String LongQuantity="";
	private   String ShortQuantity="";
	private   String TradePrice ="";
	private   String TraderID="";
	private   String filename = configClass.getFilePath();
	private   String tmpFileName = "";
	private   String Linetitle="Trade Date,Account,Currency,Exchange," +
			"ProductCurrency,Description,Symbol,"+
			"LongQuantity,ShortQuantity,TradePrice,TraderID";
	private   String accountNum = "";
	
	private   FileWriter writer=null;
	private int isFileExist = 0; // 0 means not exist , 1 means exist
	//private   int isTitleWrite = 0;//0
	public TradeDataStruct(String accountName)
	{
		accountNum = accountName;
		
	}
	public void intial()
	{
		int i = 0;
		FileReader fr;
		try {
			//fr = new FileReader(filename+accountNum+"_Intraday."+TimeUtil.getCHCurYYYYMMDD()+".csv");
			fr = new FileReader(filename+accountNum+"_Intraday."+Date+".csv");
			fr.close();
			isFileExist =1;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			i=1;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(i==1)
		{
			newFile();
		}
	}
	/**
	 * 
	 *  Function: To print the data into specified file
	 * 
	 *
	 *  @author JohnnyLiu  DateTime 2012-6-13 1:45:06
	 */
	public void print()
	{
		String tmp= Date+","+Account+","+Currency+","+Exchange+","+
				ProductCurrency+","+Description+","+Symbol+","+LongQuantity+","+ShortQuantity+","+TradePrice+","+
				TraderID+","+TimeUtil.getCHCurDate()+","+TimeUtil.getAllTime()+"\r\n";
		if(writer==null)
		{
			try {
				System.out.println("writer is null");
				//String t_Name =filename+accountNum+"_Intraday."+TimeUtil.getCHCurYYYYMMDD()+".csv";
				String t_Name =filename+accountNum+"_Intraday."+Date+".csv";
				File file = new File(t_Name);
				if(file.exists())
				{
					isFileExist = 1;
				}
				//writer = new FileWriter(filename+accountNum+"_Intraday."+TimeUtil.getCHCurYYYYMMDD()+".csv", true);
				writer = new FileWriter(filename+accountNum+"_Intraday."+Date+".csv", true);
				if(isFileExist == 0)
				{
					writer.write(Linetitle+"\r\n");
				}
				writer.write(tmp);
				writer.flush();
				//tmpFileName = filename+accountNum+"_Intraday."+TimeUtil.getCHCurYYYYMMDD()+".csv";
				tmpFileName = filename+accountNum+"_Intraday."+Date+".csv";
				//isTitleWrite =1;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}else
		{
			//String tmpname1 = filename+accountNum+"_Intraday."+TimeUtil.getCHCurYYYYMMDD()+".csv";
			String tmpname1 = filename+accountNum+"_Intraday."+Date+".csv";
			if(tmpFileName.equals(tmpname1))
			{
				try {
					
					writer.write(tmp);
					writer.flush();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}else {
				try {
					System.out.println("pre file name :"+tmpname1);
					System.out.println("new file name :"+tmpFileName);
					writer.close();
					writer = null;
					//writer = new FileWriter(filename+accountNum+"_Intraday."+TimeUtil.getCHCurYYYYMMDD()+".csv", true);
					writer = new FileWriter(filename+accountNum+"_Intraday."+Date+".csv", true);
					writer.write(Linetitle+"\r\n");
					writer.write(tmp);
					writer.flush();
					//tmpFileName = filename+accountNum+"_Intraday."+TimeUtil.getCHCurYYYYMMDD()+".csv";
					tmpFileName = filename+accountNum+"_Intraday."+Date+".csv";
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		}
	}
	/**
	 * 
	 *  Function: to set the trade date
	 *
	 *  @author JohnnyLiu  DateTime 2012-6-13 1:45:33
	 *  @param date
	 */
	public   void setDate(String date) {
		Date = date;
	}
	/**
	 * 
	 *  Function: to set the user's account
	 *
	 *  @author JohnnyLiu  DateTime 2012-6-13 1:45:48
	 *  @param account
	 */
	public   void setAccount(String account) {
		Account = account;
	}
	/**
	 * 
	 *  Function: To set the Exchange's name
	 *
	 *  @author JohnnyLiu  DateTime 2012-6-13 1:46:04
	 *  @param exchange
	 */
	public   void setExchange(String exchange) {
		if(exchange.equals("DLCE"))
		{
			Exchange = "DCE";
		}else
		{
			Exchange= exchange;
		}
		
	}
	/**
	 * 
	 *  Function: To set Symbol , contract's year, contract's month and contract's description
	 *
	 *  @author JohnnyLiu  DateTime 2012-6-13 1:46:35
	 *  @param symbol
	 */
	public   void setSymbol(String symbol) {

		String tmpym = getYearMon(symbol);
		String tmpConame = getConName(symbol);
		ContractYear="20"+ tmpym.substring(0, 2);
		//ContractMonth = tmpym.substring(2);
		String tmpconmt = tmpym.substring(2);
		String newconmon1 =tmpconmt.substring(0,1);
		String tmpcon1 = "";
		
		if(newconmon1.equals("0"))
		{
			tmpcon1 = tmpconmt.substring(1);
		}else
		{
			tmpcon1 = tmpconmt;
		}
		ContractMonth=tmpcon1;
		Symbol = tmpConame;
		Description = contractDescription.getDescription(Symbol);
		//Symbol = tmpConame.toUpperCase();
                Symbol = symbol;
	}
	/**
	 * 
	 *  Function: To set the LongQuantity(buy direction )
	 *
	 *  @author JohnnyLiu  DateTime 2012-6-13 1:47:37
	 *  @param longQuantity
	 */
	public   void setLongQuantity(String longQuantity) {
		LongQuantity = longQuantity;
	}
	/**
	 * 
	 *  Function: To set the ShortQuantity(sell direction)
	 *
	 *  @author JohnnyLiu  DateTime 2012-6-13 1:48:14
	 *  @param shortQuantity
	 */
	public   void setShortQuantity(String shortQuantity) {
		ShortQuantity = shortQuantity;
	}
	/**
	 * 
	 *  Function:To set the trade price for the order
	 *
	 *  @author JohnnyLiu  DateTime 2012-6-13 1:48:40
	 *  @param tradePrice
	 */
	public   void setTradePrice(String tradePrice) {
		TradePrice = tradePrice;
	}
	/**
	 * 
	 *  Function: To set the trade ID
	 *
	 *  @author JohnnyLiu  DateTime 2012-6-13 1:49:01
	 *  @param tradeID
	 */
	public   void setTradeID(String tradeID) {
		TraderID = tradeID;
	}
	/**
	 * 
	 *  Function: To get the specified contract's month
	 *
	 *  @author JohnnyLiu  DateTime 2012-6-13 1:49:18
	 *  @param contmp
	 *  @return
	 */
	public   String getYearMon(String contmp) {
		String conYearMon = null;
		if (contmp.length() == 4) {
			conYearMon = contmp.substring(1);
			conYearMon = "1" + conYearMon;
		} else {
			char tmpone = contmp.charAt((contmp.length() - 4));
			// tmpone.contains("[0-9]");
			if (Character.isDigit(tmpone)) {
				conYearMon = contmp.substring((contmp.length() - 4));
			} else {
				conYearMon = contmp.substring((contmp.length() - 3));
				conYearMon = "1" + conYearMon;
			}
		}
		return conYearMon;
	}
	/**
	 * 
	 *  Function:To get the contract's name(IF1202 's name is IF)
	 *
	 *  @author JohnnyLiu  DateTime 2012-6-13 1:49:54
	 *  @param contmp
	 *  @return
	 */
	public   String getConName(String contmp) {
		String conName = null;
		if (contmp.length() == 4) {
			conName = contmp.substring(0, 1);
		} else {
			char tmpone = contmp.charAt((contmp.length() - 4));
			if (Character.isDigit(tmpone)) {
				conName = contmp.substring(0, (contmp.length() - 4));
			} else {
				conName = contmp.substring(0, (contmp.length() - 3));
			}
		}
		return conName;
	}
	/**
	 * 
	 *  Function:To reset the related parms when the socket is disconnected  
	 *
	 *  @author JohnnyLiu  DateTime 2012-6-13 1:50:27
	 */
	public   void Reset()
	{
		 
		if(writer!=null)
		{
			try {
				writer.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		writer= null;
		
	}
	public void newFile()
	{
		 
		if(writer!=null)
		{
			try {
				writer.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		try {
			//writer = new FileWriter(filename+accountNum+"_Intraday."+TimeUtil.getCHCurYYYYMMDD()+".csv", false);
			//tmpFileName = filename+accountNum+"_Intraday."+TimeUtil.getCHCurYYYYMMDD()+".csv";
			writer = new FileWriter(filename+accountNum+"_Intraday."+Date+".csv", false);
			tmpFileName = filename+accountNum+"_Intraday."+Date+".csv";
			writer.write(Linetitle+"\r\n");
			writer.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private void addTitleLine()
	{
		
		
	}
	public static void main(String[] args) {
         
        
    }
	
}
