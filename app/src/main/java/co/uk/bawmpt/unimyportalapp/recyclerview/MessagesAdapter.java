package co.uk.bawmpt.unimyportalapp.recyclerview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import co.uk.bawmpt.unimyportalapp.R;
import co.uk.bawmpt.unimyportalapp.model.User;

public class MessagesAdapter extends RecyclerView.Adapter<MessagesAdapter.ViewHolder> {

    private Context context;
    private List<User> messagesUsers;

    public MessagesAdapter(Context context, List<User> messagesUsers) {
        this.context = context;
        this.messagesUsers = messagesUsers;
    }

    @NonNull
    @Override
    public MessagesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater
                .from(context)
                .inflate(R.layout.messages_card, parent, false);
        return new ViewHolder(view, context);
    }

    @Override
    public void onBindViewHolder(@NonNull MessagesAdapter.ViewHolder holder, int position) {

        User user = messagesUsers.get(position);

        holder.userName.setText(user.getFullName());

    }

    @Override
    public int getItemCount() {
        return messagesUsers.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView userName;
        public ImageView profileImage;

        public ViewHolder(@NonNull View itemView, Context ctx) {
            super(itemView);

            context = ctx;

            userName = itemView.findViewById(R.id.messageCardUsername);
            profileImage = itemView.findViewById(R.id.messagesCardUserImage);
        }
    }
}
