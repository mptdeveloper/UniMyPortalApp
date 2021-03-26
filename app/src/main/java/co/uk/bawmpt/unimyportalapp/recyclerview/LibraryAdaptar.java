package co.uk.bawmpt.unimyportalapp.recyclerview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import co.uk.bawmpt.unimyportalapp.R;
import co.uk.bawmpt.unimyportalapp.model.Book;

public class LibraryAdaptar extends RecyclerView.Adapter {
    List<Book> fetchBookList;

    public LibraryAdaptar(List<Book> fetchBookList) {
        this.fetchBookList = fetchBookList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.library_card_view,parent,false);
        ViewHolderClass viewHolderClass=new ViewHolderClass(view);

        return viewHolderClass;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        ViewHolderClass viewHolderClass=(ViewHolderClass)holder;
        Book book=fetchBookList.get(position);
        viewHolderClass.title.setText(book.getTitle());
        viewHolderClass.author.setText(book.getAuthor());
        viewHolderClass.edition.setText(book.getEdition());
        viewHolderClass.isbn.setText(book.getIsbn());
        viewHolderClass.category.setText(book.getCategory());
        viewHolderClass.available.setText(book.getAvailable().toString());

        Glide.with(viewHolderClass.image.getContext()).load(book.getImage()).into(viewHolderClass.image);

    }

    @Override
    public int getItemCount() {
        return fetchBookList.size();
    }
    public void filteredList(List<Book>filteredList){
        fetchBookList=filteredList;
        notifyDataSetChanged();
    }

    private class ViewHolderClass extends RecyclerView.ViewHolder{
            ImageView image;
            TextView title, author, edition, isbn, available, category;
        public ViewHolderClass(@NonNull View itemView) {
            super(itemView);
            image=itemView.findViewById(R.id.imageview);
            title=itemView.findViewById(R.id.title);
            author=itemView.findViewById(R.id.author);
            edition=itemView.findViewById(R.id.edition);
            isbn=itemView.findViewById(R.id.isbn);
            available=itemView.findViewById(R.id.available);
            category=itemView.findViewById(R.id.category);
        }
    }
}
