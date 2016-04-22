import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by MIYEON on 2016-04-22.
 */
public class DeleteUserStatementStrategy implements StatementStrategy {
    private Long id;

    public DeleteUserStatementStrategy(Long id){
        this.id = id;
    }

    @Override
    public PreparedStatement makestatement(Connection connection) throws SQLException {
        String sql = "delete from userinfo where id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setLong(1, (Long) id);
        return preparedStatement;
    }
}
