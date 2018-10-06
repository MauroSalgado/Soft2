package adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import co.edu.konranlorenz.kpple.R;

import java.util.List;

import entities.User;

public class FindNameAdapter extends RecyclerView.Adapter<FindNameAdapter.ImageViewHolder> {
        private List<User> mUser;
      private Context mContext;

      public FindNameAdapter(Context mContext, List<User> mUser) {
          this.mContext = mContext;
          this.mUser = mUser;
      }

      @NonNull
      @Override
      public FindNameAdapter.ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
          View v = LayoutInflater.from(mContext).inflate(R.layout.fragment_card_user, parent, false);
          return new FindNameAdapter.ImageViewHolder(v);
      }



    @Override
      public void onBindViewHolder(@NonNull final FindNameAdapter.ImageViewHolder holder, int position) {

          final User userCurrent = mUser.get(position);
          final FirebaseAuth mAuth = FirebaseAuth.getInstance();
          final DatabaseReference mRef = FirebaseDatabase.getInstance().getReference("User/" + userCurrent.getIdUser());
          final DatabaseReference refPost = FirebaseDatabase.getInstance().getReference("Post");
          final String user = mAuth.getCurrentUser().getUid();
          final String name = userCurrent.getName();

          mRef.addValueEventListener(new ValueEventListener() {
              @Override
              public void onDataChange(DataSnapshot dataSnapshot) {
                  holder.txtUserName.setText(dataSnapshot.child("name").getValue().toString());
              }

              @Override
              public void onCancelled(DatabaseError databaseError) {

              }
          });
      }
      @Override
      public int getItemCount() {
          return mUser.size();
      }

      public class ImageViewHolder extends RecyclerView.ViewHolder {
          public TextView txtUserName;


          public ImageViewHolder(View itemView) {
              super(itemView);
              txtUserName = itemView.findViewById(R.id.txtUserName);}
      }
}
