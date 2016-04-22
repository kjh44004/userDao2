import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by MIYEON on 2016-04-22.
 */
public class AddUserStatementStrategy implements StatementStrategy {
    private User user;

    public AddUserStatementStrategy(User user){
        this.user = user;
    }
    @Override
    public PreparedStatement makestatement(Connection connection) throws SQLException {
        PreparedStatement preparedStatement;
        String sql = "insert into userinfo(name, password) values(?, ?)";
        preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setString(1, user.getName());
        preparedStatement.setString(2, user.getPassword());
        return preparedStatement;
    }
}
