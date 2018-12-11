package a59070103.kmitl.ac.th.mobilefinal.SQLite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;
import android.util.Log;

import a59070103.kmitl.ac.th.mobilefinal.Model.User;

public class MyDB extends SQLiteOpenHelper {
    private SQLiteDatabase myDB;

    public MyDB(@Nullable Context context) {
        super(context, User.DATABASE_NAME, null, User.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_TABLE = String.format("CREATE TABLE %s " +
                        "(%s INTEGER PRIMARY KEY  AUTOINCREMENT, %s TEXT, %s TEXT, %s TEXT, %s TEXT)",
                User.TABLE,
                User.Column.ID,
                User.Column.USERID,
                User.Column.PASSWORD,
                User.Column.NAME,
                User.Column.AGE);
        db.execSQL(CREATE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+User.TABLE);
        onCreate(db);
    }


    public long registerUser(User user) {

        myDB = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(User.Column.USERID, user.getUserid());
        values.put(User.Column.PASSWORD, user.getPassword());
        values.put(User.Column.NAME, user.getName());
        values.put(User.Column.AGE, user.getAge());

        long result = myDB.insert(User.TABLE, null, values);
        myDB.close();

        return result;
    }


    public User checkLogin(User user) {

        myDB = this.getReadableDatabase();

        Cursor cursor = myDB.query(User.TABLE,
                null,
                User.Column.USERID + " = ? AND " +
                        User.Column.PASSWORD + " = ?",
                new String[]{user.getUserid(), user.getPassword()},
                null,
                null,
                null);



        User currentUser = new User();

        if (cursor != null) {
            if (cursor.moveToFirst()) {
                currentUser.setId((int) cursor.getLong(0));
                currentUser.setUserid(cursor.getString(1));
                currentUser.setPassword(cursor.getString(2));
                currentUser.setName(cursor.getString(3));
                currentUser.setAge(cursor.getInt(4));
                myDB.close();
                return currentUser;
            }
        }

        return null;
    }


}
