package net.xyz.user.dao;

import java.io.FileOutputStream;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import net.xyz.user.domain.User;

/*
 * User的业务逻辑层
 */
public class UserDao {
    String path="E:\\Test\\user.xml";
	//String path="D:\\学习\\javaEE\\workspace\\T1LoginandRegister\\WebContent\\WebContent\\user.xml";
    //根据用户名查询用户是否存在
    public User selectUserByUsername(String userName) {
    	/*
    	 * 1.得到document对象
    	 * 2.xpath查询
    	 * 3.查询结果是否存在，如果不存在返回null
    	 * 4.如果存在，把查询到的数据封装为User对象并返回
    	 *
    	 */
    	
    	SAXReader saxReader=new SAXReader();
    	try {
			Document document=saxReader.read(path);
			Element ele=(Element) document.selectSingleNode("//user[@username='"+userName+"']");
			if(ele==null) return null;
			String username=ele.attributeValue("username");
			String password=ele.attributeValue("password");
			User user=new User();
			user.setUsername(username);
			user.setPassword(password);
			return user;
		} catch (DocumentException e) {
			throw new RuntimeException(e);
		}
    }
    //添加用户
    public void addUser(User user) {
    	/*
    	 * 1.得到document对象
    	 * 2.找到文档的根元素
    	 * 3.在根元素中添加user
    	 * 4.回写xml文件
    	 */
    	SAXReader saxReader=new SAXReader();
    	try {
			Document document=saxReader.read(path);
			Element root=document.getRootElement();
			Element us=root.addElement("user");
			us.addAttribute("username", user.getUsername());
			us.addAttribute("password", user.getPassword());
			OutputFormat format=OutputFormat.createPrettyPrint();
			XMLWriter xmlWriter=new XMLWriter(new FileOutputStream(path),format);
			xmlWriter.write(document);
			xmlWriter.close();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
    }
}
