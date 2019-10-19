package com.arsenic.coiny.DBController;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.arsenic.coiny.Model.Budget;
import com.arsenic.coiny.Model.Usuario;

import java.util.ArrayList;
import java.util.List;

public class DBManager extends SQLiteOpenHelper {
    SQLiteDatabase db;

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
    public static final String DNI_COLUMNA = "dni";
    public static final String EMAIL_COLUMNA = "email";

    //TABLA DE PRESUPUESTO
    public static final String BUDGET_TABLA = "tbudget";
    public static final String SHOPPING_COLUMNA = "shopping";
    public static final String TRANSPORTE_COLUMNA = "transporte";
    public static final String COMIDA_COLUMNA = "comida";
    public static final String FAMILIA_COLUMNA = "familia";
    public static final String ENTRETENIMIENTO_COLUMNA = "entretenimiento";

    //TABLA DE PRESUPUESTO USADO
    public static final String USED_BUDGET_TABLA = "tubudget";


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
                + DNI_COLUMNA + " TEXT, "
                + EMAIL_COLUMNA + " TEXT, "
                + SALDO_SOL_COLUMNA + " REAL, "
                + SALDO_DOL_COLUMNA + " REAL )");

        db.execSQL("CREATE TABLE " + BUDGET_TABLA + "( "
                + NUMERO_COLUMNA + " TEXT PRIMARY KEY, "
                + SHOPPING_COLUMNA + " REAL, "
                + TRANSPORTE_COLUMNA + " REAL, "
                + COMIDA_COLUMNA + " REAL, "
                + FAMILIA_COLUMNA + " REAL, "
                + ENTRETENIMIENTO_COLUMNA + " REAL )");

        db.execSQL("CREATE TABLE " + USED_BUDGET_TABLA + "( "
                + NUMERO_COLUMNA + " TEXT PRIMARY KEY, "
                + SHOPPING_COLUMNA + " REAL, "
                + TRANSPORTE_COLUMNA + " REAL, "
                + COMIDA_COLUMNA + " REAL, "
                + FAMILIA_COLUMNA + " REAL, "
                + ENTRETENIMIENTO_COLUMNA + " REAL )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + USUARIO_TABLA);
        db.execSQL("DROP TABLE IF EXISTS " + BUDGET_TABLA);
        db.execSQL("DROP TABLE IF EXISTS " + USED_BUDGET_TABLA);
        onCreate(db);
    }

    public void insertRecord(String nombre, String apellido, int password, String numero, double saldo_sol, double saldo_dol, String dni){
        db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(NOMBRE_COLUMNA, nombre);
        contentValues.put(APELLIDO_COLUMNA, apellido);
        contentValues.put(PASS_COLUMNA, password);
        contentValues.put(NUMERO_COLUMNA, numero);
        contentValues.put(SALDO_SOL_COLUMNA, saldo_sol);
        contentValues.put(SALDO_DOL_COLUMNA, saldo_dol);
        contentValues.put(DNI_COLUMNA, dni);

        db.insert(USUARIO_TABLA, null, contentValues);


        ContentValues contentValuesBudget = new ContentValues();

        contentValues.put(NUMERO_COLUMNA, numero);
        contentValues.put(SHOPPING_COLUMNA, 0.0);
        contentValues.put(TRANSPORTE_COLUMNA, 0.0);
        contentValues.put(COMIDA_COLUMNA, 0.0);
        contentValues.put(FAMILIA_COLUMNA, 0.0);
        contentValues.put(ENTRETENIMIENTO_COLUMNA, 0.0);

        db.insert(BUDGET_TABLA, null, contentValuesBudget);
        db.insert(USED_BUDGET_TABLA, null, contentValuesBudget);
    }

    public Usuario getUsuario(String numero){
        Usuario u = null;
        db = getReadableDatabase();
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
        db = this.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(SALDO_SOL_COLUMNA,saldo_sol);
        cv.put(SALDO_DOL_COLUMNA,saldo_dol);

        db.update(USUARIO_TABLA, cv, "numero = ?", new String[]{numero});

    }

    public void updateBudget(String numero, String type, double monto){

        db = this.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(type, monto);

        db.update(BUDGET_TABLA, cv, "numero = ?", new String[]{numero});

    }

    public void updateUsedBudget(String numero, String type, double monto){

        db = this.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(type, monto);

        db.update(USED_BUDGET_TABLA, cv, "numero = ?", new String[]{numero});

    }

    public double getBudget(String numero, String type){
        db = getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM tbudget WHERE numero = ?", new String[] {numero});

        Log.i("GETBUDGET:", res.getCount()+"");

        if(res.getCount() > 0){
            res.moveToFirst();

            double b = res.getDouble(res.getColumnIndex(type));
            return 0.0;
        }

        return 0.0;
    }

    public double getUsedBudget(String numero, String type){
        db = getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM tubudget WHERE numero = ?", new String[] {numero});

        if(res.getCount() > 0){
            res.moveToFirst();

            double b = res.getDouble(res.getColumnIndex(type));
            return b;
        }

        return 0.0;
    }

    public List<Budget> getBudgets(String numero){
        List<Budget> r = new ArrayList<>();

        db = getReadableDatabase();

        Cursor resbudget = db.rawQuery("SELECT * FROM tbudget where numero=?", new String[] {numero});
        Cursor resubudget = db.rawQuery("SELECT * FROM tubudget WHERE numero=?", new String[] {numero});

        resbudget.moveToFirst();
        resubudget.moveToFirst();

        String[] types = {"shopping", "transporte", "comida", "familia", "entretenimiento"};

        for(String t : types){
            double auxb = resbudget.getDouble(resbudget.getColumnIndex(t));
            double auxub = resubudget.getDouble(resubudget.getColumnIndex(t));

            Budget b = new Budget(t, auxb, auxub, 0 );

            r.add(b);
        }



        return r;
    }




}
