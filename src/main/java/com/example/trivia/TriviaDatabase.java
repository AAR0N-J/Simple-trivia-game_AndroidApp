package com.example.trivia;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Question.class}, version = 2, exportSchema = false)
public abstract class TriviaDatabase extends RoomDatabase {
    private static TriviaDatabase sInstance;

    public abstract QuestionDao question();

    public static synchronized TriviaDatabase getInstance(Context context) {

        if (sInstance == null) {
            sInstance = Room.databaseBuilder(context.getApplicationContext(),
                            TriviaDatabase.class, "trivia.db")
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build();
        }
        return sInstance;
    }

    public void populateInitialData() {
        runInTransaction(() -> {
            if (question().count() == 0) {
                Question q1 = new Question("Monterey is the biggest city in the world",
                        false, "geography");
                question().addQuestion(q1);

                question().addQuestion(new Question("Yellowstone is in Ohio", false,
                        "geography"));
            }
        });
    }
}
