package com.example.kidsy;

import android.Manifest;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.util.HashMap;

public class EditBookActivity extends AppCompatActivity {

    private ImageButton backBtn;
    private ImageView bookIconIv;
    private TextView categoryTv;
    private EditText nameEt,priceEt,dateEt,shipEt;
    private Button updateProductBtn;

    private String bookId;

    //permission constants
    private static final int CAMERA_REQUEST_CODE=200;
    private static final int STORAGE_REQUEST_CODE=300;

    //image pick constatns
    private static final int IMAGE_PICK_GALLERY_CODE=400;
    private static final int IMAGE_PICK_CAMERA_CODE=500;

    //permission Arrays
    private String[] cameraPermissions;
    private String[] storagePermissions;

    //image picked uri
    private Uri image_uri;

    private FirebaseAuth firebaseAuth;
    //private ProgressDialog progressDialog;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_book);



        backBtn=findViewById(R.id.backBtn);
        bookIconIv=findViewById(R.id.bookIconIv);
        categoryTv=findViewById(R.id.categoryTv);
        nameEt=findViewById(R.id.nameEt);
        priceEt=findViewById(R.id.priceEt);
        shipEt=findViewById(R.id.shipEt);
        dateEt=findViewById(R.id.dateEt);

        updateProductBtn=findViewById(R.id.updateProductBtn);

        bookId=getIntent().getStringExtra("bookId");

        firebaseAuth=FirebaseAuth.getInstance();
        loadBookDetails();


