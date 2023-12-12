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

import inc.sltechnology.kidshut.Models.DataModel;
import inc.sltechnology.kidshut.R;
import inc.sltechnology.kidshut.Adapters.SinhalaMoviesAdapter;
import inc.sltechnology.kidshut.Models.SinhalaMoviesModel;
import inc.sltechnology.kidshut.Adapters.SinhalaStoriesAdapter;
import inc.sltechnology.kidshut.Models.SinhalaStoriesModel;
import inc.sltechnology.kidshut.Adapters.SliderAdapter;

public class SinhalaActivity extends AppCompatActivity {

    ProgressBar progressBar;

    // Write a message to the database
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();

    private List<DataModel> dataModels;
    private List<SinhalaStoriesModel> sinhalaStoriesModels;
    private List<SinhalaMoviesModel> sinhalaMoviesModels;

    private SliderAdapter sliderAdapter;
    SliderView sliderView;

    private RecyclerView SinhalaStoriesRecyclerView, SinhalaMoviesRecyclerView;

    private SinhalaStoriesAdapter sinhalaStoriesAdapter;
    private SinhalaMoviesAdapter sinhalaMoviesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sinhala);

        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        TextView textView1 = findViewById(R.id.sinhala_animation_story);
        textView1.setEllipsize(TextUtils.TruncateAt.MARQUEE);
        textView1.setSelected(true);

        TextView textView2 = findViewById(R.id.sinhala_animation_movie);
        textView2.setEllipsize(TextUtils.TruncateAt.MARQUEE);
        textView2.setSelected(true);

        progressBar = findViewById(R.id.progress_bar1);

        FirebaseApp.initializeApp(this);

        sliderView = findViewById(R.id.sliderViewSinhala);

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

                Toast.makeText(SinhalaActivity.this, "" +error.getMessage(), Toast.LENGTH_SHORT).show();
                finish();

            }
        });

        //Sinhala Stories
        loadSinhalaStoriesData();

    }



    private void loadSinhalaStoriesData() {

        DatabaseReference SSRef = database.getReference("sinhalastories"); //***
        SinhalaStoriesRecyclerView = findViewById(R.id.recyclerViewForSinhalaAnimationStories); //***

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.HORIZONTAL);

        layoutManager.setReverseLayout(true);
        layoutManager.setStackFromEnd(true);

        SinhalaStoriesRecyclerView.setLayoutManager(layoutManager); //***

        sinhalaStoriesModels = new ArrayList<>(); //***
        sinhalaStoriesAdapter = new SinhalaStoriesAdapter(sinhalaStoriesModels); //***
        SinhalaStoriesRecyclerView.setAdapter(sinhalaStoriesAdapter); //***

        SSRef.addValueEventListener(new ValueEventListener() { //***
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot contentSnapShot:snapshot.getChildren()){

                    SinhalaStoriesModel dataModel = contentSnapShot.getValue(SinhalaStoriesModel.class); //***
                    sinhalaStoriesModels.add(dataModel); //***
                    progressBar.setVisibility(View.GONE);

                }

                sinhalaStoriesAdapter.notifyDataSetChanged(); //***

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        //Sinhala Movies
        loadSinhalaMoviesData();

    }



    private void loadSinhalaMoviesData() {

        DatabaseReference SMRef = database.getReference("sinhalamovies"); //***
        SinhalaMoviesRecyclerView = findViewById(R.id.recyclerViewForSinhalaAnimationMovies); //***

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.HORIZONTAL);

        layoutManager.setReverseLayout(true);
        layoutManager.setStackFromEnd(true);

        SinhalaMoviesRecyclerView.setLayoutManager(layoutManager); //***

        sinhalaMoviesModels = new ArrayList<>(); //***
        sinhalaMoviesAdapter = new SinhalaMoviesAdapter(sinhalaMoviesModels); //***
        SinhalaMoviesRecyclerView.setAdapter(sinhalaMoviesAdapter); //***

        SMRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot contentSnapShot:snapshot.getChildren()){

                    SinhalaMoviesModel dataModel = contentSnapShot.getValue(SinhalaMoviesModel.class); //***
                    sinhalaMoviesModels.add(dataModel); //***
                    progressBar.setVisibility(View.GONE);

                }

                sinhalaMoviesAdapter.notifyDataSetChanged(); //***

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