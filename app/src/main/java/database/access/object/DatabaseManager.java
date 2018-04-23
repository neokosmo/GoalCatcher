package database.access.object;

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
}
