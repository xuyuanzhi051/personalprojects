package net.xyz.user.service;

import net.xyz.user.dao.UserDao;
import net.xyz.user.domain.User;
/*
 * 用户逻辑层
 */
public class UserService {
    private UserDao userDao= new UserDao();
    /*
     * 用户注册功能，检查用户是否存在，存在的话抛出异常，不存在向数据库添加用户
     */
    public void registUser(User user) throws UserException{
    	
    	User _user=userDao.selectUserByUsername(user.getUsername());
    	if(_user!=null) throw new UserException("用户名"+_user.getUsername()+"已被注册");
    	userDao.addUser(user);
    }
    /*
     * 用户登录功能，使用表单中的数据进行查询，如果用户名不存在，抛出错误信息，如果密码错误抛出错误信息
     */
	public User login(User user) throws UserException{
		User _user=userDao.selectUserByUsername(user.getUsername());
		if(_user==null) throw new UserException("用户名不存在");
		if(!_user.getPassword().equals(user.getPassword())) {
			throw new UserException("密码错误");
		}
		/*
		 * 返回数据库中查询出来的user，而不是form，因为form中只有用户名和密码,而user是用户的所有信息
		 * 
		 */
		return _user;
	}
}
