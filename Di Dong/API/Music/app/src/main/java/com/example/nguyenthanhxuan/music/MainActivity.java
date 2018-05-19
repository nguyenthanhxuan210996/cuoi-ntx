package com.example.nguyenthanhxuan.music;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    private Button btnSign;
    private TextView tvJson;
    EditText username;
    EditText passwords;
    private Button btnRes;
    public static  String passs=" ";
    public static  String name=" ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSign = (Button) findViewById(R.id.btnSign);
        tvJson = (TextView) findViewById(R.id.tvJson);
        btnRes = (Button) findViewById(R.id.btnRegister);

        passwords = findViewById(R.id.edtpass);
        username = findViewById(R.id.edtname);

        btnSign.setOnClickListener(new View.OnClickListener() {
                                       @Override
                                       public void onClick(View v) {
                                           name = username.getText().toString();
                                           passs = passwords.getText().toString();
                                           if (name.length() > 0 && passs.length() > 0)
                                               new DangNhap().execute("http://192.168.43.204/music/api/music?name=" + name);
                                           else tvJson.setText("Mời nhập đủ thông tin");
                                       }
                                   }
        );

        btnRes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, DangKy.class);
                startActivity(intent);
            }
        });
    }

    public class DangNhap extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... params) {
            HttpURLConnection connection = null;
            BufferedReader reader = null;

            try {
                URL url = new URL(params[0]);
                connection = (HttpURLConnection) url.openConnection();
                connection.connect();

                InputStream stream = connection.getInputStream();

                reader = new BufferedReader(new InputStreamReader(stream));

                StringBuffer buffer = new StringBuffer();

                String line = "";

                while ((line = reader.readLine()) != null) {
                    buffer.append(line);
                }

                String finalJson = buffer.toString();
                String data = "";

                try {

                    JSONObject jsonObject = new JSONObject(finalJson);
                    String pass = jsonObject.optString("password").toString();


                    data = pass.trim();
                } catch (JSONException e) {
                    e.printStackTrace();
                }


                return data;


            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();

            } finally {
                if (connection != null) {
                    connection.disconnect();
                }

                if (reader != null) {
                    try {
                        reader.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            return null;
        }
        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            passs= passwords.getText().toString();
            if (result.equals(passs)){
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                startActivity(intent);
            }
            else {
                tvJson.setText("Mật khẩu không chính xác hoặc tài khoản không tồn tại");
                Toast.makeText(MainActivity.this, "Mật khẩu không chính xác hoặc tài khoản không tồn tại", Toast.LENGTH_LONG);
            }
        }
    }
}




