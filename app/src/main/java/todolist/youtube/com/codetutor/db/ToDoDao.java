package todolist.youtube.com.codetutor.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import todolist.youtube.com.codetutor.bean.ToDo;

@Dao
public interface ToDoDao {

    @Insert
    public void insert(ToDo toDo);

    @Insert
    public void insertAll(ToDo... toDos);

    public List<ToDo> getAll();

    @Delete
    public void delete(ToDo toDo);

    @Update
    public void update(ToDo toDo);

    @Query("SELECT * FROM ToDo where id = :id")
    public ToDo findById(int id);
}
