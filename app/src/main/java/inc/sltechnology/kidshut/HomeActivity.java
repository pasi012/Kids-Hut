package inc.sltechnology.kidshut;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import inc.sltechnology.kidshut.loginActivities.LoginActivity;

public class HomeActivity extends AppCompatActivity {

    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        firebaseAuth = FirebaseAuth.getInstance();

    }

    @Override
    protected void onStart() {
        super.onStart();

        if (FirebaseAuth.getInstance().getCurrentUser() != null){

            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            finish();

        }else {

            startActivity(new Intent(getApplicationContext(), LoginActivity.class));
            finish();

        }

    }

}