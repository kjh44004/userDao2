import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import java.sql.SQLException;
import java.util.Random;

import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

/**
 * Created by MIYEON on 2016-04-20.
 */
public class UserDaoTest {
    private UserDao userDao;

    @Before
    public void setup(){
//        userDao =  new DaoFactory().getUserDao();
        ApplicationContext context = new GenericXmlApplicationContext("daoFactory.xml");
        userDao = context.getBean("userDao", UserDao.class);
    }

    @Test
    public void get() throws SQLException, ClassNotFoundException {

        Long id = 1L;
        String name = "허윤호";
        String password = "1234";

        User user = userDao.get(id);

        assertEquals(id, user.getId());
        assertEquals(name, user.getName());
        assertEquals(password, user.getPassword());
    }

    @Test
    public void add() throws SQLException, ClassNotFoundException {
        User user = new User();

        String name = "her";
        String password = "1234";

        user.setName(name);
        user.setPassword(password);

        Long id = userDao.add(user);

        User addedUser = userDao.get(id);

        assertEquals(id, addedUser.getId());
        assertEquals(name, addedUser.getName());
        assertEquals(password, addedUser.getPassword());

    }
    @Test
    public void delete() throws SQLException, ClassNotFoundException {
        User user = new User();

        String name = "her";
        String password = "1234";

        user.setName(name);
        user.setPassword(password);

        Long id = userDao.add(user);

        userDao.delete(id);

        User resultUser = userDao.get(id);
        assertThat(resultUser, nullValue());
    }
}
