package inc.sltechnology.kidshut.Fragments;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.pm.ActivityInfo;
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

import inc.sltechnology.kidshut.Adapters.ChineseMoviesAdapter;
import inc.sltechnology.kidshut.Models.ChineseMoviesModel;
import inc.sltechnology.kidshut.Adapters.ChineseStoriesAdapter;
import inc.sltechnology.kidshut.Models.ChineseStoriesModel;
import inc.sltechnology.kidshut.Models.DataModel;
import inc.sltechnology.kidshut.R;
import inc.sltechnology.kidshut.Adapters.SliderAdapter;

public class ChineseFragment extends Fragment {

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

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_chinese, container, false);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

        TextView textView1 = getView().findViewById(R.id.chinese_animation_story);
        textView1.setEllipsize(TextUtils.TruncateAt.MARQUEE);
        textView1.setSelected(true);

        TextView textView2 = getView().findViewById(R.id.chinese_animation_movie);
        textView2.setEllipsize(TextUtils.TruncateAt.MARQUEE);
        textView2.setSelected(true);

        progressBar = getView().findViewById(R.id.progress_bar1);

        FirebaseApp.initializeApp(getActivity());

        sliderView = getView().findViewById(R.id.sliderViewChinese);

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

        //Chinese Stories
        loadChineseStoriesData();

    }



    private void loadChineseStoriesData() {

        DatabaseReference CSRef = database.getReference("chinesestories"); //***
        ChineseStoriesRecyclerView = getView().findViewById(R.id.recyclerViewForChineseAnimationStories); //***

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
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
        ChineseMoviesRecyclerView = getView().findViewById(R.id.recyclerViewForChineseAnimationMovies); //***

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
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