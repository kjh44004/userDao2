import javax.sql.DataSource;
import java.sql.*;

/**
 * Created by MIYEON on 2016-04-20.
 */
public class UserDao {
    private JdbcContext jdbcContext;

    public void setJdbcContext(JdbcContext jdbcContext) {
        this.jdbcContext = jdbcContext;
    }

    public UserDao() {
    }

    public User get(Long id) throws ClassNotFoundException, SQLException {
        StatementStrategy statementStrategy = new GetUserStatementStrategy(id);
        return jdbcContext.jdbcContextWithStatementStrategyForQuery(statementStrategy);
    }

    public Long add(User user) throws SQLException, ClassNotFoundException {
        StatementStrategy statementStrategy = new AddUserStatementStrategy(user);
        return jdbcContext.jdbcContextWithStatementStrategyForInsert(statementStrategy);
    }

    public void delete(Long id) throws SQLException {
        StatementStrategy statementStrategy = new DeleteUserStatementStrategy(id);
        jdbcContext.jdbcContextWithStatementStrategyForUpdate(statementStrategy);
    }
}
