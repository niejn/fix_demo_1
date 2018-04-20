package output;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Map;
import data.SecurityStatus;
import java.io.BufferedReader;
import java.util.List;
import util.*;


public class readBakData {

    private String flag = ",";
    private String dir = ".\\MarketData\\";
    private String filepostfix = "DataBak.txt";
    FileReader fr;
    String newFile;
    public readBakData(){
        init();
    }
    
    private void init(){
        try {
            newFile = dir + filepostfix;
            fr = new FileReader(newFile);
        } catch (IOException ex) {
            Logger.getLogger(readBakData.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void readData(Map<String, SecurityStatus> map){
        try {
            BufferedReader br = new BufferedReader(fr);
            while(br.ready()){
                String line = br.readLine();
                List<String> linelist = StringUtil.stringAnalysis(line, flag);
                if(map.containsKey(linelist.get(0))){
                    double dd = Double.parseDouble(linelist.get(1));
                    int ii = Integer.parseInt(linelist.get(2));
                    map.get(linelist.get(0)).setPreClosePrice(dd);
                    map.get(linelist.get(0)).setPreOpenInterest(ii);
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(readBakData.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
