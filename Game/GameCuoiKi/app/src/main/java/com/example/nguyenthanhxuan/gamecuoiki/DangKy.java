package com.example.nguyenthanhxuan.gamecuoiki;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nguyen Thanh Xuan on 4/29/2018.
 */

public class DangKy extends AppCompatActivity {
    private EditText edEmail31;
    private EditText edMatKhau31;
    private EditText edNhapLaiMatKhau31;
    private Button btnDangKy31;
    private TextView tvThongBao;
    public static List<User> users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dangky);

        edEmail31=findViewById(R.id.edEmail31);
        edMatKhau31=findViewById(R.id.edMatKhau31);
        edNhapLaiMatKhau31=findViewById(R.id.edNhapLaiMatKhau31);
        btnDangKy31=findViewById(R.id.btnDangKy31);
        tvThongBao=findViewById(R.id.tvThongBao);
        users=new ArrayList<>();
        btnDangKy31.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (edEmail31.length()==0||edMatKhau31.length()==0||edNhapLaiMatKhau31.length()==0){
                    tvThongBao.setText("Không được để trống");
                }
                else {
                    if(edMatKhau31.getText().toString().equals(edNhapLaiMatKhau31.getText().toString())){
                        tvThongBao.setText("");
                        users.add(new User(edEmail31.getText().toString(),edMatKhau31.getText().toString()));
                        Toast.makeText(DangKy.this,"Đăng ký thành công",Toast.LENGTH_SHORT).show();
                    }
                    else tvThongBao.setText("Mật khẩu không khớp, xin mời nhập lại");
                }
            }
        });


    }
}
