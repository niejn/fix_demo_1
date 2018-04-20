package MyQuickfix;

import util.TradeDataStruct;
import util.TradeDataStructManager;
import util.UtilFun;
import util.timeCounter;
import data.SecurityStatus;
import java.util.concurrent.CountDownLatch;
import java.util.logging.Level;
import java.util.logging.Logger;

import quickfix.DoNotSend;
import quickfix.FieldNotFound;
import quickfix.IncorrectDataFormat;
import quickfix.IncorrectTagValue;
import quickfix.Message;
import quickfix.MessageCracker;
import quickfix.RejectLogon;
import quickfix.SessionID;
import quickfix.UnsupportedMessageType;
import quickfix.field.ExecType;
import quickfix.field.MsgSeqNum;
import quickfix.field.OrdStatus;
import quickfix.field.Symbol;
import quickfix.field.SecurityStatusReqID;
import quickfix.field.SubscriptionRequestType;
import quickfix.field.Account;
import quickfix.field.Password;
import quickfix.SessionNotFound;
import quickfix.Session;
import quickfix.field.MDEntryPx;
import quickfix.field.MDEntrySize;
import quickfix.field.MDUpdateType;
import java.util.Timer;

import quickfix.field.MDEntryType;
import quickfix.field.MDReqID;
import quickfix.field.MarketDepth;
import quickfix.field.Side;
import quickfix.fix44.component.Instrument;
import quickfix.field.SecurityListRequestType;
import quickfix.field.SecurityReqID;


import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;

import main.TradeDataByFix;
import output.readBakData;
import output.writeBakData;
import output.writeMarketData;
import quickfix.field.HighPx;
import quickfix.field.LowPx;
import quickfix.field.MDEntryTime;
import quickfix.field.SecurityExchange;
public class ApplicationTrade extends MessageCracker implements quickfix.Application { 
	
    private final SessionID expectedSessionID;
    private final CountDownLatch shutdownLatch = new CountDownLatch(1);
    private static MsgSeqNum sn = null;
    private String accout = "88995011";//"88995011";//"10000001";//"88995013"lqt
    private String pwd = "111111";
    
    private Timer timer;
    private boolean isStartTimer = true;

    private boolean haveLogin = false;
    
     
    
    private boolean bSendSecStatus = true;
  
    private String dateHZ = "";
    private Map<String, SecurityStatus>  secMap;
    private writeMarketData wtt;
    private boolean bTodayWork = true;
    private boolean bRefreshSec = false;
    private String tradingday;
    private int iFlagToMarketReq = 1;
    private TradeDataStruct tds ;
    private timeCounter eggTimer;
    private int iTimeFlag =0;//0 means the first time logon ;1 means not first time
    private void initConList(){
 
                
    }
    
     
    private TradeDataByFix mb;
    public ApplicationTrade(SessionID expectedSessionID, TradeDataByFix md) {
        this.expectedSessionID = expectedSessionID;
        timer = new Timer();
        //tds = new TradeDataStruct();
        mb = md;
        eggTimer = new timeCounter(1,tds);
    }
    
    @Override
    public void fromAdmin(Message message, SessionID sessionId) throws FieldNotFound, IncorrectDataFormat, IncorrectTagValue, RejectLogon {
        //Header hh = (Header) message.getHeader();
        //sn = hh.getMsgSeqNum();
        //System.out.println("fromAdmin:" + message.toString() + " " + sn.toString());
    	 System.out.println("fromAdmin");
    }

    @Override
    public void fromApp(Message message, SessionID sessionId) throws UnsupportedMessageType, FieldNotFound, IncorrectDataFormat, IncorrectTagValue {
        //System.out.println("fromApp:" + message.toString());
        //Header header = (Header) message.getHeader();
       // System.out.println("MsgType:" + header.getMsgType().toString());
    	 System.out.println("fromApp");
    	crack(message, sessionId);
    }

    @Override
    public void onCreate(SessionID sessionId) {

    }

