package com.example.lab8;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.lab8.DB.BookDB;
import com.example.lab8.model.BookDetailedModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.squareup.picasso.Picasso;

public class BookDetail  extends AppCompatActivity {
private BookDetailedModel bookDetailedModel;
ImageView imgCoverPhoto;
TextView tvTitle,tvAuthor,tvPublishedYear,tvNumberofPages,tvGenre,tvOlanguage,tvDescription;
BookDB db=new BookDB(BookDetail.this);
FloatingActionButton fabEdit;
private void InitiateViews()
{
    imgCoverPhoto=findViewById(R.id.imgCoverPhoto);
    tvTitle=findViewById(R.id.tvTitle);
    tvAuthor=findViewById(R.id.tvAuthor);
    tvPublishedYear=findViewById(R.id.tvPublishedYear);
    tvNumberofPages=findViewById(R.id.tvNumberofPages);
    tvGenre=findViewById(R.id.tvGenre);
    tvOlanguage=findViewById(R.id.tvOlanguage);
    tvDescription=findViewById(R.id.tvDescription);
    fabEdit=findViewById(R.id.fabEdit);
}
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_detail);
        InitiateViews();

    }

    @Override
    protected void onResume() {
        super.onResume();
        bookDetailedModel=getIntent().getParcelableExtra("Value");
        getSupportActionBar().setTitle(bookDetailedModel.getTitle());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Picasso.get().load(bookDetailedModel.getCoverPhoto()).into(imgCoverPhoto);
        tvTitle.setText(bookDetailedModel.getTitle());
        String sourceString = "Written By <b>" + bookDetailedModel.getAuthor() + "</b> ";
        tvAuthor.setText(Html.fromHtml(sourceString));
        tvPublishedYear.setText("Published in "+bookDetailedModel.getPublishedYear());
        tvNumberofPages.setText(bookDetailedModel.getNumberofPages()+" Pages");
        tvGenre.setText(bookDetailedModel.getGenre());
        tvOlanguage.setText("Language: "+bookDetailedModel.getOriginalLanguage());
        tvDescription.setText(bookDetailedModel.getDescription());
        fabEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(BookDetail.this,UpdateBook.class);
                Bundle b=new Bundle();
                b.putParcelable("OldValue",bookDetailedModel);
                i.putExtras(b);
                startActivity(i);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.deletemenu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
    switch (item.getItemId())
    {
        case android.R.id.home:
            Intent i = new Intent(BookDetail.this, MainActivity.class);

            startActivity(i);
            finish();
            break;

        case R.id.delete:
AlertDialog.Builder alertdialogbuilder=new AlertDialog.Builder(BookDetail.this);
            alertdialogbuilder.setMessage("Are You Sure You Want to Remove This Book");
            alertdialogbuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                   db.RemoveBook(bookDetailedModel.getBookID());
                    Toast.makeText(BookDetail.this, "Remove Successfully", Toast.LENGTH_SHORT).show();
Intent intent=new Intent(BookDetail.this, MainActivity.class);
startActivity(intent);

                }
            });
            alertdialogbuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                }
            });
            AlertDialog dialog=alertdialogbuilder.create();
            dialog.show();

    }
        return super.onOptionsItemSelected(item);
    }
}
