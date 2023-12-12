package inc.sltechnology.kidshut.loginActivities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.facebook.FacebookSdk;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import inc.sltechnology.kidshut.MainActivity;
import inc.sltechnology.kidshut.R;

public class LoginActivity extends AppCompatActivity {

    Button loginBtn;
    EditText email, password;
    FirebaseAuth firebaseAuth;
    TextView createAccountBtn;
    VideoView videoView;
    ImageButton fbLogin, googleLogin;

    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        progressDialog = new ProgressDialog(this);

        videoView = findViewById(R.id.video1);

        Uri uri = Uri.parse("https://firebasestorage.googleapis.com/v0/b/kids-hut-6b2ed.appspot.com/o/Butterfly%20Animation%20(1).mp4?alt=media&token=9105e761-3fc2-48e1-abde-7ce76fae4c71");

        videoView.setVideoURI(uri);
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.setLooping(true);
            }
        });

        videoView.start();

        createAccountBtn = findViewById(R.id.btn_Register1);
        loginBtn = findViewById(R.id.btn_login);
        email = findViewById(R.id.etEmail);
        password = findViewById(R.id.etPassword);

        firebaseAuth = FirebaseAuth.getInstance();

        googleLogin = findViewById(R.id.login_button_google);

        googleLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(LoginActivity.this, GoogleLoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent);

            }
        });

        FacebookSdk.sdkInitialize(getApplicationContext());
        fbLogin = findViewById(R.id.login_button_fb);

        fbLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(LoginActivity.this, Facebook_Login_Activity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent);

            }
        });

        createAccountBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
                finish();

            }
        });

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (email.getText().toString().isEmpty()){

                    email.setError("Email Required.");
                    return;

                }else if (password.getText().toString().isEmpty()){

                    password.setError("Password Required.");
                    return;

                }else {

                    progressDialog.setMessage("Please Wait While Login...");
                    progressDialog.setTitle("Login");
                    progressDialog.setCanceledOnTouchOutside(false);
                    progressDialog.show();

                }

                firebaseAuth.signInWithEmailAndPassword(email.getText().toString(), password.getText().toString()).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {

                        if (firebaseAuth.getCurrentUser().isEmailVerified()){

                            progressDialog.dismiss();
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
                            Toast.makeText(LoginActivity.this, "Login is Successful.", Toast.LENGTH_SHORT).show();
                            email.setText("");
                            password.setText("");
                            finish();

                        }else {

                            progressDialog.dismiss();
                            Toast.makeText(LoginActivity.this, "Oops! Login is Failure:\uD83D\uDE2C Please Verify Your Email.", Toast.LENGTH_LONG).show();
                            email.setText("");
                            password.setText("");

                            FirebaseUser user = firebaseAuth.getCurrentUser();

                            user.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {

                                    if (task.isSuccessful()) {

                                        Toast.makeText(LoginActivity.this, "Email sent.", Toast.LENGTH_SHORT).show();

                                        progressDialog.dismiss();
                                        FirebaseAuth.getInstance().signOut();
                                        startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                                        Toast.makeText(LoginActivity.this, "Please Check Your Email For Verification.", Toast.LENGTH_LONG).show();
                                        finish();

                                    }else {

                                        Toast.makeText(LoginActivity.this, "Error: " + task.getException().getMessage(), Toast.LENGTH_LONG).show();

                                    }

                                }
                            });

                        }

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                        progressDialog.dismiss();
                        Toast.makeText(LoginActivity.this, "Oops! Login Failure:\uD83D\uDE2C Check Your Email, Password And Try Again", Toast.LENGTH_LONG).show();
                        Toast.makeText(LoginActivity.this, "Error:" + e.getMessage(), Toast.LENGTH_SHORT).show();
                        email.setText("");
                        password.setText("");

                    }
                });
            }
        });

    }

    @Override
    public void onBackPressed() {

        finish();

    }

}