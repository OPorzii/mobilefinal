package a59070103.kmitl.ac.th.mobilefinal.Model;

import android.provider.BaseColumns;

public class User {


    private int id;
    private String userid;
    private String password;
    private String name;
    private int age;


    public static final String DATABASE_NAME = "mydb.db";
    public static final int DATABASE_VERSION = 5;
    public static final String TABLE = "user";



    public class Column {
        public static final String ID = BaseColumns._ID;
        public static final String USERID = "userid";
        public static final String PASSWORD = "password";
        public static final String NAME = "name";
        public static final String AGE = "age";
    }

    public User() { }

    public User(String userid, String password) {
        this.userid = userid;
        this.password = password;
    }

    public User(String userid, String password, String name, int age) {
        this.userid = userid;
        this.password = password;
        this.name = name;
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
