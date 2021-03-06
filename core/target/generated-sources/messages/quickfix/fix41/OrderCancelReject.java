package quickfix.fix41;

import quickfix.FieldNotFound;


public class OrderCancelReject extends Message {
    static final long serialVersionUID = 20050617;
    public static final String MSGTYPE = "9";

    public OrderCancelReject() {
        super();
        getHeader().setField(new quickfix.field.MsgType(MSGTYPE));
    }

    public OrderCancelReject(quickfix.field.OrderID orderID,
        quickfix.field.ClOrdID clOrdID, quickfix.field.OrigClOrdID origClOrdID,
        quickfix.field.OrdStatus ordStatus) {
        this();
        setField(orderID);
        setField(clOrdID);
        setField(origClOrdID);
        setField(ordStatus);
    }

    public void set(quickfix.field.OrderID value) {
        setField(value);
    }

    public quickfix.field.OrderID get(quickfix.field.OrderID value)
        throws FieldNotFound {
        getField(value);

        return value;
    }

    public quickfix.field.OrderID getOrderID() throws FieldNotFound {
        quickfix.field.OrderID value = new quickfix.field.OrderID();
        getField(value);

        return value;
    }

    public boolean isSet(quickfix.field.OrderID field) {
        return isSetField(field);
    }

    public boolean isSetOrderID() {
        return isSetField(37);
    }

    public void set(quickfix.field.SecondaryOrderID value) {
        setField(value);
    }

    public quickfix.field.SecondaryOrderID get(
        quickfix.field.SecondaryOrderID value) throws FieldNotFound {
        getField(value);

        return value;
    }

    public quickfix.field.SecondaryOrderID getSecondaryOrderID()
        throws FieldNotFound {
        quickfix.field.SecondaryOrderID value = new quickfix.field.SecondaryOrderID();
        getField(value);

        return value;
    }

    public boolean isSet(quickfix.field.SecondaryOrderID field) {
        return isSetField(field);
    }

    public boolean isSetSecondaryOrderID() {
        return isSetField(198);
    }

    public void set(quickfix.field.ClOrdID value) {
        setField(value);
    }

    public quickfix.field.ClOrdID get(quickfix.field.ClOrdID value)
        throws FieldNotFound {
        getField(value);

        return value;
    }

    public quickfix.field.ClOrdID getClOrdID() throws FieldNotFound {
        quickfix.field.ClOrdID value = new quickfix.field.ClOrdID();
        getField(value);

        return value;
    }

    public boolean isSet(quickfix.field.ClOrdID field) {
        return isSetField(field);
    }

    public boolean isSetClOrdID() {
        return isSetField(11);
    }

    public void set(quickfix.field.OrigClOrdID value) {
        setField(value);
    }

    public quickfix.field.OrigClOrdID get(quickfix.field.OrigClOrdID value)
        throws FieldNotFound {
        getField(value);

        return value;
    }

    public quickfix.field.OrigClOrdID getOrigClOrdID()
        throws FieldNotFound {
        quickfix.field.OrigClOrdID value = new quickfix.field.OrigClOrdID();
        getField(value);

        return value;
    }

    public boolean isSet(quickfix.field.OrigClOrdID field) {
        return isSetField(field);
    }

    public boolean isSetOrigClOrdID() {
        return isSetField(41);
    }

    public void set(quickfix.field.OrdStatus value) {
        setField(value);
    }

    public quickfix.field.OrdStatus get(quickfix.field.OrdStatus value)
        throws FieldNotFound {
        getField(value);

        return value;
    }

    public quickfix.field.OrdStatus getOrdStatus() throws FieldNotFound {
        quickfix.field.OrdStatus value = new quickfix.field.OrdStatus();
        getField(value);

        return value;
    }

    public boolean isSet(quickfix.field.OrdStatus field) {
        return isSetField(field);
    }

    public boolean isSetOrdStatus() {
        return isSetField(39);
    }

    public void set(quickfix.field.ClientID value) {
        setField(value);
    }

    public quickfix.field.ClientID get(quickfix.field.ClientID value)
        throws FieldNotFound {
        getField(value);

        return value;
    }

    public quickfix.field.ClientID getClientID() throws FieldNotFound {
        quickfix.field.ClientID value = new quickfix.field.ClientID();
        getField(value);

        return value;
    }

    public boolean isSet(quickfix.field.ClientID field) {
        return isSetField(field);
    }

    public boolean isSetClientID() {
        return isSetField(109);
    }

    public void set(quickfix.field.ExecBroker value) {
        setField(value);
    }

    public quickfix.field.ExecBroker get(quickfix.field.ExecBroker value)
        throws FieldNotFound {
        getField(value);

        return value;
    }

    public quickfix.field.ExecBroker getExecBroker() throws FieldNotFound {
        quickfix.field.ExecBroker value = new quickfix.field.ExecBroker();
        getField(value);

        return value;
    }

    public boolean isSet(quickfix.field.ExecBroker field) {
        return isSetField(field);
    }

    public boolean isSetExecBroker() {
        return isSetField(76);
    }

    public void set(quickfix.field.ListID value) {
        setField(value);
    }

    public quickfix.field.ListID get(quickfix.field.ListID value)
        throws FieldNotFound {
        getField(value);

        return value;
    }

    public quickfix.field.ListID getListID() throws FieldNotFound {
        quickfix.field.ListID value = new quickfix.field.ListID();
        getField(value);

        return value;
    }

    public boolean isSet(quickfix.field.ListID field) {
        return isSetField(field);
    }

    public boolean isSetListID() {
        return isSetField(66);
    }

    public void set(quickfix.field.CxlRejReason value) {
        setField(value);
    }

    public quickfix.field.CxlRejReason get(quickfix.field.CxlRejReason value)
        throws FieldNotFound {
        getField(value);

        return value;
    }

    public quickfix.field.CxlRejReason getCxlRejReason()
        throws FieldNotFound {
        quickfix.field.CxlRejReason value = new quickfix.field.CxlRejReason();
        getField(value);

        return value;
    }

    public boolean isSet(quickfix.field.CxlRejReason field) {
        return isSetField(field);
    }

    public boolean isSetCxlRejReason() {
        return isSetField(102);
    }

    public void set(quickfix.field.Text value) {
        setField(value);
    }

    public quickfix.field.Text get(quickfix.field.Text value)
        throws FieldNotFound {
        getField(value);

        return value;
    }

    public quickfix.field.Text getText() throws FieldNotFound {
        quickfix.field.Text value = new quickfix.field.Text();
        getField(value);

        return value;
    }

    public boolean isSet(quickfix.field.Text field) {
        return isSetField(field);
    }

    public boolean isSetText() {
        return isSetField(58);
    }
}
