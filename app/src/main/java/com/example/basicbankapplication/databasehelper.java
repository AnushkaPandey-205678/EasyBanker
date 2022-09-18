package com.example.basicbankapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class databasehelper extends SQLiteOpenHelper {
    private String TABLE_NAME = "customer_table";
    private String TABLE_NAME1 = "transfers_table";

    public databasehelper(@Nullable Context context) {
        super(context, "customer.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME +" (PHONENUMBER INTEGER PRIMARY KEY ,NAME TEXT,BALANCE DECIMAL,EMAIL VARCHAR,ACCOUNT_NO VARCHAR,IFSC_CODE VARCHAR)");
        db.execSQL("create table " + TABLE_NAME1 +" (TRANSACTIONID INTEGER PRIMARY KEY AUTOINCREMENT,DATE TEXT,FROMNAME TEXT,TONAME TEXT,AMOUNT DECIMAL,STATUS TEXT)");
        db.execSQL("insert into customer_table values(9060578693,'shiva',1067.00,'shiva.02@gmail.com','XXXXXXXXXXXX2351','SBC09876543')");
        db.execSQL("insert into customer_table values(9161789654,'rakhi',582.67,'rakhi.03@gmail.com','XXXXXXXXXXXX2341','ACA98765432')");
        db.execSQL("insert into customer_table values(9234987653,'jyoti',1359.56,'jyoti.04@gmail.com','XXXXXXXXXXXX3412','CAB87654321')");
        db.execSQL("insert into customer_table values(9242325262,'Rachel',1500.01,'tiwarirach.05@gmail.com','XXXXXXXXXXXX4123','ABC76543210')");
        db.execSQL("insert into customer_table values(9667895346,'Benny',2603.48,'benny.06@gmail.com','XXXXXXXXXXXX2345','BCA65432109')");
        db.execSQL("insert into customer_table values(9546794567,'Gauri',945.16,'gauri.07@gmail.com','XXXXXXXXXXXX3452','CAB54321098')");
        db.execSQL("insert into customer_table values(9876598764,'saumya',5936.00,'saumya.08@gmail.com','XXXXXXXXXXXX4523','ABC43210987')");
        db.execSQL("insert into customer_table values(9987699546,'Janice',857.22,'jannu.09@gmail.com','XXXXXXXXXXXX5234','BCA32109876')");
        db.execSQL("insert into customer_table values(9876523457,'Amy',4398.46,'amy.10@gmail.com','XXXXXXXXXXXX3456','CAB21098765')");
        db.execSQL("insert into customer_table values(9807698654,'Prakriti',273.90,'prakriti.01@gmail.com','XXXXXXXXXXXX4563','ABC10987654')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME1);
        onCreate(db);
    }

    public Cursor readalldata(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from customer_table", null);
        return cursor;
    }

    public Cursor readparticulardata(String phonenumber){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from customer_table where phonenumber = " +phonenumber, null);
        return cursor;
    }

    public Cursor readselectuserdata(String phonenumber) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from customer_table except select * from customer_table where phonenumber = " +phonenumber, null);
        return cursor;
    }

    public void updateAmount(String phonenumber, String amount){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("update customer_table set balance = " + amount + " where phonenumber = " +phonenumber);
    }

    public Cursor readtransferdata(){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery("select * from transfers_table", null);
    }

    public boolean insertTransferData(String date,String from_name, String to_name, String amount, String status){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("DATE", date);
        contentValues.put("FROMNAME", from_name);
        contentValues.put("TONAME", to_name);
        contentValues.put("AMOUNT", amount);
        contentValues.put("STATUS", status);
        Long result = db.insert(TABLE_NAME1, null, contentValues);
        if(result == -1){
            return false;
        }else{
            return true;
        }
    }

}
