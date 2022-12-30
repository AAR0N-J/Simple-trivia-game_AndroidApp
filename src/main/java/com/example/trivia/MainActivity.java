package com.example.trivia;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import com.example.trivia.databinding.ActivityMainBinding;
import java.util.Arrays;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private TriviaDatabase db;
    private List<Question> questionBank;
    private DialogFragment dialogFragment;

//    private List<Question> questionBank = Arrays.asList(
//            new Question(R.string.yellowstone, true),
//            new Question(R.string.ohio, false),
//            new Question(R.string.india, true),
//            new Question(R.string.saltFish, true),
//            new Question(R.string.freshFish, false),
//            new Question(R.string.CSUMBcenter, true),
//            new Question(R.string.dogsSee, false),
//            new Question(R.string.doctor, true)
//    );

    private TextView tv;
    private int currentIndex = 0;

    // private int question;
    private String question;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        dialogFragment = new AddTriviaDialog();
        db = TriviaDatabase.getInstance(MainActivity.this);
        db.populateInitialData();
        questionBank = db.question().getAll();

        tv = binding.questionTextView;

        // question = questionBank.get(currentIndex).getTextResId();
        question = questionBank.get(currentIndex).getQuestion();

        tv.setText(question);
        binding.trueButton.setOnClickListener(v -> checkAnswer(true));
        binding.falseButton.setOnClickListener(v -> checkAnswer(false));

        binding.nextButton.setOnClickListener(v -> {
            currentIndex = (currentIndex + 1) % questionBank.size();

            // question = questionBank.get(currentIndex).getTextResId();
            question = questionBank.get(currentIndex).getQuestion();

            tv.setText(question);
        });

        binding.previousButton.setOnClickListener(v -> {
            if (currentIndex == 0) {
                currentIndex = questionBank.size() - 1;
            } else {
                currentIndex -= currentIndex;
            }

            // question = questionBank.get(currentIndex).getTextResId();
            question = questionBank.get(currentIndex).getQuestion();

            tv.setText(question);
        });
        binding.viewQs.setOnClickListener(v -> {

        });


    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_add_task:
                Log.d("main", "add new question");
                dialogFragment.show(getSupportFragmentManager(), "");
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void checkAnswer(boolean userPressedTrue) {
        // boolean answerIsTrue = questionBank.get(currentIndex).isAnswerTrue();
        boolean answerIsTrue = questionBank.get(currentIndex).getAnswer();
        int messageResId = 0;
        if (userPressedTrue == answerIsTrue) {
            messageResId = R.string.correct;
        } else {
            messageResId = R.string.incorrect;
        }

        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT).show();
    }
}