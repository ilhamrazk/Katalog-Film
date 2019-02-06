package com.example.ilhamrazk.submissionsearchmovie;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.ilhamrazk.submissionsearchmovie.adapter.MovieListAdapter;
import com.example.ilhamrazk.submissionsearchmovie.model.Response;
import com.example.ilhamrazk.submissionsearchmovie.networks.ApiService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private RecyclerView listmovie;
    private MovieListAdapter adaptermovie;
    private List<Response.Result> result;

    private RecyclerView recyclerView;
    DataInterface movie;
    private String API_KEY = "217aba77e4ad4f88239cb4fd46ad253f";

    EditText searchbar;
    Button btnsearch;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        result = new ArrayList<>();
        adaptermovie = new MovieListAdapter(result);

        recyclerView = findViewById(R.id.rv_movie);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adaptermovie);

        searchbar = findViewById(R.id.searchbar);
        btnsearch = findViewById(R.id.buttonsearch);
        btnsearch.setOnClickListener(this);

//        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(this);
//
//        recyclerView = findViewById(R.id.rv_movie);
//        if (recyclerView!=null){
//            recyclerView.addItemDecoration(itemDecoration);
//            recyclerView.setHasFixedSize(true);
//            recyclerView.setLayoutManager(new LinearLayoutManager(this));
//            recyclerView.setAdapter(adaptermovie);
//        }

        loadmovie();

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

    }

    private void loadmovie(){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(getResources().getString(R.string.url))
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        //konek ke interfacenya retrofit
        movie = retrofit.create(DataInterface.class);

        //konekin dari interface ke method callnya


        Call<Response> callData = movie.callMovie(API_KEY);

        callData.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                if (response.isSuccessful()){
                  for (int i = 0; i < response.body().results.size(); i++){
//                        Log.e("TAG","Title : "+ response.body().results.get(i).title);
                      result.add(response.body().results.get(i));
                  }
                  adaptermovie.addData(result);
                  adaptermovie.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {

            }
        });
    }

    public void getSearchMovie(final String judul){
        result.clear();
        Log.d("TAG","Judul" + judul);
        Call<Response> call = movie.getSearchMovie(judul, API_KEY);
        call.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                Log.d("TAG","Pencarian Sukses");
                Log.d("TAG"," "+ response.raw());
                if (response.isSuccessful()){
                    for (int i = 0; i < response.body().results.size(); i++){
                        result.add(response.body().results.get(i));
                        Log.d("Tag","Ini title");
                    }
                    adaptermovie.addData(result);
                    adaptermovie.notifyDataSetChanged();

                }

            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {
                Log.e("TAG","Gagal pencarian");
                t.printStackTrace();
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.buttonsearch:
                String ubah = this.searchbar.getText().toString();
                getSearchMovie(ubah);
        }
    }
}
