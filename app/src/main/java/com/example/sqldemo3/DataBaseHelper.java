package com.example.sqldemo3;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DataBaseHelper extends SQLiteOpenHelper {

    public  static final String CUSTOMER_TABLE = "CUSTOMER_TABLE";
    public  static final  String COLUMN_ID = "ID";

    public  static final String COLUMN_CUSTOMER_NAME="CUSTOMER_NAME";
    public  static final String COLUMN_AGE="CUSTOMER_AGE";
    public  static final String COLUMN_ACTIVE_CUSTOMER="ACTIVE_CUSTOMER";


    public DataBaseHelper(@Nullable Context context) {
        super(context, "customer.db", null, 5);
    }
    //this is called the first time database accessed. There should be code in here create new database.


    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(createTableStatement);

    }
//this is called if the database version number change.
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean addOne(CustomerModel customerModel){
        SQLiteDatabase db =this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(COLUMN_CUSTOMER_NAME, customerModel.getName().toString());
        cv.put(COLUMN_AGE,customerModel.getAge());
        cv.put(COLUMN_ACTIVE_CUSTOMER,customerModel.isActive());
        long insert = db.insert(CUSTOMER_TABLE, null, cv);
        if (insert==-1){
            return false;
        }else {
            return true;

        }
    }
    public boolean delete(CustomerModel customerModel){

        SQLiteDatabase db=this.getReadableDatabase();
        String queryString = "DELETE FROM " + CUSTOMER_TABLE + " WHERE " +COLUMN_ID + " = "  + customerModel.getId();
        Cursor cursor = db.rawQuery(queryString, null);
        if(cursor.moveToFirst()){
            return true;
        }else{
            return false;
        }
    }
    public List<CustomerModel> getEveryone(){
        List<CustomerModel> returnList= new ArrayList<>();
        String queryString = "SELECT * FROM " + CUSTOMER_TABLE;
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString,null);

        if(cursor.moveToFirst()){
           //loop through the (result set) and create new customer objects.Put into the result list.
            do{
                int customerID=cursor.getInt(0);
                String customerName=cursor.getString(1);
                int customerAge=cursor.getInt(2);
                boolean customerActive=cursor.getInt(3) == 1 ? true: false;
                CustomerModel newCustomer = new CustomerModel(customerID,customerName,customerAge,customerActive);

                returnList.add(newCustomer);

            }while (cursor.moveToNext());

            {

            }
        }else{
        // failure. do not add anything to the list.
        }
        //close both the cursor and the db when done.
        cursor.close();
        db.close();
        return returnList;

    }
    String createTableStatement=
            "CREATE TABLE " + CUSTOMER_TABLE + "("
            + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_CUSTOMER_NAME + " TEXT,"
            + COLUMN_AGE + " INT,"
            + COLUMN_ACTIVE_CUSTOMER + " BOOLEN)";
}
