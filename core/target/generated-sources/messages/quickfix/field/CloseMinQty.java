package quickfix.field; 

import quickfix.DoubleField;

/** 
 * @author 
 * @version create time��2011-5-9 03:48:28 
 * @Title: CloseMinQty.java 
 * @Package quickfix.field 
 * @Description: TODO
 * @version V1.0 
 * @ ..
 */
public class CloseMinQty extends DoubleField {

    public static final int FIELD = 5005;

    public CloseMinQty() {
        super(5005);
    }

    public CloseMinQty(double data) {
        super(5005, data);
    }
}
 
