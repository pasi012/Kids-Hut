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

import inc.sltechnology.kidshut.Adapters.SliderAdapter;
import inc.sltechnology.kidshut.Models.DataModel;
import inc.sltechnology.kidshut.R;
import inc.sltechnology.kidshut.Adapters.TamilMoviesAdapter;
import inc.sltechnology.kidshut.Models.TamilMoviesModel;
import inc.sltechnology.kidshut.Adapters.TamilStoriesAdapter;
import inc.sltechnology.kidshut.Models.TamilStoriesModel;

public class TamilActivity extends AppCompatActivity {

    ProgressBar progressBar;

    // Write a message to the database
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();

    private List<DataModel> dataModels;
    private List<TamilStoriesModel> tamilStoriesModels;
    private List<TamilMoviesModel> tamilMoviesModels;

    private SliderAdapter sliderAdapter;
    SliderView sliderView;

    private RecyclerView TamilStoriesRecyclerView, TamilMoviesRecyclerView;

    private TamilStoriesAdapter tamilStoriesAdapter;
    private TamilMoviesAdapter tamilMoviesAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tamil);

        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        TextView textView1 = findViewById(R.id.tamil_animation_story);
        textView1.setEllipsize(TextUtils.TruncateAt.MARQUEE);
        textView1.setSelected(true);

        TextView textView2 = findViewById(R.id.tamil_animation_movie);
        textView2.setEllipsize(TextUtils.TruncateAt.MARQUEE);
        textView2.setSelected(true);

        progressBar = findViewById(R.id.progress_bar1);

        FirebaseApp.initializeApp(this);

        sliderView = findViewById(R.id.sliderViewTamil);

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

                Toast.makeText(TamilActivity.this, "" +error.getMessage(), Toast.LENGTH_SHORT).show();
                finish();

            }
        });

        //Tamil Stories
        loadTamilStoriesData(); //****

    }

    private void loadTamilStoriesData() {

        DatabaseReference TSRef = database.getReference("tamilstories"); //***
        TamilStoriesRecyclerView = findViewById(R.id.recyclerViewForTamilAnimationStories); //***

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.HORIZONTAL);

        layoutManager.setReverseLayout(true);
        layoutManager.setStackFromEnd(true);

        TamilStoriesRecyclerView.setLayoutManager(layoutManager);

        tamilStoriesModels = new ArrayList<>(); //***
        tamilStoriesAdapter = new TamilStoriesAdapter(tamilStoriesModels); //***
        TamilStoriesRecyclerView.setAdapter(tamilStoriesAdapter); //***

        TSRef.addValueEventListener(new ValueEventListener() { //***
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot contentSnapShot:snapshot.getChildren()){

                    TamilStoriesModel dataModel = contentSnapShot.getValue(TamilStoriesModel.class); //***
                    tamilStoriesModels.add(dataModel); //***
                    progressBar.setVisibility(View.GONE);

                }

                tamilStoriesAdapter.notifyDataSetChanged(); //***

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        //Tamil Movies
        loadTamilMoviesData();

    }



    private void loadTamilMoviesData() {

        DatabaseReference TMRef = database.getReference("tamilmovies"); //***
        TamilMoviesRecyclerView = findViewById(R.id.recyclerViewForTamilAnimationMovies); //***

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.HORIZONTAL);

        layoutManager.setReverseLayout(true);
        layoutManager.setStackFromEnd(true);

        TamilMoviesRecyclerView.setLayoutManager(layoutManager); //***

        tamilMoviesModels = new ArrayList<>(); //***
        tamilMoviesAdapter = new TamilMoviesAdapter(tamilMoviesModels); //***
        TamilMoviesRecyclerView.setAdapter(tamilMoviesAdapter); //***

        TMRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot contentSnapShot:snapshot.getChildren()){

                    TamilMoviesModel dataModel = contentSnapShot.getValue(TamilMoviesModel.class); //***
                    tamilMoviesModels.add(dataModel); //***
                    progressBar.setVisibility(View.GONE);

                }

                tamilMoviesAdapter.notifyDataSetChanged(); //***

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