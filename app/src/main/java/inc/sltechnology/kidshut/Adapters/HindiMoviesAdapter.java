package inc.sltechnology.kidshut.Adapters;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityOptionsCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import inc.sltechnology.kidshut.DetailsActivity;
import inc.sltechnology.kidshut.Models.HindiMoviesModel;
import inc.sltechnology.kidshut.R;

public class HindiMoviesAdapter extends RecyclerView.Adapter<HindiMoviesAdapter.MyViewHolder> {

    private List<HindiMoviesModel> modelList;

    public HindiMoviesAdapter(List<HindiMoviesModel> modelList) {
        this.modelList = modelList;
    }

    @NonNull
    @Override
    public HindiMoviesAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_card,
                parent, false);

        return new HindiMoviesAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HindiMoviesAdapter.MyViewHolder holder, int position) {

        holder.title.setText(modelList.get(position).getHMTitle());

        Glide.with(holder.imageView.getContext())
                .load(modelList.get(position).getHMThumb()).into(holder.imageView);

        holder.itemView.setOnClickListener(new View.OnClickListener() { //****
            @Override
            public void onClick(View v) {

                // when user is click on movie item send data to detail activity
                Intent sendDataTODetailsActivity = new Intent(holder.itemView.getContext(), DetailsActivity.class);
                sendDataTODetailsActivity.putExtra("title", modelList.get(position).getHMTitle());
                sendDataTODetailsActivity.putExtra("link", modelList.get(position).getHMVideo());
                sendDataTODetailsActivity.putExtra("cover", modelList.get(position).getHMCover());
                sendDataTODetailsActivity.putExtra("thumb", modelList.get(position).getHMThumb());
                sendDataTODetailsActivity.putExtra("desc", modelList.get(position).getHMDesc());
                sendDataTODetailsActivity.putExtra("cast", modelList.get(position).getHMCast());
                sendDataTODetailsActivity.putExtra("T_Link", modelList.get(position).getHMTLink());
                sendDataTODetailsActivity.putExtra("MVLink", modelList.get(position).getHMVLink());

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
        return modelList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView; //***
        TextView title;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.movie_title);
            imageView = itemView.findViewById(R.id.imageView);

        }
    }
}

