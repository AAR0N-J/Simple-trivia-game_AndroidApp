package com.example.trivia;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.trivia.databinding.ActivityMainBinding;

import java.util.List;

public class TriviaManageActivity extends AppCompatActivity {
    private TriviaDatabase db;
    private List<Question> triviaList;
    private ListView triviaListView;
    private ActivityMainBinding binding;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db = TriviaDatabase.getInstance(this);
        triviaList = db.question().getAll();
        triviaListView = binding.triviaList;



    }
    private void updateUI() {
        triviaList = db.question().getAll();
        if (triviaAdapter == null) {
            triviaAdapter = new ArrayAdapter<>(this, R.layout.item_question,
                    R.id.question_item, triviaList);
            triviaListView.setAdapter(triviaAdapter);
        } else {
            triviaAdapter.clear();
            triviaAdapter.addAll(triviaList);
            triviaAdapter.notifyDataSetChanged();
        }

    }
}
