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
import inc.sltechnology.kidshut.R;
import inc.sltechnology.kidshut.Models.SinhalaMoviesModel;

public class SinhalaMoviesAdapter extends RecyclerView.Adapter<SinhalaMoviesAdapter.MyViewHolder> {

    private List<SinhalaMoviesModel> modelList;

    public SinhalaMoviesAdapter(List<SinhalaMoviesModel> modelList) {
        this.modelList = modelList;
    }

    @NonNull
    @Override
    public SinhalaMoviesAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_card,
                parent, false);

        return new SinhalaMoviesAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SinhalaMoviesAdapter.MyViewHolder holder, int position) {

        holder.title.setText(modelList.get(position).getSMTitle());

        Glide.with(holder.imageView.getContext())
                .load(modelList.get(position).getSMThumb()).into(holder.imageView);

        holder.itemView.setOnClickListener(new View.OnClickListener() { //****
            @Override
            public void onClick(View v) {

                // when user is click on movie item send data to detail activity
                Intent sendDataTODetailsActivity = new Intent(holder.itemView.getContext(), DetailsActivity.class);
                sendDataTODetailsActivity.putExtra("title", modelList.get(position).getSMTitle());
                sendDataTODetailsActivity.putExtra("link", modelList.get(position).getSMVideo());
                sendDataTODetailsActivity.putExtra("cover", modelList.get(position).getSMCover());
                sendDataTODetailsActivity.putExtra("thumb", modelList.get(position).getSMThumb());
                sendDataTODetailsActivity.putExtra("desc", modelList.get(position).getSMDesc());
                sendDataTODetailsActivity.putExtra("cast", modelList.get(position).getSMCast());
                sendDataTODetailsActivity.putExtra("T_Link", modelList.get(position).getSMTLink());
                sendDataTODetailsActivity.putExtra("MVLink", modelList.get(position).getSMVLink());

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

