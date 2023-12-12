package inc.sltechnology.kidshut;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import inc.sltechnology.kidshut.Adapters.CommentAdapter;
import inc.sltechnology.kidshut.Models.CommentModel;

public class CommentActivity extends AppCompatActivity {

    EditText addComment;
    ImageView image_profile;
    ImageButton postBtn;

    String postId;

    FirebaseUser firebaseUser;
    FirebaseDatabase database;
    RecyclerView recyclerView;
    DatabaseReference databaseReference;

    CommentAdapter commentAdapter;
    List<CommentModel> listComments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);

        postId = databaseReference.getKey();
        database = FirebaseDatabase.getInstance();

        Toolbar toolbar = findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Comments");

        addComment = findViewById(R.id.addComment);
        image_profile = findViewById(R.id.imageProfile);
        postBtn = findViewById(R.id.post);

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        postBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                postBtn.setVisibility(View.INVISIBLE);

                if (addComment.getText().toString().equals("")){

                    Toast.makeText(CommentActivity.this,"Please Write Somethings!!!",Toast.LENGTH_SHORT).show();
                    postBtn.setVisibility(View.VISIBLE);

                }else {

                    DatabaseReference commentRef = database.getReference("comment").child(postId).push();
                    String content = addComment.getText().toString();
                    String uid = firebaseUser.getUid();
                    String uName = firebaseUser.getDisplayName();
                    CommentModel comment = new CommentModel(content,uid,uName);

                    commentRef.setValue(comment).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {

                            showMassege("Comment Added");
                            addComment.setText("");
                            postBtn.setVisibility(View.VISIBLE);

                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {

                            showMassege("Comment Added Fail!"+e.getMessage());

                        }
                    });

                }

            }
        });

        iniComment();

    }

    private void iniComment() {

        DatabaseReference commentsRef = database.getReference("comment").child(postId);
        commentsRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                listComments = new ArrayList<>();

                for (DataSnapshot snap:snapshot.getChildren()){

                    CommentModel commentModel = snap.getValue(CommentModel.class);
                    listComments.add(commentModel);

                }

                commentAdapter = new CommentAdapter(getApplicationContext(),listComments);
                recyclerView.setAdapter(commentAdapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    private void showMassege(String massege) {

        Toast.makeText(this,massege,Toast.LENGTH_LONG).show();

    }

}