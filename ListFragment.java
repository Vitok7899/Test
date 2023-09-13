 

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.example.autocatalog.Models.CarsModel;

import java.util.ArrayList;

import io.paperdb.Paper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ListFragment extends Fragment {

    private ArrayList<CarsModel> cars;

    RecyclerView recyclerViewTop;
    Button searchButton, filtrButton;
    EditText searchText, filtrText;
    Interface apiInterface;
    Adapter.OnFavoriteClickListener favoriteClickListener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        searchButton = view.findViewById(R.id.search_button);
        searchText = view.findViewById(R.id.search_text);
        filtrButton = view.findViewById(R.id.filtr_button);
        filtrText = view.findViewById(R.id.filtr_text);
        recyclerViewTop = view.findViewById(R.id.car_list);
        Adapter.OnCarClickListener carClickListener = new Adapter.OnCarClickListener() {
            @Override
            public void onCarClick(CarsModel carsModel, int position) {

                Intent intent = new Intent(getContext(), SecondActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("id", carsModel.getIdEngine());
                startActivity(intent);
            }
        };
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(searchText.getText().length() >0 ) {
                    getData(carClickListener);
                }
                else if(searchText.getText().toString().equals("")){
                    getCarsList(view, carClickListener);
                }


            }
        });

        filtrButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Adapter.OnCarClickListener carClickListener = new Adapter.OnCarClickListener() {
                    @Override
                    public void onCarClick(CarsModel carsModel, int position) {
                        Intent intent = new Intent(getContext(), SecondActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent.putExtra("id", carsModel.getIdEngine());
                        startActivity(intent);
                    }
                };

                apiInterface = RequestBuilder.buildRequest().create(Interface.class);
                Call<ArrayList<CarsModel>> getSearchingCar = apiInterface.getFiltarationCar(filtrText.getText().toString());
                getSearchingCar.enqueue(new Callback<ArrayList<CarsModel>>() {
                    @Override
                    public void onResponse(Call<ArrayList<CarsModel>> call, Response<ArrayList<CarsModel>> response) {
                        if(response.isSuccessful()){
                            recyclerViewTop.setLayoutManager(new LinearLayoutManager(getContext()));
                            recyclerViewTop.setHasFixedSize(true);
                            ArrayList<CarsModel> list = response.body();
                            Adapter adapter = new Adapter(getContext(), list, carClickListener, favoriteClickListener);
                            recyclerViewTop.setAdapter(adapter);
                        }else{
                            Toast.makeText(getContext(), "Ошибка со стороны пользователя", Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ArrayList<CarsModel>> call, Throwable t) {
                        Toast.makeText(getContext(), "Ошибка со стороны сервера", Toast.LENGTH_LONG).show();
                    }
                });

            }
        });
        return view;
    }



    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        favoriteClickListener = new Adapter.OnFavoriteClickListener() {
            @Override
            public void onFavClick(CarsModel carsModel, int Position) {
                updateFavorite(carsModel);
            }
        };

        Adapter.OnCarClickListener carClickListener = new Adapter.OnCarClickListener() {
            @Override
            public void onCarClick(CarsModel carsModel, int position) {

                Intent intent = new Intent(getContext(), SecondActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("id", carsModel.getIdEngine());
                startActivity(intent);

            }
        };

        getCarsList(view, carClickListener);

    }

    private void updateFavorite(CarsModel car) {
        if (car.favorites){
            car.setFavorites(false);
        }else {
            car.setFavorites(true);
        }
        apiInterface = RequestBuilder.buildRequest().create(Interface.class);
        Call<CarsModel> updateCars = apiInterface.updateCars(car.idCar, car);
        updateCars.enqueue(new Callback<CarsModel>() {
            @Override
            public void onResponse(Call<CarsModel> call, Response<CarsModel> response) {
                if (response.isSuccessful()){
                    Toast.makeText(getContext(), "Данная машина добавлена в избранное", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<CarsModel> call, Throwable t) {
                Log.e("err", t.getMessage());
            }
        });
    }

    private void getData(Adapter.OnCarClickListener carClickListener)
    {
        apiInterface = RequestBuilder.buildRequest().create(Interface.class);
        Call<ArrayList<CarsModel>> getSearchingCars = apiInterface.getCarManufacturer(searchText.getText().toString());
        getSearchingCars.enqueue(new Callback<ArrayList<CarsModel>>() {
            @Override
            public void onResponse(Call<ArrayList<CarsModel>> call, Response<ArrayList<CarsModel>> response) {
                if(response.isSuccessful()){
                    recyclerViewTop.setLayoutManager(new LinearLayoutManager(getContext()));
                    recyclerViewTop.setHasFixedSize(true);
                    ArrayList<CarsModel> list = response.body();
                    Adapter adapter = new Adapter(getContext(), list, carClickListener, favoriteClickListener);
                    recyclerViewTop.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<ArrayList<CarsModel>> call, Throwable t) {

            }
        });
    }

    private void getCarsList(View view, Adapter.OnCarClickListener carClickListener){
        apiInterface = RequestBuilder.buildRequest().create(Interface.class);
        Call<ArrayList<CarsModel>> getCars = apiInterface.getCars();
        getCars.enqueue(new Callback<ArrayList<CarsModel>>() {
            @Override
            public void onResponse(Call<ArrayList<CarsModel>> call, Response<ArrayList<CarsModel>> response) {
                if(response.isSuccessful()){
                    recyclerViewTop.setLayoutManager(new LinearLayoutManager(getContext()));
                    recyclerViewTop.setHasFixedSize(true);
                    ArrayList<CarsModel> list = response.body();
                    Adapter adapter = new Adapter(view.getContext(), list, carClickListener, favoriteClickListener);
                    recyclerViewTop.setAdapter(adapter);
                }else{
                    Toast.makeText(getContext(), "Ошибка со стороны клиента", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<CarsModel>> call, Throwable t) {
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}