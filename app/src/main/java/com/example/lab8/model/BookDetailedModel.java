package com.example.lab8.model;

import android.os.Parcel;
import android.os.Parcelable;

public class BookDetailedModel implements Parcelable {
    private int BookID;
    private String CoverPhoto,Title,Author,Genre,OriginalLanguage,Description;
    private int PublishedYear,NumberofPages;

    public BookDetailedModel(int bookID, String coverPhoto, String title, String author, String genre, String originalLanguage, String description, int publishedYear, int numberofPages) {
        BookID = bookID;
        CoverPhoto = coverPhoto;
        Title = title;
        Author = author;
        Genre = genre;
        OriginalLanguage = originalLanguage;
        Description = description;
        PublishedYear = publishedYear;
        NumberofPages = numberofPages;
    }

    public int getBookID() {
        return BookID;
    }

    public String getCoverPhoto() {
        return CoverPhoto;
    }

    public String getTitle() {
        return Title;
    }

    public String getAuthor() {
        return Author;
    }

    public String getGenre() {
        return Genre;
    }

    public String getOriginalLanguage() {
        return OriginalLanguage;
    }

    public String getDescription() {
        return Description;
    }

    public int getPublishedYear() {
        return PublishedYear;
    }

    public int getNumberofPages() {
        return NumberofPages;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.BookID);
        dest.writeString(this.CoverPhoto);
        dest.writeString(this.Title);
        dest.writeString(this.Author);
        dest.writeString(this.Genre);
        dest.writeString(this.OriginalLanguage);
        dest.writeString(this.Description);
        dest.writeInt(this.PublishedYear);
        dest.writeInt(this.NumberofPages);
    }

    protected BookDetailedModel(Parcel in) {
        this.BookID = in.readInt();
        this.CoverPhoto = in.readString();
        this.Title = in.readString();
        this.Author = in.readString();
        this.Genre = in.readString();
        this.OriginalLanguage = in.readString();
        this.Description = in.readString();
        this.PublishedYear = in.readInt();
        this.NumberofPages = in.readInt();
    }

    public static final Creator<BookDetailedModel> CREATOR = new Creator<BookDetailedModel>() {
        @Override
        public BookDetailedModel createFromParcel(Parcel source) {
            return new BookDetailedModel(source);
        }

        @Override
        public BookDetailedModel[] newArray(int size) {
            return new BookDetailedModel[size];
        }
    };
}
