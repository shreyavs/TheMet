package cmu.edu.themet;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import com.squareup.picasso.Picasso;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    public static final int NO_OF_DEPARTMENTS = 5;

    ImageView imgView;
    TextView textView;
    LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String departments = getIntent().getStringExtra("departments");
        setUpDepartmentView(departments);

        // Changing actionBar UI
        ActionBar actionBar = getSupportActionBar();
        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("red"));
        actionBar.setBackgroundDrawable(colorDrawable);
    }

    protected void setUpDepartmentView(String departments){

        try {
            JSONObject deptResponseObject = new JSONObject(departments);
            JSONArray departmentsArray = new JSONArray(deptResponseObject.getString("departments"));

            for(int i = 0; i < departmentsArray.length(); i++){
                JSONObject dept = (JSONObject) departmentsArray.get(i);
                int layoutID = getResources().getIdentifier("linearLayout" + (i+1), "id", getPackageName());
                int imgViewID = getResources().getIdentifier("departmentImg" + (i+1), "id", getPackageName());
                int textID = getResources().getIdentifier("deptText" + (i+1), "id", getPackageName());

                imgView = findViewById(imgViewID);
                Picasso.get().load(dept.getString("imgURL")).into(imgView);
                textView = findViewById(textID);
                textView.setText(dept.getString("name"));
                linearLayout = findViewById(layoutID);

                linearLayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        onDeptClick(view.getId());
                    }
                });
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    protected void onDeptClick(int layoutID){
        int deptID = 0;
        switch(layoutID){
            case R.id.linearLayout1:
                deptID = 1;
                break;
            case R.id.linearLayout2:
                deptID = 3;
                break;
            case R.id.linearLayout3:
                deptID = 4;
                break;
            case R.id.linearLayout4:
                deptID = 5;
                break;
            case R.id.linearLayout5:
                deptID = 6;
                break;
            default:
                deptID = 7;
        }

        Intent mainIntent = new Intent(MainActivity.this,LoaderScreen.class);
        mainIntent.putExtra("deptID", String.valueOf(deptID));
        MainActivity.this.startActivity(mainIntent);
        MainActivity.this.finish();
    }

}
