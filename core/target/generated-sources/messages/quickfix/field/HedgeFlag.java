package quickfix.field; 

import quickfix.IntField;
import quickfix.StringField;

/** 
 * @author 
 * @version create time��2011-5-6 01:29:44 
 * @Title: HedgeFlag.java 
 * @Package quickfix.field 
 * @Description: TODO
 * @version V1.0 
 * @ ..
 */
public class HedgeFlag extends IntField {
    public static final int FIELD = 5006;

    public HedgeFlag() {
        super(5006);
    }

    public HedgeFlag(int data) {
        super(5006, data);
    }
}
 
