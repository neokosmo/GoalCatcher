package database.access.object;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.concurrent.atomic.AtomicInteger;

public class DatabaseManager {

    private static DatabaseManager instance;
    private static SQLiteOpenHelper dbHelper;
    private AtomicInteger aiCounter = new AtomicInteger();
    private SQLiteDatabase sdDatabase;

    public static synchronized void initializedInstance(SQLiteOpenHelper helper){
        if (instance == null) {
            instance = new DatabaseManager();
            dbHelper = helper;
        }
    }

    public static synchronized DatabaseManager getInstance(){
        if (instance == null) {
            throw new IllegalStateException(DatabaseManager.class.getSimpleName() + " is not initialized");
        }
        return  instance;
    }

    public synchronized void openDatabase(){
        if (aiCounter.incrementAndGet() == 1) {
            sdDatabase = dbHelper.getWritableDatabase();
        }
    }

    public  synchronized void closeDatabase(){
        if (aiCounter.decrementAndGet() == 0) {
            sdDatabase.close();
        }
    }

    public Cursor getGoalDetails(String query){
        return sdDatabase.rawQuery(query, null);
    }

    public boolean insertGoal(String tableName, ContentValues cvContent){
        long returnVal = -1;
        try {
            returnVal = sdDatabase.insert(tableName,null,cvContent);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return returnVal != -1;
    }

    public boolean deleteGoal(String tableName, String[] goalId){
        try {
            sdDatabase.delete(tableName,GoalCatcherDAO.GOAL_ID + "=?",goalId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }
}
