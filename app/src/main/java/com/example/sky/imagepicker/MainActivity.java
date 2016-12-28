package com.example.sky.imagepicker;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
 Button btn;
    ImageView ImView;
   private  int result=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn=(Button)findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(
                        Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
             startActivityForResult(intent,result);

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
         try {
             if (requestCode == result && resultCode ==AppCompatActivity.RESULT_OK && null != data) {
                 Uri select = data.getData();
                 String[] filePath = {MediaStore.Images.Media.DATA};
                 Cursor cursor = getContentResolver().query(select, filePath, null, null, null);
                 cursor.moveToFirst();
                 int colume = cursor.getColumnIndex(filePath[0]);
                 String picPath = cursor.getString(colume);
                 cursor.close();
                 ImView = (ImageView) findViewById(R.id.Image);
                 ImView.setImageBitmap(BitmapFactory.decodeFile(picPath));}

             }
         catch(Exception e){
             Toast.makeText(this,"Please wait",Toast.LENGTH_LONG).show();

        }

    }
}
