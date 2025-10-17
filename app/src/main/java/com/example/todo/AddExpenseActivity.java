package com.example.todo;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class AddExpenseActivity extends AppCompatActivity {
    private EditText etAmount, etRemark, etDate;
    private Spinner spCurrency, spCategory;
    private Button btnAddExpense;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_expense);

        etAmount = findViewById(R.id.etAmount);
        etRemark = findViewById(R.id.etRemark);
        etDate = findViewById(R.id.etDate);
        spCurrency = findViewById(R.id.spCurrency);
        spCategory = findViewById(R.id.spCategory);
        btnAddExpense = findViewById(R.id.btnAddExpense);

        // Spinner setup
        ArrayAdapter<CharSequence> currencyAdapter = ArrayAdapter.createFromResource(this,
                R.array.currency_options, android.R.layout.simple_spinner_item);
        currencyAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spCurrency.setAdapter(currencyAdapter);

        ArrayAdapter<CharSequence> categoryAdapter = ArrayAdapter.createFromResource(this,
                R.array.category_options, android.R.layout.simple_spinner_item);
        categoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spCategory.setAdapter(categoryAdapter);

        // DatePicker for Created Date
        etDate.setFocusable(false);
        etDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePicker();
            }
        });

        // Add Expense button with validation
        btnAddExpense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String amount = etAmount.getText().toString().trim();
                String remark = etRemark.getText().toString().trim();
                String date = etDate.getText().toString().trim();

                if (TextUtils.isEmpty(amount)) {
                    etAmount.setError("Amount is required");
                    return;
                }
                if (TextUtils.isEmpty(remark)) {
                    etRemark.setError("Remark is required");
                    return;
                }
                if (TextUtils.isEmpty(date)) {
                    etDate.setError("Date is required");
                    return;
                }

                // Send data back to MainActivity
                Intent resultIntent = new Intent();
                resultIntent.putExtra("amount", amount);
                resultIntent.putExtra("currency", spCurrency.getSelectedItem().toString());
                resultIntent.putExtra("category", spCategory.getSelectedItem().toString());
                resultIntent.putExtra("remark", remark);
                resultIntent.putExtra("date", date);

                setResult(RESULT_OK, resultIntent);
                finish();
            }
        });
    }

    private void showDatePicker() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int y, int m, int d) {
                        etDate.setText(d + "/" + (m + 1) + "/" + y);
                    }
                }, year, month, day);
        datePickerDialog.show();
    }
}