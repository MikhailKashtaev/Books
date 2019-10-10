package com.example.books.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.books.R;
import com.example.books.model.Book;

import java.util.ArrayList;
import java.util.List;

public class AdapterBooks extends RecyclerView.Adapter<AdapterBooks.ViewHolder> {

    private List<Book> data;

    public AdapterBooks() {
        data = new ArrayList<>();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.book_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Book book = data.get(position);
        holder.setTitle(book.getTitle());
        holder.setAuthor(book.getAuthor());
        holder.setImage(book.getImageURL());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView title;
        TextView author;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image);
            title = itemView.findViewById(R.id.title);
            author = itemView.findViewById(R.id.author);
        }

        public void setImage(String s) {
            Glide.with(image).load(s).into(image);
        }

        public void setTitle(String s) {
            title.setText(s);
        }

        public void setAuthor(String s) {
            author.setText(s);
        }
    }

    public void update(List<Book> books){
        data.clear();
        data.addAll(books);
        notifyDataSetChanged();
    }
}
