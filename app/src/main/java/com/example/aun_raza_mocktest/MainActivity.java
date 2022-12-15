package com.example.aun_raza_mocktest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.Manifest;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import kotlinx.coroutines.scheduling.Task;

public class MainActivity extends AppCompatActivity {
    TextView txtProductId, txtProductName, txtProductPrice, tvOutput, txtSms;
    Button btnInsertProduct, btnDisplayProductInfo, btnSendSms;
    RadioGroup rgProducts; RadioButton tempRadioButton;
    ProductInfoViewModel mViewModel;
    private static final int SMS_RECEIVE_PERMISSION_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.RECEIVE_SMS, Manifest.permission.SEND_SMS, Manifest.permission.READ_PHONE_STATE},
                SMS_RECEIVE_PERMISSION_REQUEST);

        mViewModel = new ViewModelProvider(MainActivity.this).get(ProductInfoViewModel.class);

        txtProductId = findViewById(R.id.txtProductId);
        txtProductName = findViewById(R.id.txtProductName);
        txtProductPrice = findViewById(R.id.txtProductPrice);
        tvOutput = findViewById(R.id.tvOutput);
        txtSms = findViewById(R.id.txtSms);
        btnInsertProduct = findViewById(R.id.btnInsertProduct);
        btnDisplayProductInfo = findViewById(R.id.btnDisplayProductInfo);
        btnSendSms = findViewById(R.id.btnSendSms);
        rgProducts = findViewById(R.id.rgProducts);

        mViewModel.getAllProductInfo().observe(this, new Observer<List<ProductInfo>>() {
            @Override
            public void onChanged(List<ProductInfo> productInfos) {
                rgProducts.removeAllViews();
                for (ProductInfo productInfo : productInfos) {
                    String text = productInfo.getProductId() + ": " + productInfo.getProductName();
                    tempRadioButton = new RadioButton(MainActivity.this);
                    tempRadioButton.setText(text);
                    rgProducts.addView(tempRadioButton);
                }
            }
        });
    }

    public void insertProductInfo(View view) {
        int id = Integer.parseInt(txtProductId.getText().toString());
        String productName = txtProductName.getText().toString();
        double price = Double.parseDouble(txtProductPrice.getText().toString());

        ProductInfo productInfo = new ProductInfo(id, productName, price);
        mViewModel.insertProductInfo(productInfo);
    }

    public void displayProductInfo(View view) {
        int radioBtnId = rgProducts.getCheckedRadioButtonId();
        if (radioBtnId == -1) return;

        String selectedProductInfo = ((RadioButton) findViewById(radioBtnId)).getText().toString();
        String id = selectedProductInfo.split(":")[0];

        ProductInfo productInfo = mViewModel.findProductInfo(Integer.parseInt(id));
        tvOutput.setText("ID: " + productInfo.getProductId() +
                "\nProduct Name: " + productInfo.getProductName() +
                "\nProduct Price: " + productInfo.getProductPrice());
    }

    public void sendSMS(View view) {
        try{
            SmsManager smgr = SmsManager.getDefault();
            smgr.sendTextMessage(txtSms.getText().toString(),null,tvOutput.getText().toString(),null,null);
            Toast.makeText(MainActivity.this, "SMS Sent Successfully", Toast.LENGTH_SHORT).show();
        }
        catch (Exception e){
            Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}