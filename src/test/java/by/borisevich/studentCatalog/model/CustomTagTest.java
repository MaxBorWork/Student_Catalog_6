package by.borisevich.studentCatalog.model;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import javax.servlet.jsp.JspContext;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.*;
import javax.servlet.jsp.JspWriter;

import java.io.IOException;

import static org.junit.Assert.*;

public class CustomTagTest {
    private CustomTag customTag;
    private JspWriter jspWriter;
    private JspContext context;

    @Before
    public void setUp() throws Exception {
        customTag = new CustomTag();
        jspWriter = Mockito.mock(JspWriter.class);
        context = Mockito.mock(JspContext.class);
        Mockito.doReturn(jspWriter).when(context).getOut();
        customTag.setJspContext(context);
    }

    @Test
    public void doTag() throws IOException, JspException {
        customTag.doTag();
        Mockito.verify(jspWriter, Mockito.times(1)).println("Добро пожаловать!");
    }
}