        cameraPermissions=new String[]{Manifest.permission.CAMERA,Manifest.permission.WRITE_EXTERNAL_STORAGE};
        storagePermissions=new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE};

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });


        bookIconIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showImagePickDialog();
            }
        });


        categoryTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                categoryDialog();
            }
        });


        updateProductBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                inputData();
            }
        });



    }

    private void loadBookDetails() {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Books");
        reference.child("book").child(bookId)
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        String bookid=""+dataSnapshot.child("bookid").getValue();
                        String bookimage=""+dataSnapshot.child("bookimage").getValue();
                        String bookcategory=""+dataSnapshot.child("bookcategory").getValue();
                        String bookname=""+dataSnapshot.child("bookname").getValue();
                        String bookprice=""+dataSnapshot.child("bookprice").getValue();
                        String bookship=""+dataSnapshot.child("bookship").getValue();
                        String bookuploaddate=""+dataSnapshot.child("bookuploaddate").getValue();
                        String timestamp=""+dataSnapshot.child("timestamp").getValue();
                        String uid=""+dataSnapshot.child("uid").getValue();


                        categoryTv.setText(bookcategory);
                        nameEt.setText(bookname);
                        priceEt.setText(bookprice);
                        shipEt.setText(bookship);
                        dateEt.setText(bookuploaddate);

                        try{
                            Picasso.get().load(bookimage).placeholder(R.drawable.ic_image_search).into(bookIconIv);

                        }catch (Exception e){
                            bookIconIv.setImageResource(R.drawable.ic_image_search);

                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
    }


    private String bookCategory, bookTitle,bookPrice,bookShip,bookDate;
    private void inputData() {
        bookCategory=categoryTv.getText().toString().trim();
        bookTitle=nameEt.getText().toString().trim();
        bookPrice=priceEt.getText().toString().trim();
        bookShip=shipEt.getText().toString().trim();
        bookDate=dateEt.getText().toString().trim();


        if(TextUtils.isEmpty(bookCategory)){
            Toast.makeText(this,"Book category is required",Toast.LENGTH_SHORT).show();
            return;
        }

        if(TextUtils.isEmpty(bookTitle)){
            Toast.makeText(this,"Book name is required",Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(bookPrice)){
            Toast.makeText(this,"Book price is required",Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(bookShip)){
            Toast.makeText(this,"Shipping amount is required",Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(bookDate)){
            Toast.makeText(this,"Book upload date is required",Toast.LENGTH_SHORT).show();
            return;
        }
        updateBook();
    }

    private void updateBook() {
        Toast.makeText(this,"Updating..",Toast.LENGTH_SHORT).show();

        if(image_uri==null){

            HashMap<String,Object> hashMap = new HashMap<>();

            hashMap.put("bookTitle",""+bookTitle);
            hashMap.put("bookCategory",""+bookCategory);
            hashMap.put("bookPrice",""+bookPrice);
            hashMap.put("bookShip",""+bookShip);
            hashMap.put("bookDate",""+bookDate);

            DatabaseReference reference= FirebaseDatabase.getInstance().getReference("Books");
            reference.child("book").child(bookId)
                    .updateChildren(hashMap)
                    .addOnSuccessListener(new OnSuccessListener<Void>(){

                        @Override
                        public void onSuccess(Void aVoid) {
                            Toast.makeText(EditBookActivity.this,"Uploaded..",Toast.LENGTH_SHORT).show();

                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(EditBookActivity.this,""+e.getMessage(),Toast.LENGTH_SHORT).show();


                        }
                    });

        }else {

            String filePathAndName = "book_images/"+""+bookId;
            StorageReference storageReference= FirebaseStorage.getInstance().getReference(filePathAndName);
            storageReference.putFile(image_uri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            Task<Uri> uriTask =taskSnapshot.getStorage().getDownloadUrl();
                            while (!uriTask.isSuccessful());
                            Uri downloadImageUri = uriTask.getResult();

                            if(uriTask.isSuccessful()){

                                HashMap<String,Object> hashMap = new HashMap<>();

                                hashMap.put("bookTitle",""+bookTitle);
                                hashMap.put("bookCategory",""+bookCategory);
                                hashMap.put("bookPrice",""+bookPrice);
                                hashMap.put("bookIconIv",""+downloadImageUri);
                                hashMap.put("bookShip",""+bookShip);
                                hashMap.put("bookDate",""+bookDate);

                                DatabaseReference reference= FirebaseDatabase.getInstance().getReference("Books");
                                reference.child("book").child(bookId)
                                        .updateChildren(hashMap)
                                        .addOnSuccessListener(new OnSuccessListener<Void>(){

                                            @Override
                                            public void onSuccess(Void aVoid) {
                                                Toast.makeText(EditBookActivity.this,"Uploaded..",Toast.LENGTH_SHORT).show();

                                            }
                                        })
                                        .addOnFailureListener(new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) {
                                                Toast.makeText(EditBookActivity.this,""+e.getMessage(),Toast.LENGTH_SHORT).show();


                                            }
                                        });


                            }

                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(EditBookActivity.this,""+e.getMessage(),Toast.LENGTH_SHORT).show();

                        }
                    });

        }
    }

    private void categoryDialog() {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("Book Category")
                .setItems(Constants.bookcategories, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String category = Constants.bookcategories[which];
                        categoryTv.setText(category);

                    }
                })
                .show();
    }



    private void showImagePickDialog() {
        String[] options ={"Camera" , "Gallery"};
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("Pick Image From")
                .setItems(options, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(which==0){
                            if(checkCameraPermission()){
                                pickFromCamera();
                            }
                            else {
                                requestCameraPermission();
                            }
                        }
                        else {
                            if(checkStoragePermission()){
                                pickFromGallery();
                            }
                            else {
                                requestStoragePermission();
                            }
                        }

                    }
                })
                .show();

    }

    private void pickFromGallery(){
        Intent intent=new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent,IMAGE_PICK_GALLERY_CODE);
    }

    private void pickFromCamera(){

        ContentValues contentValues=new ContentValues();
        contentValues.put(MediaStore.Images.Media.TITLE,"Temp_Image_Title");
        contentValues.put(MediaStore.Images.Media.DESCRIPTION,"Temp_Image_Title");

        image_uri =getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,contentValues);


        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT,image_uri);
        startActivityForResult(intent,IMAGE_PICK_CAMERA_CODE);
    }
    private boolean checkStoragePermission(){

        boolean result = ContextCompat.checkSelfPermission(this,Manifest.permission.WRITE_EXTERNAL_STORAGE) ==
                (PackageManager.PERMISSION_GRANTED);

        return result;
    }
    private void requestStoragePermission(){

        ActivityCompat.requestPermissions(this,storagePermissions,STORAGE_REQUEST_CODE);

    }
    private boolean checkCameraPermission(){

        boolean result= ContextCompat.checkSelfPermission(this,Manifest.permission.CAMERA) ==
                (PackageManager.PERMISSION_GRANTED);

        boolean result1= ContextCompat.checkSelfPermission(this,Manifest.permission.WRITE_EXTERNAL_STORAGE) ==
                (PackageManager.PERMISSION_GRANTED);

        return result && result1;
    }

    private void requestCameraPermission(){
        ActivityCompat.requestPermissions(this,cameraPermissions,CAMERA_REQUEST_CODE);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        switch (requestCode){
            case CAMERA_REQUEST_CODE:{
                if(grantResults.length>0){
                    boolean cameraAccepted =grantResults[0] == PackageManager.PERMISSION_GRANTED;
                    boolean storageAccepted =grantResults[1] == PackageManager.PERMISSION_GRANTED;
                    if(cameraAccepted && storageAccepted){
                        pickFromCamera();
                    }
                    else{
                        Toast.makeText(this,"Camera and storage permissions are required...",Toast.LENGTH_SHORT).show();
                    }
                }
            }
            case STORAGE_REQUEST_CODE:{
                if(grantResults.length>0){
                    boolean storageAccepted =grantResults[0] == PackageManager.PERMISSION_GRANTED;
                    if(storageAccepted){
                        pickFromGallery();
                    }else {
                        Toast.makeText(this,"Storage permission is required...",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(resultCode==RESULT_OK){
            if(requestCode==IMAGE_PICK_GALLERY_CODE){
                image_uri=data.getData();
                bookIconIv.setImageURI(image_uri);
            }
            else if(requestCode==IMAGE_PICK_CAMERA_CODE){
                bookIconIv.setImageURI(image_uri);
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }
}