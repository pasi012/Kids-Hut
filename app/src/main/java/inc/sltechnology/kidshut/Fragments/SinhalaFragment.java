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

import inc.sltechnology.kidshut.Models.DataModel;
import inc.sltechnology.kidshut.R;
import inc.sltechnology.kidshut.Adapters.SinhalaMoviesAdapter;
import inc.sltechnology.kidshut.Models.SinhalaMoviesModel;
import inc.sltechnology.kidshut.Adapters.SinhalaStoriesAdapter;
import inc.sltechnology.kidshut.Models.SinhalaStoriesModel;
import inc.sltechnology.kidshut.Adapters.SliderAdapter;

public class SinhalaFragment extends Fragment {

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

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_sinhala, container, false);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

        TextView textView1 = getView().findViewById(R.id.sinhala_animation_story);
        textView1.setEllipsize(TextUtils.TruncateAt.MARQUEE);
        textView1.setSelected(true);

        TextView textView2 = getView().findViewById(R.id.sinhala_animation_movie);
        textView2.setEllipsize(TextUtils.TruncateAt.MARQUEE);
        textView2.setSelected(true);

        progressBar = getView().findViewById(R.id.progress_bar1);

        FirebaseApp.initializeApp(getActivity());

        sliderView = getView().findViewById(R.id.sliderViewSinhala);

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

        //Sinhala Stories
        loadSinhalaStoriesData();

    }



    private void loadSinhalaStoriesData() {

        DatabaseReference SSRef = database.getReference("sinhalastories"); //***
        SinhalaStoriesRecyclerView = getView().findViewById(R.id.recyclerViewForSinhalaAnimationStories); //***

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
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
        SinhalaMoviesRecyclerView = getView().findViewById(R.id.recyclerViewForSinhalaAnimationMovies); //***

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
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