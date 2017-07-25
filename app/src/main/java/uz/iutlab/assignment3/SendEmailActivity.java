package uz.iutlab.assignment3;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SendEmailActivity extends AppCompatActivity {
    EditText fromName;
    EditText email;
    EditText subject;
    EditText body;
    Button sendBtn;
    String checkedItems;
    String userName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_email);

        fromName = (EditText)findViewById(R.id.fromNameXML);
        email = (EditText)findViewById(R.id.emailEditTextXML);
        subject = (EditText)findViewById(R.id.subjectEditTextXML);
        body = (EditText)findViewById(R.id.bodyEditTextXML);
        sendBtn  = (Button)findViewById(R.id.sendButtonXML);

        Intent intent = new Intent(getIntent());
        checkedItems = intent.getStringExtra("checked_items");
        userName = intent.getStringExtra("user_name");

        fromName.setText(userName);
        subject.setText(String.format("The purchase list: %s", userName));


        body.setText(getString(R.string.body_text, checkedItems));

        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse("mailto:" + email.getText().toString())
                        .buildUpon()
                        .appendQueryParameter("subject", subject.getText().toString())
                        .appendQueryParameter("body", body.getText().toString())
                        .build();
                Intent emailIntent = new Intent(Intent.ACTION_SENDTO, uri);
                emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL,new String[] { email.getText().toString()});
                startActivity(Intent.createChooser(emailIntent, "Send Shopping list Email"));
            }
        });
    }
}
