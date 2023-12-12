package inc.sltechnology.kidshut.Fragments;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

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

public class HindiFragment extends Fragment {

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

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_hindi, container, false);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

        TextView textView1 = getView().findViewById(R.id.hindi_animation_story);
        textView1.setEllipsize(TextUtils.TruncateAt.MARQUEE);
        textView1.setSelected(true);

        TextView textView2 = getView().findViewById(R.id.hindi_animation_movie);
        textView2.setEllipsize(TextUtils.TruncateAt.MARQUEE);
        textView2.setSelected(true);

        progressBar = getView().findViewById(R.id.progress_bar1);

        FirebaseApp.initializeApp(getActivity());

        sliderView = getView().findViewById(R.id.sliderViewHindi);

        sliderAdapter = new SliderAdapter(getActivity());
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



            }
        });

        //Hindi Stories
        loadHindiStoriesData();

    }

    private void loadHindiStoriesData() {

        DatabaseReference HSRef = database.getReference("hindistories"); //***
        HindiStoriesRecyclerView = getView().findViewById(R.id.recyclerViewForHindiAnimationStories); //***

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
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
        HindiMoviesRecyclerView = getView().findViewById(R.id.recyclerViewForHindiAnimationMovies); //***

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
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