 

import com.example.autocatalog.Models.Auth;
import com.example.autocatalog.Models.CarsModel;
import com.example.autocatalog.Models.DimensionModel;
import com.example.autocatalog.Models.EngineModel;
import com.example.autocatalog.Models.InfoModel;
import com.example.autocatalog.Models.PerformanceIndicatorModel;
import com.example.autocatalog.Models.ProfileModels;
import com.example.autocatalog.Models.SuspencionModel;
import com.example.autocatalog.Models.TransmissionModel;
import com.example.autocatalog.Models.UsersModel;
import com.example.autocatalog.Models.VolumeAndMassModel;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface Interface {
    @GET("Engines")
    Call<ArrayList<EngineModel>> getEngines();

    @GET("Cars")
    Call<ArrayList<CarsModel>> getCars();

    @GET("Engines/{id}")
    Call<EngineModel> getIDEngine(@Path("id") int id);

    @GET("Dimensions/{id}")
    Call<DimensionModel> getIDDimencion(@Path("id") int id);

    @GET("Infoes/{id}")
    Call<InfoModel> getIDInfo(@Path("id") int id);

    @GET("PerformanceIndicators/{id}")
    Call<PerformanceIndicatorModel> getIDPerformanceIndicators(@Path("id") int id);

    @GET("Suspensions/{id}")
    Call<SuspencionModel> getIDSuspensions(@Path("id") int id);

    @GET("Transmissions/{id}")
    Call<TransmissionModel> getIDTransmissions(@Path("id") int id);

    @GET("VolumeAndMasses/{id}")
    Call<VolumeAndMassModel> getIDVolumeAndMasses(@Path("id") int id);

    @GET("Cars/SearchManufacturer")
    Call<ArrayList<CarsModel>> getCarManufacturer(@Query("manufacturers") String searchmanufacturer);

    @GET("Cars/Filtration")
    Call<ArrayList<CarsModel>> getFiltarationCar(@Query("body") String filtration);

    @PUT("Cars/{id}")
    Call<CarsModel>  updateCars(@Path("id") int id, @Body CarsModel car);

    @GET("Cars/favourite")
    Call<ArrayList<CarsModel>> getFavCars();

    @POST("Users/sign_up")
    Call<UsersModel> addUser(@Body UsersModel accauntsModel);

    @POST("Users/Sign_in")
    Call<String> authAccaunt(@Body Auth auth);

//    @POST("Profiles")
//    Call<ProfileModels> addAccount(@Body ProfileModels clientModel, @Header("Authorization") String token);

}

