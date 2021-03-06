package main.demo;

//import MyQuickfix.ApplicationTrade;

import data.SecurityStatus;
import quickfix.*;
import quickfix.field.*;
import util.TradeDataStructManager;

import java.io.IOException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//public class ApplicationTrade extends MessageCracker implements quickfix.Application
public class FooApplication extends MessageCracker implements Application {
    public static SessionID MySessionID = null;
    private Session _session = null;

    public FooApplication(SessionID sessionID) {
        System.out.println("FooApplication" + sessionID.toString());
    }

    private int expectedSeqSum = 0;

    /**
     * This method is called when quickfix creates a new session. A session
     * comes into and remains in existence for the life of the application.
     * Sessions exist whether or not a counter party is connected to it. As soon
     * as a session is created, you can begin sending messages to it. If no one
     * is logged on, the messages will be sent at the time a connection is
     * established with the counterparty.
     *
     * @param sessionId
     */
//    OnCreate - this method is called whenever a new session is created.
    @Override
    public void onCreate(SessionID sessionId) {
        System.out.println("onCreate" + sessionId.toString());
        MySessionID = sessionId;
        _session = Session.lookupSession(MySessionID);
        setExpectedSeqSum();
//        try {
//            _session.setNextSenderMsgSeqNum(919);
//        }catch (Exception ex){
//            System.out.println(ex.toString());
//        }

    }
    /*
    * Session lookupSession = Session.lookupSession(sessionId);
                if(resetMsgSeqNum) {
                        //both are required.
                        lookupSession.setNextSenderMsgSeqNum(msgSeqNum);
        lookupSession.setNextTargetMsgSeqNum(msgSeqNum);
                } else {
                        lookupSession.reset();
                }
    */

    /**
     * This callback notifies you when a valid logon has been established with a
     * counter party. This is called when a connection has been established and
     * the FIX logon process has completed with both parties exchanging valid
     * logon messages.
     *
     * @param sessionId QuickFIX session ID
     */
//    OnLogon - notifies when a successful logon has completed.
    @Override
    public void onLogon(SessionID sessionId) {

        System.out.println("onLogon" + sessionId.toString());
        QueryEnterOrder();
    }

    /**
     * This callback notifies you when an FIX session is no longer online. This
     * could happen during a normal logout exchange or because of a forced
     * termination or a loss of network connection.
     *
     * @param sessionId QuickFIX session ID
     */
//    OnLogout - notifies when a session is offline - either from an exchange of logout messages or network connectivity loss.
    @Override
    public void onLogout(SessionID sessionId) {
//        session.reset();
        System.out.println("onLogout" + sessionId.toString());
        setExpectedSeqSum();


    }

    private void setExpectedSeqSum() {
        try {

            if (expectedSeqSum > 0) {
                _session.setNextSenderMsgSeqNum(expectedSeqSum);
            } else {

            }

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(e.toString());
        }
    }

    /**
     * This callback provides you with a peek at the administrative messages
     * that are being sent from your FIX engine to the counter party. This is
     * normally not useful for an application however it is provided for any
     * logging you may wish to do. You may add fields in an adminstrative
     * message before it is sent.
     *
     * @param message   QuickFIX message
     * @param sessionId
     */
//    ToAdmin - all outbound admin level messages pass through this callback.
    @Override
    public void toAdmin(Message message, SessionID sessionId) {
        System.out.println("toAdmin " + message.toString());

    }

    /**
     * This callback notifies you when an administrative message is sent from a
     * counterparty to your FIX engine. This can be usefull for doing extra
     * validation on logon messages such as for checking passwords. Throwing a
     * RejectLogon exception will disconnect the counterparty.
     *
     * @param message   QuickFIX message
     * @param sessionId QuickFIX session ID
     * @throws FieldNotFound
     * @throws IncorrectDataFormat
     * @throws IncorrectTagValue
     * @throws RejectLogon         causes a logon reject
     */
    /*收到的消息序列号小于所期望的序列号。则会产生Logout消息，并断开连接，这时对方收到后，要么手动处理，
    要么自动尝试重新登录连接自动增长序列号，直到和对方望值一样时候，双方建立会话
    (注意：一般的FIX引擎在断开的时候，不会增长接收序列号，是为了让对方自动增长序列号，以达到自动重新连接的目的）
    * */
//    FromAdmin - every inbound admin level message will pass through this method, such as heartbeats, logons, and logouts.
    @Override
    public void fromAdmin(Message message, SessionID sessionId) throws FieldNotFound, IncorrectDataFormat, IncorrectTagValue, RejectLogon {
        System.out.println("fromAdmin " + message.toString());
//        message.getField();
        final int Text_FIELD = 58;
        if (message.isSetField(Text_FIELD)) {
            String msg = message.getString(Text_FIELD);
            System.out.println("*************************************************text " + msg);
//            String msg = text;
            String pattern = "^MsgSeqNum too low, expecting\\W*(\\d+) but received (\\d+)";
//        String pattern = "(\\d+)";
            // 创建 Pattern 对象
            Pattern r = Pattern.compile(pattern);

            // 现在创建 matcher 对象
            Matcher m = r.matcher(msg);
            if (m.find()) {
                System.out.println("Found value: " + m.group(0));
                System.out.println("Found value: " + m.group(1));
                System.out.println("Found value: " + m.group(2));
                expectedSeqSum = Integer.parseInt(m.group(1));
//            System.out.println("Found value: " + m.group(3) );
            } else {
                System.out.println("NO MATCH");
            }
        }




    }

