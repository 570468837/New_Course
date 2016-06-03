package A.businesslogicservice;

import A.po.StudentPO;

public interface UserBLService {

	//登录

	public StudentPO loginValidity(String account, String password);

}