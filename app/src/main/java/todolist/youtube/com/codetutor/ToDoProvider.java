package todolist.youtube.com.codetutor;

import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import todolist.youtube.com.codetutor.db.ToDoListDBAdapter;

/**
 * Created by anildeshpande on 4/26/17.
 */

public class ToDoProvider extends ContentProvider {

    public static final String AUTHORITY="todolist.youtube.com.codetutor";

    public static final String PATH_TODO_LIST ="TODO_LIST";
    public static final String PATH_TODO_PLACE ="TODO_LIST_FROM_PLACE";
    public static final String PATH_TODO_COUNT ="TODOS_COUNT";

    public static final Uri CONTENT_URI_1=Uri.parse("content://"+AUTHORITY+"/"+ PATH_TODO_LIST);
    public static final Uri CONTENT_URI_2=Uri.parse("content://"+AUTHORITY+"/"+ PATH_TODO_PLACE);
    public static final Uri CONTENT_URI_3=Uri.parse("content://"+AUTHORITY+"/"+ PATH_TODO_COUNT);

    public static final int TODOS_LIST =1;
    public static final int TODOS_FROM_SPECIFIC_PLACE=2;
    public static final int TODOS_COUNT =3;

    private static final UriMatcher MATCHER=new UriMatcher(UriMatcher.NO_MATCH);

    static {
        MATCHER.addURI(AUTHORITY, PATH_TODO_LIST, TODOS_LIST);
        MATCHER.addURI(AUTHORITY, PATH_TODO_PLACE,TODOS_FROM_SPECIFIC_PLACE);
        MATCHER.addURI(AUTHORITY, PATH_TODO_COUNT, TODOS_COUNT);
    }

    public static final String MIME_TYPE_1 = ContentResolver.CURSOR_DIR_BASE_TYPE+"/"+ "vnd.com.codetutore.todos";
    public static final String MIME_TYPE_2 = ContentResolver.CURSOR_DIR_BASE_TYPE+"/"+ "vnd.com.codetutore.todos.place";
    public static final String MIME_TYPE_3 = ContentResolver.CURSOR_ITEM_BASE_TYPE+"/"+ "vnd.com.codetutore.todocount";

    private ToDoListDBAdapter toDoListDBAdapter;

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        switch (MATCHER.match(uri)){
            case TODOS_LIST: return MIME_TYPE_1;
            case TODOS_FROM_SPECIFIC_PLACE: return MIME_TYPE_2;
            case TODOS_COUNT: return MIME_TYPE_3;
        }
        return null;
    }



    @Override
    public boolean onCreate() {
        toDoListDBAdapter=ToDoListDBAdapter.getToDoListDBAdapterInstance(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] strings, @Nullable String s, @Nullable String[] strings1, @Nullable String s1) {

        Cursor cursor=null;
        switch (MATCHER.match(uri)){
            case TODOS_LIST: cursor=toDoListDBAdapter.getCursorsForAllToDos();break;
            case TODOS_FROM_SPECIFIC_PLACE: cursor=toDoListDBAdapter.getCursorForSpecificPlace(strings1[0]);break;
            case TODOS_COUNT:cursor=toDoListDBAdapter.getCount();break;
            default:cursor=null; break;
        }
        return cursor;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) throws UnsupportedOperationException{
        Uri returnUri = null;
        switch (MATCHER.match(uri)){
            case TODOS_LIST: returnUri= insertToDo(uri,contentValues);break;
            default: new UnsupportedOperationException("insert operation not supported"); break;
        }

        return returnUri ;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String s, @Nullable String[] strings) throws UnsupportedOperationException{
        int updateCount=-1;
        switch (MATCHER.match(uri)){
            case TODOS_LIST: updateCount=update(contentValues,s,strings);break;
            default:new UnsupportedOperationException("insert operation not supported"); break;
        }
        return updateCount;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String s, @Nullable String[] strings) throws UnsupportedOperationException{
        int deleteCount=-1;
        switch (MATCHER.match(uri)){
            case TODOS_LIST: deleteCount= delete(s,strings);break;
            default:new UnsupportedOperationException("delete operation not supported"); break;
        }
        return deleteCount;
    }

    private int update(ContentValues contentValues, String whereCluase, String [] strings){
        return toDoListDBAdapter.update(contentValues,whereCluase,strings);
    }


    private Uri insertToDo(Uri uri, ContentValues contentValues){
       long id = toDoListDBAdapter.insert(contentValues);
        getContext().getContentResolver().notifyChange(uri,null);
        return Uri.parse("content://"+AUTHORITY+"/"+ PATH_TODO_LIST +"/"+id);
    }

    private int delete(String whereClause, String [] whereValues){
        return toDoListDBAdapter.delete(whereClause,whereValues);
    }

}
