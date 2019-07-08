package todolist.youtube.com.codetutor.model;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import todolist.youtube.com.codetutor.model.db.ToDoListDBAdapter;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.CoreMatchers.any;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.mockito.ArgumentCaptor.*;


@RunWith(MockitoJUnitRunner.class)
public class MCVModelImplementorTest {

    public static final String TODOITEM = "todoitem";
    public static final String PLACE = "place";

    @Mock
    ToDoListDBAdapter mockToDoListDBAdapter;

    MCVModelImplementor SUT;

    @Before
    public void setUp() {
        SUT = new MCVModelImplementor(mockToDoListDBAdapter);
    }

    //addToDoItem invokes dbadapters insert method
    @Test
    public void addToDoItems_shouldDelegate_to_dbAdapter_insertMethod() throws Exception{
        ArgumentCaptor<String> argumentCaptor = ArgumentCaptor.forClass(String.class);
        SUT.addToDoItem(TODOITEM, PLACE);
        verify(mockToDoListDBAdapter,times(1)).insert(argumentCaptor.capture(), argumentCaptor.capture());
    }
    //addToDoItem returns addedValues

    //AddToDoItem throws exception when some error is caused

}