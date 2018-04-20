package output;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import util.UtilFun;

public class writeBakData implements writeData{

    private String flag = ",";
    private String dir = ".\\MarketData\\";
    private String filepostfix = "DataBak.txt";
    FileWriter fw;
    String newFile;
    public writeBakData(){
        init();
    }
    
    private void init(){
        try {
            newFile = dir + filepostfix;
            fw = new FileWriter(newFile, false);
            printInit();
        } catch (IOException ex) {
            Logger.getLogger(writeBakData.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void printInit(){
        try {
            fw.write("Inst    closeprice    preopeninterest");
            fw.write("\r\n");
        } catch (IOException ex) {
            Logger.getLogger(writeBakData.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void AddFinished(){
        try {
            fw.close();
        } catch (IOException ex) {
            Logger.getLogger(writeBakData.class.getName()).log(Level.SEVERE, null, ex);
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
