package com.example.pratiksha.student_management.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.jar.Attributes;

public class DBhelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "student.sqlite";
    private static final int DB_VERSION = 1;

    //Define all columns name of tables and all should be in String.

    private static final String STUD_TABLE = "student";
    private static final String ID = "id";
    private static final String NAME = "name";
    private static final String ADDRESS = "address";
    private static final String PHONE = "phone";
    private static final String BRANCH = "branch";
    private static final String COURSE = "course";

    public DBhelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query = "create table "+STUD_TABLE+" ( "+ID+ " integer primary key autoincrement, "+
                " "+NAME+ " text, "+ADDRESS+ " text, "+PHONE+ " text, "+BRANCH+ " text, "+COURSE+ " text )";
        Log.e("DBQuery","======"+query);

        sqLiteDatabase.execSQL(query);
    }

    public boolean addStudents(Student student){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(NAME,student.getName());
        values.put(ADDRESS,student.getAddress());
        values.put(PHONE,student.getPhone());
        values.put(BRANCH,student.getBranch());
         values.put(COURSE,student.getCourse());

        long no = db.insert(STUD_TABLE,null,values);

        db.close();

        if(no == 0){
            return false;
        }
        else {
            return true;
        }
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean updateStudent(Student student,int id){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(NAME,student.getName());
        values.put(ADDRESS,student.getAddress());
        values.put(PHONE,student.getPhone());
        values.put(BRANCH,student.getBranch());
        values.put(COURSE,student.getCourse());

        long no = db.update(STUD_TABLE, values,ID + " = "+id,null);

        db.close();

        if(no == 0){
            return false;
        }
        else {
            return true;
        }
    }


    public List<Student> getAllStudent(){
        List<Student> students = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();

        String query = "select * from "+STUD_TABLE;

        Cursor cursor = db.rawQuery(query,null);

        while (cursor.moveToNext()){
                    String name = cursor.getString(cursor.getColumnIndex(NAME));
                    String address = cursor.getString(cursor.getColumnIndex(ADDRESS));
                    String phone = cursor.getString(cursor.getColumnIndex(PHONE));
                    String branch = cursor.getString(cursor.getColumnIndex(BRANCH));
                    String course = cursor.getString(cursor.getColumnIndex(COURSE));
                    int id = cursor.getInt(cursor.getColumnIndex(ID));

                    Student student = new Student(name,address,phone,branch,course);
                    student.setId(id);
                    students.add(student);

                }

                if(cursor!=null && !cursor.isClosed()){
                    cursor.close();
                }
                db.close();
        return students;
    }


    //Search method
    public Student getStudent(String name){
        Student student = null;
        SQLiteDatabase db = getReadableDatabase();

        String query = "select * from "+STUD_TABLE+ " where "+NAME+ " = '"+name+ "'";

        Cursor cursor = db.rawQuery(query,null);

        if(cursor.moveToFirst()){
            int id = cursor.getInt(cursor.getColumnIndex(ID));
            String address = cursor.getString(cursor.getColumnIndex(ADDRESS));
            String phone = cursor.getString(cursor.getColumnIndex(PHONE));
            String branch = cursor.getString(cursor.getColumnIndex(BRANCH));
            String course = cursor.getString(cursor.getColumnIndex(COURSE));

            student = new Student(name, address, phone, branch,course);
            student.setId(id);

        }
           return student;
    }

    public boolean deleteStudent(int id){
        SQLiteDatabase db =getWritableDatabase();

        long row = db.delete(STUD_TABLE, ID + " = " + id, null);

        if (row > 0) {
            return true;
        }
        else {
            return false;
        }
    }

}
