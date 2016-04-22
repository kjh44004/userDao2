import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by MIYEON on 2016-04-22.
 */
public interface StatementStrategy {
    public abstract PreparedStatement makestatement(Connection connection) throws SQLException;
}
