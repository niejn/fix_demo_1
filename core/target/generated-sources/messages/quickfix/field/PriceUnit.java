package quickfix.field; 

import quickfix.DoubleField;

/** 
 * @author 
 * @version create time��2011-5-9 03:46:26 
 * @Title: PriceUnit.java 
 * @Package quickfix.field 
 * @Description: TODO
 * @version V1.0 
 * @ ..
 */
public class PriceUnit extends DoubleField {

    public static final int FIELD = 5003;

    public PriceUnit() {
        super(5003);
    }

    public PriceUnit(double data) {
        super(5003, data);
    }
}
 
