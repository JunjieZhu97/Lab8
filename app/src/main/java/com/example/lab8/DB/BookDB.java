package com.example.lab8.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.lab8.model.BookDetailedModel;

import java.util.ArrayList;
import java.util.List;

public class BookDB extends SQLiteOpenHelper {
    public static final String DB_NAME="BookDB";
    public static final int DB_VERSION=1;
    public final String BOOK_TABLE="BookTbl";

    public BookDB(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
db.execSQL("CREATE TABLE "+BOOK_TABLE+"(Book_ID int NOT NULL PRIMARY KEY UNIQUE,Cover_Photo TEXT,Title TEXT,Author TEXT,Genre TEXT,OLanguage TEXT,Description TEXT,PublishedYear int,NumberofPages int)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public boolean AddBook(int BookID,String CoverPhoto,String Title,String Author,String Genre,String OLanguage,String Description,int PublishedYear,int NumberofPages)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put("Book_ID",BookID);
        cv.put("Cover_Photo",CoverPhoto);
        cv.put("Title",Title);
        cv.put("Author",Author);
        cv.put("Genre",Genre);
        cv.put("OLanguage",OLanguage);
        cv.put("Description",Description);
        cv.put("PublishedYear",PublishedYear);
        cv.put("NumberofPages",NumberofPages);

        try {
            db.insert(BOOK_TABLE,null,cv);
            db.close();
            return true;
        } catch (Exception e) {
            db.close();
            return false;
        }
    }
    public List<BookDetailedModel> GetBooks()
    {
        List<BookDetailedModel> bookDetailedModelList=new ArrayList<>();
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cs=db.rawQuery("SELECT * FROM "+BOOK_TABLE,null);
        if(cs.moveToFirst())
        {
            while(!cs.isAfterLast())
            {
                bookDetailedModelList.add(new BookDetailedModel(cs.getInt(cs.getColumnIndex("Book_ID")),
                        cs.getString(cs.getColumnIndex("Cover_Photo")),
                        cs.getString(cs.getColumnIndex("Title")),
                        cs.getString(cs.getColumnIndex("Author")),
                        cs.getString(cs.getColumnIndex("Genre")),
                        cs.getString(cs.getColumnIndex("OLanguage")),
                        cs.getString(cs.getColumnIndex("Description")),
                        cs.getInt(cs.getColumnIndex("PublishedYear")),
                        cs.getInt(cs.getColumnIndex("NumberofPages"))));
                cs.moveToNext();
            }
        }
        cs.close();
        db.close();
        return bookDetailedModelList;
    }
    public BookDetailedModel GetBookByID(int ID)
    {
        BookDetailedModel bookDetailedModel=null;
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cs=db.rawQuery("SELECT * FROM "+BOOK_TABLE+" WHERE Book_ID="+ID,null);
        if(cs.moveToFirst())
        {
            bookDetailedModel=new BookDetailedModel(cs.getInt(cs.getColumnIndex("Book_ID")),
                    cs.getString(cs.getColumnIndex("Cover_Photo")),
                    cs.getString(cs.getColumnIndex("Title")),
                    cs.getString(cs.getColumnIndex("Author")),
                    cs.getString(cs.getColumnIndex("Genre")),
                    cs.getString(cs.getColumnIndex("OLanguage")),
                    cs.getString(cs.getColumnIndex("Description")),
                    cs.getInt(cs.getColumnIndex("PublishedYear")),
                    cs.getInt(cs.getColumnIndex("NumberofPages")));
        }
        return bookDetailedModel;
    }
public void UpdateBook(int ID,String CoverPhoto,String Title,String Author,String Genre,String OLanguage,String Description,int PublishedYear,int NumberofPages)
{
    SQLiteDatabase db=this.getWritableDatabase();
ContentValues cv=new ContentValues();
cv.put("Cover_Photo",CoverPhoto);
cv.put("Title",Title);
cv.put("Author",Author);
cv.put("Genre",Genre);
cv.put("OLanguage",OLanguage);
cv.put("Description",Description);
cv.put("PublishedYear",PublishedYear);
cv.put("NumberofPages",NumberofPages);

    String where = "Book_ID=?";
    String[] whereArgs = new String[] {String.valueOf(ID)};

    db.update(BOOK_TABLE, cv, where, whereArgs);
    db.close();
}
public void RemoveBook(int ID)
{
    SQLiteDatabase db=this.getWritableDatabase();
    db.execSQL("DELETE FROM "+BOOK_TABLE+" WHERE Book_ID="+ID);
    db.close();
}

}