    /**
     * This is a callback for application messages that you are sending to a
     * counterparty. If you throw a DoNotSend exception in this function, the
     * application will not send the message. This is mostly useful if the
     * application has been asked to resend a message such as an order that is
     * no longer relevant for the current market. Messages that are being resent
     * are marked with the PossDupFlag in the header set to true; If a DoNotSend
     * exception is thrown and the flag is set to true, a sequence reset will be
     * sent in place of the message. If it is set to false, the message will
     * simply not be sent. You may add fields before an application message
     * before it is sent out.
     *
     * @param message   QuickFIX message
     * @param sessionId QuickFIX session ID
     * @throws DoNotSend --
     *                   This exception aborts message transmission
     */
//    ToApp - all outbound application level messages pass through this callback before they are sent. If a tag needs to be added to every outgoing message, this is a good place to do that.
    @Override
    public void toApp(Message message, SessionID sessionId) throws DoNotSend {
        System.out.println("toApp" + message.toString());
    }

    /**
     * This callback receives messages for the application. This is one of the
     * core entry points for your FIX application. Every application level
     * request will come through here. If, for example, your application is a
     * sell-side OMS, this is where you will get your new order requests. If you
     * were a buy side, you would get your execution reports here. If a
     * FieldNotFound exception is thrown, the counterparty will receive a reject
     * indicating a conditionally required field is missing. The Message class
     * will throw this exception when trying to retrieve a missing field, so you
     * will rarely need the throw this explicitly. You can also throw an
     * UnsupportedMessageType exception. This will result in the counterparty
     * getting a business reject informing them your application cannot process
     * those types of messages. An IncorrectTagValue can also be thrown if a
     * field contains a value that is out of range or you do not support.
     *
     * @param message   QuickFIX message
     * @param sessionId QuickFIX session ID
     * @throws FieldNotFound
     * @throws IncorrectDataFormat
     * @throws IncorrectTagValue
     * @throws UnsupportedMessageType
     */
//    FromApp - every inbound application level message will pass through this method, such as orders, executions, secutiry definitions, and market data.
    @Override
    public void fromApp(Message message, SessionID sessionId) throws FieldNotFound, IncorrectDataFormat, IncorrectTagValue, UnsupportedMessageType {
        System.out.println("fromApp" + message.toString());
//        Crack(msg, sessionID);
        //Crack will then call the appropriate overloaded OnMessage
        try {
            crack(message, sessionId);
        } catch (Exception ex) {

            System.out.println("==Cracker exception==");
            System.out.println(ex.toString());
            ex.printStackTrace();

        }

    }

    //Receiving messages in QuickFIX/N is type safe and simple:
    public void OnMessage(quickfix.fix44.NewOrderSingle order, SessionID sessionID) {
        System.out.println("NewOrderSingle:" + order.toString());
    }

    public void onMessage(quickfix.fix44.MarketDataSnapshotFullRefresh message, SessionID sessionID) {
        System.out.println("MarketDataSnapshotFullRefresh:" + message.toString());
        System.out.println("On MEssage");

    }


    public void onMessage(quickfix.fix44.SecurityList message, SessionID sessionID) {
        System.out.println("SecurityList:" + message.toString());

        //message.g
    }


    public void onMessage(quickfix.fix44.SecurityStatus message, SessionID sessionID) {
        System.out.println("SecurityStatus:" + message.toString());

    }

    public void onMessage(quickfix.fix44.SecurityDefinition message, SessionID sessionID) {
        System.out.println("SecurityDefinition:" + message.toString());

    }

