package main.demo;
//package main.demo;
//
//public class fix_demo1 {
//}

import quickfix.*;

import java.io.FileInputStream;

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

//import main.TradeDataByFix;
import output.readBakData;
import output.writeBakData;
import output.writeMarketData;
import quickfix.field.HighPx;
import quickfix.field.LowPx;
import quickfix.field.MDEntryTime;
import quickfix.field.SecurityExchange;

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
        defaults.put("UseDataDictionary", "Y");
        defaults.put("DataDictionary", "E:\\恒生fix\\BTG_Intraday\\FIX44_Futu.xml");
        settings.set(defaults);

        //settings.setString(new SessionID(BEGINSTRING_FIX44, senderCompID, "CiticNewedge"), "SocketConnectPort", "7568");
        settings.setString(new SessionID(BEGINSTRING_FIX44, SenderID, "CiticNewedge"), "SocketConnectPort", serverPort);

        return settings;
    }

    public static void main(String args[]) throws Exception {
//        if (args.length != 1) return;
//        String fileName = args[0];

        // FooApplication is your class that implements the Application interface
        Application application = new FooApplication(sessionID);
        SessionSettings settings = createSettings();
        sessionID = settings.sectionIterator().next();
//        SessionSettings settings = new SessionSettings(new FileInputStream(fileName));
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
        while (true){

        }
    }
}