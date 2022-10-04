import com.example.pavel.entity.Person;
import com.example.pavel.service.Database;
import com.example.pavel.service.PersonService;
import org.junit.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

public class PersonServiceTest extends Database {
    private PersonService ps = new PersonService();

    private Person test = new Person("Test", "Testov");
    private Person test2 = new Person("Test1", "Testov1");

    @BeforeAll
    public static void setUp() {
        Connection connection = getConnection();
        try {
            connection.setAutoCommit(false);
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


        @AfterAll
    public static void teardown() {
        Connection connection = getConnection();
        try {
            connection.setAutoCommit(true);
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



    @Test
    public void add() {
        Person expected = test;
        ps.add(expected);
        Person actual = ps.getById(test.getId());
        assertEquals(actual.getName(),expected.getName());
        assertEquals(actual.getSecondName(),expected.getSecondName());
        ps.remove(actual);
    }

    @Test
    public void getAll() {
        List<Person> expectedList = new ArrayList<>();
        ps.add(test);
        ps.add(test2);

        expectedList.add(test);
        expectedList.add(test2);

        assertEquals(expectedList.toString(),ps.getAll().toString());

        ps.remove(test);
        ps.remove(test2);
    }

    @Test
    public void getById() {
        ps.add(test);
        Person actual = ps.getById(test.getId());
        assertEquals(test.getName(),actual.getName());
        assertEquals(test.getSecondName(),actual.getSecondName());
        ps.remove(test);
    }

    @Test
    public void update() {
        ps.add(test);
        test.setSecondName("Testovich");
        ps.update(test);
        assertEquals(test.getName(), ps.getById(test.getId()).getName());
        assertEquals(test.getSecondName(), ps.getById(test.getId()).getSecondName());
        ps.remove(test);
    }

    @Test
    public void remove() {
        ps.add(test);
        ps.remove(test);
        assertEquals(ps.getAll(), Collections.EMPTY_LIST);
    }
}