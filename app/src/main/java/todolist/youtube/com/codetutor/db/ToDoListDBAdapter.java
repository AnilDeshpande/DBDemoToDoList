package todolist.youtube.com.codetutor.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import todolist.youtube.com.codetutor.bean.ToDo;
import todolist.youtube.com.codetutor.exception.ToDoNotFoundException;

/**
 * Created by anildeshpande on 3/23/17.
 */

public class ToDoListDBAdapter {

    private static final String TAG=ToDoListDBAdapter.class.getSimpleName();

    private static final String DB_NAME="todolist.db";
    private static final int DB_VERSION=2;

    private static final String TABLE_TODO="table_todo";
    private static final String COLUMN_TODO_ID="task_id";
    private static final String COLUMN_TODO="todo";
    private static final String COLUMN_PLACE="place";

    //create table table_todo(task_id integer primary key, todo text not null);
    private static String CREATE_TABLE_TODO="CREATE TABLE "+TABLE_TODO+"("+COLUMN_TODO_ID+" INTEGER PRIMARY KEY, "+COLUMN_TODO+" TEXT NOT NULL, "+
            COLUMN_PLACE+ " TEXT )";

    private Context context;
    private SQLiteDatabase  sqLliteDatabase;
    private static ToDoListDBAdapter toDoListDBAdapterInstance;


    private ToDoListDBAdapter(Context context){
        this.context=context;
        sqLliteDatabase=new ToDoListDBHelper(this.context,DB_NAME,null,DB_VERSION).getWritableDatabase();
    }

    public  static ToDoListDBAdapter getToDoListDBAdapterInstance(Context context){
        if(toDoListDBAdapterInstance==null){
            toDoListDBAdapterInstance=new ToDoListDBAdapter(context);
        }
        return toDoListDBAdapterInstance;
    }

    //insert,delete,modify,query methods

    public boolean insert(String toDoItem, String place) throws Exception{
        ContentValues contentValues=new ContentValues();
        contentValues.put(COLUMN_TODO,toDoItem);
        contentValues.put(COLUMN_PLACE,place);

        boolean success = sqLliteDatabase.insert(TABLE_TODO,null,contentValues)>0;

        if(!success){
            throw new Exception("Something went wrong!!!");
        }

        return success;
    }

    public boolean delete(int taskId) throws Exception{
        boolean success = sqLliteDatabase.delete(TABLE_TODO, COLUMN_TODO_ID+" = "+taskId,null)>0;
        if(!success){
            throw new ToDoNotFoundException("Id is wrong");
        }
       return  success;
    }

    public boolean modify(int taskId, String newToDoItem) throws Exception{
        ContentValues contentValues=new ContentValues();
        contentValues.put(COLUMN_TODO,newToDoItem);
        boolean success = sqLliteDatabase.update(TABLE_TODO,contentValues, COLUMN_TODO_ID+" = "+taskId,null)>0;
        if(!success){
            throw new ToDoNotFoundException("Id is wrong");
        }
       return success;
    }

    public List<ToDo> getAllToDos() throws Exception{
        List<ToDo> toDoList=new ArrayList<ToDo>();

        Cursor cursor=sqLliteDatabase.query(TABLE_TODO,new String[]{COLUMN_TODO_ID,COLUMN_TODO, COLUMN_PLACE},null,null,null,null,null,null);

        if(cursor!=null &cursor.getCount()>0){
            while(cursor.moveToNext()){
                ToDo toDo=new ToDo(cursor.getLong(0),cursor.getString(1), cursor.getString(2));
                toDoList.add(toDo);

            }
        } else {
          throw new Exception("List Emptry");
        }
        cursor.close();
        return toDoList;
    }

    public ToDo getToDo(long id) throws Exception{
        ToDo toDo = null;
        Cursor cursor = sqLliteDatabase.query(TABLE_TODO,new String[]{COLUMN_TODO_ID,COLUMN_TODO, COLUMN_PLACE},COLUMN_TODO_ID+" = "+id,null,null,null,null,null);
        if(cursor!=null &cursor.getCount()>0){
            cursor.moveToFirst();
            toDo=new ToDo(cursor.getLong(0),cursor.getString(1), cursor.getString(2));
        }else {
            throw new Exception("No Task found");
        }
        return toDo;
    }

    private static class ToDoListDBHelper extends SQLiteOpenHelper{

        public ToDoListDBHelper(Context context, String databaseName, SQLiteDatabase.CursorFactory factory, int dbVersion){
            super(context,databaseName,factory,dbVersion);
        }

        @Override
        public void onConfigure(SQLiteDatabase db) {
            super.onConfigure(db);
            db.setForeignKeyConstraintsEnabled(true);
            Log.i(TAG,"Inside onConfigure");
        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
            sqLiteDatabase.execSQL(CREATE_TABLE_TODO);
            Log.i(TAG,"Inside onCreate");

        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase,
                              int oldVersion, int newVersion) {
            //Not implemented now

            switch (oldVersion){
                case 1: sqLiteDatabase.execSQL("ALTER TABLE "+TABLE_TODO+ " ADD COLUMN "+COLUMN_PLACE+" TEXT");break;
                default: break;
            }
            Log.i(TAG,"Inside onUpgrade");
        }
    }

}
