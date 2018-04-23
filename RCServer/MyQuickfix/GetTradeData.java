/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package MyQuickfix;


import main.TradeDataByFix;

import java.util.Map;
import java.util.HashMap;
import quickfix.DefaultMessageFactory;
import quickfix.DefaultMessageFactory;
import quickfix.FileStoreFactory;
import quickfix.FileStoreFactory;
import quickfix.ScreenLogFactory;
import quickfix.ScreenLogFactory;
import quickfix.SessionID;
import quickfix.SessionID;
import quickfix.SessionSettings;
import quickfix.SessionSettings;
import quickfix.SocketAcceptor;
import quickfix.SocketAcceptor;
import quickfix.SocketInitiator;
import quickfix.SocketInitiator;
import util.configClass;
import static quickfix.FixVersions.BEGINSTRING_FIX44;


/**
 *
 * @author Administrator
 */
public class GetTradeData {

    public static SessionID sessionID = null;
    //private static RCFrameViewNew rcf;
    private static TradeDataByFix mb;
    private static Thread tr=null;
    //private static String sendID = "ESolution1";// for demo
    private static String sendID = configClass.getSenderID(); // for real back 
    //private static String sendID = "DRW"; // for real
    public GetTradeData(TradeDataByFix mbd){
        //rcf = view;
         this.mb = mbd;
         
    }
    
    public static void login() {
    	if(tr!=null)
    	{
    		logout();
    	}
    	tr = new Thread(new Runnable() {
            public void run() {
                SocketInitiator initiator = null;
                try {
                    SessionSettings settings = createSettings();
                    sessionID = settings.sectionIterator().next();
                    //register the class
                    initiator = new SocketInitiator(new ApplicationTrade(sessionID, mb), new FileStoreFactory(settings),
                            settings, new ScreenLogFactory(settings), new DefaultMessageFactory());
                    initiator.start();
                    Thread.sleep(3000);
                    //new CountDownLatch(5).await();
                } catch (Exception e) {
                        System.out.println("errrrrrrrrrrrrrrrrr");
                        e.printStackTrace();
                        //LoggerPrint.out(e.getLocalizedMessage());
                } finally {
                }
            }

        });
    	tr.run();
    }
    @SuppressWarnings("deprecation")
	public static void logout()
    {
    	if(tr!=null)
    	{
    		tr.stop();
    	}
    	tr= null;
    	//tr.destroy();
    }
    @SuppressWarnings("unchecked")
	private static SessionSettings createSettings() {
        SessionSettings settings = new SessionSettings();

        Map  defaults = new HashMap<String,String>();
        defaults.put("FileStorePath", "examples/target/data/banzai");
        defaults.put("ConnectionType", "initiator");
        defaults.put("TargetCompID", "CiticNewedge");
        //defaults.put("SenderCompID", "DRW");
        defaults.put("SenderCompID", sendID);
        defaults.put("SocketConnectHost", TradeDataByFix.fixIP);
        defaults.put("SocketConnectPort ", TradeDataByFix.fixPort);//hangqing
        
        defaults.put("DataDictionary", "FIX44_Futu.xml");
        defaults.put("StartTime", configClass.getStartTime());
        defaults.put("EndTime", configClass.getEndTime());
        defaults.put("HeartBtInt", "30");
        defaults.put("ReconnectInterval", "5");
        defaults.put("ScreenIncludeMilliseconds", "Y");
        defaults.put("BeginString", BEGINSTRING_FIX44);
        //E:\ºãÉúfix\BTG_Intraday\FIX44_Futu.xml
        defaults.put("UseDataDictionary", "Y");
        defaults.put("DataDictionary", "E:\\ºãÉúfix\\BTG_Intraday\\FIX44_Futu.xml");
        settings.set(defaults);

        //settings.setString(new SessionID(BEGINSTRING_FIX44, senderCompID, "CiticNewedge"), "SocketConnectPort", "7568");
        settings.setString(new SessionID(BEGINSTRING_FIX44, sendID, "CiticNewedge"), "SocketConnectPort", mb.fixPort);

        return settings;
    }
}
