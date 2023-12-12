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
import inc.sltechnology.kidshut.Models.EnglishMoviesModel;
import inc.sltechnology.kidshut.R;

public class EnglishMoviesSearchAdapter extends RecyclerView.Adapter<EnglishMoviesSearchAdapter.MyViewHolder> implements Filterable {

    List<EnglishMoviesModel> modelList;
    List<EnglishMoviesModel> modelListAll;

    public EnglishMoviesSearchAdapter(List<EnglishMoviesModel> modelList) {

        this.modelList = modelList;
        this.modelListAll = modelList;
    }

    @NonNull
    @Override
    public EnglishMoviesSearchAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_holder,
                parent, false);

        return new EnglishMoviesSearchAdapter.MyViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull EnglishMoviesSearchAdapter.MyViewHolder holder, int position) {

        holder.title.setText(modelListAll.get(position).getEMTitle());

        Glide.with(holder.imageView.getContext())
                .load(modelListAll.get(position).getEMThumb()).into(holder.imageView);

        holder.itemView.setOnClickListener(new View.OnClickListener() { //****
            @Override
            public void onClick(View v) {

                // when user is click on movie item send data to detail activity
                Intent sendDataTODetailsActivity = new Intent(holder.itemView.getContext(), DetailsActivity.class);
                sendDataTODetailsActivity.putExtra("title", modelListAll.get(position).getEMTitle());
                sendDataTODetailsActivity.putExtra("link", modelListAll.get(position).getEMVideo());
                sendDataTODetailsActivity.putExtra("cover", modelListAll.get(position).getEMCover());
                sendDataTODetailsActivity.putExtra("thumb", modelListAll.get(position).getEMThumb());
                sendDataTODetailsActivity.putExtra("desc", modelListAll.get(position).getEMDesc());
                sendDataTODetailsActivity.putExtra("cast", modelListAll.get(position).getEMCast());
                sendDataTODetailsActivity.putExtra("T_Link", modelListAll.get(position).getEMTLink());
                sendDataTODetailsActivity.putExtra("MVLink", modelListAll.get(position).getEMVLink());

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

                    List<EnglishMoviesModel> filterList = new ArrayList<>();
                    for (EnglishMoviesModel moviesModel: modelList){

                        if (moviesModel.getEMTitle().toLowerCase().contains(searchText.toLowerCase())){

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

                modelListAll = (ArrayList<EnglishMoviesModel>) results.values;
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