    @Override
    public void onLogon(SessionID sessionId) {
        System.out.println("Logon!!!" );
        //sendSecurityStatusRequest("IF1109");
        //sendSecurityListRequest("IF1109");
        //sendMarketDataRequest("IF1109");
//        if(!haveLogin){
//            initTimerService();
//        }
        if(iTimeFlag==0)
        {
        	//tds.intial();
        	eggTimer.start();
        	iTimeFlag=1;
        	
        }
        haveLogin = true;        
        mb.updateIsService(true);
       // tds.Reset();
    } 

    @Override
    public void onLogout(SessionID sessionId) {
        System.out.println("Logout!!!" );
        mb.updateIsService(false);
       // mb.Reconnect();
    }

    @Override
    public void toAdmin(Message message, SessionID sessionId) {
        //System.out.println("toAdmin:" + message.toString());
    }

    @Override
    public void toApp(Message message, SessionID sessionId) throws DoNotSend {
        //System.out.println("toApp:" + message.toString());
    }

    

    public void onMessage(quickfix.fix44.MarketDataSnapshotFullRefresh message, SessionID sessionID){
        //System.out.println("MarketDataSnapshotFullRefresh:" + message.toString());
        System.out.println("On MEssage");
    	MDReqID reqID = new MDReqID();
        Symbol symbol = new Symbol();
        //NoMDEntries noMDEntries = new NoMDEntries();
        quickfix.fix44.MarketDataSnapshotFullRefresh.NoMDEntries group =
                  new quickfix.fix44.MarketDataSnapshotFullRefresh.NoMDEntries();
        MDEntryType MDEntryType = new MDEntryType();
        MDEntryPx MDEntryPx = new MDEntryPx();
        MDEntrySize MDEntrySize = new MDEntrySize();
        MDEntryTime mdTime = new MDEntryTime();
        
        String updateTime;
        double lastprice = 0.0;
        double bidprice = 0.0;
        double bidvol = 0.0;
        double askprice = 0.0;
        double askvol = 0.0;
        double lastvol = 0.0;
        double turnover = 0.0;
        double openinterest = 0.0;
        //upperprice;
        //lowerprice;
        double openprice = 0.0;
        double settlement = 0.0;
        //double precloseprice;
        //double preopeninterest;
        double tradevolume = 0.0;
         
        try {
            //message.get(reqID);				
            message.get(symbol);
            wtt.appendNewLine(symbol.getValue());
            wtt.append(tradingday);
            
            message.getGroup(1, group);
            group.get(MDEntryType);
            group.get(MDEntryPx);
            group.get(MDEntrySize);
            group.get(mdTime);
            //System.out.println("symbol:" + symbol.toString());
            //System.out.println("MDEntryType:" + MDEntryType.toString());
            //System.out.println("MDEntryPx:" + MDEntryPx.toString());
            //System.out.println("MDEntrySize:" + MDEntrySize.toString());
            bidprice = MDEntryPx.getValue();
            bidvol = MDEntrySize.getValue();
            
            
            message.getGroup(2, group);
            group.get(MDEntryType);
            group.get(MDEntryPx);
            group.get(MDEntrySize);
            group.get(mdTime);
            System.out.println("symbol:" + symbol.toString());
            //System.out.println("MDEntryType:" + MDEntryType.toString());
            //System.out.println("MDEntryPx:" + MDEntryPx.toString());
            //System.out.println("MDEntrySize:" + MDEntrySize.toString());
            //System.out.println("MDEntryTime:" + mdTime.toString());
            String tmp1 = mdTime.getValue().toLocaleString();
            tmp1 = tmp1.substring(tmp1.length()-8, tmp1.length());
            String tmp2 = "" + mdTime.getValue().getTime();
            tmp2 = tmp2.substring(tmp2.length() - 3, tmp2.length());
            System.out.println("MDEntryTime:"  + tmp1 + "."
                            + tmp2);
            updateTime = tmp1 + "." + tmp2;
            askprice = MDEntryPx.getValue();
            askvol = MDEntrySize.getValue();
            
            try{
                message.getGroup(3, group);
                group.get(MDEntryType);
                group.get(MDEntryPx);


                group.get(mdTime);
                //System.out.println("symbol:" + symbol.toString());
                //System.out.println("MDEntryType:" + MDEntryType.toString());
                //System.out.println("MDEntryPx:" + MDEntryPx.toString());
                //System.out.println("MDEntrySize:" + MDEntrySize.toString());
                lastprice = MDEntryPx.getValue();
                group.get(MDEntrySize);
                lastvol = MDEntrySize.getValue();
            }
            catch(Exception e){
                lastvol = 0;
            }
           	
            message.getGroup(4, group);
            group.get(MDEntryType);
            group.get(MDEntryPx);
            //group.get(MDEntrySize);
            //System.out.println("reqID:" + reqID.toString());
            //System.out.println("symbol:" + symbol.toString());
            //System.out.println("MDEntryType:" + MDEntryType.toString());
            //System.out.println("MDEntryPx:" + MDEntryPx.toString());
            //System.out.println("MDEntrySize:" + MDEntrySize.toString());
            openprice = MDEntryPx.getValue();
	
            message.getGroup(5, group);
            group.get(MDEntryType);
            group.get(MDEntryPx);
            //group.get(MDEntrySize);
            //System.out.println("reqID:" + reqID.toString());
            //System.out.println("symbol:" + symbol.toString());
            //System.out.println("MDEntryType:" + MDEntryType.toString());
            //System.out.println("MDEntryPx:" + MDEntryPx.toString());
            //System.out.println("MDEntrySize:" + MDEntrySize.toString());
            settlement = MDEntryPx.getValue();
	
            message.getGroup(6, group);
            group.get(MDEntryType);
            group.get(MDEntryPx);
            //group.get(MDEntrySize);
            //System.out.println("reqID:" + reqID.toString());
            //System.out.println("symbol:" + symbol.toString());
            //System.out.println("MDEntryType:" + MDEntryType.toString());
            //System.out.println("MDEntryPx:" + MDEntryPx.toString());
            //System.out.println("MDEntrySize:" + MDEntrySize.toString());
            //message.getGroup(2, group);
            //group.get(MDEntryType);
            //group.get(MDEntryPx);
            //group.get(MDEntrySize);
                
	
            message.getGroup(7, group);
            group.get(MDEntryType);
            group.get(MDEntryPx);
            //group.get(MDEntrySize);
            //System.out.println("reqID:" + reqID.toString());
            //System.out.println("symbol:" + symbol.toString());
            //System.out.println("MDEntryType:" + MDEntryType.toString());
            //System.out.println("MDEntryPx:" + MDEntryPx.toString());
            //System.out.println("MDEntrySize:" + MDEntrySize.toString());
            
	
            message.getGroup(8, group);
            group.get(MDEntryType);
            //group.get(MDEntryPx);
            group.get(MDEntrySize);
            //System.out.println("reqID:" + reqID.toString());
            //System.out.println("symbol:" + symbol.toString());
            //System.out.println("MDEntryType:" + MDEntryType.toString());
            //System.out.println("MDEntryPx:" + MDEntryPx.toString());
            //System.out.println("MDEntrySize:" + MDEntrySize.toString());
            tradevolume = MDEntrySize.getValue();
	
            message.getGroup(9, group);
            group.get(MDEntryType);
            //group.get(MDEntryPx);
            group.get(MDEntrySize);
            //System.out.println("reqID:" + reqID.toString());
            //System.out.println("symbol:" + symbol.toString());
            //System.out.println("MDEntryType:" + MDEntryType.toString());
            //System.out.println("MDEntryPx:" + MDEntryPx.toString());
            //System.out.println("MDEntrySize:" + MDEntrySize.toString());
            openinterest = MDEntrySize.getValue();
            
            turnover = lastprice * lastvol;
            
            
            wtt.append(updateTime);
            wtt.append("" + lastprice);
            wtt.append("" + bidprice);
            wtt.append("" + bidvol);
            wtt.append("" + askprice);
            wtt.append("" + askvol);
            //wtt.append("" + lastvol);
            //wtt.append("" + turnover);
            //wtt.append("" + openinterest);
            
            if(secMap.containsKey(symbol.getValue())){
                SecurityStatus ss = secMap.get(symbol.getValue());
                if(ss.getTradevolume() == 0.0){
                    lastvol = tradevolume;
                    ss.setTradevolume(tradevolume);
                }
                else{
                    lastvol = tradevolume - ss.getTradevolume();
                    ss.setTradevolume(tradevolume);
                }
                
                turnover = lastprice * lastvol;
                wtt.append("" + lastvol);
                wtt.append("" + turnover);
                wtt.append("" + openinterest);
                
                wtt.append("" + ss.getHighLimitPrice());
                wtt.append("" + ss.getLowLimitPrice());
                wtt.append("" + openprice);
                wtt.append("" + settlement);
                wtt.append("" + ss.getPreClosePrice());
                wtt.append("" + ss.getPreOpenInterest());
            }
            else{
                wtt.append("" + lastvol);
                wtt.append("" + turnover);
                wtt.append("" + openinterest);
            }

        } catch (FieldNotFound e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
        }	
    }


