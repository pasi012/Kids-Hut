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
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import inc.sltechnology.kidshut.R;

public class RegisterActivity extends AppCompatActivity {

    EditText registerName, registerEmail, registerPassword, confirmPassword;
    Button registerBtn;
    TextView loginBtn;

    FirebaseAuth firebaseAuth;
    VideoView videoView;

    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        videoView = findViewById(R.id.video2);

        Uri uri = Uri.parse("https://firebasestorage.googleapis.com/v0/b/kids-hut-6b2ed.appspot.com/o/Butterfly%20Animation%20(1).mp4?alt=media&token=9105e761-3fc2-48e1-abde-7ce76fae4c71");

        videoView.setVideoURI(uri);
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.setLooping(true);
            }
        });

        videoView.start();

        registerName = findViewById(R.id.UserName);
        registerEmail = findViewById(R.id.email);
        registerPassword = findViewById(R.id.Password2);
        confirmPassword = findViewById(R.id.conPassword);
        registerBtn = findViewById(R.id.btn_Register);
        loginBtn = findViewById(R.id.btn_login2);

        progressDialog = new ProgressDialog(this);

        firebaseAuth = FirebaseAuth.getInstance();

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();

            }
        });

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String fullName = registerName.getText().toString();
                String email = registerEmail.getText().toString();
                String password = registerPassword.getText().toString();
                String conPassword = confirmPassword.getText().toString();

                if (fullName.isEmpty()){

                    registerName.setError("Name Is Required");
                    return;

                }else if (email.isEmpty() || !email.matches(emailPattern)){

                    registerEmail.setError("Email Is Required Or Email is Incorrect!");
                    return;

                }else if (password.isEmpty() || password.length()<6){

                    registerPassword.setError("Password Is Required Please Enter 6 Digit");
                    return;

                }else if (conPassword.isEmpty()){

                    confirmPassword.setError("Confirm Password Is Required");
                    return;

                }else if (!password.equals(conPassword)){

                    confirmPassword.setError("Password Do Not Match");
                    return;

                }else {

                    progressDialog.setMessage("Please Wait While Registration...");
                    progressDialog.setTitle("Registration");
                    progressDialog.setCanceledOnTouchOutside(false);
                    progressDialog.show();
                    Toast.makeText(RegisterActivity.this, "Data Validated", Toast.LENGTH_SHORT).show();

                }

                firebaseAuth.createUserWithEmailAndPassword(email, password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {

                        FirebaseUser user = firebaseAuth.getCurrentUser();

                        user.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {

                                        if (task.isSuccessful()) {

                                            Toast.makeText(RegisterActivity.this, "Email sent.", Toast.LENGTH_SHORT).show();

                                            progressDialog.dismiss();
                                            FirebaseAuth.getInstance().signOut();
                                            startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                                            Toast.makeText(RegisterActivity.this, "Registration is Successful.", Toast.LENGTH_SHORT).show();
                                            Toast.makeText(RegisterActivity.this, "Please Check Your Email For Verification.", Toast.LENGTH_LONG).show();
                                            finish();

                                        }else {

                                            Toast.makeText(RegisterActivity.this, "Error: " + task.getException().getMessage(), Toast.LENGTH_LONG).show();

                                        }

                                    }
                        });

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                        progressDialog.dismiss();
                        Toast.makeText(RegisterActivity.this, "Registration Failure:", Toast.LENGTH_SHORT).show();
                        Toast.makeText(RegisterActivity.this, "Error:" + e.getMessage(), Toast.LENGTH_SHORT).show();

                        registerName.setText("");
                        registerEmail.setText("");
                        registerPassword.setText("");
                        confirmPassword.setText("");

                    }
                });

            }
        });

    }

    @Override
    public void onBackPressed() {

        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();

    }

}