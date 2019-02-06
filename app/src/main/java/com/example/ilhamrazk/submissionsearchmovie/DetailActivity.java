package com.example.ilhamrazk.submissionsearchmovie;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

public class DetailActivity extends AppCompatActivity {
    ImageView imgposter;
    TextView title;
    TextView rating;
    TextView overview;
    TextView release;
    TextView language;

//    public static final String EXTRA_TITLE = "extra_title";




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        imgposter = findViewById(R.id.posterFoto);
        title = findViewById(R.id.detailTitle);
        rating = findViewById(R.id.rating);
        overview = findViewById(R.id.overviewpalingbawah);
        release = findViewById(R.id.releasedate);
        language = findViewById(R.id.language);

        /**
         * moveIntent.putExtra("title", list.get(i).title);
         *                 moveIntent.putExtra("overview", list.get(i).overview);
         *                 moveIntent.putExtra("rating", list.get(i).voteAverage);
         *                 moveIntent.putExtra("language", list.get(i).originalLanguage);
         *                 moveIntent.putExtra("Release", list.get(i).releaseDate);
         *                 String urlposter = "https://image.tmdb.org/t/p/w185"+list.get(i).posterPath;
         */

//        String setTitle = getIntent().getStringExtra(EXTRA_TITLE);
//        title.setText(setTitle);
        title.setText(getIntent().getStringExtra("title"));
        Bundle bundle = getIntent().getExtras();
        float yourFloat = bundle.getFloat("rating");
        String parseFloat = Float.toString(yourFloat);
        rating.setText(parseFloat);
        overview.setText(getIntent().getStringExtra("overview"));
        release.setText(getIntent().getStringExtra("Release"));
        language.setText(getIntent().getStringExtra("language").toUpperCase());

        RequestOptions options = new RequestOptions()
                .circleCropTransform()
                .placeholder(R.mipmap.ic_launcher_round)
                .error(R.mipmap.ic_launcher_round);

        Glide.with(this).load(getIntent().getStringExtra("poster")).apply(options).into(imgposter);


    }
}
