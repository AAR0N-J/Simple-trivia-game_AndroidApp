package com.example.trivia;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import java.util.List;

@Dao
public interface QuestionDao {

    @Insert
    void addQuestion(Question q);

    @Query("SELECT COUNT(*) FROM questionBank")
    int count();

    @Query("select * from questionBank")
    List<Question> getAll();

}