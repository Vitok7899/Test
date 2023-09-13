 

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.autocatalog.Models.ProfileModels;
import com.example.autocatalog.Models.UserModel;
import com.example.autocatalog.Models.UsersModel;

import io.paperdb.Paper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Registration extends AppCompatActivity {

    Button add, regist;
    Interface apiInterface;
    EditText login, password, surname, name, middlename, email, numphone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        login = findViewById(R.id.Login);
        password = findViewById(R.id.Password);
        surname = findViewById(R.id.surname);
        name = findViewById(R.id.name);
        middlename = findViewById(R.id.midname);
        email = findViewById(R.id.emails);
        numphone = findViewById(R.id.number);

        add = findViewById(R.id.add_accaunt);
        regist = findViewById(R.id.regist);

        apiInterface = RequestBuilder.buildRequest().create(Interface.class);

        regist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Athorization.class);
                startActivity(intent);
            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean check = true;
                if(login.getText().length() < 1){
                    check = false;
                    login.setError("Поле не заполнено");
                }
                if(password.getText().length() < 1){
                    check = false;
                    password.setError("Поле не заполнено");
                }
                if(check){
                    PostData(surname.getText().toString(),name.getText().toString(),middlename.getText().toString(),email.getText().toString(),numphone.getText().toString(),login.getText().toString(),password.getText().toString());
                }
            }
        });
    }

    private void PostData(String surname, String name, String middlename, String email, String numphone, String login, String password){
        UsersModel usersModel = new UsersModel(surname, name, middlename, email, numphone, login, password);


        Call<UsersModel> call = apiInterface.addUser(usersModel);

        call.enqueue(new Callback<UsersModel>() {
            @Override
            public void onResponse(Call<UsersModel> call, Response<UsersModel> response) {
                if(response.isSuccessful()){
                    Intent intent = new Intent(getApplicationContext(), Athorization.class);
                    startActivity(intent);
                    Paper.book("token").write("token", response.body());
                }
            }

            @Override
            public void onFailure(Call<UsersModel> call, Throwable t) {
                Toast.makeText(Registration.this, "error",  Toast.LENGTH_SHORT).show();
            }
        });
        //Call<ProfileModels> call = apiInterface.addAccount();




    }
}