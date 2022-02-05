package cmu.edu.themet;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONObject;

public class SplashScreen extends Activity {

    private final int SPLASH_DISPLAY_LENGTH = 3000;
    private JSONObject departments = new JSONObject();

    /**
     * This function is executed when the Activity created
     * @param icicle
     */
    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        getDepartments();
        setContentView(R.layout.activity_splash_screen);
    }

    /**
     * This function gets the department information from the service
     */
    protected void getDepartments() {
        String departmentUrl = "https://afternoon-mesa-32052.herokuapp.com/departments?noOfDepartments="+MainActivity.NO_OF_DEPARTMENTS;
        RequestQueue queue = Volley.newRequestQueue(this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, departmentUrl, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        departments = response;
                        /* New Handler to start the Menu-Activity
                         * and close this Splash-Screen after some seconds.*/
                        new Handler().postDelayed(new Runnable(){
                            @Override
                            public void run() {
                                /* Create an Intent that will start the Menu-Activity. */
                                Intent mainIntent = new Intent(SplashScreen.this,MainActivity.class);
                                mainIntent.putExtra("departments", departments.toString());
                                SplashScreen.this.startActivity(mainIntent);
                                SplashScreen.this.finish();
                            }
                        }, SPLASH_DISPLAY_LENGTH);
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