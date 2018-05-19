package com.example.nguyenthanhxuan.gamecuoiki;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Nguyen Thanh Xuan on 4/29/2018.
 */

public class DangNhap extends AppCompatActivity {
    private EditText edEmail21;
    private EditText edMatKhau21;
    private Button btnDangNhap21;
    private TextView tv21;
    public static String userName21;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dangnhap);
        edEmail21=findViewById(R.id.edEmail21);
        edMatKhau21=findViewById(R.id.edMatKhau21);
        btnDangNhap21=findViewById(R.id.btnDangNhap21);
        tv21=findViewById(R.id.tv21);
        btnDangNhap21.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int count=0;
                if(edEmail21.getText().toString().length()==0||edMatKhau21.getText().toString().length()==0){
                    Toast.makeText(DangNhap.this,"Không được để trống",Toast.LENGTH_SHORT).show();
                }
                else{
                    for (User user:DangKy.users){
                        if(user.getUserName().equals(edEmail21.getText().toString())&&user.getUserPassword().equals(edMatKhau21.getText().toString())){
                            count++;
                            userName21=edEmail21.getText().toString();
                            GameView gameView= new GameView(DangNhap.this);
                            setContentView(gameView);

                        }
                    }
                    if (count==0) Toast.makeText(DangNhap.this,"Tài khoản không đúng",Toast.LENGTH_SHORT).show();
                }
            }
        });
        tv21.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent22=new Intent(DangNhap.this,DangKy.class);
                startActivity(intent22);
            }
        });

    }
}
