import java.sql.*;

/**
 * Created by MIYEON on 2016-04-20.
 */
public class UserDao {
    public User get(String id) throws ClassNotFoundException, SQLException {
        //사용자는 어디에 저장되있는거지
        //Database를 사용해보자
        //어떤 DB를 사용하지
        //Mysql을 사용해보자
        //Connection을 맺고
        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://db.skyserv.kr/jejunu?characterEncoding=utf-8", "jeju", "jejupw");
        //쿼리를 만들어서
        PreparedStatement preparedStatement = connection.prepareStatement(
                "select * from userinfo where id = ?");
        preparedStatement.setString(1, id);
        //실행시키고
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        //결과를 User에 잘 매핑하고
        User user = new User();
        user.setId(resultSet.getString("id"));
        user.setName(resultSet.getString("name"));
        user.setPassword(resultSet.getString("password"));
        //지원을 해지한다.
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
        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://db.skyserv.kr/jejunu?characterEncoding=utf-8", "jeju", "jejupw");
        //쿼리를 만들어서
        PreparedStatement preparedStatement = connection.prepareStatement(
                "insert into userinfo(id, name, password) values(?, ?, ?)");
        preparedStatement.setString(1, user.getId());
        preparedStatement.setString(2, user.getName());
        preparedStatement.setString(3, user.getPassword());

        //실행시키고
        preparedStatement.executeUpdate();

        //지원을 해지한다.
        preparedStatement.close();
        connection.close();
    }
}
