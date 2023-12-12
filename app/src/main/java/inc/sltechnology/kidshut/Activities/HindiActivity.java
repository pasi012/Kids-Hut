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

import inc.sltechnology.kidshut.Adapters.HindiMoviesAdapter;
import inc.sltechnology.kidshut.Models.HindiMoviesModel;
import inc.sltechnology.kidshut.Adapters.HindiStoriesAdapter;
import inc.sltechnology.kidshut.Models.HindiStoriesModel;
import inc.sltechnology.kidshut.Models.DataModel;
import inc.sltechnology.kidshut.R;
import inc.sltechnology.kidshut.Adapters.SliderAdapter;

public class HindiActivity extends AppCompatActivity {

    ProgressBar progressBar;

    // Write a message to the database
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();

    private List<DataModel> dataModels;
    private List<HindiStoriesModel> hindiStoriesModels;
    private List<HindiMoviesModel> hindiMoviesModels;

    private SliderAdapter sliderAdapter;
    SliderView sliderView;

    private RecyclerView HindiStoriesRecyclerView, HindiMoviesRecyclerView;

    private HindiStoriesAdapter hindiStoriesAdapter;
    private HindiMoviesAdapter hindiMoviesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hindi);

        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        TextView textView1 = findViewById(R.id.hindi_animation_story);
        textView1.setEllipsize(TextUtils.TruncateAt.MARQUEE);
        textView1.setSelected(true);

        TextView textView2 = findViewById(R.id.hindi_animation_movie);
        textView2.setEllipsize(TextUtils.TruncateAt.MARQUEE);
        textView2.setSelected(true);

        progressBar = findViewById(R.id.progress_bar1);

        FirebaseApp.initializeApp(this);

        sliderView = findViewById(R.id.sliderViewHindi);

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

                Toast.makeText(HindiActivity.this, "" +error.getMessage(), Toast.LENGTH_SHORT).show();
                finish();

            }
        });

        //Hindi Stories
        loadHindiStoriesData();

    }

    private void loadHindiStoriesData() {

        DatabaseReference HSRef = database.getReference("hindistories"); //***
        HindiStoriesRecyclerView = findViewById(R.id.recyclerViewForHindiAnimationStories); //***

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.HORIZONTAL);

        layoutManager.setReverseLayout(true);
        layoutManager.setStackFromEnd(true);

        HindiStoriesRecyclerView.setLayoutManager(layoutManager); //***

        hindiStoriesModels = new ArrayList<>(); //***
        hindiStoriesAdapter = new HindiStoriesAdapter(hindiStoriesModels); //***
        HindiStoriesRecyclerView.setAdapter(hindiStoriesAdapter); //***

        HSRef.addValueEventListener(new ValueEventListener() { //***
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot contentSnapShot:snapshot.getChildren()){

                    HindiStoriesModel dataModel = contentSnapShot.getValue(HindiStoriesModel.class); //***
                    hindiStoriesModels.add(dataModel); //***
                    progressBar.setVisibility(View.GONE);

                }

                hindiStoriesAdapter.notifyDataSetChanged(); //***

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        //Hindi Movies
        loadHindiMoviesData();

    }



    private void loadHindiMoviesData() {

        DatabaseReference HMRef = database.getReference("hindimovies"); //***
        HindiMoviesRecyclerView = findViewById(R.id.recyclerViewForHindiAnimationMovies); //***

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.HORIZONTAL);

        layoutManager.setReverseLayout(true);
        layoutManager.setStackFromEnd(true);

        HindiMoviesRecyclerView.setLayoutManager(layoutManager); //***

        hindiMoviesModels = new ArrayList<>(); //***
        hindiMoviesAdapter = new HindiMoviesAdapter(hindiMoviesModels); //***
        HindiMoviesRecyclerView.setAdapter(hindiMoviesAdapter); //***

        HMRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot contentSnapShot:snapshot.getChildren()){

                    HindiMoviesModel dataModel = contentSnapShot.getValue(HindiMoviesModel.class); //***
                    hindiMoviesModels.add(dataModel); //***
                    progressBar.setVisibility(View.GONE);

                }

                hindiMoviesAdapter.notifyDataSetChanged(); //***

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