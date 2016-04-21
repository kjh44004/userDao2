import org.junit.Test;

import java.sql.SQLException;
import java.util.Random;

import static org.junit.Assert.assertEquals;

/**
 * Created by MIYEON on 2016-04-20.
 */
public class UserDaoTest {

    @Test
    public void get() throws SQLException, ClassNotFoundException {
        UserDao userDao = new UserDao();
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

        Long id = Long.valueOf(new Random().nextInt());
        String name = "허윤호";
        String password = "1234";

        user.setId(id);
        user.setName(name);
        user.setPassword(password);

        UserDao userDao = new UserDao();
        userDao.add(user);
        User addedUser = userDao.get(id);

        assertEquals(id, user.getId());
        assertEquals(name, user.getName());
        assertEquals(password, user.getPassword());

    }
}
