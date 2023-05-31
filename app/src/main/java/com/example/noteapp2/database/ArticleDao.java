package com.example.noteapp2.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ArticleDao {

    @Query("SELECT * FROM article")
    List<Article> getAllUsers();

    @Query("SELECT * FROM article WHERE id = :id")
    List<Article> findById(int id);

    @Insert
    void insertUser(Article... articles);

    @Delete
    void delete(Article article);

    @Update
    void updateArticle(Article article);

}