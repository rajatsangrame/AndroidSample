package com.example.test;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.gson.Gson;

public class NewUserActivity extends AppCompatActivity {


    public static final String EXTRA_REPLY = "USER_DETAIL";
    private EditText mEditFirstName;
    private EditText mEditLastName;
    private EditText mEditRollNo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_user);

        mEditFirstName = findViewById(R.id.edit_first_name);
        mEditLastName = findViewById(R.id.edit_last_name);
        mEditRollNo = findViewById(R.id.edit_roll_no);

        final Button button = findViewById(R.id.button_save);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent replyIntent = new Intent();
                if (TextUtils.isEmpty(mEditFirstName.getText())) {
                    setResult(RESULT_CANCELED, replyIntent);
                } else {
                    String firstName = mEditFirstName.getText().toString();
                    String lastName = mEditLastName.getText().toString();
                    int rollNo = Integer.parseInt(mEditRollNo.getText().toString());
                    User user = new User(firstName, lastName, rollNo);
                    replyIntent.putExtra(EXTRA_REPLY, new Gson().toJson(user));
                    setResult(RESULT_OK, replyIntent);
                }
                finish();
            }
        });
    }
}
