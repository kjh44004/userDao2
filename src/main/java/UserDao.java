import javax.sql.DataSource;
import java.sql.*;

/**
 * Created by MIYEON on 2016-04-20.
 */
public class UserDao {

    private DataSource dataSource;


//    public UserDao(ConnectionMaker connectionMaker){
//        this.dataSource = connectionMaker;
//    }

    public UserDao() {
    }

    public User get(Long id) throws ClassNotFoundException, SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        User user = null;
        try {
            connection = dataSource.getConnection();

            StatementStrategy statementStrategy = new GetUserStatementStrategy(id);
            preparedStatement = statementStrategy.makestatement(connection);

            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {

                user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setPassword(resultSet.getString("password"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return user;
    }

    private PreparedStatement makestatement(Long id, Connection connection, PreparedStatement preparedStatement, String sql) throws SQLException {
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setLong(1, id);
        return preparedStatement;
    }

    public Long add(User user) throws SQLException, ClassNotFoundException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        Long id = null;
        try {
            connection = dataSource.getConnection();

            StatementStrategy statementStrategy = new AddUserStatementStrategy(user);
            preparedStatement = statementStrategy.makestatement(connection);



            preparedStatement.executeUpdate();

            id = getLastInsertId(connection);
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return id;
    }

    private Long getLastInsertId(Connection connection) throws SQLException {
        PreparedStatement preparedStatement2 = connection.prepareStatement(
                "select last_insert_id()");

        ResultSet resultSet = preparedStatement2.executeQuery();
        resultSet.next();

        Long id = resultSet.getLong(1);

        resultSet.close();
        preparedStatement2.close();
        return id;

    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void delete(Long id) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = dataSource.getConnection();

            StatementStrategy statementStrategy = new DeleteUserStatementStrategy(id);
            preparedStatement = statementStrategy.makestatement(connection);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }



//    public void setConnectionMaker(ConnectionMaker connectionMaker) {
//        this.dataSource = connectionMaker;
//    }
}
