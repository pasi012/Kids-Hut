package inc.sltechnology.kidshut;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.Manifest;
import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class StoriesActivity extends AppCompatActivity {

    public static final int PERMISSION_STORAGE_CODE = 1000;

    private Button button;
    private ImageButton imageButton;

    private ImageView thumb, cover, download, share;
    private TextView title, desc;

    private String title_story;
    private String desc_story;
    private String thumb_story;
    private String cover_story;
    private String story_video;
    private String downloadUrl;

    ImageView likeButton;
    TextView likeDisplay;
    int likesCount;

    DatabaseReference databaseReference, likesRef, likesReference;
    FirebaseDatabase database;
    Boolean likeChecker = false;
    FirebaseUser firebaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stories);

        likeButton = findViewById(R.id.favourite);
        likeDisplay = findViewById(R.id.like_count);

        database = FirebaseDatabase.getInstance();
        likesReference = database.getReference("likes");

        imageButton = findViewById(R.id.back2);
        share = findViewById(R.id.share);
        download = findViewById(R.id.download);
        button = findViewById(R.id.watch);
        thumb = findViewById(R.id.thumb);
        cover = findViewById(R.id.cover);
        title = findViewById(R.id.title_details);
        desc = findViewById(R.id.desc);

        //getting data intent
        title_story = getIntent().getStringExtra("title");
        desc_story = getIntent().getStringExtra("desc");
        thumb_story = getIntent().getStringExtra("thumb");
        cover_story = getIntent().getStringExtra("cover");
        story_video = getIntent().getStringExtra("SVLink");

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(title_story);

        Glide.with(this).load(thumb_story).into(thumb);
        Glide.with(this).load(cover_story).into(cover);

        thumb.setAnimation(AnimationUtils.loadAnimation(this, R.anim.scale_animation));
        cover.setAnimation(AnimationUtils.loadAnimation(this, R.anim.scale_animation));

        title.setText(title_story);
        desc.setText(desc_story);

        likeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                databaseReference = database.getReference("likes");
                firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
                String uid = firebaseUser.getUid();
                String postId = databaseReference.getKey();
                setLikebuttonStatus(postId);

                likeChecker = true;
                likesReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        if (likeChecker.equals(true)){

                            if (dataSnapshot.child(postId).hasChild(uid)){

                                likesReference.child(postId).child(uid).removeValue();
                                likeChecker = false;

                            }else {

                                likesReference.child(postId).child(uid).setValue(true);
                                likeChecker = false;

                            }

                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

            }
        });

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();

            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference();
                myRef.child("link").child(story_video).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        String vidUrl = snapshot.getValue(String.class);
                        Intent intent = new Intent(StoriesActivity.this, PlayerActivity.class);
                        intent.putExtra("vid", vidUrl);
                        startActivity(intent);

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                        Toast.makeText(StoriesActivity.this, "Error !", Toast.LENGTH_SHORT).show();

                    }
                });


            }
        });

        download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference();
                myRef.child("link").child(story_video).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){

                            if (checkCallingOrSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) ==
                                    PackageManager.PERMISSION_DENIED){

                                String permission = (Manifest.permission.WRITE_EXTERNAL_STORAGE);
                                requestPermissions(new String[]{permission},PERMISSION_STORAGE_CODE);

                            }else {

                                downloadUrl = snapshot.getValue(String.class);
                                startDownloading(downloadUrl);

                            }

                        }else {

                            downloadUrl = snapshot.getValue(String.class);
                            startDownloading(downloadUrl);

                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                        Toast.makeText(StoriesActivity.this, "Error !", Toast.LENGTH_SHORT).show();

                    }
                });
            }
        });

        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference shareRef = database.getReference();
                shareRef.child("link").child(story_video).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        String shareUrl = snapshot.getValue(String.class);
                        Intent intent = new Intent(Intent.ACTION_SEND);
                        intent.setType("text/plain");
                        String body = title_story;
                        String sub = shareUrl;
                        intent.putExtra(Intent.EXTRA_TEXT, body);
                        intent.putExtra(Intent.EXTRA_TEXT, sub);
                        startActivity(Intent.createChooser(intent, "Share Using"));

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                        Toast.makeText(StoriesActivity.this, "Error !", Toast.LENGTH_SHORT).show();

                    }
                });
            }
        });


    }

    public void setLikebuttonStatus(String postKey){

        likesRef = FirebaseDatabase.getInstance().getReference("likes");
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String userId = user.getUid();
        String likes = "likes";

        likesRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if (dataSnapshot.child(postKey).hasChild(userId)){

                    likesCount = (int) dataSnapshot.child(postKey).getChildrenCount();
                    likeButton.setImageResource(R.drawable.favo);
                    likeDisplay.setText(Integer.toString(likesCount) + likes);

                }else {

                    likesCount = (int) dataSnapshot.child(postKey).getChildrenCount();
                    likeButton.setImageResource(R.drawable.favo1);
                    likeDisplay.setText(Integer.toString(likesCount) + likes);

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    private void startDownloading(String downloadUrl) {

        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(downloadUrl));
        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI | DownloadManager.Request.NETWORK_MOBILE);
        request.setTitle(title_story);
        request.setDescription("Downloading...");

        request.allowScanningByMediaScanner();
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, ""+ System.currentTimeMillis());

        DownloadManager manager = (DownloadManager)getSystemService(Context.DOWNLOAD_SERVICE);
        manager.enqueue(request);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {

            case PERMISSION_STORAGE_CODE:

                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    startDownloading(downloadUrl);

                } else {

                    Toast.makeText(this, "Permission Denied!", Toast.LENGTH_SHORT).show();

                }
        }
    }

    @Override
    public void onBackPressed() {

        finish();

    }
}