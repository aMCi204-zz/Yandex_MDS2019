package com.amciof.Yandex_MDS2019.Activities.Profile;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.amciof.Yandex_MDS2019.R;



public class ActivityProfile
        extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        RelativeLayout github = findViewById(R.id.action_github);
        github.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView textView = findViewById(R.id.action_github_text);
                String url = textView.getText().toString();
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });

        RelativeLayout phone = findViewById(R.id.action_phone);
        phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                TextView textView = findViewById(R.id.action_phone_text);
                String phoneNumber = textView.getText().toString();
                String uri = "tel:" + phoneNumber;
                intent.setData(Uri.parse(uri));
                startActivity(intent);
            }
        });

        RelativeLayout email = findViewById(R.id.action_email);
        email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("plain/text");
                TextView textView = findViewById(R.id.action_email_text);
                String emailAddress = textView.getText().toString();
                intent.putExtra(Intent.EXTRA_EMAIL, new String[] { emailAddress });
                intent.putExtra(Intent.EXTRA_SUBJECT, "YMD-2019 Vyacheslav");

                intent.putExtra(Intent.EXTRA_TEXT, "Оставь свой отзыв)");
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
