package inc.sltechnology.kidshut.loginActivities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Arrays;

import inc.sltechnology.kidshut.HomeActivity;
import inc.sltechnology.kidshut.R;

import static android.content.ContentValues.TAG;

public class Facebook_Login_Activity extends LoginActivity {

    CallbackManager callbackManager;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        firebaseAuth = FirebaseAuth.getInstance();

        callbackManager = CallbackManager.Factory.create();

        LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("public_profile"));

        LoginManager.getInstance().registerCallback(callbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {

                        handleFacebookAccessToken(loginResult.getAccessToken());

                    }

                    @Override
                    public void onCancel() {

                        Intent intent = new Intent(Facebook_Login_Activity.this, LoginActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);

                        Toast.makeText(Facebook_Login_Activity.this, "Facebook Login Cancel", Toast.LENGTH_SHORT).show();
                        finish();

                    }

                    @Override
                    public void onError(FacebookException e) {

                        Intent intent = new Intent(Facebook_Login_Activity.this, LoginActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);

                        Toast.makeText(Facebook_Login_Activity.this, "Oops! Facebook Login Failure:", Toast.LENGTH_SHORT).show();
                        Toast.makeText(Facebook_Login_Activity.this, "Error:" + e.getMessage(), Toast.LENGTH_LONG).show();
                        finish();

                    }
                });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Pass the activity result back to the Facebook SDK
        callbackManager.onActivityResult(requestCode, resultCode, data);

    }

    private void handleFacebookAccessToken(AccessToken token) {

        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());

        firebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            FirebaseUser user = firebaseAuth.getCurrentUser();
                            updateUI(user);

                        } else {

                            Toast.makeText(Facebook_Login_Activity.this, "Authentication Failed:", Toast.LENGTH_SHORT).show();
                            Toast.makeText(Facebook_Login_Activity.this, "Error:" + task.getException(), Toast.LENGTH_SHORT).show();
                            updateUI(null);
                            finish();

                        }
                    }
                });
    }

    private void updateUI(FirebaseUser user) {

        Intent intent = new Intent(Facebook_Login_Activity.this, HomeActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);

        Toast.makeText(this, "Login Successful.", Toast.LENGTH_SHORT).show();
        finish();

    }

}