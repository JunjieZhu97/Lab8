package com.example.lab8;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lab8.DB.BookDB;
import com.example.lab8.adapters.BookAdapter;
import com.example.lab8.model.BookDetailedModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView rvMain;
    List<BookDetailedModel> bookModelList=new ArrayList<>();
    FloatingActionButton fabAdd;
    BookDB db=new BookDB(MainActivity.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rvMain=findViewById(R.id.rvMain);
        fabAdd=findViewById(R.id.fabAdd);
        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MainActivity.this, AddBook.class);
                startActivity(i);
            }
        });

        getSupportActionBar().setTitle("Home");



    }

    @Override
    protected void onResume() {
        super.onResume();
        bookModelList=db.GetBooks();
        rvMain.addItemDecoration(new DividerItemDecoration(MainActivity.this, DividerItemDecoration.VERTICAL));

        rvMain.setLayoutManager(new LinearLayoutManager(MainActivity.this,RecyclerView.VERTICAL,false));
        rvMain.setHasFixedSize(true);
        rvMain.setAdapter(new BookAdapter(bookModelList));
    }

    private List<BookDetailedModel> DataSource()
    {
        bookModelList.add(new BookDetailedModel(1,"https://cdn.waterstones.com/override/v2/large/9780/0997/9780099740919.jpg","The Handmaid's Tale","MARGARET ATWOOD","Novel","En_US",
                "Offred is the premiere episode of the American television drama series The Handmaid's Tale. It was directed by Reed Morano, and written by Bruce Miller, adapting material from the 1985 Margaret Atwood novel The Handmaid's Tale. The episode debuted on the streaming service Hulu on April 26, 2017.",1985,311));

        bookModelList.add(new BookDetailedModel(2,"https://images-na.ssl-images-amazon.com/images/I/51nx5xk7tFL._SX326_BO1,204,203,200_.jpg","Wrinkle in Time","MADELEINE L'ENGLE","Novel","En_US",
                "A Wrinkle in Time is a young adult novel written by American author Madeleine L'Engle. First published in 1962, the book has won the Newbery Medal, the Sequoyah Book Award, the Lewis Carroll Shelf Award, and was runner-up for the Hans Christian Andersen Award. The main characters, Meg Murry, Charles Wallace Murry, and Calvin O'Keefe, embark on a journey through space and time, from universe to universe, as they endeavor to save the Murrys' father and the world. The novel offers a glimpse into the battles between light and darkness, and goodness and evil, as the young characters mature into adolescents on their journey. The novel wrestles with questions of spirituality and purpose, as the characters are often thrown into conflicts of love, divinity, and goodness. It is the first book in L'Engle's Time Quintet, which follows the Murrys and Calvin O'Keefe.",
                1962,311));

        bookModelList.add(new BookDetailedModel(3,"https://www.books.ba/images/stories/virtuemart/product/9780141328294.jpg","Thirteen Reasons Why","JAY ASHER","Young Adult Fiction","En_US",
                "Thirteen Reasons Why is a young adult novel written in 2007 by Jay Asher. It is the story of a young high school student as she descends into despair brought on by betrayal and bullying, culminating with her suicide. She details the thirteen reasons why she was driven to end her life in an audio diary which is mailed to a friend two weeks after her death.",
                2007,288));

        bookModelList.add(new BookDetailedModel(4,"https://images-na.ssl-images-amazon.com/images/I/51Pb-OAREFL._SX327_BO1,204,203,200_.jpg","American Gods","NEIL GAIMAN","Fantasy","En_US",
                "American Gods is a fantasy novel by British author Neil Gaiman. The novel is a blend of Americana, fantasy, and various strands of ancient and modern mythology, all centering on the mysterious and taciturn Shadow.",
                2001,465));

        bookModelList.add(new BookDetailedModel(5,"https://images-na.ssl-images-amazon.com/images/I/41eHmvXNo9L._SX325_BO1,204,203,200_.jpg","It","STEPHEN KING","Horror,Thriller","En_US",
                "It is a 1986 horror novel by American author Stephen King. It was his 22nd book, and his 17th novel written under his own name. The story follows the experiences of seven children as they are terrorized by an evil entity that exploits the fears of its victims to disguise itself while hunting its prey. It primarily appears in the form of Pennywise the Dancing Clown to attract its preferred prey of young children",
                1986,1138));

        bookModelList.add(new BookDetailedModel(6,"https://images-na.ssl-images-amazon.com/images/I/91SZSW8qSsL.jpg","Nineteen Eighty-Four","GEORGE ORWELL","Political Fiction","En_US",
                "Nineteen Eighty-Four: A Novel, often published as 1984, is a dystopian novel by English novelist George Orwell. It was published in June 1949 by Secker & Warburg as Orwell's ninth and final book completed in his lifetime. The story was mostly written at Barnhill, a farmhouse on the Scottish island of Jura, at times while Orwell suffered from severe tuberculosis. Thematically, Nineteen Eighty-Four centres on the risks of government overreach, totalitarianism, and repressive regimentation of all persons and behaviours within society.",
                1949,328));

        bookModelList.add(new BookDetailedModel(7,"https://images-na.ssl-images-amazon.com/images/I/91WubiE4doL.jpg","Ready Player One","ERNEST CLINE","Utopian and Dystopian fiction","En_US",
                "Ready Player One is a 2011 science fiction novel, and the debut novel of American author Ernest Cline. The story, set in a dystopia in 2044, follows protagonist Wade Watts on his search for an Easter egg in a worldwide virtual reality game, the discovery of which will lead him to inherit the game creator's fortune. Cline sold the rights to publish the novel in June 2010, in a bidding war to the Crown Publishing Group. The book was published on August 16, 2011. An audiobook was released the same day; it was narrated by Wil Wheaton, who was mentioned briefly in one of the chapters. In 2012, the book received an Alex Award from the Young Adult Library Services Association division of the American Library Association and won the 2012 Prometheus Award.",
                2011,385));

        bookModelList.add(new BookDetailedModel(8,"https://images-na.ssl-images-amazon.com/images/I/51%2B2QZIRWfL.jpg","Murder on the Orient Express","AGATHA CHRISTIE","Crime Fiction","En_US",
                "Murder on the Orient Express is a detective novel by British writer Agatha Christie featuring the Belgian detective Hercule Poirot. It was first published in the United Kingdom by the Collins Crime Club on 1 January 1934. In the United States, it was published on 28 February 1934, under the title of Murder in the Calais Coach, by Dodd, Mead and Company. The UK edition retailed at seven shillings and sixpence and the U.S. edition at $2.00.",
                1934,256));

        bookModelList.add(new BookDetailedModel(9,"https://images-na.ssl-images-amazon.com/images/I/51IXWZzlgSL.jpg","To Kill a Mockingbird","HARPER LEE","Southern Gothic","En_US",
                "To Kill a Mockingbird is a novel by Harper Lee published in 1960. Instantly successful, widely read in high schools and middle schools in the United States, it has become a classic of modern American literature, winning the Pulitzer Prize. The plot and characters are loosely based on Lee's observations of her family, her neighbors and an event that occurred near her hometown of Monroeville, Alabama, in 1936, when she was 10 years old.",
                1960,311));

        bookModelList.add(new BookDetailedModel(10,"https://images-na.ssl-images-amazon.com/images/I/71VBpx0qsmL.jpg","The Glass Castle","JEANETTE WALLS","Memoir","En_US",
                "The Glass Castle is a 2005 memoir by Jeannette Walls. The book recounts the unconventional, poverty-stricken upbringing Walls and her siblings had at the hands of their deeply dysfunctional parents. The title refers to her father’s long held intention of building his dream house, a glass castle.",
                2005,289));



        return bookModelList;
    }
}
