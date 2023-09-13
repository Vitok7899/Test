 

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Window;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.autocatalog.Models.CarsModel;
import com.example.autocatalog.Models.DimensionModel;
import com.example.autocatalog.Models.EngineModel;
import com.example.autocatalog.Models.InfoModel;
import com.example.autocatalog.Models.PerformanceIndicatorModel;
import com.example.autocatalog.Models.SuspencionModel;
import com.example.autocatalog.Models.TransmissionModel;
import com.example.autocatalog.Models.VolumeAndMassModel;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SecondActivity extends AppCompatActivity {

    TextView enginetype;
    TextView enginecapacity;
    TextView enginecylinder;
    TextView numcylinder;
    TextView carlength;
    TextView carwidth;
    TextView carheight;
    TextView carclearance;
    TextView infoworld;
    TextView infoclass;
    TextView numdoors;
    TextView numseats;
    TextView trunk;
    TextView fueltank;
    TextView curbweight;
    TextView full_mass;
    TextView transmissiontype;
    TextView numgear;
    TextView drivetype;
    TextView frontsuspencion;
    TextView rearsuspencion;
    TextView frontbreak;
    TextView rearbreak;
    TextView maxspeed;
    TextView fuelconsumption;
    Interface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_second);

        enginetype = findViewById(R.id.engine_type);
        enginecapacity = findViewById(R.id.engine_capacity);
        enginecylinder = findViewById(R.id.engine_cylinder);
        numcylinder = findViewById(R.id.num_cylinder);
        carlength = findViewById(R.id.car_length);
        carwidth = findViewById(R.id.car_width);
        carheight = findViewById(R.id.car_height);
        carclearance = findViewById(R.id.car_clearance);
        infoworld = findViewById(R.id.info_world);
        infoclass = findViewById(R.id.info_class);
        numdoors = findViewById(R.id.num_doors);
        numseats = findViewById(R.id.num_seats);
        trunk = findViewById(R.id.trunk);
        fueltank = findViewById(R.id.fuel_tank);
        curbweight = findViewById(R.id.curb_weight);
        full_mass = findViewById(R.id.full_mass);
        transmissiontype = findViewById(R.id.transmission_type);
        numgear = findViewById(R.id.num_gear);
        drivetype = findViewById(R.id.drive_type);
        frontsuspencion = findViewById(R.id.front_suspencion);
        rearsuspencion = findViewById(R.id.rear_suspencion);
        frontbreak = findViewById(R.id.front_break);
        rearbreak = findViewById(R.id.rear_break);
        maxspeed = findViewById(R.id.max_speed);
        fuelconsumption = findViewById(R.id.fuel_consumption);

        int id = getIntent().getIntExtra("id", 0);
        apiInterface = RequestBuilder.buildRequest().create(Interface.class);
        Call<EngineModel> getEngineId = apiInterface.getIDEngine(id);
        getEngineId.enqueue(new Callback<EngineModel>() {
            @Override
            public void onResponse(Call<EngineModel> call, Response<EngineModel> response) {
                if(response.isSuccessful()){
                    EngineModel engineModel = response.body();
                    enginetype.setText(engineModel.getEngineType());
                    enginecapacity.setText(String.valueOf(engineModel.getEngineCapacity()));
                    enginecylinder.setText(engineModel.getCylinderArrange());
                    numcylinder.setText(String.valueOf(engineModel.getNumCylinder()));
                }else{
                    Toast.makeText(getApplicationContext(), "Ошибка со сотороны клиента", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<EngineModel> call, Throwable t) {

            }
        });

        Call<DimensionModel> getDimensionID = apiInterface.getIDDimencion(id);
        getDimensionID.enqueue(new Callback<DimensionModel>() {
            @Override
            public void onResponse(Call<DimensionModel> call, Response<DimensionModel> response) {
                if (response.isSuccessful()) {
                    DimensionModel dimensionModel = response.body();
                    carlength.setText(String.valueOf(dimensionModel.getCarLength()));
                    carwidth.setText(String.valueOf(dimensionModel.getWidth()));
                    carheight.setText(String.valueOf(dimensionModel.getHeight()));
                    carclearance.setText(String.valueOf(dimensionModel.getClearance()));
                } else {
                    Toast.makeText(getApplicationContext(), "Ошибка со сотороны клиента", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<DimensionModel> call, Throwable t) {

            }
        });

        Call<InfoModel> getInfoID = apiInterface.getIDInfo(id);
        getInfoID.enqueue(new Callback<InfoModel>() {
            @Override
            public void onResponse(Call<InfoModel> call, Response<InfoModel> response) {
                if(response.isSuccessful()){
                    InfoModel infoModel = response.body();
                    infoworld.setText(infoModel.getWorld());
                    infoclass.setText(infoModel.getClassCar());
                    numdoors.setText(String.valueOf(infoModel.getNumDoors()));
                    numseats.setText(String.valueOf(infoModel.getNumSeats()));
                }else{
                    Toast.makeText(getApplicationContext(), "Ошибка со сотороны клиента", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<InfoModel> call, Throwable t) {

            }
        });

        Call<VolumeAndMassModel> getVolumeAndMassId = apiInterface.getIDVolumeAndMasses(id);
        getVolumeAndMassId.enqueue(new Callback<VolumeAndMassModel>() {
            @Override
            public void onResponse(Call<VolumeAndMassModel> call, Response<VolumeAndMassModel> response) {
                if(response.isSuccessful()){
                    VolumeAndMassModel volumeAndMassModel = response.body();
                    trunk.setText(String.valueOf(volumeAndMassModel.getTrunkVolume()));
                    fueltank.setText(String.valueOf(volumeAndMassModel.getFuelTankVolume()));
                    curbweight.setText(String.valueOf(volumeAndMassModel.getCurbWeight()));
                    full_mass.setText(String.valueOf(volumeAndMassModel.getFullMass()));
                }else{
                    Toast.makeText(getApplicationContext(), "Ошибка со сотороны клиента", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<VolumeAndMassModel> call, Throwable t) {

            }
        });

        Call<TransmissionModel> getTransmissionId = apiInterface.getIDTransmissions(id);
        getTransmissionId.enqueue(new Callback<TransmissionModel>() {
            @Override
            public void onResponse(Call<TransmissionModel> call, Response<TransmissionModel> response) {
                if(response.isSuccessful()){
                    TransmissionModel transmissionModel = response.body();
                    transmissiontype.setText(transmissionModel.getGearbox());
                    numgear.setText(transmissionModel.getNumberGears());
                    drivetype.setText(transmissionModel.getTypeDrive());
                }else{
                    Toast.makeText(getApplicationContext(), "Ошибка со сотороны клиента", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<TransmissionModel> call, Throwable t) {

            }
        });

        Call<SuspencionModel> getSuspencionId = apiInterface.getIDSuspensions(id);
        getSuspencionId.enqueue(new Callback<SuspencionModel>() {
            @Override
            public void onResponse(Call<SuspencionModel> call, Response<SuspencionModel> response) {
                if(response.isSuccessful()){
                     SuspencionModel suspencionModel = response.body();
                     frontsuspencion.setText(suspencionModel.getFrontSuspension());
                     rearsuspencion.setText(suspencionModel.getRearSuspension());
                     frontbreak.setText(suspencionModel.getFrontBreak());
                     rearbreak.setText(suspencionModel.getRearBreak());
                }else{
                    Toast.makeText(getApplicationContext(), "Ошибка со сотороны клиента", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<SuspencionModel> call, Throwable t) {

            }
        });

        Call<PerformanceIndicatorModel> getPerformanceIndicatorId = apiInterface.getIDPerformanceIndicators(id);
        getPerformanceIndicatorId.enqueue(new Callback<PerformanceIndicatorModel>() {
            @Override
            public void onResponse(Call<PerformanceIndicatorModel> call, Response<PerformanceIndicatorModel> response) {
                if(response.isSuccessful()){
                    PerformanceIndicatorModel performanceIndicatorModel = response.body();
                    maxspeed.setText(String.valueOf(performanceIndicatorModel.getMaxSpeed()));
                    fuelconsumption.setText(performanceIndicatorModel.getFuelConsumption());
                }else{
                    Toast.makeText(getApplicationContext(), "Ошибка со сотороны клиента", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<PerformanceIndicatorModel> call, Throwable t) {

            }
        });

    }
}