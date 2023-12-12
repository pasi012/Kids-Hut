package inc.sltechnology.kidshut.Adapters;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityOptionsCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import inc.sltechnology.kidshut.Models.TamilStoriesModel;
import inc.sltechnology.kidshut.R;
import inc.sltechnology.kidshut.StoriesActivity;

public class TamilStoriesSearchAdapter extends RecyclerView.Adapter<TamilStoriesSearchAdapter.MyViewHolder> implements Filterable {

    List<TamilStoriesModel> dataModels;
    List<TamilStoriesModel> dataModelsAll;

    public TamilStoriesSearchAdapter(List<TamilStoriesModel> dataModels) {

        this.dataModels = dataModels;
        this.dataModelsAll = dataModels;

    }

    @NonNull
    @Override
    public TamilStoriesSearchAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_holder, parent, false);

        return new TamilStoriesSearchAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TamilStoriesSearchAdapter.MyViewHolder holder, int position) {

        holder.textView.setText(dataModelsAll.get(position).getTSTitle());

        //thumbnail load here
        Glide.with(holder.itemView.getContext())
                .load(dataModelsAll.get(position).getTSThumb()).into(holder.imageView);

        holder.itemView.setOnClickListener(new View.OnClickListener() { //****
            @Override
            public void onClick(View v) {

                // when user is click on movie item send data to detail activity
                Intent sendDataTOStoriesActivity = new Intent(holder.itemView.getContext(), StoriesActivity.class);
                sendDataTOStoriesActivity.putExtra("title", dataModelsAll.get(position).getTSTitle());
                sendDataTOStoriesActivity.putExtra("cover", dataModelsAll.get(position).getTSCover());
                sendDataTOStoriesActivity.putExtra("thumb", dataModelsAll.get(position).getTSThumb());
                sendDataTOStoriesActivity.putExtra("desc", dataModelsAll.get(position).getTSDesc());
                sendDataTOStoriesActivity.putExtra("SVLink", dataModelsAll.get(position).getTSVideo());

                //before sending data we create transition animation
                ActivityOptionsCompat optionsCompat = ActivityOptionsCompat
                        .makeSceneTransitionAnimation((Activity)holder.itemView.getContext(), holder.imageView,
                                "imageMain");

                //sharedElement name is same as xml file
                holder.itemView.getContext().startActivity(sendDataTOStoriesActivity, optionsCompat.toBundle());

                //create adapter & datamodel for detailsActivity recycler view for episodes and casts

            }
        });


    }

    @Override
    public int getItemCount() {
        return dataModelsAll.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {

                String searchText = constraint.toString();
                if (searchText.isEmpty()){

                    dataModelsAll = dataModels;

                }else {

                    List<TamilStoriesModel> filterList = new ArrayList<>();
                    for (TamilStoriesModel storiesModel: dataModels){

                        if (storiesModel.getTSTitle().toLowerCase().contains(searchText.toLowerCase())){

                            filterList.add(storiesModel);

                        }

                    }

                    dataModelsAll = filterList;

                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = dataModelsAll;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {

                dataModelsAll = (ArrayList<TamilStoriesModel>) results.values;
                notifyDataSetChanged();

            }
        };
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView; //***
        TextView textView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.thumbForSearch);
            textView = itemView.findViewById(R.id.titleForSearch);

        }
    }
}


