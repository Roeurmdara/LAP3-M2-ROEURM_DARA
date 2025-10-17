package com.example.todo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ExpenseDetailActivity extends AppCompatActivity {

    private TextView tvAmount, tvCurrency, tvCategory, tvRemark, tvDate;
    private Button btnAddNewExpense, btnBackHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expense_detail);

        tvAmount = findViewById(R.id.tvAmount);
        tvCurrency = findViewById(R.id.tvCurrency);
        tvCategory = findViewById(R.id.tvCategory);
        tvRemark = findViewById(R.id.tvRemark);
        tvDate = findViewById(R.id.tvDate);

        btnAddNewExpense = findViewById(R.id.btnAddNewExpense);
        btnBackHome = findViewById(R.id.btnBackHome);

        // Get data from Intent
        Intent intent = getIntent();
        tvAmount.setText("Amount: " + intent.getStringExtra("amount"));
        tvCurrency.setText("Currency: " + intent.getStringExtra("currency"));
        tvCategory.setText("Category: " + intent.getStringExtra("category"));
        tvRemark.setText("Remark: " + intent.getStringExtra("remark"));
        tvDate.setText("Created Date: " + intent.getStringExtra("date"));

        // Add New Expense button: open AddExpenseActivity
        btnAddNewExpense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent addIntent = new Intent(ExpenseDetailActivity.this, AddExpenseActivity.class);
                startActivity(addIntent);
                finish(); // optional, close current detail screen
            }
        });

        // Back to Home button: go back to MainActivity
        btnBackHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // just close this activity to return to MainActivity
            }
        });
    }
}
