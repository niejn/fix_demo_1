package main.demo;

//import MyQuickfix.ApplicationTrade;
import data.SecurityStatus;
import quickfix.*;
import quickfix.field.*;
import util.TradeDataStructManager;

import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

//public class ApplicationTrade extends MessageCracker implements quickfix.Application
public class FooApplication extends MessageCracker implements Application {
    public static SessionID MySessionID  = null;
    private Session _session = null;

    public FooApplication(SessionID sessionID) {
        System.out.println("FooApplication" + sessionID.toString());
    }

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
    }

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
        System.out.println("onLogout" + sessionId.toString());
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
        System.out.println("toAdmin "+ message.toString());
//        System.out.println("toAdmin");
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
//    FromAdmin - every inbound admin level message will pass through this method, such as heartbeats, logons, and logouts.
    @Override
    public void fromAdmin(Message message, SessionID sessionId) throws FieldNotFound, IncorrectDataFormat, IncorrectTagValue, RejectLogon {
        System.out.println("fromAdmin "+ message.toString());
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
        try
        {
            crack(message, sessionId);
        }
        catch (Exception ex)
        {
//            Console.WriteLine("==Cracker exception==");
//            Console.WriteLine(ex.ToString());
//            Console.WriteLine(ex.StackTrace);
            System.out.println("==Cracker exception==");
            System.out.println(ex.toString());
            ex.printStackTrace();
//            System.out.println(ex.printStackTrace(););
        }
        crack(message, sessionId);
    }

    //Receiving messages in QuickFIX/N is type safe and simple:
    public void OnMessage(quickfix.fix44.NewOrderSingle order, SessionID sessionID){
        System.out.println("NewOrderSingle:" + order.toString());
    }

    public void onMessage(quickfix.fix44.MarketDataSnapshotFullRefresh message, SessionID sessionID){
        System.out.println("MarketDataSnapshotFullRefresh:" + message.toString());
        System.out.println("On MEssage");

    }


    public void onMessage(quickfix.fix44.SecurityList message, SessionID sessionID){
        System.out.println("SecurityList:" + message.toString());

        //message.g
    }


    public void onMessage(quickfix.fix44.SecurityStatus message, SessionID sessionID){
        System.out.println("SecurityStatus:" + message.toString());

    }

    public void onMessage(quickfix.fix44.SecurityDefinition message, SessionID sessionID){
        System.out.println("SecurityDefinition:" + message.toString());

    }

    /* To recieve Execution report
     */
    public void onMessage(quickfix.fix44.ExecutionReport message, SessionID sessionID){
        System.out.println("ExecutionReport:" + message.toString());

    }

//    #region Message creation functions
    /*        private QuickFix.FIX44.NewOrderSingle QueryNewOrderSingle44()
        {
            QuickFix.Fields.OrdType ordType = null;

            QuickFix.FIX44.NewOrderSingle newOrderSingle = new QuickFix.FIX44.NewOrderSingle(
                QueryClOrdID(),
                QuerySymbol(),
                QuerySide(),
                new TransactTime(DateTime.Now),
                ordType = QueryOrdType());

            newOrderSingle.Set(new HandlInst('1'));
            newOrderSingle.Set(QueryOrderQty());
            newOrderSingle.Set(QueryTimeInForce());
            if (ordType.getValue() == OrdType.LIMIT || ordType.getValue() == OrdType.STOP_LIMIT)
                newOrderSingle.Set(QueryPrice());
            if (ordType.getValue() == OrdType.STOP || ordType.getValue() == OrdType.STOP_LIMIT)
                newOrderSingle.Set(QueryStopPx());

            return newOrderSingle;
        }
         private void QueryEnterOrder()
        {
            Console.WriteLine("\nNewOrderSingle");

            QuickFix.FIX44.NewOrderSingle m = QueryNewOrderSingle44();

            if (m != null && QueryConfirm("Send order"))
            {
                m.Header.GetField(Tags.BeginString);

                SendMessage(m);
            }
        }

        */
    private quickfix.fix44.NewOrderSingle QueryNewOrderSingle44()
    {
        quickfix.field.TriggerOrderType ordType = null;
//        quickfix.field.AllocNoOrdersType

        quickfix.fix44.NewOrderSingle newOrderSingleRequest = new quickfix.fix44.NewOrderSingle();
        newOrderSingleRequest.set(new ClOrdID("test"));
        newOrderSingleRequest.set(new OrderQty(1));
        newOrderSingleRequest.set(new OrdType('2'));
        newOrderSingleRequest.set(new Price(4001));
        newOrderSingleRequest.set(new Side('1'));
        newOrderSingleRequest.set(new Symbol("ag1807"));
        newOrderSingleRequest.set(new TransactTime(new Date()));
        newOrderSingleRequest.set(new HedgeFlag(00));
//        _session.send(newOrderSingleRequest);

        return newOrderSingleRequest;

    }

    private void SendMessage(Message m)
    {
        if (_session != null)
//            _session.send
            _session.send(m);
        else
        {
            // This probably won't ever happen.
            System.out.println("Can't send message: session not created.");
        }
    }

}
