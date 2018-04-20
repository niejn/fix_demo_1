package quickfix.field; 

import quickfix.DoubleField;

/** 
 * @author 
 * @version create time��2011-5-9 03:47:29 
 * @Title: OpenMinQty.java 
 * @Package quickfix.field 
 * @Description: TODO
 * @version V1.0 
 * @ ..
 */
public class OpenMinQty extends DoubleField {

    public static final int FIELD = 5004;

    public OpenMinQty() {
        super(5004);
    }

    public OpenMinQty(double data) {
        super(5004, data);
    }
}
 
