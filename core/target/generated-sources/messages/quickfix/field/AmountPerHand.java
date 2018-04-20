package quickfix.field; 

import quickfix.DoubleField;
import quickfix.StringField;

/** 
 * @author 
 * @version create time��2011-5-9 03:23:23 
 * @Title: AmountPerHand.java 
 * @Package quickfix.field 
 * @Description: TODO
 * @version V1.0 
 * @ ..
 */
public class AmountPerHand extends DoubleField {

    public static final int FIELD = 5002;

    public AmountPerHand() {
        super(5002);
    }

    public AmountPerHand(double data) {
        super(5002, data);
    }
}
 
