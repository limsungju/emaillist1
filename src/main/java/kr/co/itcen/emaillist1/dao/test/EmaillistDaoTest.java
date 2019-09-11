package kr.co.itcen.emaillist1.dao.test;

import java.util.List;

import kr.co.itcen.emaillist1.dao.EmaillistDao;
import kr.co.itcen.emaillist1.vo.EmaillistVo;

public class EmaillistDaoTest {

	public static void main(String[] args) {
		insertTest();
		getListTest();
		//deleteAllTest();
	}
	
	private static void deleteAllTest() {
		new EmaillistDao().delete();
	}
	
	public static void insertTest() {
		EmaillistVo emailVo = new EmaillistVo();
		emailVo.setFirstName("임");
		emailVo.setLastName("성주");
		emailVo.setEmail("fullmoon4860@gmailcom");
		
		new EmaillistDao().insert(emailVo);
		
		
	}
	
	public static void getListTest() {
		List<EmaillistVo> emailList = new EmaillistDao().getList();
		for(EmaillistVo emailVo : emailList) {
			System.out.println(emailVo);
		}
	}
}
