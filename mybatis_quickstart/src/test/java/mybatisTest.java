import com.lagou.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

public class mybatisTest {

    @Test
    public void mybatisQuickStart() throws IOException {

        //1.加载核心配置文件
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");

        //2.获取工厂对象
        SqlSessionFactory sqlSessionFactory= new SqlSessionFactoryBuilder().build(resourceAsStream);

        //3.获取sqlsession会话对象
        SqlSession sqlSession = sqlSessionFactory.openSession();


        //4.执行sql 参数就是statement id
        List<User> users = sqlSession.selectList("userMapper.findAll");

        //5.遍历打印结果
        for (User user : users) {
            System.out.println(user);
        }

        sqlSession.close();
    }

    @Test
    public void saveUser() throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();

        User user = new User();
        user.setUsername("路明");
        user.setAddress("成都");
        user.setSex("男");
        Date date = new Date();
        user.setBirthday(date);
        int i = sqlSession.insert("userMapper.saveUser", user);
        sqlSession.commit();
        System.out.println(i);
        sqlSession.close();
    }

    @Test
    public void updateUser() throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        User user = new User();
        user.setUsername("lucy");
        user.setAddress("成都");
        user.setSex("男");
        user.setId(8);
        Date date = new Date();
        user.setBirthday(date);
        int i = sqlSession.update("userMapper.updateUser", user);
        System.out.println(i);
        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void deleteUser() throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();

        int i = sqlSession.delete("userMapper.deleteUser", 4);

        System.out.println(i);
        sqlSession.commit();
        sqlSession.close();
    }
}
