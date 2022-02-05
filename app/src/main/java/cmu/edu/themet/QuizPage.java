package cmu.edu.themet;

import androidx.activity.OnBackPressedCallback;
import androidx.activity.OnBackPressedDispatcherOwner;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NavUtils;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.text.Layout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class QuizPage extends AppCompatActivity {

    public static final int NO_OF_QUESTIONS = 5;

    int questionCount;
    int score;

    int correctOption = -1;
    ImageView imgView;
    TextView progressText;
    TextView question;
    TextView option1;
    TextView option2;
    TextView option3;
    TextView option4;
    RadioGroup radioGroup;
    Button submitEnabled;
    Button submitDisabled;
    Button backButton;
    ImageView bufferBG;
    ProgressBar bufferIcon;
    JSONObject quizRound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_page);

        // Changing actionBar UI
        ActionBar actionBar = getSupportActionBar();
        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("red"));
        actionBar.setBackgroundDrawable(colorDrawable);

        question = findViewById(R.id.quizQuestion);
        progressText = findViewById(R.id.progressText);
        option1 = findViewById(R.id.option1);
        option2 = findViewById(R.id.option2);
        option3 = findViewById(R.id.option3);
        option4 = findViewById(R.id.option4);
        submitEnabled = findViewById(R.id.submitButtonEnabled);
        submitDisabled = findViewById(R.id.submitButtonDisabled);
        backButton = findViewById(R.id.backButton);
        bufferBG = findViewById(R.id.bufferBG);
        bufferIcon = findViewById(R.id.bufferIcon);
        quizRound = new JSONObject();
        try{
            quizRound = new JSONObject(getIntent().getStringExtra("questions"));
            setDisplay();
        } catch(Exception e){

        }

        radioGroup = findViewById(R.id.answers);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                submitEnabled.setVisibility(View.VISIBLE);
                submitDisabled.setVisibility(View.INVISIBLE);
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bufferBG.setVisibility(View.VISIBLE);
                bufferIcon.setVisibility(View.VISIBLE);
                getDepartments();
            }
        });

        submitEnabled.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bufferBG.setVisibility(View.VISIBLE);
                bufferIcon.setVisibility(View.VISIBLE);

                // get selected radio button from radioGroup
                int selectedId = radioGroup.getCheckedRadioButtonId();
                int selectedOption;
                switch(selectedId){
                    case R.id.option1:
                        selectedOption = 0;
                        break;
                    case R.id.option2:
                        selectedOption = 1;
                        break;
                    case R.id.option3:
                        selectedOption = 2;
                        break;
                    default:
                        selectedOption = 3;
                }
                score += selectedOption == correctOption? 1 : 0;
                if (questionCount < NO_OF_QUESTIONS) {
                    radioGroup.clearCheck();
                    setDisplay();
                } else {
                    Intent mainIntent = new Intent(QuizPage.this, ScoreBoard.class);
                    mainIntent.putExtra("score", String.valueOf(score));
                    QuizPage.this.startActivity(mainIntent);
                    QuizPage.this.finish();
                }

            }
        });


    }

    protected void setDisplay(){
        questionCount++;
        try {
            JSONObject quizDetails = (JSONObject) quizRound.get(String.valueOf(questionCount));
            submitEnabled.setVisibility(View.INVISIBLE);
            submitDisabled.setVisibility(View.VISIBLE);
            imgView = findViewById(R.id.artPieceImg);
            Picasso.get().load(quizDetails.getString("imgURL")).into(imgView);
            question.setText(quizDetails.getString("question"));
            progressText.setText(questionCount + " out of " + NO_OF_QUESTIONS +" questions");
            JSONArray options = (JSONArray) quizDetails.get("options");
            option1.setText(options.getString(0));
            option2.setText(options.getString(1));
            option3.setText(options.getString(2));
            option4.setText(options.getString(3));
            bufferBG.setVisibility(View.INVISIBLE);
            bufferIcon.setVisibility(View.INVISIBLE);
            correctOption = Integer.parseInt(quizDetails.getString("correctOption"));

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    protected void getDepartments() {
        int display_loader = 3000;
        final JSONObject[] departments = {new JSONObject()};
        String departmentUrl = "https://afternoon-mesa-32052.herokuapp.com/departments?noOfDepartments="+MainActivity.NO_OF_DEPARTMENTS;
        RequestQueue queue = Volley.newRequestQueue(this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, departmentUrl, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        departments[0] = response;
                        /* New Handler to start the Menu-Activity
                         * and close this Splash-Screen after some seconds.*/
                        new Handler().postDelayed(new Runnable(){
                            @Override
                            public void run() {
                                /* Create an Intent that will start the Menu-Activity. */
                                Intent mainIntent = new Intent(QuizPage.this,MainActivity.class);
                                mainIntent.putExtra("departments", departments[0].toString());
                                QuizPage.this.startActivity(mainIntent);
                                QuizPage.this.finish();
                            }
                        }, display_loader);
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO: Handle error

                    }
                });
        jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(
                DefaultRetryPolicy.DEFAULT_TIMEOUT_MS * 10,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue.add(jsonObjectRequest);
    }


}