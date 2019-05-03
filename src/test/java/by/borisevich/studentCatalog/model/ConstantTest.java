package by.borisevich.studentCatalog.model;

import by.borisevich.studentCatalog.controller.AddStudentServlet;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.*;

public class ConstantTest {
    private Logger logger;

    @Before
    public void setUp() throws Exception {
        logger = Logger.getLogger(Constant.class);
    }

    @Test
    public void loggerConfig() {
        Constant.loggerConfig(logger);
        assertEquals(logger.getLevel(), Level.ALL);
    }
}