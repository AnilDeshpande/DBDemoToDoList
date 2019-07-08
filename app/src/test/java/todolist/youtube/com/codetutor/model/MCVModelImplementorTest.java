package todolist.youtube.com.codetutor.model;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.mockito.ArgumentCaptor;


@RunWith(MockitoJUnitRunner.class)
public class MCVModelImplementorTest {

    @Mock

    MCVModelImplementor SUT;

    @Before
    public void setUp() {
        SUT = new MCVModelImplementor();
    }

}