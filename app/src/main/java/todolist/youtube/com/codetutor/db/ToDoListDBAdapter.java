package todolist.youtube.com.codetutor.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by anildeshpande on 3/23/17.
 */

public class ToDoListDBAdapter {

    private static final String TAG=ToDoListDBAdapter.class.getSimpleName();

    private static final String DB_NAME="todolist.db";
    private static final int DB_VERSION=1;

    private static final String TABLE_TODO="table_todo";
    private static final String COLUMN_TODO_ID="task_id";
    private static final String COLUMN_TODO="todo";

    //create table table_todo(task_id integer primary key, todo text not null);
    private static String CREATE_TABLE_TODO="CREATE TABLE"+TABLE_TODO+"("+COLUMN_TODO_ID+" INTEGER PRIMARY KEY, "+COLUMN_TODO+" TEXT NOT NULL)";

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


    //insert,delete,modify methods



    private static class ToDoListDBHelper extends SQLiteOpenHelper{

        public ToDoListDBHelper(Context context, String databaseName, SQLiteDatabase.CursorFactory factory, int dbVersion){
            super(context,databaseName,factory,dbVersion);
        }

        @Override
        public void onConfigure(SQLiteDatabase db) {
            super.onConfigure(db);
            db.setForeignKeyConstraintsEnabled(true);
        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
            sqLiteDatabase.execSQL(CREATE_TABLE_TODO);
        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
            //Not imlemented now
        }
    }

}
