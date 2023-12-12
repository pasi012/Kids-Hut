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

import inc.sltechnology.kidshut.Adapters.EnglishMoviesAdapter;
import inc.sltechnology.kidshut.Models.EnglishMoviesModel;
import inc.sltechnology.kidshut.Adapters.EnglishStoriesAdapter;
import inc.sltechnology.kidshut.Models.EnglishStoriesModel;
import inc.sltechnology.kidshut.Models.DataModel;
import inc.sltechnology.kidshut.R;
import inc.sltechnology.kidshut.Adapters.SliderAdapter;

public class EnglishActivity extends AppCompatActivity {

    ProgressBar progressBar;

    // Write a message to the database
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();

    private List<DataModel> dataModels;
    private List<EnglishStoriesModel> englishStoriesModels; // model class for EngStories //***
    private List<EnglishMoviesModel> englishMoviesModels;

    private SliderAdapter sliderAdapter;
    SliderView sliderView;

    private RecyclerView EnglishStoriesRecyclerView,
                         EnglishMoviesRecyclerView;

    private EnglishStoriesAdapter englishStoriesAdapter; //*****
    private EnglishMoviesAdapter englishMoviesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_english);

        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        progressBar = findViewById(R.id.progress_bar1);

        TextView textView1 = findViewById(R.id.eng_animation_story);
        textView1.setEllipsize(TextUtils.TruncateAt.MARQUEE);
        textView1.setSelected(true);

        TextView textView2 = findViewById(R.id.eng_animation_movie);
        textView2.setEllipsize(TextUtils.TruncateAt.MARQUEE);
        textView2.setSelected(true);

        FirebaseApp.initializeApp(this);

        sliderView = findViewById(R.id.sliderViewEnglish);

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

                Toast.makeText(EnglishActivity.this, "" +error.getMessage(), Toast.LENGTH_SHORT).show();
                finish();

            }
        });

        //English Stories
        loadEnglishStoriesData(); //****

    }



    private void loadEnglishStoriesData() {

        DatabaseReference ESRef = database.getReference("englishstories");
        EnglishStoriesRecyclerView = findViewById(R.id.recyclerViewForEngAnimationStories);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.HORIZONTAL);

        layoutManager.setReverseLayout(true);
        layoutManager.setStackFromEnd(true);

        EnglishStoriesRecyclerView.setLayoutManager(layoutManager);

        englishStoriesModels = new ArrayList<>();
        englishStoriesAdapter = new EnglishStoriesAdapter(englishStoriesModels);
        EnglishStoriesRecyclerView.setAdapter(englishStoriesAdapter);

        ESRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot contentSnapShot:snapshot.getChildren()){

                    EnglishStoriesModel dataModel = contentSnapShot.getValue(EnglishStoriesModel.class);
                    englishStoriesModels.add(dataModel);
                    progressBar.setVisibility(View.GONE);

                }

                englishStoriesAdapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        //English Movies
        loadEnglishMoviesData(); //*****

    }



    private void loadEnglishMoviesData() {

        DatabaseReference EMRef = database.getReference("englishmovies");
        EnglishMoviesRecyclerView = findViewById(R.id.recyclerViewEngForAnimationMovies);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.HORIZONTAL);

        layoutManager.setReverseLayout(true);
        layoutManager.setStackFromEnd(true);

        EnglishMoviesRecyclerView.setLayoutManager(layoutManager);

        englishMoviesModels = new ArrayList<>();
        englishMoviesAdapter = new EnglishMoviesAdapter(englishMoviesModels);
        EnglishMoviesRecyclerView.setAdapter(englishMoviesAdapter);

        EMRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot contentSnapShot : snapshot.getChildren()) {

                    EnglishMoviesModel dataModel = contentSnapShot.getValue(EnglishMoviesModel.class);
                    englishMoviesModels.add(dataModel);
                    progressBar.setVisibility(View.GONE);

                }

                englishMoviesAdapter.notifyDataSetChanged();

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