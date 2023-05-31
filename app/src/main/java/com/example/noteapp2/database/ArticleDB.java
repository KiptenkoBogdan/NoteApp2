package com.example.noteapp2.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Article.class}, version  = 1)
public abstract class ArticleDB extends RoomDatabase {

    public abstract ArticleDao articleDao();

    private static ArticleDB INSTANCE;

    public static ArticleDB getDbInstance(Context context) {

        if(INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), ArticleDB.class, "DB_NAME")
                    .allowMainThreadQueries()
                    .build();

        }
        return INSTANCE;
    }
}
