package com.example.lab8;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.lab8.DB.BookDB;
import com.example.lab8.model.BookDetailedModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;

public class UpdateBook extends AppCompatActivity {
    TextInputEditText tietCoverPhoto,tietTitle,tietAuthor,tietGenre,tietOlanguage,tietDescription,tietPublishedYear,tietNumberofPages;
    FloatingActionButton fabDone;
    BookDB db=new BookDB(UpdateBook.this);
    BookDetailedModel bookDetailedModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_book);
        tietCoverPhoto=findViewById(R.id.edtietCoverPhoto);
        tietTitle=findViewById(R.id.edtietTitle);
        tietAuthor=findViewById(R.id.edtietAuthor);
        tietGenre=findViewById(R.id.edtietGenre);
        tietOlanguage=findViewById(R.id.edtietOlanguage);
        tietDescription=findViewById(R.id.edtietDescription);
        tietPublishedYear=findViewById(R.id.edtietPublishedYear);
        tietNumberofPages=findViewById(R.id.edtietNumberofPages);
        fabDone=findViewById(R.id.edfabDone);




        bookDetailedModel=getIntent().getParcelableExtra("OldValue");


        getSupportActionBar().setTitle("Edit the "+bookDetailedModel.getTitle());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        tietCoverPhoto.setText(bookDetailedModel.getCoverPhoto());
        tietTitle.setText(bookDetailedModel.getTitle());
        tietAuthor.setText(bookDetailedModel.getAuthor());
        tietGenre.setText(bookDetailedModel.getGenre());
        tietOlanguage.setText(bookDetailedModel.getOriginalLanguage());
        tietDescription.setText(bookDetailedModel.getDescription());
        tietPublishedYear.setText(bookDetailedModel.getPublishedYear()+"");
        tietNumberofPages.setText(bookDetailedModel.getNumberofPages()+"");

        fabDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(tietCoverPhoto.getText().toString().isEmpty() || tietTitle.getText().toString().isEmpty() || tietAuthor.getText().toString().isEmpty()
                        || tietGenre.getText().toString().isEmpty() || tietOlanguage.getText().toString().isEmpty() || tietDescription.getText().toString().isEmpty() || tietPublishedYear.getText().toString().isEmpty()||
                        tietNumberofPages.getText().toString().isEmpty())
                {
                    Toast.makeText(UpdateBook.this, "Fields Cannot Be Empty", Toast.LENGTH_SHORT).show();
                }
                else if(tietCoverPhoto.getText().toString().equals(bookDetailedModel.getCoverPhoto()) &&
                tietTitle.getText().toString().equals(bookDetailedModel.getTitle()) &&
                tietAuthor.getText().toString().equals(bookDetailedModel.getAuthor()) &&
                tietGenre.getText().toString().equals(bookDetailedModel.getGenre()) &&
                tietOlanguage.getText().toString().equals(bookDetailedModel.getOriginalLanguage())&&
                tietDescription.getText().toString().equals(bookDetailedModel.getDescription())&&
               tietPublishedYear.getText().toString().equals(String.valueOf(bookDetailedModel.getPublishedYear()))&&
                tietNumberofPages.getText().toString().equals(String.valueOf(bookDetailedModel.getNumberofPages())))
                {
                    Toast.makeText(UpdateBook.this, "No Changes Deteced! Kindly Change the Updated Information!", Toast.LENGTH_LONG).show();
                }


                else
                {
                    db.UpdateBook(bookDetailedModel.getBookID(),
                            tietCoverPhoto.getText().toString(),
                            tietTitle.getText().toString(),
                            tietAuthor.getText().toString(),
                            tietGenre.getText().toString(),
                            tietOlanguage.getText().toString(),
                            tietDescription.getText().toString(),
                            Integer.parseInt(tietPublishedYear.getText().toString()),
                            Integer.parseInt(tietNumberofPages.getText().toString()));
                            Toast.makeText(UpdateBook.this, "Updated Successfully!", Toast.LENGTH_LONG).show();
                    clear();
                    Intent i=new Intent(UpdateBook.this, MainActivity.class);
                    startActivity(i);







                }
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {
            case android.R.id.home:
               this.finish();

        }
        return super.onOptionsItemSelected(item);
    }

    public void clear()
    {
        tietCoverPhoto.setText("");
        tietTitle.setText("");
        tietAuthor.setText("");
        tietGenre.setText("");
        tietOlanguage.setText("");
        tietDescription.setText("");
        tietPublishedYear.setText("");
        tietNumberofPages.setText("");
        tietCoverPhoto.requestFocus();
    }
}
