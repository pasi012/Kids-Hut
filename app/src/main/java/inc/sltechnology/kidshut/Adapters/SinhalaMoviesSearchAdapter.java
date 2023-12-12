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

import inc.sltechnology.kidshut.DetailsActivity;
import inc.sltechnology.kidshut.Models.SinhalaMoviesModel;
import inc.sltechnology.kidshut.R;

public class SinhalaMoviesSearchAdapter extends RecyclerView.Adapter<SinhalaMoviesSearchAdapter.MyViewHolder> implements Filterable {

    List<SinhalaMoviesModel> modelList;
    List<SinhalaMoviesModel> modelListAll;

    public SinhalaMoviesSearchAdapter(List<SinhalaMoviesModel> modelList) {

        this.modelList = modelList;
        this.modelListAll = modelList;
    }

    @NonNull
    @Override
    public SinhalaMoviesSearchAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_holder,
                parent, false);

        return new SinhalaMoviesSearchAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SinhalaMoviesSearchAdapter.MyViewHolder holder, int position) {

        holder.title.setText(modelListAll.get(position).getSMTitle());

        Glide.with(holder.imageView.getContext())
                .load(modelListAll.get(position).getSMThumb()).into(holder.imageView);

        holder.itemView.setOnClickListener(new View.OnClickListener() { //****
            @Override
            public void onClick(View v) {

                // when user is click on movie item send data to detail activity
                Intent sendDataTODetailsActivity = new Intent(holder.itemView.getContext(), DetailsActivity.class);
                sendDataTODetailsActivity.putExtra("title", modelListAll.get(position).getSMTitle());
                sendDataTODetailsActivity.putExtra("link", modelListAll.get(position).getSMVideo());
                sendDataTODetailsActivity.putExtra("cover", modelListAll.get(position).getSMCover());
                sendDataTODetailsActivity.putExtra("thumb", modelListAll.get(position).getSMThumb());
                sendDataTODetailsActivity.putExtra("desc", modelListAll.get(position).getSMDesc());
                sendDataTODetailsActivity.putExtra("cast", modelListAll.get(position).getSMCast());
                sendDataTODetailsActivity.putExtra("T_Link", modelListAll.get(position).getSMTLink());
                sendDataTODetailsActivity.putExtra("MVLink", modelListAll.get(position).getSMVLink());

                //before sending data we create transition animation
                ActivityOptionsCompat optionsCompat = ActivityOptionsCompat
                        .makeSceneTransitionAnimation((Activity)holder.itemView.getContext(), holder.imageView,
                                "imageMain");

                //sharedElement name is same as xml file
                holder.itemView.getContext().startActivity(sendDataTODetailsActivity, optionsCompat.toBundle());

                //create adapter & datamodel for detailsActivity recycler view for episodes and casts

            }
        });


    }

    @Override
    public int getItemCount() {
        return modelListAll.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {

                String searchText = constraint.toString();
                if (searchText.isEmpty()){

                    modelListAll = modelList;

                }else {

                    List<SinhalaMoviesModel> filterList = new ArrayList<>();
                    for (SinhalaMoviesModel moviesModel: modelList){

                        if (moviesModel.getSMTitle().toLowerCase().contains(searchText.toLowerCase())){

                            filterList.add(moviesModel);

                        }

                    }

                    modelListAll = filterList;

                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = modelListAll;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {

                modelListAll = (ArrayList<SinhalaMoviesModel>) results.values;
                notifyDataSetChanged();

            }
        };
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView; //***
        TextView title;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.titleForSearch);
            imageView = itemView.findViewById(R.id.thumbForSearch);

        }
    }
}

