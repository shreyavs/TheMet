package cmu.edu.themet;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONObject;

public class LoaderScreen extends AppCompatActivity {

    private final int RESPONSE_WAIT_TIME = 3000;
    private final int LOADER_TEXT_TIMER = 5000;

    /**
     * This function is executed when the Activity starts
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loader_screen);

        // Changing actionBar UI
        ActionBar actionBar = getSupportActionBar();
        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("red"));
        actionBar.setBackgroundDrawable(colorDrawable);

        String deptID;
        try{
            deptID = getIntent().getStringExtra("deptID");
        } catch(Exception e){
            deptID = "1";
        }
        getQuizQuestions(deptID);

        TextView loadingText = findViewById(R.id.loadingText);
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                loadingText.setText("Just a few more seconds..");
            }
        }, LOADER_TEXT_TIMER);
    }

    /**
     * This function gets the quiz questions once the user has selected department
     * @param deptID code of department selected by user
     */
    protected void getQuizQuestions(String deptID) {
        String quizUrl = "https://afternoon-mesa-32052.herokuapp.com/question?deptID=" + deptID + "&noOfQuestions=" + QuizPage.NO_OF_QUESTIONS;
        RequestQueue queue = Volley.newRequestQueue(this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, quizUrl, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {

                        new Handler().postDelayed(new Runnable(){
                            @Override
                            public void run() {
                                Intent mainIntent = new Intent(LoaderScreen.this,QuizPage.class);
                                mainIntent.putExtra("questions", response.toString());
                                LoaderScreen.this.startActivity(mainIntent);
                                LoaderScreen.this.finish();
                            }
                        }, RESPONSE_WAIT_TIME);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        System.out.println("VolleyError: " + error.getMessage());
                    }
                });
        jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(
                DefaultRetryPolicy.DEFAULT_TIMEOUT_MS * 10,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue.add(jsonObjectRequest);
    }
}