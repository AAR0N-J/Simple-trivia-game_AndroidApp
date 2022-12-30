package com.example.trivia;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "questionBank")
public class Question {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo
    private String question;

    @ColumnInfo
    private boolean answer;

    @ColumnInfo
    private String topic;

    public Question(String question, boolean answer, String topic) {
        this.question = question;
        this.answer = answer;
        this.topic = topic;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public boolean getAnswer() {
        return answer;
    }

    public void setAnswer(boolean answer) {
        this.answer = answer;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

}