package isetb.tp7.testprojet;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;

import isetb.tp7.testprojet.model.Tache;

public class TacheDatabase extends SQLiteOpenHelper{
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "tache_db";
    public static final String TTache = "tache";
    public static final String CID = "idt";
    public static final String NOMT = "nomt";
    public static final String DESCRPTION = "descption";
    public static final String CREATE_Tache_TABLE = "CREATE TABLE " + TTache + "("
            + CID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + NOMT + " VARCHAR(20) NOT NULL, "
            + DESCRPTION + " VARCHAR(50)) ";

    private SQLiteDatabase db;

    public TacheDatabase(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_Tache_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TTache);
        onCreate(sqLiteDatabase);
    }
    public Boolean addTache (Tache tache){
        db = this.getWritableDatabase();
        ContentValues v = new ContentValues();
        v.put(NOMT, tache.getNomt());
        v.put(DESCRPTION, tache.getDescrption());
        long x = db.insert(TTache, null, v);
        return x != -1;
    }

    public void updateTache(Tache tache, int idt) {
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(NOMT, tache.getNomt());
        values.put(DESCRPTION, tache.getDescrption());
        db.update(TTache, values, CID + "=?", new String[]{String.valueOf(idt)});
    }

    public void removeTache(int idt) {
        db = this.getWritableDatabase();
        db.delete(TTache, CID + "=?", new String[]{String.valueOf(idt)});
        db.close();
    }

    public int getTacheCount() {
        db = this.getReadableDatabase();
        Cursor cursor = db.query(TTache, null, null, null, null, null, null);
        return cursor.getCount();
    }

    public ArrayList<Tache> getAllTache() {
        ArrayList<Tache> list = new ArrayList<>();
        db = this.getReadableDatabase();

        Cursor cursor = db.query(TTache, null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                int idt = cursor.getInt(cursor.getColumnIndexOrThrow(CID));
                String nomt = cursor.getString(cursor.getColumnIndexOrThrow(NOMT));
                String description = cursor.getString(cursor.getColumnIndexOrThrow(DESCRPTION));

                Tache tache = new Tache(idt, nomt, description);
                list.add(tache);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return list;
    }
}
