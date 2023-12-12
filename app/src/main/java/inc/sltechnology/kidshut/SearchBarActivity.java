package inc.sltechnology.kidshut;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import inc.sltechnology.kidshut.Adapters.ChineseMoviesSearchAdapter;
import inc.sltechnology.kidshut.Adapters.ChineseStoriesSearchAdapter;
import inc.sltechnology.kidshut.Adapters.EnglishMoviesSearchAdapter;
import inc.sltechnology.kidshut.Adapters.EnglishStoriesSearchAdapter;
import inc.sltechnology.kidshut.Adapters.HindiMoviesSearchAdapter;
import inc.sltechnology.kidshut.Adapters.HindiStoriesSearchAdapter;
import inc.sltechnology.kidshut.Adapters.JapaneseMoviesSearchAdapter;
import inc.sltechnology.kidshut.Adapters.JapaneseStoriesSearchAdapter;
import inc.sltechnology.kidshut.Adapters.SinhalaMoviesSearchAdapter;
import inc.sltechnology.kidshut.Adapters.SinhalaStoriesSearchAdapter;
import inc.sltechnology.kidshut.Adapters.TamilMoviesSearchAdapter;
import inc.sltechnology.kidshut.Adapters.TamilStoriesSearchAdapter;
import inc.sltechnology.kidshut.Models.ChineseMoviesModel;
import inc.sltechnology.kidshut.Models.ChineseStoriesModel;
import inc.sltechnology.kidshut.Models.EnglishMoviesModel;
import inc.sltechnology.kidshut.Models.EnglishStoriesModel;
import inc.sltechnology.kidshut.Models.HindiMoviesModel;
import inc.sltechnology.kidshut.Models.HindiStoriesModel;
import inc.sltechnology.kidshut.Models.JapaneseMoviesModel;
import inc.sltechnology.kidshut.Models.JapaneseStoriesModel;
import inc.sltechnology.kidshut.Models.SinhalaMoviesModel;
import inc.sltechnology.kidshut.Models.SinhalaStoriesModel;
import inc.sltechnology.kidshut.Models.TamilMoviesModel;
import inc.sltechnology.kidshut.Models.TamilStoriesModel;

public class SearchBarActivity extends AppCompatActivity {

    // Write a message to the database
    FirebaseDatabase database = FirebaseDatabase.getInstance();

    private List<ChineseStoriesModel> chineseStoriesModels;
    private List<ChineseMoviesModel> chineseMoviesModels;
    private List<EnglishStoriesModel> englishStoriesModels;
    private List<EnglishMoviesModel> englishMoviesModels;
    private List<HindiStoriesModel> hindiStoriesModels;
    private List<HindiMoviesModel> hindiMoviesModels;
    private List<JapaneseStoriesModel> japaneseStoriesModels;
    private List<JapaneseMoviesModel> japaneseMoviesModels;
    private List<SinhalaStoriesModel> sinhalaStoriesModels;
    private List<SinhalaMoviesModel> sinhalaMoviesModels;
    private List<TamilStoriesModel> tamilStoriesModels;
    private List<TamilMoviesModel> tamilMoviesModels;

    private RecyclerView recyclerView1;
    private RecyclerView recyclerView2;
    private RecyclerView recyclerView3;
    private RecyclerView recyclerView4;
    private RecyclerView recyclerView5;
    private RecyclerView recyclerView6;
    private RecyclerView recyclerView7;
    private RecyclerView recyclerView8;
    private RecyclerView recyclerView9;
    private RecyclerView recyclerView10;
    private RecyclerView recyclerView11;
    private RecyclerView recyclerView12;

