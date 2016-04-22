import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by MIYEON on 2016-04-22.
 */
public class GetUserStatementStrategy implements StatementStrategy {
    private Long id;

    public GetUserStatementStrategy(Long id) {
        this.id = id;
    }

    @Override
    public PreparedStatement makestatement(Connection connection) throws SQLException {
        PreparedStatement preparedStatement;
        String sql = "select * from userinfo where id = ?";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setLong(1, (Long) id);
        return preparedStatement;
    }
}
