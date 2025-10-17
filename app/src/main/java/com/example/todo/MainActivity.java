package com.example.todo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_ADD_EXPENSE = 1;

    private TextView tvLastExpense;
    private Button btnAddExpense, btnViewDetail;

    // Store last expense data
    private String lastAmount = "0";
    private String lastCurrency = "";
    private String lastCategory = "";
    private String lastRemark = "";
    private String lastDate = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvLastExpense = findViewById(R.id.tvLastExpense);
        btnAddExpense = findViewById(R.id.btnAddExpense);
        btnViewDetail = findViewById(R.id.btnViewDetail);

        // Initially disabled and gray
        btnViewDetail.setEnabled(false);
        btnViewDetail.setBackgroundTintList(getResources().getColorStateList(android.R.color.darker_gray));

        // Open AddExpenseActivity
        btnAddExpense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddExpenseActivity.class);
                startActivityForResult(intent, REQUEST_ADD_EXPENSE);
            }
        });

        // Open ExpenseDetailActivity
        btnViewDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ExpenseDetailActivity.class);
                intent.putExtra("amount", lastAmount);
                intent.putExtra("currency", lastCurrency);
                intent.putExtra("category", lastCategory);
                intent.putExtra("remark", lastRemark);
                intent.putExtra("date", lastDate);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_ADD_EXPENSE && resultCode == RESULT_OK && data != null) {
            lastAmount = data.getStringExtra("amount");
            lastCurrency = data.getStringExtra("currency");
            lastCategory = data.getStringExtra("category");
            lastRemark = data.getStringExtra("remark");
            lastDate = data.getStringExtra("date");

            // Update TextView
            tvLastExpense.setText("My last expense was " + lastAmount + " " + lastCurrency);

            // Enable View Detail button and change color
            btnViewDetail.setEnabled(true);
            btnViewDetail.setBackgroundTintList(getResources().getColorStateList(android.R.color.holo_blue_light));
        }
    }
}