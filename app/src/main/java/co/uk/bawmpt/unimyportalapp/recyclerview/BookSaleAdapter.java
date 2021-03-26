package co.uk.bawmpt.unimyportalapp.recyclerview;

import android.content.Context;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import co.uk.bawmpt.unimyportalapp.R;
import co.uk.bawmpt.unimyportalapp.model.Book;

public class BookSaleAdapter extends RecyclerView.Adapter<BookSaleAdapter.ViewHolder> {

    private Context context;
    private List<Book> bookSaleList;

    public BookSaleAdapter(Context context, List<Book> bookSaleList) {
        this.context = context;
        this.bookSaleList = bookSaleList;
    }

    @NonNull
    @Override
    public BookSaleAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
         View view = LayoutInflater.from(context)
                 .inflate(R.layout.book_sale_card, parent, false);
        return new ViewHolder(view, context);
    }

    @Override
    public void onBindViewHolder(@NonNull BookSaleAdapter.ViewHolder holder, int position) {
        Book book = bookSaleList.get(position);
        String imageUrl;

        holder.title.setText(book.getTitle());
        holder.author.setText(book.getAuthor());
        holder.sellerName.setText(book.getUserName());
        //Showing posted books in Book Sale Activity in format of - 3 minutes ago
        imageUrl = book.getImageUrl();

        /*
        Use Picasso Library to download and show image:
         */
        Picasso.get()
                .load(imageUrl)
                .fit()
                .into(holder.bookSaleImage);

        //Date Stamp source: https://medium.com/@shaktisinh/time-a-go-in-android-8bad8b171f87
        String timeAgo = (String) DateUtils.getRelativeTimeSpanString(book.
                getTimeAdded().
                getSeconds() * 1000);
        holder.dateAdded.setText(timeAgo);

    }

    @Override
    public int getItemCount() {
        return bookSaleList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView title;
        public TextView author;
        public TextView sellerName;
        public TextView dateAdded;
        public TextView contactSeller;
        public ImageView bookSaleImage;
        String userId;
        String username;

        public ViewHolder(@NonNull View itemView, Context ctx) {
            super(itemView);

            context = ctx;

            title = itemView.findViewById(R.id.bookSaleTitle);
            author = itemView.findViewById(R.id.bookSaleAuthor);
            sellerName = itemView.findViewById(R.id.bookSaleName);
            contactSeller = itemView.findViewById(R.id.bookSaleContactSeller);
            dateAdded = itemView.findViewById(R.id.bookSaleTimeStamp);
            bookSaleImage = itemView.findViewById(R.id.bookSaleImage);
        }
    }
}
