package cmu.edu.themet;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;
import org.json.JSONObject;

public class ScoreBoard extends AppCompatActivity {

    String score = "";
    TextView displayMsg;
    PieChart mPieChart;

    TextView backButton;
    ImageView bufferBG;
    ProgressBar bufferIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_board);

        // Changing actionBar UI
        ActionBar actionBar = getSupportActionBar();
        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("red"));
        actionBar.setBackgroundDrawable(colorDrawable);

        score = getIntent().getStringExtra("score");
        displayMsg = findViewById(R.id.displayText);
        mPieChart = findViewById(R.id.scoreChart);
        bufferBG = findViewById(R.id.bufferBgScore);
        bufferIcon = findViewById(R.id.bufferIconScore);
        backButton = findViewById(R.id.backButton);


        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bufferBG.setVisibility(View.VISIBLE);
                bufferIcon.setVisibility(View.VISIBLE);
                getDepartments();
            }
        });
        setDisplay();
    }

    protected void setDisplay(){
        int finalScore;
        try{
            finalScore = Integer.parseInt(score);
        }catch(Exception e){
            finalScore = 0;
        }
        if(finalScore <= QuizPage.NO_OF_QUESTIONS/2)
            displayMsg.setText("Better luck next time. :(");
        else
            displayMsg.setText("Congratulations!");

        mPieChart.addPieSlice(new PieModel("Correct", finalScore, Color.parseColor("red")));
        mPieChart.addPieSlice(new PieModel("Wrong", (QuizPage.NO_OF_QUESTIONS - finalScore), Color.parseColor("grey")));
        mPieChart.startAnimation();
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
                                Intent mainIntent = new Intent(ScoreBoard.this,MainActivity.class);
                                mainIntent.putExtra("departments", departments[0].toString());
                                ScoreBoard.this.startActivity(mainIntent);
                                ScoreBoard.this.finish();
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