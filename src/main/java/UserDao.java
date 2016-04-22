import java.sql.*;

/**
 * Created by MIYEON on 2016-04-20.
 */
public class UserDao {

    private ConnectionMaker ConnectionMaker;

    public UserDao(){
        this.ConnectionMaker = new DConnectionMaker();
    }

    public User get(Long id) throws ClassNotFoundException, SQLException {
        Connection connection = ConnectionMaker.getConnection();

        PreparedStatement preparedStatement = connection.prepareStatement(
                "select * from userinfo where id = ?");
        preparedStatement.setLong(1, id);

        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();

        User user = new User();
        user.setId(resultSet.getLong("id"));
        user.setName(resultSet.getString("name"));
        user.setPassword(resultSet.getString("password"));

        resultSet.close();
        preparedStatement.close();
        connection.close();

        return user;
    }

    public void add(User user) throws SQLException, ClassNotFoundException {
        //사용자는 어디에 저장되있는거지
        //Database를 사용해보자
        //어떤 DB를 사용하지
        //Mysql을 사용해보자
        //Connection을 맺고
        Connection connection = ConnectionMaker.getConnection();
        //쿼리를 만들어서
        PreparedStatement preparedStatement = connection.prepareStatement(
                "insert into userinfo(id, name, password) values(?, ?, ?)");
        preparedStatement.setLong(1, user.getId());
        preparedStatement.setString(2, user.getName());
        preparedStatement.setString(3, user.getPassword());

        //실행시키고
        preparedStatement.executeUpdate();

        //지원을 해지한다.
        preparedStatement.close();
        connection.close();
    }
}
