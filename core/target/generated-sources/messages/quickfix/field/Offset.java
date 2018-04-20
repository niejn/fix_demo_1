package quickfix.field; 

import quickfix.CharField;

/** 
 * @author 
 * @version create time��2011-4-6 02:48:13 
 * @Title: Offset.java 
 * @Package quickfix.field 
 * @Description: TODO
 * @version V1.0 
 * @ ..
 */
public class Offset extends CharField {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final char OPEN = '1';
    public static final char CLOSE = '2';
    public static final char CLOSE_AT_PRESENT_DAY = '4';
	public static final int FIELD = 5001;

    public Offset() {
        super(5001);
    }

    public Offset(char data) {
        super(5001, data);
    }
}
 
