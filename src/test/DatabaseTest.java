import org.junit.Test;

import java.sql.Connection;
import com.example.pavel.service.Database;

import static org.junit.Assert.*;

public class DatabaseTest extends Database{

    @Test
    public void getConnectionTest() {
        Connection connection = getConnection();
        assertNotNull("Connection should be successful",connection);
    }
}