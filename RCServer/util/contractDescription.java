package util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class contractDescription {

	private static int isFilled = 0;
	private static Map<String, String> contractMap = null;
	private static String filename = "ContractDescription.txt";
	/**
	 * 
	 *  Function: To get the contract's Description
	 *
	 *  @author JohnnyLiu  DateTime 2012-6-13 ÉÏÎç1:43:29
	 *  @param contractname
	 *  @return String 
	 */
	public static String getDescription(String contractname) {
		if (isFilled == 0) {
			// ContractDescription.txt
			try {
				FileReader fr = new FileReader(filename);
				BufferedReader br = new BufferedReader(fr);
				String line = null;
				while (br.ready()) {
					line = br.readLine();
					while (line != null) {
						if (line.trim().equals("")) {
							break;
						}
						if (line.contains("------")) {
							break;
						} else {
							String tmpArrray[] = line.split(",");
							addContract(tmpArrray[0], tmpArrray[1]);
							line = br.readLine();
						}
					}
				}
				br.close();
				fr.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (contractMap == null) {
			return contractname;
		} else {
			String des = contractMap.get(contractname);
			if(des!=null)
			{
				return des;
			}else
			{
				return contractname;
			}
		}
	}
	/**
	 * 
	 *  Function: To add the contract's description in the static map
	 *
	 *  @author JohnnyLiu  DateTime 2012-6-13 ÉÏÎç1:44:27
	 *  @param name
	 *  @param decsrip
	 */
	private static void addContract(String name, String decsrip) {
		if (isFilled == 0) {
			contractMap = new HashMap<String, String>();
			isFilled = 1;
		}
		contractMap.put(name, decsrip);
	}
	// public static void main(String[] args){
	// System.out.println(contractDescription.getDescription("a"));
	// }

}
