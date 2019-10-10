package com.example.books.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.books.adapters.AdapterBooks;
import com.example.books.R;
import com.example.books.model.Book;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    MainActivityViewModel viewModel;
    RecyclerView rvListOfBooks;
    AdapterBooks adapterBooks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvListOfBooks = findViewById(R.id.list_of_books);
        adapterBooks = new AdapterBooks();
        rvListOfBooks.setLayoutManager(new LinearLayoutManager(this));
        rvListOfBooks.setAdapter(adapterBooks);

        viewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);
        viewModel.getBooksLiveData().observe(this, new Observer<List<Book>>() {
            @Override
            public void onChanged(List<Book> books) {
                adapterBooks.update(books);
            }
        });
        viewModel.getError().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Toast.makeText(MainActivity.this, s, Toast.LENGTH_SHORT).show();
            }
        });

        if(savedInstanceState==null){
            // Dont need to request on every orientation change
            viewModel.requestBooks();
        }
    }

}
