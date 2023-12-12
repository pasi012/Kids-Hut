package inc.sltechnology.kidshut;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ViewHolder extends RecyclerView.ViewHolder {

    ImageView likeButton;
    TextView likeDisplay;
    int likesCount;
    DatabaseReference likesRef;

    public ViewHolder(@NonNull View itemView) {
        super(itemView);

        likeButton = itemView.findViewById(R.id.favourite_btn);
        likeDisplay = itemView.findViewById(R.id.favourite_txt);
        likesRef = FirebaseDatabase.getInstance().getReference("likes");

    }

    public void setLikebuttonStatus(final String postKey){

        likeButton = itemView.findViewById(R.id.favourite_btn);
        likeDisplay = itemView.findViewById(R.id.favourite_txt);
        likesRef = FirebaseDatabase.getInstance().getReference("likes");
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String userId = user.getUid();
        String likes = "likes";

        likesRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if (dataSnapshot.child(postKey).hasChild(userId)){

                    likesCount = (int) dataSnapshot.child(postKey).getChildrenCount();
                    likeButton.setImageResource(R.drawable.favo);
                    likeDisplay.setText(Integer.toString(likesCount) + likes);

                }else {

                    likesCount = (int) dataSnapshot.child(postKey).getChildrenCount();
                    likeButton.setImageResource(R.drawable.favo1);
                    likeDisplay.setText(Integer.toString(likesCount) + likes);

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}
