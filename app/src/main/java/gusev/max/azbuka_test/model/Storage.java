package gusev.max.azbuka_test.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import gusev.max.azbuka_test.model.data.Product;

/**
 * Created by v on 10/10/2017.
 */

public class Storage extends SQLiteOpenHelper {

    private static final String TAG = Storage.class.getSimpleName();

    public Storage(Context context) {
        super(context, "azbuka_db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            db.execSQL(CREATE_TABLE);
        } catch (SQLException e) {
            Log.d(TAG, e.getMessage());
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_TABLE);
        onCreate(db);
    }

    public void addProduct(Product product) {
        SQLiteDatabase db = this.getWritableDatabase();
        if (!ifExists(product, db)) {
            ContentValues values = new ContentValues();
            values.put(ID, product.getId());
            values.put(NAME, product.getName());
            values.put(DESCRIPTION, product.getDescription());
            values.put(PRICE, product.getPrice());
            values.put(IMG_URL, product.getImgUrl());

            try {
                db.insert(TABLE_NAME, null, values);
            } catch (SQLException e) {
                Log.d(TAG, e.getMessage());
            }
        }
        db.close();
    }

    public List<Product> getProductsByPriceDESC() {
        List<Product> productList;
        productList = getProducts(SELECT_BY_PRICE_DESC_QUERY);
        return productList;
    }

    public List<Product> getProductsByPrice() {
        List<Product> productList;
        productList = getProducts(SELECT_BY_PRICE_QUERY);
        return productList;
    }

    public List<Product> getSavedProducts() {
        List<Product> productList;
        productList = getProducts(SELECT_QUERY);
        return productList;
    }

    private List<Product> getProducts(String query) {
        List<Product> productList = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();

        try {
            Cursor cursor = db.rawQuery(query, null);
            if (cursor != null) {
                if (cursor.getCount() > 0) {
                    if (cursor.moveToFirst()) {
                        do {
                            Product product = new Product();
                            product.setId(cursor.getInt(cursor.getColumnIndex(ID)));
                            product.setName(cursor.getString(cursor.getColumnIndex(NAME)));
                            product.setDescription(cursor.getString(cursor.getColumnIndex(DESCRIPTION)));
                            product.setPrice(cursor.getString(cursor.getColumnIndex(PRICE)));
                            product.setImgUrl(cursor.getString(cursor.getColumnIndex(IMG_URL)));

                            productList.add(product);

                        } while (cursor.moveToNext());
                    }
                }
                cursor.close();
            }
        } catch (SQLException e) {
            Log.d(TAG, e.getMessage());
        }
        return productList;
    }

    private boolean ifExists(Product product, SQLiteDatabase db) {
        Cursor cursor;
        String checkQuery = "SELECT " + ID + " FROM " + TABLE_NAME + " WHERE " + ID + "= '" + product.getId() + "'";
        cursor = db.rawQuery(checkQuery, null);
        boolean exists = (cursor.getCount() > 0);
        cursor.close();
        return exists;
    }


    private static final String ID = "id";
    private static final String NAME = "name";
    private static final String DESCRIPTION = "description";
    private static final String PRICE = "price";
    private static final String IMG_URL = "imgUrl";
    private static final String TABLE_NAME = "azbuka";


    private static final String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;
    private static final String SELECT_QUERY = "SELECT * FROM " + TABLE_NAME;
    private static final String SELECT_BY_PRICE_DESC_QUERY = "SELECT * FROM " + TABLE_NAME + " ORDER BY " + PRICE + " DESC";
    private static final String SELECT_BY_PRICE_QUERY = "SELECT * FROM " + TABLE_NAME + " ORDER BY " + PRICE;


    private static final String CREATE_TABLE = "create table " + TABLE_NAME + "(" +
            ID + " integer primary key autoincrement not null," +
            NAME + " text not null," +
            DESCRIPTION + " text not null," +
            PRICE + " text not null," +
            IMG_URL + " text not null)";
}
