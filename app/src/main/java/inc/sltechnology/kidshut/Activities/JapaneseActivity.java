package inc.sltechnology.kidshut.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

import inc.sltechnology.kidshut.Adapters.JapaneseMoviesAdapter;
import inc.sltechnology.kidshut.Models.JapaneseMoviesModel;
import inc.sltechnology.kidshut.Adapters.JapaneseStoriesAdapter;
import inc.sltechnology.kidshut.Models.JapaneseStoriesModel;
import inc.sltechnology.kidshut.Models.DataModel;
import inc.sltechnology.kidshut.R;
import inc.sltechnology.kidshut.Adapters.SliderAdapter;

public class JapaneseActivity extends AppCompatActivity {

    ProgressBar progressBar;

    // Write a message to the database
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();

    private List<DataModel> dataModels;
    private List<JapaneseStoriesModel> japaneseStoriesModels;
    private List<JapaneseMoviesModel> japaneseMoviesModels;

    private SliderAdapter sliderAdapter;
    SliderView sliderView;

    private RecyclerView JapaneseStoriesRecyclerView, JapaneseMoviesRecyclerView;

    private JapaneseStoriesAdapter japaneseStoriesAdapter;
    private JapaneseMoviesAdapter japaneseMoviesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_japanese);

        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        TextView textView1 = findViewById(R.id.japanese_animation_story);
        textView1.setEllipsize(TextUtils.TruncateAt.MARQUEE);
        textView1.setSelected(true);

        TextView textView2 = findViewById(R.id.japanese_animation_movie);
        textView2.setEllipsize(TextUtils.TruncateAt.MARQUEE);
        textView2.setSelected(true);

        progressBar = findViewById(R.id.progress_bar1);

        FirebaseApp.initializeApp(this);

        sliderView = findViewById(R.id.sliderViewJapanese);

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

                Toast.makeText(JapaneseActivity.this, "" +error.getMessage(), Toast.LENGTH_SHORT).show();
                finish();

            }
        });

        //Japanese Stories
        loadJapaneseStoriesData();

    }

    private void loadJapaneseStoriesData() {

        DatabaseReference JSRef = database.getReference("japanesestories"); //***
        JapaneseStoriesRecyclerView = findViewById(R.id.recyclerViewForJapaneseAnimationStories); //***

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.HORIZONTAL);

        layoutManager.setReverseLayout(true);
        layoutManager.setStackFromEnd(true);

        JapaneseStoriesRecyclerView.setLayoutManager(layoutManager); //***

        japaneseStoriesModels = new ArrayList<>(); //***
        japaneseStoriesAdapter = new JapaneseStoriesAdapter(japaneseStoriesModels); //***
        JapaneseStoriesRecyclerView.setAdapter(japaneseStoriesAdapter); //***

        JSRef.addValueEventListener(new ValueEventListener() { //***
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot contentSnapShot:snapshot.getChildren()){

                    JapaneseStoriesModel dataModel = contentSnapShot.getValue(JapaneseStoriesModel.class); //***
                    japaneseStoriesModels.add(dataModel); //***
                    progressBar.setVisibility(View.GONE);

                }

                japaneseStoriesAdapter.notifyDataSetChanged(); //***

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        //Japanese Movies
        loadJapaneseMoviesData();

    }

    private void loadJapaneseMoviesData() {

        DatabaseReference JMRef = database.getReference("japanesemovies"); //***
        JapaneseMoviesRecyclerView = findViewById(R.id.recyclerViewForJapaneseAnimationMovies); //***

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.HORIZONTAL);

        layoutManager.setReverseLayout(true);
        layoutManager.setStackFromEnd(true);

        JapaneseMoviesRecyclerView.setLayoutManager(layoutManager); //***

        japaneseMoviesModels = new ArrayList<>(); //***
        japaneseMoviesAdapter = new JapaneseMoviesAdapter(japaneseMoviesModels); //***
        JapaneseMoviesRecyclerView.setAdapter(japaneseMoviesAdapter); //***

        JMRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot contentSnapShot:snapshot.getChildren()){

                    JapaneseMoviesModel dataModel = contentSnapShot.getValue(JapaneseMoviesModel.class); //***
                    japaneseMoviesModels.add(dataModel); //***
                    progressBar.setVisibility(View.GONE);

                }

                japaneseMoviesAdapter.notifyDataSetChanged(); //***

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