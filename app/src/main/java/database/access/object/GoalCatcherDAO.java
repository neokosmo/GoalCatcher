package database.access.object;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class GoalCatcherDAO extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "database_goal";
    private static final int DATABASE_VERSION = 1;

    private static final String TBL_GOAL = "goal_table";
    private static final String GOAL_ID = "id";
    private static final String GOAL_NAME = "goal_name";
    private static final String GOAL_DATE_TIME = "goal_date_time";

    private final String TABLE_GOAL_DROP = "DROP TABLE IF EXIST " + TBL_GOAL;

    private final String TABLE_GOAL_CREATE = "CREATE TABLE " + TBL_GOAL + " (" +
                                        GOAL_ID + " INTEGER PRIMARY KEY," +
                                        GOAL_NAME + " TEXT," +
                                        GOAL_DATE_TIME + "DATETIME)";

    public GoalCatcherDAO(Context context){
        super(context,DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_GOAL_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void dropDatabase(SQLiteDatabase db){
        db.execSQL(TABLE_GOAL_DROP);
        db.execSQL(TABLE_GOAL_CREATE);
    }
}
