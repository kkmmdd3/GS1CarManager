package kr.ac.kaist.resl.gs1_car_manager;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nnlee on 2017-09-14.
 */

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // 새로운 테이블 생성
        db.execSQL("CREATE TABLE repair_shop (" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "name TEXT, " +
                "gln TEXT, " +
                "address TEXT, " +
                "phone_number TEXT, " +
                "gps TEXT, " +
                "create_at TEXT);");

        db.execSQL("CREATE TABLE product (" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "name TEXT, " +
                "category TEXT, " +
                "sgtin TEXT, " +
                "manufacturer TEXT, " +
                "seller TEXT, " +
                "seller_address TEXT, " +
                "seller_call TEXT, " +
                "seller_website TEXT, " +
                "create_at TEXT);");

        db.execSQL("CREATE TABLE car_info (" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "name TEXT, " +
                "_sgtin_vin TEXT, " +
                "model TEXT, " +
                "current_milege TEXT, " +
                "engine_oil TEXT, " +
                "air_conditioner_filter TEXT, " +
                "battery TEXT, " +
                "tires_front TEXT, " +
                "tires_rear TEXT, " +
                "create_at TEXT);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    public void insert_repair_shop(String name, String gln, String address, String phone_number, String gps, String create_at) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("INSERT INTO repair_shop VALUES(null, '" +
                name + "', '" +
                gln + "', '" +
                address + "', '" +
                phone_number + "', '" +
                gps + "', '" +
                create_at + "');");

        db.close();
    }

    public void insert_product(String name, String category, String sgtin, String manufacturer, String seller,
                               String seller_address, String seller_call, String seller_website, String create_at) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("INSERT INTO product VALUES(null, '" +
                name + "', '" +
                category + "', '" +
                sgtin + "', '" +
                manufacturer + "', '" +
                seller + "', '" +
                seller_address + "', '" +
                seller_call + "', '" +
                seller_website + "', '" +
                create_at + "');");

        db.close();
    }

    public void insert_car_info(String name, String _sgtin_vin, String model, String current_milege, String engine_oil,
                                String airconditioner_filter, String battery, String tires_front,
                                String tires_rear, String create_at) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("INSERT INTO car_info VALUES(null, '" +
                name + "', '" +
                _sgtin_vin + "', '" +
                model + "', '" +
                current_milege + "', '" +
                engine_oil + "', '" +
                airconditioner_filter + "', '" +
                battery + "', '" +
                tires_front + "', '" +
                tires_rear + "', '" +
                create_at + "');");
        db.close();
    }

    public void drop(String name) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DROP TABLE if exists " + name + "';");
        db.close();
    }

    public void delete(String name) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM repair_shop WHERE name='" + name + "';");
        db.close();
    }

    public String getResult_repair_shop() {
        SQLiteDatabase db = getReadableDatabase();
        String result = "";

        Cursor cursor = db.rawQuery("SELECT * FROM repair_shop", null);
        while (cursor.moveToNext()) {
            result += "_id는 : "
                    + cursor.getString(0)
                    + "\n name은 : "
                    + cursor.getString(1)
                    + "\n gln는 : "
                    + cursor.getString(2)
                    + "\n address는 : "
                    + cursor.getString(3)
                    + "\n phone_number는 : "
                    + cursor.getString(4)
                    + "\n gps는 : "
                    + cursor.getString(5)
                    + "\n create_at은 : "
                    + cursor.getString(6)
                    + "\n";
        }
        return result;
    }

    public String getResult_car_info() {
        SQLiteDatabase db = getReadableDatabase();
        String result = "";

        Cursor cursor = db.rawQuery("SELECT * FROM car_info", null);
        while (cursor.moveToNext()) {
            result += "_id는 : "
                    + cursor.getString(0)
                    + "\n name은 : "
                    + cursor.getString(1)
                    + "\n _sgtin_vin는 : "
                    + cursor.getString(2)
                    + "\n model은 : "
                    + cursor.getString(3)
                    + "\n current_milege는 : "
                    + cursor.getString(4)
                    + "\n engine_oil은 : "
                    + cursor.getString(5)
                    + "\n airconditioner_filter는 : "
                    + cursor.getString(6)
                    + "\n battery는 : "
                    + cursor.getString(7)
                    + "\n tires_front는 : "
                    + cursor.getString(8)
                    + "\n tires_rear는 : "
                    + cursor.getString(9)
                    + "\n create_at은 : "
                    + cursor.getString(10)
                    + "\n";
        }
        return result;
    }

    public List<String> getResult_current_car_info() {  // 모든 데이터 출력
        SQLiteDatabase db = getReadableDatabase();
        List<String> result = new ArrayList();
        String temp;

        Cursor cursor = db.rawQuery("SELECT * FROM car_info", null);
        while (cursor.moveToNext()) {
            for (int i = 0; i < 11; i++) {
                temp = cursor.getString(i);
                result.set(i, temp);
            }
        }
        cursor.close();
        db.close();
        return result;
    }
}
