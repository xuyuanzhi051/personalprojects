package net.text.dao;

import net.xyz.user.dao.UserDao;
import net.xyz.user.domain.User;
import net.xyz.user.service.UserException;
import net.xyz.user.service.UserService;

public class UserDaoTest {
	public static void main(String[] args) {
        UserDao userDao=new UserDao();
//        User user=new User();
//        user.setUsername("123");
//        user.setPassword("zhangsan");
//        User user2=new User();
//        user2.setUsername("李四");
//        user2.setPassword("lisi");
//        userDao.addUser(user);
//        userDao.addUser(user2);
//        User user=new User();
//        user.setUsername("123");
//        user.setPassword("123");
//        userDao.addUser(user);
//        User user3=userDao.selectUserByUsername("123");
//        System.out.println(user3);
        UserService userService=new UserService();
        User user=new User();
        user.setUsername("张三");
        try {
			userService.registUser(user);
		} catch (UserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
