package ly.generalassemb.drewmahrt.shoppinglistwithdetailview;

import android.content.ContentValues;
import android.content.Context;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.TextUtils;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by drewmahrt on 12/28/15.
 */
public class ShoppingSQLiteOpenHelper extends SQLiteOpenHelper {
    private static final String TAG = ShoppingSQLiteOpenHelper.class.getCanonicalName();

    private static final int DATABASE_VERSION = 7;
    public static final String DATABASE_NAME = "SHOPPING_DB";
    public static final String SHOPPING_LIST_TABLE_NAME = "SHOPPING_LIST";

    public static final String COL_ID = "_id";
    public static final String COL_ITEM_NAME = "ITEM_NAME";
    public static final String COL_ITEM_PRICE = "PRICE";
    public static final String COL_ITEM_DESCRIPTION = "DESCRIPTION";
    public static final String COL_ITEM_TYPE = "TYPE";

    public static final String[] SHOPPING_COLUMNS = {COL_ID, COL_ITEM_NAME, COL_ITEM_DESCRIPTION, COL_ITEM_PRICE, COL_ITEM_TYPE};

    private static final String CREATE_SHOPPING_LIST_TABLE =
            "CREATE TABLE " + SHOPPING_LIST_TABLE_NAME +
                    "(" +
                    COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COL_ITEM_NAME + " TEXT, " +
                    COL_ITEM_DESCRIPTION + " TEXT, " +
                    COL_ITEM_PRICE + " TEXT, " +
                    COL_ITEM_TYPE + " TEXT )";

    public ShoppingSQLiteOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_SHOPPING_LIST_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + SHOPPING_LIST_TABLE_NAME);
        this.onCreate(db);

    }

    public Cursor showAllGroceries() {
        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.query(SHOPPING_LIST_TABLE_NAME,
                SHOPPING_COLUMNS,
                null,
                null,
                null, null, null);

        return cursor;
    }

    public String getColItemDescription(int id) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(SHOPPING_LIST_TABLE_NAME,
                new String[]{COL_ITEM_DESCRIPTION},
                COL_ID + " = ?",
                new String[]{String.valueOf(id)},
                null, null, null);

        if (cursor.moveToFirst()) {
            return cursor.getString(cursor.getColumnIndex(COL_ITEM_DESCRIPTION));
        } else {
            return "There is no description by that ID";
        }
    }

    public String getColItemPrice(int id) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(SHOPPING_LIST_TABLE_NAME,
                new String[]{COL_ITEM_PRICE},
                COL_ID + " = ?",
                new String[]{String.valueOf(id)},
                null, null, null);

        if (cursor.moveToFirst()) {
            return cursor.getString(cursor.getColumnIndex(COL_ITEM_PRICE));
        } else {
            return "There is no price by that ID";
        }
    }

    public String getColItemType(int id) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(SHOPPING_LIST_TABLE_NAME,
                new String[]{COL_ITEM_TYPE},
                COL_ID + " = ?",
                new String[]{String.valueOf(id)},
                null, null, null);

        if (cursor.moveToFirst()) {
            return cursor.getString(cursor.getColumnIndex(COL_ITEM_TYPE));
        } else {
            return "There is no type by that ID";
        }
    }

    public String getColItemName(int id) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(SHOPPING_LIST_TABLE_NAME,
                new String[]{COL_ITEM_NAME},
                COL_ID + " = ?",
                new String[]{String.valueOf(id)},
                null, null, null);

        if (cursor.moveToFirst()) {
            return cursor.getString(cursor.getColumnIndex(COL_ITEM_NAME));
        } else {
            return "There is no name by that ID";
        }
    }
}
