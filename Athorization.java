 

import static android.icu.number.NumberRangeFormatter.with;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.autocatalog.Models.Auth;

import io.paperdb.Paper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Athorization extends AppCompatActivity {

    Button enter, reg;
    EditText login, password;
    Interface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_athorization);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);



        Paper.init(this);

        login = findViewById(R.id.Login);
        password = findViewById(R.id.Password);

        enter = findViewById(R.id.EnterButton);
        reg = findViewById(R.id.Registration);

        apiInterface = RequestBuilder.buildRequest().create(Interface.class);

        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Registration.class);
                startActivity(intent);
            }
        });

        enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean check = true;
                if(login.getText().length()<1){
                    check = false;
                    login.setError("Поле не заполнено");
                }
                if(password.getText().length()<1){
                    check = false;
                    password.setError("Поле не заполнено");
                }
                if(check){
                    PostData(login.getText().toString(), password.getText().toString());
                }
            }
        });
    }
    private void PostData(String log, String pass){
        Auth auth = new Auth( log, pass);

        Call<String> call = apiInterface.authAccaunt(auth);

       call.enqueue(new Callback<String>() {
           @Override
           public void onResponse(Call<String> call, Response<String> response) {
                if(response.isSuccessful()){
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                //Toast.makeText(getApplicationContext(), "" + response.body().getToken(), Toast.LENGTH_SHORT).show();
                Paper.book("token").write("token", response.body());
                }
           }

           @Override
           public void onFailure(Call<String> call, Throwable t) {
               Toast.makeText(Athorization.this, t.getMessage(),  Toast.LENGTH_SHORT).show();
           }
       });
    }
}