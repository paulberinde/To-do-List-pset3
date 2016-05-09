package paulberinde_tampanariu.paulberinde_pset3;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHandler extends SQLiteOpenHelper {

    // Database Name
    public static final String DB_NAME = "TODO.DB";

    // Database version
    static final int DB_VERSION = 1;

    // Table Name
    public static final String TABLE_NAME = "TASKS";

    // Table Columns
    public static final String _ID = "_id";
    public static final String TASK = "task";

    // Table Creation
    private static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "(" + _ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT," + TASK +")";

    public DBHandler(Context context) {
        super(context, DB_NAME, null,DB_VERSION);
    }



    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int OldVersion, int NewVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

}