package util;

import java.util.HashMap;
import java.util.Map;

public class TradeDataStructManager {
	
	private static Map<String,TradeDataStruct> map1 = new HashMap<String,TradeDataStruct>();
	public static TradeDataStruct getTradeDataStruct(String accountNum)
	{
		if(map1.get(accountNum)==null)
		{
			TradeDataStruct tds = new TradeDataStruct(accountNum);
			map1.put(accountNum, tds);
			return tds;	
		}else
		{
			return map1.get(accountNum);
		}
	}

}
