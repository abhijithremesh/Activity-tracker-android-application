package com.example.manish.activitytracker;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.drawable.AnimationDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Toast;


public class SendEmail2 extends AppCompatActivity {

    EditText et_email;
    EditText et_subject;
    EditText et_message;
    Button Send;
    Button Attachment;
    String email;
    String subject;
    String message;
    String attachmentFile;
    ScrollView sv;
    AnimationDrawable anim1, anim2;
    Uri URI = null;
    private static final int PICK_FROM_GALLERY = 101;
    int columnIndex;
    //private static final int READ_REQUEST_CODE =filename ;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
            setContentView(R.layout.email_page2);
        et_email = (EditText) findViewById(R.id.et_to);
        et_subject = (EditText) findViewById(R.id.et_subject);
        et_message = (EditText) findViewById(R.id.et_message);
        Attachment = (Button) findViewById(R.id.bt_attachment);
        Send = (Button) findViewById(R.id.bt_send);

        //send button listener
        Send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendEmail();

            }
        });

        //attachment button listener
        Attachment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFolder();
            }
        });

        sv = (ScrollView) findViewById(R.id.container_email);


        anim1 = (AnimationDrawable) sv.getBackground();
        anim1.setEnterFadeDuration(6000);
        anim1.setExitFadeDuration(2000);




    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if (requestCode == PICK_FROM_GALLERY && resultCode == RESULT_OK) {
            Uri selectedImage = data.getData();
            String[] filePathColumn = { MediaStore.Images.Media.DATA };
            Cursor cursor = getContentResolver().query(selectedImage,filePathColumn, null, null, null);
            cursor.moveToFirst();
            columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            attachmentFile = cursor.getString(columnIndex);
           // Log.e("Attachment Path:", attachmentFile);
            URI = Uri.parse("file://" + attachmentFile);
            cursor.close();
        }

    }


    public void sendEmail()
    {
        try
        {
           // Intent intent = new Intent(Intent.ACTION_SEND);
            email = et_email.getText().toString();
            subject = et_subject.getText().toString();
            message = et_message.getText().toString();
            final Intent emailIntent = new Intent(Intent.ACTION_SEND);
            emailIntent.setType("plain/text");
            emailIntent.putExtra(Intent.EXTRA_EMAIL,new String[] { email });
            emailIntent.putExtra(Intent.EXTRA_SUBJECT,subject);
            if (URI != null) {
                emailIntent.putExtra(Intent.EXTRA_STREAM, URI);
            }
            emailIntent.putExtra(Intent.EXTRA_TEXT, message);
            //startActivity(Intent.createChooser(intent, "Choose an email client"));
            this.startActivity(Intent.createChooser(emailIntent,"Sending email..."));

        }
        catch (Throwable t)
        {
            Toast.makeText(this, "Request failed try again: " + t.toString(),Toast.LENGTH_LONG).show();
        }
    }


    public void openFolder()
    {
       // Bundle extras = getIntent().getExtras();
       // int filename = extras.getInt("f1");
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        intent.putExtra("return-data", true);
        startActivityForResult(Intent.createChooser(intent, "Complete action using"), PICK_FROM_GALLERY);
        //startActivityForResult(intent,READ_REQUEST_CODE);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (anim1 != null && !anim1.isRunning())
            anim1.start();
//        if (anim2 != null && !anim2.isRunning())
//            anim2.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (anim1 != null && anim1.isRunning())
            anim1.stop();
//        if (anim2 != null && anim2.isRunning())
//            anim2.stop();
    }


}