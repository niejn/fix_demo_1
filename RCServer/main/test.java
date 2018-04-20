package main;

import MyQuickfix.ApplicationTrade;
import output.readdir;
import quickfix.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

//import static MyQuickfix.GetTradeData.createSettings;

public class test {
//    public static void login() {
//
//        Thread tr = new Thread(new Runnable() {
//            public void run() {
//                SocketInitiator initiator = null;
//                try {
//                    SessionSettings settings = createSettings();
//                    SessionID sessionID = settings.sectionIterator().next();
//                    //register the class
//                    initiator = new SocketInitiator(new ApplicationTrade(sessionID, mb), new FileStoreFactory(settings), settings, new ScreenLogFactory(settings), new DefaultMessageFactory());
//                    initiator.start();
//                    Thread.sleep(3000);
//                    //new CountDownLatch(5).await();
//                } catch (Exception e) {
//                    System.out.println("errrrrrrrrrrrrrrrrr");
//                    e.printStackTrace();
//                    //LoggerPrint.out(e.getLocalizedMessage());
//                } finally {
//                }
//            }
//
//        });
//        tr.run();
//    }

    public static void main(String[] args) {

        System.out.println("ok");
    }
}
