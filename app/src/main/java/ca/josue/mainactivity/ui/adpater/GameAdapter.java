package ca.josue.mainactivity.ui.adpater;

import static android.graphics.text.LineBreaker.JUSTIFICATION_MODE_INTER_WORD;
import static ca.josue.mainactivity.BaseApplication.answersMapSession;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Build;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.inject.Inject;

import ca.josue.mainactivity.R;
import ca.josue.mainactivity.data.repository.AnswersRepo;
import ca.josue.mainactivity.domain.entity.Answers;
import ca.josue.mainactivity.domain.entity.QuizEntity;
import ca.josue.mainactivity.utils.ResponseAnswer;

public class GameAdapter extends RecyclerView.Adapter<GameAdapter.QuizzesVH> {
    private static final String TAG = GameAdapter.class.getSimpleName();
    private final Context context;
    private List<QuizEntity> quizzes;
    private final AnswersRepo answersRepo;

    private final Animation animationFadeIn;

    @Inject
    public GameAdapter(Context context, AnswersRepo answersRepo) {
        this.context = context;
        this.quizzes = new ArrayList<>();
        this.answersRepo = answersRepo;
        this.animationFadeIn = AnimationUtils.loadAnimation(context, R.anim.fade_in_400);
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

    @RequiresApi(api = Build.VERSION_CODES.Q)
    @Override
    public void onBindViewHolder(@NonNull QuizzesVH holder, int position) {
        QuizEntity quiz = quizzes.get(position);

        animationFadeIn.setDuration(1200);
        holder.cardViewQuiz.setAnimation(animationFadeIn);

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

        // convert HashMap to List<String
        List<String> answersList = new ArrayList<>(answersMap.values());
        answersList.removeIf(Objects::isNull);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, answersList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        holder.spinner.setAdapter(adapter);

        if(quiz.getExplanation() != null){
            holder.questionIdea.setOnClickListener(v -> createPopupWindow(v, quiz.getExplanation()));
        }
        else {
            holder.questionIdea.setVisibility(View.GONE);
        }

        // set default value coming to answersMapSession
        if(answersMapSession.containsKey(quiz.getId())){
            ResponseAnswer defaultAnswer = answersMapSession.get(quiz.getId());
            assert defaultAnswer != null;
            holder.spinner.setSelection(adapter.getPosition(defaultAnswer.getResponse()));
        }

        holder.spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
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

    @RequiresApi(api = Build.VERSION_CODES.Q)
    private void createPopupWindow(View v, String explanationMessage) {
        PopupWindow popupWindow = new PopupWindow(context);

        @SuppressLint("InflateParams")
        View popupView = LayoutInflater.from(context).inflate(R.layout.popup_explanation, null);
        popupWindow.setContentView(popupView);
        popupWindow.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.showAtLocation(v, Gravity.CENTER, 0, 0);
        popupWindow.setFocusable(true);

        // style of text in the popup
        TextView explanation = popupView.findViewById(R.id.explanation);
        explanation.setText(explanationMessage);
        explanation.setGravity(Gravity.CENTER);
        explanation.setJustificationMode(JUSTIFICATION_MODE_INTER_WORD);
        explanation.setTextSize(TypedValue.COMPLEX_UNIT_SP, 13);
        explanation.setTypeface(Typeface.DEFAULT_BOLD);
        explanation.setPadding(5,15,5,5);

        // set animation fade in and fade out

        animationFadeIn.setDuration(400);
        popupView.startAnimation(animationFadeIn);
        popupView.findViewById(R.id.close_popup).setOnClickListener(v1 -> popupWindow.dismiss());
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

        private final ImageView questionIdea;

        private final CardView cardViewQuiz;

        public QuizzesVH(@NonNull View itemView) {
            super(itemView);
            question = itemView.findViewById(R.id.question);
            spinner = itemView.findViewById(R.id.dropdown_answers);
            questionIdea = itemView.findViewById(R.id.questionIdea);
            cardViewQuiz = itemView.findViewById(R.id.card_view_quiz);
        }
    }
}
