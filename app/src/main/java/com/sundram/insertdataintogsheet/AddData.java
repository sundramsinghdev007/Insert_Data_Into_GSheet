package com.sundram.insertdataintogsheet;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class AddData extends AppCompatActivity {

    EditText name_et,mobile_et,email_et;
    String str_name, str_mobile, str_email;
    String url = "https://script.google.com/macros/s/AKfycbyB50Cf6E4-LNtQmnCI-nTZ7yoMfu61Hox0Kw-yFRKoZSWMFYH_/exec";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_data);
        init();
    }

    //method fot getting the id's of view
    public void init(){
        name_et = findViewById(R.id.name);
        mobile_et = findViewById(R.id.mobile_number);
        email_et = findViewById(R.id.email);
    }

    //onClick of the button
    public void submit(View view) {
        str_name = name_et.getText().toString().trim();
        str_mobile = mobile_et.getText().toString().trim();
        str_email = email_et.getText().toString().trim();
        insertData(str_name,str_mobile,str_email);
    }

    //submit the data into google sheet using volley
    public void insertData(final String name, final String mobile, final String email){
        final ProgressDialog loading = ProgressDialog.show(this,"Adding Item","Please wait");

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                loading.dismiss();
                Toast.makeText(AddData.this,response,Toast.LENGTH_LONG).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(AddData.this,error.toString(),Toast.LENGTH_LONG).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<String, String>();
                params.put("action","submitData");
                params.put("name",name);
                params.put("mobile",mobile);
                params.put("email",email);
                return params;
            }
        };
//        int socketTimeOut = 50000;
//        RetryPolicy retryPolicy = new DefaultRetryPolicy(socketTimeOut,0,DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
//        stringRequest.setRetryPolicy(retryPolicy);
        RequestQueue addQueue = Volley.newRequestQueue(AddData.this);
        addQueue.add(stringRequest);
    }

}
