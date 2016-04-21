import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by MIYEON on 2016-04-21.
 */
public interface ConnectionMaker {
    Connection getConnection() throws ClassNotFoundException, SQLException;
}
