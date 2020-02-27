package todolist.youtube.com.codetutor.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import todolist.youtube.com.codetutor.bean.ToDo;

@Dao
public interface ToDosDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(ToDo toDo);

    @Delete
    void delete(ToDo... toDos);

    @Update
    void updateToDo(ToDo... oldToDo);

    @Query("SELECT * from table_todos")
    LiveData<List<ToDo>> getAllToDos();
}
