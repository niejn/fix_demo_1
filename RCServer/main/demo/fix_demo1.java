package main.demo;
//package main.demo;
//
//public class fix_demo1 {
//}

import quickfix.*;

import java.io.FileInputStream;

import quickfix.field.*;
import util.*;
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
import quickfix.SessionNotFound;
import quickfix.Session;

import java.util.Timer;

import quickfix.fix44.component.Instrument;


import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;

//import main.TradeDataByFix;
import output.readBakData;
import output.writeBakData;
import output.writeMarketData;

import static quickfix.FixVersions.BEGINSTRING_FIX44;

public class fix_demo1 {
    public static SessionID sessionID = null;

    private static SessionSettings createSettings() {
        SessionSettings settings = new SessionSettings();
        /*
        * SenderID=ESolution1
        writeFilePath=E:/恒生fix/log/
        serverIP=211.95.40.133
        serverPort=7567
        startTime=11:45:00
        endTime=11:20:00
        title=IntradayForBTG
        * */
        String SenderID = "ESolution1";
        String writeFilePath = "E:/恒生fix/log/";
        String serverIP = "211.95.40.133";
        String serverPort = "7567";
        String startTime = "11:45:00";
        String endTime = "11:20:00";
        String title = "IntradayForBTG";

        Map defaults = new HashMap<String, String>();
//        FileStorePath
        defaults.put("FileStorePath", "examples/target/data/banzai");
        defaults.put("ConnectionType", "initiator");
        defaults.put("TargetCompID", "CiticNewedge");
        //defaults.put("SenderCompID", "DRW");
        defaults.put("SenderCompID", SenderID);
        defaults.put("SocketConnectHost", serverIP);
        defaults.put("SocketConnectPort ", serverPort);//hangqing

        defaults.put("DataDictionary", "FIX44_Futu.xml");
        defaults.put("StartTime", configClass.getStartTime());
        defaults.put("EndTime", configClass.getEndTime());
        defaults.put("HeartBtInt", "30");
        defaults.put("ReconnectInterval", "5");
        defaults.put("ScreenIncludeMilliseconds", "Y");
        defaults.put("BeginString", BEGINSTRING_FIX44);
        //E:\恒生fix\BTG_Intraday\FIX44_Futu.xml
        //"E:/恒生fix/log/"
        //E:/恒生fix/BTG_Intraday/FIX44_Futu.xml
        defaults.put("UseDataDictionary", "Y");
        defaults.put("DataDictionary", "E:/恒生fix/BTG_Intraday/FIX44_Futu.xml");
//        defaults.put("ResetOnLogon", "Y");
//        defaults.put("ResetOnLogout", "Y");
//        defaults.put("ResetOnDisconnect", "Y");
//        defaults.put("ResetOnError", "Y");
        //NextExpectedMsgSeqNum
        defaults.put("EnableNextExpectedMsgSeqNum", "Y");
        settings.set(defaults);

        //settings.setString(new SessionID(BEGINSTRING_FIX44, senderCompID, "CiticNewedge"), "SocketConnectPort", "7568");
        //sessionQualifer用于区分具有相同的targetCompID不同的session
        settings.setString(new SessionID(BEGINSTRING_FIX44, SenderID, "CiticNewedge", "citicsf"), "SocketConnectPort", serverPort);
//        settings.setString(new SessionID(BEGINSTRING_FIX44, SenderID, "CiticNewedge"), "SocketConnectPort", serverPort);
        /*
        * 4. quickfix.SessionID：是Session的唯一标识。SessionID中包含beginString（必须），senderCompID（必须），
        * senderSubID （可选），senderLocationID（可选），targetCompID（必须），targetSubID（可选），
        * targetSubID（可选），targetLocationID（可选），sessionQualifier（可选）。
        * sessionQualifer用于区分具有相同的targetCompID不同的session，只能用在initiator角色中。
        * SessionID.toString生成的可读的Session ID字符串组成为：
        * beginString:senderCompID/senderSubID/senderLocationID->
        * targetCompID/targetSubID/targetLocationID/sessionQualifier。
        * 如果可选值未设置则在Session ID字符串中默认空字符串。
        * */
        return settings;
    }

    public static void main(String args[]) throws Exception {
//        if (args.length != 1) return;
//        String fileName = args[0];

        // FooApplication is your class that implements the Application interface
//        Application application = new FooApplication(sessionID);
        SessionSettings settings = createSettings();
        sessionID = settings.sectionIterator().next();
//        SessionSettings settings = new SessionSettings(new FileInputStream(fileName));
//        LogoutTimeout
        SocketInitiator initiator = null;
        initiator = new SocketInitiator(new FooApplication(sessionID), new FileStoreFactory(settings),
                settings, new ScreenLogFactory(settings), new DefaultMessageFactory());
//        MessageStoreFactory storeFactory = new FileStoreFactory(settings);
//        LogFactory logFactory = new FileLogFactory(settings);
//        MessageFactory messageFactory = new DefaultMessageFactory();
//        Acceptor acceptor = new SocketAcceptor
//                (application, storeFactory, settings, logFactory, messageFactory);
//        acceptor.start();
        // while(condition == true) { do something; }
        initiator.start();

//        quickfix.fix44.TradeCaptureReport tcr = new quickfix.fix44.TradeCaptureReport();
////        tcr.addGroup();
////        var tcr = new quickfifix.FIX44.TradeCaptureReport();
//        quickfix.fix44.TradeCaptureReport.NoSides sidesGrp1 = new quickfix.fix44.TradeCaptureReport.NoSides();
//        sidesGrp1.set(new Account("Silvio"));
//        sidesGrp1.set(new OrderID("09011900"));
//        sidesGrp1.set(new Side(Side.BUY));
//        tcr.addGroup(sidesGrp1);
////        sidesGrp1.Account = new Account("Silvio");
////        sidesGrp1.OrderID = new OrderID("09011900");
////        sidesGrp1.Side = new Side(Side.BUY);
//        quickfix.fix44.TradeCaptureReport.NoSides noSidesGrp = new quickfix.fix44.TradeCaptureReport.NoSides();
////        quickfix.fix44.Message
//
//        for(int grpIndex = 1; grpIndex<= Message.GetInt(Tags.NoSides); grpIndex += 1)
//        {
//            message.GetGroup(grpIndex, noSidesGrp);
//            // ...do stuff with noSidesGrp...
//        }

        while (true){

        }
    }
}