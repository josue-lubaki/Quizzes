package ca.josue.mainactivity.ui.adpater;

import static ca.josue.mainactivity.BaseApplication.answersMapSession;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import ca.josue.mainactivity.BaseApplication;
import ca.josue.mainactivity.MainActivity;
import ca.josue.mainactivity.R;
import ca.josue.mainactivity.data.repository.AnswersRepo;
import ca.josue.mainactivity.database.QuizzesDatabase;
import ca.josue.mainactivity.domain.entity.Answers;
import ca.josue.mainactivity.domain.entity.QuizEntity;
import ca.josue.mainactivity.utils.ResponseAnswer;

public class QuizzesAdapter extends RecyclerView.Adapter<QuizzesAdapter.QuizzesVH> {
    private static final String TAG = QuizzesAdapter.class.getSimpleName();
    private final Context context;
    private List<QuizEntity> quizzes;
    private final AnswersRepo answersRepo;
    private final MainActivity activity;

    public QuizzesAdapter(Context context) {
        this.context = context;
        this.quizzes = new ArrayList<>();
        this.activity = (MainActivity) context;
        this.answersRepo = new AnswersRepo(activity.getApplication());
    }

    public Context getContext() {
        return context;
    }

    @NonNull
    @Override
    public QuizzesVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.fragment_quiz_card, parent, false);
        return new QuizzesVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull QuizzesVH holder, int position) {
        QuizEntity quiz = quizzes.get(position);
        holder.question.setText(quiz.getQuestion());

        Answers answers = answersRepo.getAnswerById(quiz.getId());
        if(answers == null){
            return;
        }

        Map<String, String> answersMap = new HashMap<>();
        answersMap.put("answer_a", answers.getAnswer_a());
        answersMap.put("answer_b", answers.getAnswer_b());
        answersMap.put("answer_c", answers.getAnswer_c());
        answersMap.put("answer_d", answers.getAnswer_d());
        answersMap.put("answer_e", answers.getAnswer_e());
        answersMap.put("answer_f", answers.getAnswer_f());

        Log.d(TAG, "MainActivity - onBindViewHolder: " + answersMap);


        // convert HashMap to List<String
        List<String> answersList = new ArrayList<>(answersMap.values());
        answersList.removeIf(Objects::isNull);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, answersList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        holder.spinner.setAdapter(adapter);

        // set default value coming to answersMapSession
        if(answersMapSession.containsKey(quiz.getId())){
            ResponseAnswer defaultAnswer = answersMapSession.get(quiz.getId());
            assert defaultAnswer != null;
            holder.spinner.setSelection(adapter.getPosition(defaultAnswer.getResponse()));
        }

        holder.spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.d(TAG, "QuizzesAdapter - onItemSelected: " + parent.getItemAtPosition(position));
                String answer = (String) parent.getItemAtPosition(position);

                Map<Integer, String> answersAssertions = new HashMap<>();
                answersAssertions.put(0, "answer_a");
                answersAssertions.put(1, "answer_b");
                answersAssertions.put(2, "answer_c");
                answersAssertions.put(3, "answer_d");
                answersAssertions.put(4, "answer_e");
                answersAssertions.put(5, "answer_f");

                if(!answersMapSession.containsKey(quiz.getId())){
                    // add answer to map
                    answersMapSession.put(quiz.getId(), new ResponseAnswer(answersAssertions.get(position), answer));
                }
                else {
                    // replace answer in map with new answer
                    answersMapSession.replace(quiz.getId(), new ResponseAnswer(answersAssertions.get(position), answer));
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Log.d(TAG, "QuizzesAdapter - onNothingSelected: ");
            }
        });
    }

    @Override
    public int getItemCount() {
        return quizzes.size();
    }

    public void setQuizzes(List<QuizEntity> quizzes) {
        this.quizzes = quizzes;
    }

    public List<QuizEntity> getQuizzes() {
        return quizzes;
    }

    public static class QuizzesVH extends RecyclerView.ViewHolder {
        private final TextView question;
        private final Spinner spinner;

        public QuizzesVH(@NonNull View itemView) {
            super(itemView);
            question = itemView.findViewById(R.id.question);
            spinner = itemView.findViewById(R.id.dropdown_answers);
        }
    }
}
