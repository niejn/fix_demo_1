/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

/**
 *
 * @author Administrator
 */
public class SecurityStatus {
    private String sec;
    private double highPrice = -1;
    private double lowPrice = -1;
    
    
    private double preclosePrice = -1;
    private int preopeninterest = -1;
    
    private double tradevolume = 0;
    
    public SecurityStatus(String security){
        sec = security;
    }
    
    public String getSecurity(){
        return sec;
    }
    
    public void setHighLimitPrice(double value){
        highPrice = value;
    }
    
    public double getHighLimitPrice(){
        return highPrice;
    }
    
    public void setLowLimitPrice(double value){
        lowPrice = value;
    }
    
    public double getLowLimitPrice(){
        return lowPrice;
    }
    
    public void setPreClosePrice(double value){
        preclosePrice = value;
    }
    
    public double getPreClosePrice(){
        return preclosePrice;
    }
    
    public void setPreOpenInterest(int value){
        preopeninterest = value;
    }
    
    public int getPreOpenInterest(){
        return preopeninterest;
    }
    
    
    public void setTradevolume(double value){
        tradevolume = value;
    }
    
    public double getTradevolume(){
        return tradevolume;
    }
}
