package paulberinde_tampanariu.paulberinde_pset3;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;


// Class for CRUD Operations
public class CRUDOperations {

    private DBHandler dbHandler;

    private Context context;

    private SQLiteDatabase database;

    public CRUDOperations(Context c) {
        context = c;
    }

    public CRUDOperations open() throws SQLException {
        dbHandler = new DBHandler(context);
        database = dbHandler.getWritableDatabase();
        return this;
    }
    // Definition for DB close
    public void close() {
        dbHandler.close();
    }

    // Definition for DB insertion
    public void insert(String input) {
        ContentValues contentValue = new ContentValues();
        contentValue.put(DBHandler.TASK, input);

        database.insert(DBHandler.TABLE_NAME, null, contentValue);
    }
    // Definition for cursor used to fetch data to list
    public Cursor fetch() {
        String[] columns = new String[]{DBHandler._ID, DBHandler.TASK};
        Cursor cursor = database.query(DBHandler.TABLE_NAME, columns, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    // Definition for deletion
    public void delete(long _id) {
        database.delete(DBHandler.TABLE_NAME, DBHandler._ID + "=" + _id, null);
    }
}
 