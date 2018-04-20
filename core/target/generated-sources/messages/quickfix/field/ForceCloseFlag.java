package quickfix.field; 

import quickfix.IntField;

/** 
 * @author 
 * @version create time��2011-5-8 09:44:30 
 * @Title: ForceCloseFlag.java 
 * @Package quickfix.field 
 * @Description: TODO
 * @version V1.0 
 * @ ..
 */
public class ForceCloseFlag extends IntField {

    public static final int FIELD = 5007;

    public ForceCloseFlag() {
        super(5007);
    }

    public ForceCloseFlag(int data) {
        super(5007, data);
    }
}
 