    private ChineseStoriesSearchAdapter chineseStoriesSearchAdapter;
    private ChineseMoviesSearchAdapter chineseMoviesSearchAdapter;
    private EnglishStoriesSearchAdapter englishStoriesSearchAdapter;
    private EnglishMoviesSearchAdapter englishMoviesSearchAdapter;
    private HindiStoriesSearchAdapter hindiStoriesSearchAdapter;
    private HindiMoviesSearchAdapter hindiMoviesSearchAdapter;
    private JapaneseStoriesSearchAdapter japaneseStoriesSearchAdapter;
    private JapaneseMoviesSearchAdapter japaneseMoviesSearchAdapter;
    private SinhalaStoriesSearchAdapter sinhalaStoriesSearchAdapter;
    private SinhalaMoviesSearchAdapter sinhalaMoviesSearchAdapter;
    private TamilStoriesSearchAdapter tamilStoriesSearchAdapter;
    private TamilMoviesSearchAdapter tamilMoviesSearchAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_bar);

        Toolbar toolbar = findViewById(R.id.toolbarSearch);
        setSupportActionBar(toolbar);

        FirebaseApp.initializeApp(this);

        loadChineseStoriesSearchData();

    }

    private void loadChineseStoriesSearchData() {

        DatabaseReference CSSRef = database.getReference("chinesestories"); //***
        recyclerView1 = findViewById(R.id.recyclerViewForSearchCS); //***

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);

        layoutManager.setReverseLayout(true);
        layoutManager.setStackFromEnd(true);

        recyclerView1.setLayoutManager(layoutManager); //***

        chineseStoriesModels = new ArrayList<>(); //***
        chineseStoriesSearchAdapter = new ChineseStoriesSearchAdapter(chineseStoriesModels); //***
        recyclerView1.setAdapter(chineseStoriesSearchAdapter); //***

        CSSRef.addValueEventListener(new ValueEventListener() { //***
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot contentSnapShot:snapshot.getChildren()){

                    ChineseStoriesModel dataModel = contentSnapShot.getValue(ChineseStoriesModel.class); //***
                    chineseStoriesModels.add(dataModel); //***

                }

                chineseStoriesSearchAdapter.notifyDataSetChanged(); //***

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        loadChineseMoviesSearchData();

    }

    private void loadChineseMoviesSearchData() {

        DatabaseReference CMSRef = database.getReference("chinesemovies"); //***
        recyclerView2 = findViewById(R.id.recyclerViewForSearchCM); //***

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);

        layoutManager.setReverseLayout(true);
        layoutManager.setStackFromEnd(true);

        recyclerView2.setLayoutManager(layoutManager); //***

        chineseMoviesModels = new ArrayList<>(); //***
        chineseMoviesSearchAdapter = new ChineseMoviesSearchAdapter(chineseMoviesModels); //***
        recyclerView2.setAdapter(chineseMoviesSearchAdapter); //***

        CMSRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot contentSnapShot:snapshot.getChildren()){

                    ChineseMoviesModel dataModel = contentSnapShot.getValue(ChineseMoviesModel.class); //***
                    chineseMoviesModels.add(dataModel); //***

                }

                chineseMoviesSearchAdapter.notifyDataSetChanged(); //***

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        loadEnglishStoriesSearchData();

    }

    private void loadEnglishStoriesSearchData() {

        DatabaseReference ESSRef = database.getReference("englishstories"); //***
        recyclerView3 = findViewById(R.id.recyclerViewForSearchES); //***

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);

        layoutManager.setReverseLayout(true);
        layoutManager.setStackFromEnd(true);

        recyclerView3.setLayoutManager(layoutManager); //***

        englishStoriesModels = new ArrayList<>(); //***
        englishStoriesSearchAdapter = new EnglishStoriesSearchAdapter(englishStoriesModels); //***
        recyclerView3.setAdapter(englishStoriesSearchAdapter); //***

        ESSRef.addValueEventListener(new ValueEventListener() { //***
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot contentSnapShot:snapshot.getChildren()){

                    EnglishStoriesModel dataModel = contentSnapShot.getValue(EnglishStoriesModel.class); //***
                    englishStoriesModels.add(dataModel); //***

                }

                englishStoriesSearchAdapter.notifyDataSetChanged(); //***

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        loadEnglishMoviesSearchData();

    }

    private void loadEnglishMoviesSearchData() {

        DatabaseReference EMSRef = database.getReference("englishmovies"); //***
        recyclerView4 = findViewById(R.id.recyclerViewForSearchEM); //***

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);

        layoutManager.setReverseLayout(true);
        layoutManager.setStackFromEnd(true);

        recyclerView4.setLayoutManager(layoutManager); //***

        englishMoviesModels = new ArrayList<>(); //***
        englishMoviesSearchAdapter = new EnglishMoviesSearchAdapter(englishMoviesModels); //***
        recyclerView4.setAdapter(englishMoviesSearchAdapter); //***

        EMSRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot contentSnapShot:snapshot.getChildren()){

                    EnglishMoviesModel dataModel = contentSnapShot.getValue(EnglishMoviesModel.class); //***
                    englishMoviesModels.add(dataModel); //***

                }

                englishMoviesSearchAdapter.notifyDataSetChanged(); //***

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        loadHindiStoriesSearchData();

    }

    private void loadHindiStoriesSearchData() {

        DatabaseReference HSSRef = database.getReference("hindistories"); //***
        recyclerView5 = findViewById(R.id.recyclerViewForSearchHS); //***

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);

        layoutManager.setReverseLayout(true);
        layoutManager.setStackFromEnd(true);

        recyclerView5.setLayoutManager(layoutManager); //***

        hindiStoriesModels = new ArrayList<>(); //***
        hindiStoriesSearchAdapter = new HindiStoriesSearchAdapter(hindiStoriesModels); //***
        recyclerView5.setAdapter(hindiStoriesSearchAdapter); //***

        HSSRef.addValueEventListener(new ValueEventListener() { //***
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot contentSnapShot:snapshot.getChildren()){

                    HindiStoriesModel dataModel = contentSnapShot.getValue(HindiStoriesModel.class); //***
                    hindiStoriesModels.add(dataModel); //***

                }

                hindiStoriesSearchAdapter.notifyDataSetChanged(); //***

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        loadHindiMoviesSearchData();

    }

    private void loadHindiMoviesSearchData() {

        DatabaseReference HMSRef = database.getReference("hindimovies"); //***
        recyclerView6 = findViewById(R.id.recyclerViewForSearchHM); //***

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);

        layoutManager.setReverseLayout(true);
        layoutManager.setStackFromEnd(true);

        recyclerView6.setLayoutManager(layoutManager); //***

        hindiMoviesModels = new ArrayList<>(); //***
        hindiMoviesSearchAdapter = new HindiMoviesSearchAdapter(hindiMoviesModels); //***
        recyclerView6.setAdapter(hindiMoviesSearchAdapter); //***

        HMSRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot contentSnapShot:snapshot.getChildren()){

                    HindiMoviesModel dataModel = contentSnapShot.getValue(HindiMoviesModel.class); //***
                    hindiMoviesModels.add(dataModel); //***

                }

                hindiMoviesSearchAdapter.notifyDataSetChanged(); //***

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        loadJapaneseStoriesSearchData();

    }

    private void loadJapaneseStoriesSearchData() {

        DatabaseReference JSSRef = database.getReference("japanesestories"); //***
        recyclerView7 = findViewById(R.id.recyclerViewForSearchJS); //***

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);

        layoutManager.setReverseLayout(true);
        layoutManager.setStackFromEnd(true);

        recyclerView7.setLayoutManager(layoutManager); //***

        japaneseStoriesModels = new ArrayList<>(); //***
        japaneseStoriesSearchAdapter = new JapaneseStoriesSearchAdapter(japaneseStoriesModels); //***
        recyclerView7.setAdapter(japaneseStoriesSearchAdapter); //***

        JSSRef.addValueEventListener(new ValueEventListener() { //***
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot contentSnapShot:snapshot.getChildren()){

                    JapaneseStoriesModel dataModel = contentSnapShot.getValue(JapaneseStoriesModel.class); //***
                    japaneseStoriesModels.add(dataModel); //***

                }

                japaneseStoriesSearchAdapter.notifyDataSetChanged(); //***

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        loadJapaneseMoviesSearchData();

    }

    private void loadJapaneseMoviesSearchData() {

        DatabaseReference JMSRef = database.getReference("japanesemovies"); //***
        recyclerView8 = findViewById(R.id.recyclerViewForSearchJM); //***

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);

        layoutManager.setReverseLayout(true);
        layoutManager.setStackFromEnd(true);

        recyclerView8.setLayoutManager(layoutManager); //***

        japaneseMoviesModels = new ArrayList<>(); //***
        japaneseMoviesSearchAdapter = new JapaneseMoviesSearchAdapter(japaneseMoviesModels); //***
        recyclerView8.setAdapter(japaneseMoviesSearchAdapter); //***

        JMSRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot contentSnapShot:snapshot.getChildren()){

                    JapaneseMoviesModel dataModel = contentSnapShot.getValue(JapaneseMoviesModel.class); //***
                    japaneseMoviesModels.add(dataModel); //***

                }

                japaneseMoviesSearchAdapter.notifyDataSetChanged(); //***

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        loadSinhalaStoriesSearchData();

    }

    private void loadSinhalaStoriesSearchData() {

        DatabaseReference SSSRef = database.getReference("sinhalastories"); //***
        recyclerView9 = findViewById(R.id.recyclerViewForSearchSS); //***

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);

        layoutManager.setReverseLayout(true);
        layoutManager.setStackFromEnd(true);

        recyclerView9.setLayoutManager(layoutManager); //***

        sinhalaStoriesModels = new ArrayList<>(); //***
        sinhalaStoriesSearchAdapter = new SinhalaStoriesSearchAdapter(sinhalaStoriesModels); //***
        recyclerView9.setAdapter(sinhalaStoriesSearchAdapter); //***

        SSSRef.addValueEventListener(new ValueEventListener() { //***
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot contentSnapShot:snapshot.getChildren()){

                    SinhalaStoriesModel dataModel = contentSnapShot.getValue(SinhalaStoriesModel.class); //***
                    sinhalaStoriesModels.add(dataModel); //***

                }

                sinhalaStoriesSearchAdapter.notifyDataSetChanged(); //***

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        loadSinhalaMoviesSearchData();

    }

    private void loadSinhalaMoviesSearchData() {

        DatabaseReference SMSRef = database.getReference("sinhalamovies"); //***
        recyclerView10 = findViewById(R.id.recyclerViewForSearchSM); //***

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);

        layoutManager.setReverseLayout(true);
        layoutManager.setStackFromEnd(true);

        recyclerView10.setLayoutManager(layoutManager); //***

        sinhalaMoviesModels = new ArrayList<>(); //***
        sinhalaMoviesSearchAdapter = new SinhalaMoviesSearchAdapter(sinhalaMoviesModels); //***
        recyclerView10.setAdapter(sinhalaMoviesSearchAdapter); //***

        SMSRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot contentSnapShot:snapshot.getChildren()){

                    SinhalaMoviesModel dataModel = contentSnapShot.getValue(SinhalaMoviesModel.class); //***
                    sinhalaMoviesModels.add(dataModel); //***

                }

                sinhalaMoviesSearchAdapter.notifyDataSetChanged(); //***

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        loadTamilStoriesSearchData();

    }

    private void loadTamilStoriesSearchData() {

        DatabaseReference TSSRef = database.getReference("tamilstories"); //***
        recyclerView11 = findViewById(R.id.recyclerViewForSearchTS); //***

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);

        layoutManager.setReverseLayout(true);
        layoutManager.setStackFromEnd(true);

        recyclerView11.setLayoutManager(layoutManager); //***

        tamilStoriesModels = new ArrayList<>(); //***
        tamilStoriesSearchAdapter = new TamilStoriesSearchAdapter(tamilStoriesModels); //***
        recyclerView11.setAdapter(tamilStoriesSearchAdapter); //***

        TSSRef.addValueEventListener(new ValueEventListener() { //***
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot contentSnapShot:snapshot.getChildren()){

                    TamilStoriesModel dataModel = contentSnapShot.getValue(TamilStoriesModel.class); //***
                    tamilStoriesModels.add(dataModel); //***

                }

                tamilStoriesSearchAdapter.notifyDataSetChanged(); //***

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        loadTamilMoviesSearchData();

    }

    private void loadTamilMoviesSearchData() {

        DatabaseReference TMSRef = database.getReference("tamilmovies"); //***
        recyclerView12 = findViewById(R.id.recyclerViewForSearchTM); //***

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);

        layoutManager.setReverseLayout(true);
        layoutManager.setStackFromEnd(true);

        recyclerView12.setLayoutManager(layoutManager); //***

        tamilMoviesModels = new ArrayList<>(); //***
        tamilMoviesSearchAdapter = new TamilMoviesSearchAdapter(tamilMoviesModels); //***
        recyclerView12.setAdapter(tamilMoviesSearchAdapter); //***

        TMSRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot contentSnapShot:snapshot.getChildren()){

                    TamilMoviesModel dataModel = contentSnapShot.getValue(TamilMoviesModel.class); //***
                    tamilMoviesModels.add(dataModel); //***

                }

                tamilMoviesSearchAdapter.notifyDataSetChanged(); //***

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        MenuItem item = menu.findItem(R.id.action_search);
        android.widget.SearchView searchView = (android.widget.SearchView)item.getActionView();
        searchView.setMaxWidth(Integer.MAX_VALUE);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                chineseMoviesSearchAdapter.getFilter().filter(query);
                chineseStoriesSearchAdapter.getFilter().filter(query);

                englishMoviesSearchAdapter.getFilter().filter(query);
                englishStoriesSearchAdapter.getFilter().filter(query);

                hindiMoviesSearchAdapter.getFilter().filter(query);
                hindiStoriesSearchAdapter.getFilter().filter(query);

                japaneseMoviesSearchAdapter.getFilter().filter(query);
                japaneseStoriesSearchAdapter.getFilter().filter(query);

                sinhalaMoviesSearchAdapter.getFilter().filter(query);
                sinhalaStoriesSearchAdapter.getFilter().filter(query);

                tamilMoviesSearchAdapter.getFilter().filter(query);
                tamilStoriesSearchAdapter.getFilter().filter(query);

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                chineseMoviesSearchAdapter.getFilter().filter(newText);
                chineseStoriesSearchAdapter.getFilter().filter(newText);

                englishMoviesSearchAdapter.getFilter().filter(newText);
                englishStoriesSearchAdapter.getFilter().filter(newText);

                hindiMoviesSearchAdapter.getFilter().filter(newText);
                hindiStoriesSearchAdapter.getFilter().filter(newText);

                japaneseMoviesSearchAdapter.getFilter().filter(newText);
                japaneseStoriesSearchAdapter.getFilter().filter(newText);

                sinhalaMoviesSearchAdapter.getFilter().filter(newText);
                sinhalaStoriesSearchAdapter.getFilter().filter(newText);

                tamilMoviesSearchAdapter.getFilter().filter(newText);
                tamilStoriesSearchAdapter.getFilter().filter(newText);

                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }
}