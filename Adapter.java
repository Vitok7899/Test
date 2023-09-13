 

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.autocatalog.Models.CarsModel;
import com.example.autocatalog.Models.EngineModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder>{

    interface OnCarClickListener{
        void onCarClick(CarsModel carsModel, int position);
    }

    interface OnFavoriteClickListener{
        void onFavClick(CarsModel carsModel, int Position);
    }

    private OnCarClickListener onCarClickListener;
    private OnFavoriteClickListener onFavoriteClickListener;
    private Context context;
    private ArrayList<CarsModel> carsModels;

    public Adapter(Context context, ArrayList<CarsModel> carsList, OnCarClickListener onCarClickListener, OnFavoriteClickListener onFavoriteClickListener){
        this.context = context;
        this.carsModels = carsList;
        this.onCarClickListener = onCarClickListener;
        this.onFavoriteClickListener = onFavoriteClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View view = LayoutInflater.from(context).inflate(R.layout.item_card, parent, false);
        return new ViewHolder(view);

    }

    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position){
        CarsModel carsModel = carsModels.get(position);
        holder.CarManufacturer.setText(carsModel.getManufacturer());
        holder.CarModel.setText(carsModel.getModel());
        holder.CarBody.setText(carsModel.getBody());
        Picasso.with(context).load(carsModel.getPhoto()).into(holder.carPhoto);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onCarClickListener.onCarClick(carsModel, position);
            }
        });

        holder.favorites.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onFavoriteClickListener.onFavClick(carsModel, position);
            }
        });
    }



    @Override
    public int getItemCount(){return  carsModels.size();}

    public static class ViewHolder extends  RecyclerView.ViewHolder{
        TextView CarModel;
        TextView CarManufacturer;
        TextView CarBody;
        ImageView carPhoto;
        FloatingActionButton favorites;
        ViewHolder(View view){
            super(view);
            CarModel = view.findViewById(R.id.car_Model);
            CarManufacturer = view.findViewById(R.id.car_Name);
            CarBody = view.findViewById(R.id.car_Body);
            carPhoto = view.findViewById(R.id.car_photo);
            favorites = view.findViewById(R.id.favorites);
        }
    }



}
