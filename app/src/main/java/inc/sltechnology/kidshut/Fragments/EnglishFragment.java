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

import inc.sltechnology.kidshut.Adapters.EnglishMoviesAdapter;
import inc.sltechnology.kidshut.Models.EnglishMoviesModel;
import inc.sltechnology.kidshut.Adapters.EnglishStoriesAdapter;
import inc.sltechnology.kidshut.Models.EnglishStoriesModel;
import inc.sltechnology.kidshut.Models.DataModel;
import inc.sltechnology.kidshut.R;
import inc.sltechnology.kidshut.Adapters.SliderAdapter;

public class EnglishFragment extends Fragment {

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

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_english, container, false);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

        TextView textView1 = getView().findViewById(R.id.eng_animation_story);
        textView1.setEllipsize(TextUtils.TruncateAt.MARQUEE);
        textView1.setSelected(true);

        TextView textView2 = getView().findViewById(R.id.eng_animation_movie);
        textView2.setEllipsize(TextUtils.TruncateAt.MARQUEE);
        textView2.setSelected(true);

        progressBar = getView().findViewById(R.id.progress_bar1);

        FirebaseApp.initializeApp(getActivity());

        sliderView = getView().findViewById(R.id.sliderViewEnglish);

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

        //English Stories
        loadEnglishStoriesData(); //****

    }



    private void loadEnglishStoriesData() {

        DatabaseReference ESRef = database.getReference("englishstories");
        EnglishStoriesRecyclerView = getView().findViewById(R.id.recyclerViewForEngAnimationStories);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
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
        EnglishMoviesRecyclerView = getView().findViewById(R.id.recyclerViewEngForAnimationMovies);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
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