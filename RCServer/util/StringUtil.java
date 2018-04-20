package util;

import java.util.ArrayList;
import java.util.List;


public class StringUtil {
	/**
	 * @author Administrator
	 * 锟�
	 */
	public static List<String> stringAnalysis(String linestring, String split) {
		List<String> linelist = new ArrayList<String>();
		String[] cellstring = linestring.split(split);

		for (int x = 0; x < cellstring.length; x++) {
			if (!(cellstring[x].trim().equals(""))) {
				linelist.add(cellstring[x].trim());
			}
		}
		return linelist;
	}
	
	public static List<String> stringAnalysisByComma(String linestring) {
		List<String> linelist = new ArrayList<String>();
		String[] cellstring = linestring.split(",");

		for (int x = 0; x < cellstring.length; x++) {
			if (!(cellstring[x].trim().equals(""))) {
				linelist.add(cellstring[x].trim());
			}
		}
		return linelist;
	}

}