    public void onMessage(quickfix.fix44.SecurityList message, SessionID sessionID){
        //System.out.println("SecurityList:" + message.toString());

        //message.g
    }


    public void onMessage(quickfix.fix44.SecurityStatus message, SessionID sessionID){
        //System.out.println("SecurityStatus:" + message.toString());
        Symbol symbol = new Symbol();
        try { 
            message.get(symbol);
            int len = message.getText().getValue().length();
        } catch (FieldNotFound ex) {
            try {
                Logger.getLogger(ApplicationTrade.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println(symbol + "11111111111111");
                 
                HighPx hp = new HighPx();
                LowPx lp = new LowPx();
                message.get(hp);
                message.get(lp);
                SecurityStatus ss = new SecurityStatus(symbol.getValue());
                ss.setHighLimitPrice(hp.getValue());
                ss.setLowLimitPrice(lp.getValue());
                if(!secMap.containsKey(symbol.getValue())){
                    secMap.put(symbol.getValue(), ss);
                }
            } catch (FieldNotFound ex1) {
                Logger.getLogger(ApplicationTrade.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
    }	
    
    
    /* To recieve Execution report
     */
    public void onMessage(quickfix.fix44.ExecutionReport message, SessionID sessionID){
        System.out.println("ExecutionReport:" + message.toString());
        try {
            if(message.getExecType().getValue() == ExecType.CANCELED 
                            && message.getOrdStatus().getValue() == OrdStatus.CANCELED){
            }
            if((message.getExecType().getValue()== ExecType.TRADE
            		||message.getExecType().getValue()== ExecType.NEW) 
            		&&(message.getOrdStatus().getValue()==ExecType.NEW 
            		||message.getOrdStatus().getValue()==ExecType.FILL
            		||message.getOrdStatus().getValue()==ExecType.PARTIAL_FILL))
            {
            	 
            	//quickfix.field.ExecID execID= new ExecID();
            	String datetmp = "";
            	if(message. getExecID().valueEquals("0"))
            	{
            		 //NO exce ID, ignore it
            	}else
            	{
            		String tmpdate = message.getDate();
            		if(tmpdate == null)
            		{
            			
            		}else
            		{
            			System.out.println(tmpdate);
            			String tmpDateArray[]= tmpdate.split("-");
            			datetmp = tmpDateArray[0];
            		}
            		String account = message.getAccount().getValue();
            		tds = TradeDataStructManager.getTradeDataStruct(account);
            		
            		tds.setDate(datetmp); 
                	tds.setAccount(message.getAccount().getValue());
                	tds.setExchange(message.getSecurityExchange().getValue());
                	tds.setSymbol(message.getSymbol().getValue());
                	char sidetmp = message.getSide().getValue();
                	String tmpquant = "" +message.getLastQty().getValue();
                	if (sidetmp == Side.SELL)
                	{
                		tds.setShortQuantity(tmpquant);
                		tds.setLongQuantity("0");
                	}else
                	{
                		tds.setShortQuantity("0");
                		tds.setLongQuantity(tmpquant);
                	}
                	String tmprice = ""+message.getLastPx().getValue();
                	tds.setTradePrice(tmprice);
                	String tradeID= message.getExecID().getValue();
                	tds.setTradeID(tradeID);
                	tds.print();
            	}
            	
            	
            }
        } catch (FieldNotFound e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }	
    public static void main(String[] args) {
        String str = "12";
        int p = Integer.parseInt(str);
        System.out.println(""+p);
        ApplicationTrade am = new ApplicationTrade(null, null);
        str = "IF1107";
        for(int i =0 ; i < 20; i ++){
             
        }
        
    }
}
