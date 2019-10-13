package com.arsenic.coiny.DBController;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.arsenic.coiny.Model.Usuario;

public class DBManager extends SQLiteOpenHelper {

    public static final String DB_NAME = "COINYDB";

    //TABLA DE USUARIOS
    public static final String USUARIO_TABLA = "tusuario";
    public static final String ID_COLUMNA = "id";
    public static final String NOMBRE_COLUMNA = "nombre";
    public static final String APELLIDO_COLUMNA = "apellido";
    public static final String PASS_COLUMNA = "password";
    public static final String NUMERO_COLUMNA = "numero";
    public static final String SALDO_SOL_COLUMNA = "saldo_sol";
    public static final String SALDO_DOL_COLUMNA = "saldo_dol";



    public DBManager(Context context) {
        super(context, DB_NAME, null, 1 );
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + USUARIO_TABLA + "( "
                + ID_COLUMNA + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "
                + NOMBRE_COLUMNA + " TEXT, "
                + APELLIDO_COLUMNA + " INTEGER, "
                + PASS_COLUMNA + " INTEGER, "
                + NUMERO_COLUMNA + " TEXT, "
                + SALDO_SOL_COLUMNA + " REAL, "
                + SALDO_DOL_COLUMNA + " REAL )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + USUARIO_TABLA);
        onCreate(db);
    }

    public void insertRecord(String nombre, String apellido, int password, String numero, double saldo_sol, double saldo_dol){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(NOMBRE_COLUMNA, nombre);
        contentValues.put(APELLIDO_COLUMNA, apellido);
        contentValues.put(PASS_COLUMNA, password);
        contentValues.put(NUMERO_COLUMNA, numero);
        contentValues.put(SALDO_SOL_COLUMNA, saldo_sol);
        contentValues.put(SALDO_DOL_COLUMNA, saldo_dol);

        db.insert(USUARIO_TABLA, null, contentValues);
    }

    public Usuario getUsuario(String numero){
        Usuario u = null;
        SQLiteDatabase db = getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM tusuario WHERE numero=?", new String[] {numero});



        if(res.getCount() > 0){
            res.moveToFirst();
            String unombre = res.getString(res.getColumnIndex(NOMBRE_COLUMNA));
            String uapellido = res.getString(res.getColumnIndex(APELLIDO_COLUMNA));
            int upass = res.getInt(res.getColumnIndex(PASS_COLUMNA));
            String unumero = res.getString(res.getColumnIndex(NUMERO_COLUMNA));
            double usaldo_sol = res.getDouble(res.getColumnIndex(SALDO_SOL_COLUMNA));
            double usaldo_dol = res.getDouble(res.getColumnIndex(SALDO_DOL_COLUMNA));


            u = new Usuario(unombre, uapellido, unumero, upass, usaldo_sol, usaldo_dol);

        }

        res.close();
        return u;

    }

    public void updateSaldo(String numero, double saldo_sol, double saldo_dol ){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(SALDO_SOL_COLUMNA,saldo_sol);
        cv.put(SALDO_DOL_COLUMNA,saldo_dol);

        db.update(USUARIO_TABLA, cv, "numero = ?", new String[]{numero});

    }




}
