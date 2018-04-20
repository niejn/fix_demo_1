package output;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import util.UtilFun;

public class writeMarketData implements writeData{

    private String flag = ",";
    private String dir = ".\\MarketData\\";
    private String filepostfix = "MData.txt";
    FileWriter fw;
    String newFile;
    public writeMarketData(){
        init();
    }
    
    private void init(){
        try {
            String date = UtilFun.getCurDate().substring(0, 8);
            String path = date.substring(4, 8) + date.substring(2, 4) + date.substring(0, 2);
            newFile =dir + path + filepostfix;
            
            fw = new FileWriter(newFile, true);
            printInit();
        } catch (IOException ex) {
            Logger.getLogger(writeMarketData.class.getName()).log(Level.SEVERE, null, ex);
            try {
                fw = new FileWriter(newFile, false);
            } catch (IOException ex1) {
                Logger.getLogger(writeMarketData.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
    }
    
    private void printInit(){
        try {
            fw.write("Inst    ");
            fw.write("\r\n");
        } catch (IOException ex) {
            Logger.getLogger(writeMarketData.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void AddFinished(){
        try {
            fw.close();
        } catch (IOException ex) {
            Logger.getLogger(writeMarketData.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void append(String str){
    	try{		
            fw.write(str);
            fw.write(flag);  
            //fw.write("\r\n");
            //fw.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    public void appendNewLine(String str){
        try{	
            fw.write("\r\n");
            fw.write(str);  
            fw.write(flag);  
            //fw.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
