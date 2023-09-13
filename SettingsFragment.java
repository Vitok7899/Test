 

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.example.autocatalog.Models.CarsModel;
import com.google.android.material.switchmaterial.SwitchMaterial;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SettingsFragment extends Fragment {

    SwitchMaterial theme;

    Interface apiInterface;
    RecyclerView favoriteCars;
    Adapter.OnFavoriteClickListener favoriteClickListener;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container,
                             @NonNull Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings, container, false);
        favoriteCars = view.findViewById(R.id.favoriteCarlist);
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

        getFavCars(view, carClickListener);
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

    private void getFavCars(View view, Adapter.OnCarClickListener carClickListener)
    {
        apiInterface = RequestBuilder.buildRequest().create(Interface.class);
        Call<ArrayList<CarsModel>> getFavCars = apiInterface.getFavCars();
        getFavCars.enqueue(new Callback<ArrayList<CarsModel>>() {
            @Override
            public void onResponse(Call<ArrayList<CarsModel>> call, Response<ArrayList<CarsModel>> response) {
                if(response.isSuccessful()){
                    favoriteCars.setLayoutManager(new LinearLayoutManager(getContext()));
                    favoriteCars.setHasFixedSize(true);
                    ArrayList<CarsModel> list = response.body();
                    Adapter adapter = new Adapter(view.getContext(), list, carClickListener, favoriteClickListener);
                    favoriteCars.setAdapter(adapter);
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