package A.data;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;

public class inStudent {
	public static void main(String[] args)  {
		DBHelper dbh=new DBHelper();
		inStudent is = new inStudent();
		Connection conn = dbh.getConnection();
		for(int i =1;i<=10;i++){
		int xhon = 3000 + i;
		int xhgn = 300 + i;
		
		String xuehao = "0"+Integer.toString(xhon);
		String xuehaogai = "0"+Integer.toString(xhgn);
		String sql = "update course set 课程编号 = '"+xuehaogai +"'where 课程编号 ='"+xuehao +"'";
		
//		for(int i=0;i<48;i++){
//			account+=1;
//			xuehao +=1;
//			DecimalFormat df = new DecimalFormat("0000");
//			String xh = df.format(xuehao);
//			int gender = (int)(Math.random()*2);
//			String nam = is.name(gender);
//			String sex;
//			System.out.println(account);
//			if(gender==0)
//				sex="女";
//			else 
//				sex="男";
//			sql = sql + ",('"+ xh + "','"+nam + "','"+sex + "','dz','" + account + "')";
//		}
	//	String sql = "insert into student(学号,姓名,性别,院系,关联账户) values ('120','舟舟','女','dz','03001')";
		Statement select;
		try {
			select = conn.createStatement();
			int count = select.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e);
		}
		}
		
	}
	
	String[] lastName = {"赵","钱","孙","李","周","吴","郑","王","尧","姚"};
	String[] nameOneBoy = {"哲","江","壮","建","山","家","超","时","震","盛","雄","琛","钧"};
	String[] nameOneGirl = {"秋","珊","莎","锦","萍","青","荣","婷","姣","婉","娴","瑾","颖"};
	String[] nameTwoBoy = {"固","之","轮","良","海","朗","伯","言","若","鸣","朋","斌","梁"};
	String[] nameTwoGirl = {"聪","欣","兰","凤","洁","梅","琳","丹","云","莲","梦","雪","爱"};
	
	public String name(int gender){
		String n;
		if(gender==0)
			n = random(lastName)+random(nameOneGirl)+random(nameTwoGirl);
		else
			n = random(lastName)+random(nameOneBoy)+random(nameTwoBoy);
		return n;
	}
	
	public String random(String[] strs){
		int index = (int)(Math.random()*strs.length);
		if(index == strs.length)
			index--;
		return strs[index];
	}
}
