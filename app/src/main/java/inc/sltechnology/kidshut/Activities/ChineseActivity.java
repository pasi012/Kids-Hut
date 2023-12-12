package inc.sltechnology.kidshut.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;
import java.util.List;

import inc.sltechnology.kidshut.Adapters.ChineseMoviesAdapter;
import inc.sltechnology.kidshut.Models.ChineseMoviesModel;
import inc.sltechnology.kidshut.Adapters.ChineseStoriesAdapter;
import inc.sltechnology.kidshut.Models.ChineseStoriesModel;
import inc.sltechnology.kidshut.Models.DataModel;
import inc.sltechnology.kidshut.R;
import inc.sltechnology.kidshut.Adapters.SliderAdapter;

public class ChineseActivity extends AppCompatActivity {

    ProgressBar progressBar;

    // Write a message to the database
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();

    private List<DataModel> dataModels;
    private List<ChineseStoriesModel> chineseStoriesModels;
    private List<ChineseMoviesModel> chineseMoviesModels;

    private SliderAdapter sliderAdapter;
    SliderView sliderView;

    private RecyclerView ChineseStoriesRecyclerView, ChineseMoviesRecyclerView;

    private ChineseStoriesAdapter chineseStoriesAdapter;
    private ChineseMoviesAdapter chineseMoviesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chinese);

        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        TextView textView1 = findViewById(R.id.chinese_animation_story);
        textView1.setEllipsize(TextUtils.TruncateAt.MARQUEE);
        textView1.setSelected(true);

        TextView textView2 = findViewById(R.id.chinese_animation_movie);
        textView2.setEllipsize(TextUtils.TruncateAt.MARQUEE);
        textView2.setSelected(true);

        progressBar = findViewById(R.id.progress_bar1);

        FirebaseApp.initializeApp(this);

        sliderView = findViewById(R.id.sliderViewChinese);

        sliderAdapter = new SliderAdapter(this);
        sliderView.setSliderAdapter(sliderAdapter);
        sliderView.setIndicatorAnimation(IndicatorAnimationType.WORM);
        sliderView.setSliderTransformAnimation(SliderAnimations.HORIZONTALFLIPTRANSFORMATION);
         //can increase times of Auto slider
        renewItems(sliderView);
        sliderView.startAutoCycle();

        //load data from firebase
        loadFirebaseForSlider();

    }

    private void loadFirebaseForSlider() {

        myRef.child("trailer").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot contentSlider: snapshot.getChildren()){

                    DataModel sliderItem = contentSlider.getValue(DataModel.class);
                    dataModels.add(sliderItem);
                    progressBar.setVisibility(View.GONE);

                }

                sliderAdapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Toast.makeText(ChineseActivity.this, "" +error.getMessage(), Toast.LENGTH_SHORT).show();
                finish();

            }
        });

        //Chinese Stories
        loadChineseStoriesData();

    }



    private void loadChineseStoriesData() {

        DatabaseReference CSRef = database.getReference("chinesestories"); //***
        ChineseStoriesRecyclerView = findViewById(R.id.recyclerViewForChineseAnimationStories); //***

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.HORIZONTAL);

        layoutManager.setReverseLayout(true);
        layoutManager.setStackFromEnd(true);

        ChineseStoriesRecyclerView.setLayoutManager(layoutManager); //***

        chineseStoriesModels = new ArrayList<>(); //***
        chineseStoriesAdapter = new ChineseStoriesAdapter(chineseStoriesModels); //***
        ChineseStoriesRecyclerView.setAdapter(chineseStoriesAdapter); //***

        CSRef.addValueEventListener(new ValueEventListener() { //***
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot contentSnapShot:snapshot.getChildren()){

                    ChineseStoriesModel dataModel = contentSnapShot.getValue(ChineseStoriesModel.class); //***
                    chineseStoriesModels.add(dataModel); //***
                    progressBar.setVisibility(View.GONE);

                }

                chineseStoriesAdapter.notifyDataSetChanged(); //***

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        //Chinese Movies
        loadChineseMoviesData();

    }



    private void loadChineseMoviesData() {

        DatabaseReference CMRef = database.getReference("chinesemovies"); //***
        ChineseMoviesRecyclerView = findViewById(R.id.recyclerViewForChineseAnimationMovies); //***

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.HORIZONTAL);

        layoutManager.setReverseLayout(true);
        layoutManager.setStackFromEnd(true);

        ChineseMoviesRecyclerView.setLayoutManager(layoutManager); //***

        chineseMoviesModels = new ArrayList<>(); //***
        chineseMoviesAdapter = new ChineseMoviesAdapter(chineseMoviesModels); //***
        ChineseMoviesRecyclerView.setAdapter(chineseMoviesAdapter); //***

        CMRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot contentSnapShot:snapshot.getChildren()){

                    ChineseMoviesModel dataModel = contentSnapShot.getValue(ChineseMoviesModel.class); //***
                    chineseMoviesModels.add(dataModel); //***
                    progressBar.setVisibility(View.GONE);

                }

                chineseMoviesAdapter.notifyDataSetChanged(); //***

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }


    public void renewItems(View view) {

        dataModels = new ArrayList<>();
        DataModel dataItems = new DataModel();
        dataModels.add(dataItems);

        sliderAdapter.renewItems(dataModels);
        sliderAdapter.deleteItems(0);

    }

}