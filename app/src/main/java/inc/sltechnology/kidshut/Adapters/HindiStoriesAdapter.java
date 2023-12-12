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

import inc.sltechnology.kidshut.Models.HindiStoriesModel;
import inc.sltechnology.kidshut.R;
import inc.sltechnology.kidshut.StoriesActivity;

public class HindiStoriesAdapter extends RecyclerView.Adapter<HindiStoriesAdapter.MyViewHolder> {

    private List<HindiStoriesModel> dataModels;

    public HindiStoriesAdapter(List<HindiStoriesModel> dataModels) {
        this.dataModels = dataModels;
    }

    @NonNull
    @Override
    public HindiStoriesAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.movie_card, parent, false);

        return new HindiStoriesAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HindiStoriesAdapter.MyViewHolder holder, int position) {

        holder.textView.setText(dataModels.get(position).getHSTitle());

        //thumbnail load here
        Glide.with(holder.itemView.getContext())
                .load(dataModels.get(position).getHSThumb()).into(holder.imageView);

        holder.itemView.setOnClickListener(new View.OnClickListener() { //****
            @Override
            public void onClick(View v) {

                // when user is click on movie item send data to detail activity
                Intent sendDataTOStoriesActivity = new Intent(holder.itemView.getContext(), StoriesActivity.class);
                sendDataTOStoriesActivity.putExtra("title", dataModels.get(position).getHSTitle());
                sendDataTOStoriesActivity.putExtra("cover", dataModels.get(position).getHSCover());
                sendDataTOStoriesActivity.putExtra("thumb", dataModels.get(position).getHSThumb());
                sendDataTOStoriesActivity.putExtra("desc", dataModels.get(position).getHSDesc());
                sendDataTOStoriesActivity.putExtra("SVLink", dataModels.get(position).getHSVideo());

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
        return dataModels.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView; //***
        TextView textView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imageView);
            textView = itemView.findViewById(R.id.movie_title);

        }
    }

}
