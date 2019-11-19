package com.example.lab8.adapters;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lab8.BookDetail;
import com.example.lab8.DB.BookDB;
import com.example.lab8.MainActivity;
import com.example.lab8.R;
import com.example.lab8.model.BookDetailedModel;

import java.util.List;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.ViewHolder> {
    private List<BookDetailedModel> bookModelList;
    public BookAdapter(List<BookDetailedModel> bookModelList) {
        this.bookModelList = bookModelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.booklistitem,parent,false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.tvNumber.setText(bookModelList.get(position).getBookID()+"");
holder.tvTitle.setText(bookModelList.get(position).getTitle());
holder.tvAuthor.setText(bookModelList.get(position).getAuthor());
holder.tvPublishedYear.setText(bookModelList.get(position).getPublishedYear()+"");
holder.imgDelete.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(final View v) {
        AlertDialog.Builder alertdialogbuilder=new AlertDialog.Builder(v.getContext());
        alertdialogbuilder.setMessage("Are You Sure You Want to Remove This Book");
        alertdialogbuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                BookDB db=new BookDB(v.getContext());
                db.RemoveBook(bookModelList.get(position).getBookID());
                Toast.makeText(v.getContext(), "Remove Successfully", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(v.getContext(), MainActivity.class);
                v.getContext().startActivity(intent);

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
});
    }

    @Override
    public int getItemCount() {
        return bookModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvNumber,tvTitle,tvAuthor,tvPublishedYear;
        ImageView imgDelete;
        public ViewHolder(@NonNull View v) {
            super(v);
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i=new Intent(v.getContext(), BookDetail.class);
                    Bundle b=new Bundle();
                    b.putParcelable("Value",bookModelList.get(getPosition()));
                    i.putExtras(b);

                    v.getContext().startActivity(i);
                }
            });
            tvNumber=v.findViewById(R.id.tvNumber);
            tvTitle=v.findViewById(R.id.tvTitle);
            tvAuthor=v.findViewById(R.id.tvAuthor);
            tvPublishedYear=v.findViewById(R.id.tvPublishedYear);
            imgDelete=v.findViewById(R.id.imgDelete);
        }
    }
}
