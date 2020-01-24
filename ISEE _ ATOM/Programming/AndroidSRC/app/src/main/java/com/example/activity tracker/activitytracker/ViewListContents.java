package com.example.manish.activitytracker;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.drawable.AnimationDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.itextpdf.text.Document;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ViewListContents extends Activity {

    DatabaseHelper myDB;
    ArrayList<Activityy> userList;
    ListView listView;
    Activityy user;
    TextView txtFileName;
    Button btnExport,btnOpenPdf,btnEmail;
    ProgressDialog progressDialog;
    String pdfToopen="toBeCreated";
    LinearLayout container;
    AnimationDrawable anim1;




    //private Button btnEmail;
    private static final int REQUEST_WRITE_STORAGE_REQUEST_CODE = 112;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder(); StrictMode.setVmPolicy(builder.build());
        setContentView(R.layout.viewcontents_layout);

        myDB = new DatabaseHelper(this);
        listView = (ListView) findViewById(R.id.listView);
        userList = new ArrayList<>();
        btnExport = (Button) findViewById(R.id.btnExport);
        btnOpenPdf=(Button) findViewById(R.id.btnOpenPdf);
        btnEmail=(Button)findViewById(R.id.btnEmail);
        txtFileName=(TextView)findViewById(R.id.txtfilename);


        Cursor data = myDB.getListContents();

        int numRows = data.getCount();

        if (numRows == 0) {
            Toast.makeText(ViewListContents.this, "The Database is empty  :(.", Toast.LENGTH_LONG).show();
        } else {
            int i = 0;
            while (data.moveToNext()) {
                user = new Activityy(data.getString(1), data.getString(2), data.getString(3), data.getString(4));
                userList.add(i, user);
                System.out.println(data.getString(1) + " " + data.getString(2) + " " + data.getString(3) + " " + data.getString(4));
                System.out.println(userList.get(i).getCategory());
                i++;
            }
            FourColumn_ListAdapter1 adapter = new FourColumn_ListAdapter1(this, R.layout.list_adapter_view, userList);
            listView = (ListView) findViewById(R.id.listView);
            listView.setAdapter(adapter);
        }
        //btnEmail=findViewById(R.id.btnEmail);

        BottomNavigationView bottomNavigationView = findViewById(R.id.btnnvg);

        bottomNavigationView.setOnNavigationItemReselectedListener(new BottomNavigationView.OnNavigationItemReselectedListener() {
            @Override
            public void onNavigationItemReselected(@NonNull MenuItem item) {

                switch (item.getItemId()) {


                    case R.id.menu_home:
                        Intent intent1 = new Intent(ViewListContents.this,Homescreen.class);
                        startActivity(intent1);
                        break;

                    case R.id.menu_search:
                        Intent intent2 = new Intent(ViewListContents.this,FilterView.class);
                        startActivity(intent2);
                        break;

                    case R.id.menu_overview:
                        Intent intent3 = new Intent(ViewListContents.this,Overview.class);
                        startActivity(intent3);
                        break;

                }



            }
        });


        // fetching data

        btnExport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog = new ProgressDialog(ViewListContents.this);
                progressDialog.setMessage("Exporting. Please wait");
                progressDialog.show();
                txtFileName.setText("Please Wait");
                String state = Environment.getExternalStorageState();
                if (!Environment.MEDIA_MOUNTED.equals(state)) {

                }


                //creating filename

               // intent.putExtra("f1",filename );
                String filename;
                SimpleDateFormat formatter = new SimpleDateFormat("dd_MM_yyyy");
                Date date=new Date();
                filename ="Export_"+formatter.format(date).toString()+".pdf";
                // creating file directory
                String targetPdf = "/sdcard/"+filename;
                File pdfFile = new File(targetPdf);
                pdfToopen=targetPdf;
                //filename ends

                try {
                    Document document = new Document(PageSize.A4);
                    PdfWriter.getInstance(document, new FileOutputStream(pdfFile));
                    document.open();


                    ListAdapter adapter  = listView.getAdapter();
                    int itemsCount       = adapter.getCount();

                    document.add(createFirstTable());

                    document.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                btnExport.setText("EXPORT TO PDF");
                txtFileName.setText("Saved locally as "+filename);
                btnOpenPdf.setEnabled(true);
                progressDialog.cancel();
            }
        });



        btnOpenPdf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //code for opening the pdf once created
                File file = new File(pdfToopen);
                Uri path = Uri.fromFile(file);
                Intent pdfOpenintent = new Intent(Intent.ACTION_VIEW);
                pdfOpenintent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                pdfOpenintent.setDataAndType(path, "application/pdf");
                try {
                    startActivity(pdfOpenintent);
                }
                catch (ActivityNotFoundException e) {

                }

            }
        });
        btnEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(ViewListContents.this,SendEmail2.class);
                startActivity(intent);

            }
        });

        //btnEmail.setOnClickListener(new View.OnClickListener() {
            //@Override
           // public void onClick(View v) {


                //File file = new File(pdfToopen);
                //Intent email = new Intent(Intent.ACTION_SEND);
                //email.putExtra(Intent.EXTRA_EMAIL, "receiver_email_address");
                //email.putExtra(Intent.EXTRA_SUBJECT, "subject");
                //email.putExtra(Intent.EXTRA_TEXT, "email body");
                //Uri uri = Uri.fromFile(new File(pdfToopen,  "pdfFileName"));
                //email.putExtra(Intent.EXTRA_STREAM, uri);
               // email.setType("application/pdf");
               // email.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

               // startActivity(email);

                //try {
                //    startActivity(email);
               // }
               // catch (ActivityNotFoundException e) {

               // }

           // }
        //});
        requestAppPermissions();

        container = (LinearLayout) findViewById(R.id.container_share);

        anim1 = (AnimationDrawable) container.getBackground();
        anim1.setEnterFadeDuration(6000);
        anim1.setExitFadeDuration(2000);


    }

    public PdfPTable createFirstTable() {
        // a table with four columns
        PdfPTable table = new PdfPTable(4);
        // the cell object
        PdfPCell cell;


        ListAdapter adapter = listView.getAdapter();
        int itemsCount = adapter.getCount();
        // Font boldFont = new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLD);
        // Paragraph title = new Paragraph("Hello User!!! Here is the document with your activities for the day", boldFont );

        table.addCell("    Activity Name");
        table.addCell("       Category");
        table.addCell("      Description");
        table.addCell("           Date");

        table.addCell(" ");
        table.addCell(" ");
        table.addCell(" ");
        table.addCell(" ");

        for (int listViewIndex = 0; listViewIndex < itemsCount; listViewIndex++) {


            View childView = adapter.getView(listViewIndex, null, listView);
            findViewById(R.id.cat);
            TextView catView = (TextView) childView.findViewById(R.id.cat);
            TextView actView = (TextView) childView.findViewById(R.id.act);
            TextView dateView = (TextView) childView.findViewById(R.id.strdate);
            TextView durationView = (TextView) childView.findViewById(R.id.duration);

            String catText = catView.getText().toString().trim();
            String actText = actView.getText().toString().trim();
            String dateText = dateView.getText().toString().trim();
            String duractionText = durationView.getText().toString().trim();

            table.addCell(catText);
            table.addCell(actText);
            table.addCell(dateText);
            table.addCell(duractionText);

        }

        return table;
    }

    private void requestAppPermissions() {
        if (android.os.Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            return;
        }

        if (hasReadPermissions() && hasWritePermissions()) {
            return;
        }

        ActivityCompat.requestPermissions(this,
                new String[] {
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE
                }, REQUEST_WRITE_STORAGE_REQUEST_CODE); // your request code
    }

    private boolean hasReadPermissions() {
        return (ContextCompat.checkSelfPermission(getBaseContext(), Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED);
    }

    private boolean hasWritePermissions() {
        return (ContextCompat.checkSelfPermission(getBaseContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (anim1 != null && !anim1.isRunning())
            anim1.start();

    }

    @Override
    protected void onPause() {
        super.onPause();
        if (anim1 != null && anim1.isRunning())
            anim1.stop();

    }


}