    /* To recieve Execution report
     */
    public void onMessage(quickfix.fix44.ExecutionReport message, SessionID sessionID) throws FieldNotFound {
        System.out.println("ExecutionReport:" + message.toString());
//        String account = msg.GetString(Tags.Account);
//        Decimal price = msg.GetDecimal(Tags.Price);
        if (message.getExecType().getValue() == ExecType.NEW) {

            System.out.println("message.getExecType().getValue()== ExecType.NEW:" + message.toString());


        } else if (message.getOrdStatus().getValue() == OrdStatus.NEW) {
            System.out.println("message.getOrdStatus().getValue()==OrdStatus.NEW:" + message.toString());
        } else {
            return;
        }

        String datetmp = "";
        if (message.getExecID().valueEquals("0")) {
            //NO exce ID, ignore it
            System.out.println("NO exce ID, ignore it:" + message.toString());
        }
        String tmpdate = message.getDate();
        if (tmpdate == null) {

        } else {
            System.out.println(tmpdate);
            String tmpDateArray[] = tmpdate.split("-");
            datetmp = tmpDateArray[0];
        }
        String account = message.getAccount().getValue();


        String exchange = message.getSecurityExchange().getValue();
        String Symbol = message.getSymbol().getValue();
        char sidetmp = message.getSide().getValue();
        String tmpquant = "" + message.getLastQty().getValue();
        String str_side = "";
        if (sidetmp == Side.SELL) {
            str_side = "SELL";
        } else {
            str_side = "BUY";
        }
        String tmprice = "" + message.getPrice().getValue();
        String order_id = message.getOrderID().getValue();
        char ExecType = message.getExecType().getValue();
        String str_ExecType = "";
        if (ExecType == '0') {
            str_ExecType = "NEW";
        }
        char orderStatus = message.getOrdStatus().getValue();
        String exchange_name = message.getSecurityExchange().getValue();
        final int OffSet_FIELD = 5001;
        String offset = message.getString(OffSet_FIELD);
        final int HedgeFlag_FIELD = 5006;
        String hedgeFlag = message.getString(HedgeFlag_FIELD);
        double qty = message.getOrderQty().getValue();
        double leavesQty = message.getLeavesQty().getValue();
//        char offset = message.getDiscretionOffsetValue();
//        char offset = messat();
        String parse_tag = " ";
//        10013102 SHFE ag1808 BUY 1.0 4630.0 NEW 0 1 1.0 1 SHFE 16
//        10013101 SHFE ag1812 BUY 1.0 3900.0 NEW 0 1 1.0 1 SHFE 8
//        10013102 DCE j1705 SELL 0.0 2053.0 0 15
        System.out.println("" + account + parse_tag + exchange + parse_tag + Symbol + parse_tag + str_side +
                parse_tag + qty + parse_tag + tmprice + parse_tag + str_ExecType + parse_tag + orderStatus + parse_tag
                + offset + parse_tag + leavesQty + parse_tag + hedgeFlag + parse_tag + exchange_name
                + parse_tag + order_id);

    }

    //    #region Message creation functions

    private void QueryEnterOrder() {
        System.out.println("\nNewOrderSingle");

        quickfix.fix44.NewOrderSingle m = QueryNewOrderSingle44();

        if (m != null) {


            SendMessage(m);
            System.out.println("\n==================Order Submitted!!!==================");

        }
    }

    private quickfix.fix44.NewOrderSingle QueryNewOrderSingle44() {
        quickfix.field.TriggerOrderType ordType = null;
//        quickfix.field.AllocNoOrdersType
//        quickfix.fix44.ResendRequest newResendRequest = new quickfix.fix44.ResendRequest();
        quickfix.fix44.NewOrderSingle newOrderSingleRequest = new quickfix.fix44.NewOrderSingle();
        newOrderSingleRequest.set(new ClOrdID("test"));
        newOrderSingleRequest.set(new OrderQty(1));
        newOrderSingleRequest.set(new OrdType('2'));
        newOrderSingleRequest.set(new Price(4001));
        newOrderSingleRequest.set(new Side('1'));
        newOrderSingleRequest.set(new Symbol("ag1807"));
        newOrderSingleRequest.set(new TransactTime(new Date()));
        newOrderSingleRequest.setField(new HedgeFlag(0));
        newOrderSingleRequest.set(new Account("10013102"));
        newOrderSingleRequest.set(new Password("111111"));
        newOrderSingleRequest.set(new HandlInst('1'));
        newOrderSingleRequest.set(new SecurityExchange("SHFE"));
        newOrderSingleRequest.setField(new Offset('1'));
//        newOrderSingleRequest.set(new HedgeFlag(0));
//        _session.send(newOrderSingleRequest);

        return newOrderSingleRequest;

    }

    private void SendMessage(Message m) {
        if (_session != null)
//            _session.send
            _session.send(m);
        else {
            // This probably won't ever happen.
            System.out.println("Can't send message: session not created.");
        }
    }

}
