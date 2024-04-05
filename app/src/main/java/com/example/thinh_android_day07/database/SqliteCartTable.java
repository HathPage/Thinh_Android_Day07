package com.example.thinh_android_day07.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.thinh_android_day07.models.CartModel;
import com.example.thinh_android_day07.models.Price;

public class SqliteCartTable extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "cart_db.db";
    public static final String CART_TABLE = "cart_db";
    public static final String ID_COLUMN = "id";
    public static final String ONION_QUANTITY_COLUMN = "onion";
    public static final String LEMON_QUANTITY_COLUMN = "lemon";
    public static final String POTATO_QUANTITY_COLUMN = "potato";
    public static final String CUCUMBER_QUANTITY_COLUMN = "cucumber";
    public static final String TOTAL_PRICE_COLUMN = "total_price";


    public static final String CART_TABLE2 = "price";
    public static final String ID_COLUMN2 = "id";
    public static final String ONION_PRICE_COLUMN = "onion_price";
    public static final String LEMON_PRICE_COLUMN = "lemon_price";
    public static final String POTATO_PRICE_COLUMN = "potato_price";
    public static final String CUCUMBER_PRICE_COLUMN = "cucumber_price";
    private Context mContext;

    public static final int VERSION = 5;
    public SqliteCartTable(@Nullable Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE " + CART_TABLE + " ("
                + ID_COLUMN + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,"
                + ONION_QUANTITY_COLUMN + " REAL,"
                + LEMON_QUANTITY_COLUMN + " REAL,"
                + POTATO_QUANTITY_COLUMN + " REAL,"
                + CUCUMBER_QUANTITY_COLUMN + " REAL,"
                + TOTAL_PRICE_COLUMN + " REAL);";
        db.execSQL(sql);
        String sql2 = "CREATE TABLE " + CART_TABLE2 + " ("
                + ID_COLUMN2 + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,"
                + ONION_PRICE_COLUMN + " REAL,"
                + LEMON_PRICE_COLUMN + " REAL,"
                + POTATO_PRICE_COLUMN + " REAL,"
                + CUCUMBER_PRICE_COLUMN + " REAL);";
        db.execSQL(sql2);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public void addCart(CartModel cart) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ONION_QUANTITY_COLUMN, cart.getOnion());
        contentValues.put(LEMON_QUANTITY_COLUMN, cart.getLemon());
        contentValues.put(POTATO_QUANTITY_COLUMN, cart.getPotato());
        contentValues.put(CUCUMBER_QUANTITY_COLUMN, cart.getCucumber());
        contentValues.put(TOTAL_PRICE_COLUMN, cart.getTotalPrice());
        db.insert(CART_TABLE, null, contentValues);
        db.close();
    }
    public void addPrice(double onion_price, double lemon_price, double potato_price, double cucumber_price) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ONION_PRICE_COLUMN, onion_price);
        contentValues.put(LEMON_PRICE_COLUMN, lemon_price);
        contentValues.put(POTATO_PRICE_COLUMN, potato_price);
        contentValues.put(CUCUMBER_PRICE_COLUMN, cucumber_price);
        db.insert(CART_TABLE2, null, contentValues);
        db.close();
    }
    public Price getPrice(int id){
        String sql = "SELECT*FROM " + CART_TABLE2 +" WHERE ID = "+ id;
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        cursor.moveToFirst();
        Price price = new Price();
        int idIndex = cursor.getColumnIndex(ID_COLUMN2);
        if (idIndex >= 0) {
            int id_id = cursor.getInt(idIndex);
            price.setId(id_id);
        }
        double onion = cursor.getDouble(1);
        price.setOnion(onion);
        double lemon = cursor.getDouble(2);
        price.setLemon(lemon);
        double potato = cursor.getDouble(3);
        price.setPotato(potato);
        double cucumber = cursor.getDouble(4);
        price.setCucumber(cucumber);
        return price;
    }
}
