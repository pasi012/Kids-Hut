package inc.sltechnology.kidshut;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import inc.sltechnology.kidshut.Adapters.CastAdapter;
import inc.sltechnology.kidshut.Adapters.PartAdapter;
import inc.sltechnology.kidshut.Models.PartModel;
import inc.sltechnology.kidshut.Models.castModel;

public class DetailsActivity extends AppCompatActivity {

    public static final int PERMISSION_STORAGE_CODE = 1000;

    private List<castModel> castModels;
    private List<PartModel> partModels;
    private CastAdapter castAdapter;
    private PartAdapter partAdapter;
    private Button button;
    private String downloadUrl;

    private ImageButton imageButton;

    private RecyclerView part_recycler_view, cast_recycler_view;

    private ImageView thumb, cover, download, share;
    private TextView title, desc;
    private FloatingActionButton actionButton;
    private String title_movie;
    private String desc_movie;
    private String thumb_movie;
    private String link_movie;
    private String cover_movie;
    private String cast_movie;
    private String trailer_movie;
    private String movie_video;
    
    DatabaseReference databaseReference, likesReference;
    RecyclerView recyclerView;
    FirebaseDatabase database;
    String name;
    Boolean likeChecker = false;

    ImageView comment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        comment = findViewById(R.id.comment);

        imageButton = findViewById(R.id.back1);

        share = findViewById(R.id.share);
        download = findViewById(R.id.download);
        button = findViewById(R.id.watch);

        thumb = findViewById(R.id.thumb);
        cover = findViewById(R.id.cover);
        title = findViewById(R.id.title_details);
        desc = findViewById(R.id.desc);
        actionButton = findViewById(R.id.floatingActionButton2);
        cast_recycler_view = findViewById(R.id.recyclerView_casts);
        part_recycler_view = findViewById(R.id.recyclerView_parts);

        recyclerView = findViewById(R.id.favouriteMovies);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("members");
        likesReference = database.getReference("likes");

        //getting data intent
        title_movie = getIntent().getStringExtra("title");
        desc_movie = getIntent().getStringExtra("desc");
        thumb_movie = getIntent().getStringExtra("thumb");
        link_movie = getIntent().getStringExtra("link");
        cover_movie = getIntent().getStringExtra("cover");
        cast_movie = getIntent().getStringExtra("cast");
        trailer_movie = getIntent().getStringExtra("T_Link");
        movie_video = getIntent().getStringExtra("MVLink");

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(title_movie);

        Glide.with(this).load(thumb_movie).into(thumb);
        Glide.with(this).load(cover_movie).into(cover);

        thumb.setAnimation(AnimationUtils.loadAnimation(this, R.anim.scale_animation));
        cover.setAnimation(AnimationUtils.loadAnimation(this, R.anim.scale_animation));

        title.setText(title_movie);
        desc.setText(desc_movie);

        comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(DetailsActivity.this, CommentActivity.class);
                startActivity(intent);

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
                myRef.child("link").child(movie_video).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                            String vidUrl = snapshot.getValue(String.class);
                            Intent intent = new Intent(DetailsActivity.this, PlayerActivity.class);
                            intent.putExtra("vid", vidUrl);
                            startActivity(intent);

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                        Toast.makeText(DetailsActivity.this, "Error !", Toast.LENGTH_SHORT).show();

                    }
                });
            }
        });

        download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference downloadRef = database.getReference();
                downloadRef.child("link").child(movie_video).addValueEventListener(new ValueEventListener() {
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

                        Toast.makeText(DetailsActivity.this, "Error !", Toast.LENGTH_SHORT).show();

                    }
                });
            }
        });

        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference shareRef = database.getReference();
                shareRef.child("link").child(movie_video).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        String shareUrl = snapshot.getValue(String.class);
                        Intent intent = new Intent(Intent.ACTION_SEND);
                        intent.setType("text/plain");
                        String body = title_movie;
                        String sub = "https://play.google.com/store/apps/details?id=" + BuildConfig.APPLICATION_ID;
                        intent.putExtra(Intent.EXTRA_TEXT, body);
                        intent.putExtra(Intent.EXTRA_TEXT, sub);
                        startActivity(Intent.createChooser(intent, "Share Using"));

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                        Toast.makeText(DetailsActivity.this, "Error !", Toast.LENGTH_SHORT).show();

                    }
                });
            }
        });

        actionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference showRef = database.getReference();
                showRef.child("link").child(trailer_movie).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        String vidUrl = snapshot.getValue(String.class);
                        Intent intent = new Intent(DetailsActivity.this, PlayerActivity.class);
                        intent.putExtra("vid", vidUrl);
                        startActivity(intent);
                        
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                        Toast.makeText(DetailsActivity.this, "Error !", Toast.LENGTH_SHORT).show();

                    }
                });

            }
        });

        loadCast(); //****
        loadPart();

    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseRecyclerOptions<Member> options =
                new FirebaseRecyclerOptions.Builder<Member>()
                .setQuery(databaseReference, Member.class)
                .build();

        FirebaseRecyclerAdapter<Member, ViewHolder> firebaseRecyclerAdapter =
                new FirebaseRecyclerAdapter<Member, ViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull ViewHolder holder, int position, @NonNull Member model) {

                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                String currentUserId = user.getUid();
                final String postKey = getRef(position).getKey();

                holder.setLikebuttonStatus(postKey);
                holder.likeButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        likeChecker = true;
                        likesReference.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                if (likeChecker.equals(true)){

                                    if (dataSnapshot.child(postKey).hasChild(currentUserId)){

                                        likesReference.child(postKey).child(currentUserId).removeValue();
                                        likeChecker = false;

                                    }else {

                                        likesReference.child(postKey).child(currentUserId).setValue(true);
                                        likeChecker = false;

                                    }

                                }

                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });

                    }
                });

            }

            @NonNull
            @Override
            public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.favorite_holder,
                        parent, false);

                return new ViewHolder(view);

            }
        };

        firebaseRecyclerAdapter.startListening();
        recyclerView.setAdapter(firebaseRecyclerAdapter);

    }

    private void startDownloading(String downloadUrl) {

        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(downloadUrl));
        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI | DownloadManager.Request.NETWORK_MOBILE);
        request.setTitle(title_movie);
        request.setDescription("Downloading...");

        request.allowScanningByMediaScanner();
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, ""+ System.currentTimeMillis());

        DownloadManager manager = (DownloadManager)getSystemService(Context.DOWNLOAD_SERVICE);
        manager.enqueue(request);

    }

    private void loadPart() {

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference partRef = database.getReference();
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.HORIZONTAL);
        part_recycler_view.setLayoutManager(layoutManager);

        partModels = new ArrayList<>();
        partAdapter = new PartAdapter(partModels);
        part_recycler_view.setAdapter(partAdapter);

        partRef.child("link").child(link_movie).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot content:snapshot.getChildren()){

                    PartModel partModel = content.getValue(PartModel.class);
                    partModels.add(partModel);

                }

                partAdapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    private void loadCast() {

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference castRef = database.getReference();
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.HORIZONTAL);
        cast_recycler_view.setLayoutManager(layoutManager);

        castModels = new ArrayList<>();
        castAdapter = new CastAdapter(castModels);
        cast_recycler_view.setAdapter(castAdapter);

        castRef.child("cast").child(cast_movie).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot content:snapshot.getChildren()){

                    castModel castModel = content.getValue(castModel.class);
                    castModels.add(castModel);

                }

                castAdapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

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