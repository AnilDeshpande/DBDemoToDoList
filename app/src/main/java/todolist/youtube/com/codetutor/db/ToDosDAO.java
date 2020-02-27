package todolist.youtube.com.codetutor.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import todolist.youtube.com.codetutor.bean.ToDo;

@Dao
public interface ToDosDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(ToDo toDo);

    @Query("DELETE from table_todos where id = :toDo")
    void delete(ToDo toDo);

    @Query("")
    void updateToDo(ToDo oldToDo, ToDo newToDo);

    @Query("SELECT * from table_todos")
    LiveData<List<ToDo>> getAllToDos();
}
