package common;
/**
 * 
 * @author FrankYao
 *	一些公共的方法
 */
public class Common {
	public static Faculty getFacultyById(String id){
		String head = id.substring(0,2);
		if(head.equals("01"))
			return Faculty.C;
		else if(head.equals("02"))
			return Faculty.B;
		else if(head.equals("03"))
			return Faculty.A;
		else 
			return null;
	}
}
