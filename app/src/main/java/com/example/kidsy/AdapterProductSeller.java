package com.example.kidsy;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AdapterProductSeller extends RecyclerView.Adapter<AdapterProductSeller.HolderProductSeller> implements Filterable {


    private Context context;
    public ArrayList<ModelBook> productList,filterList;
    private FilterBook filter;


    public AdapterProductSeller(Context context, ArrayList<ModelBook> productList) {
        this.context = context;
        this.productList = productList;
        this.filterList =productList;
    }

    @NonNull
    @Override
    public HolderProductSeller onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(context).inflate(R.layout.row_product_seller,parent,false);

        return new HolderProductSeller(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HolderProductSeller holder, int position) {

        final ModelBook modelBook=productList.get(position);
        String id =modelBook.getBookid();
        String image =modelBook.getBookimage();
        String bookCategory =modelBook.getBookcategory();
        String bookName =modelBook.getBookname();
        String bookPrice =modelBook.getBookprice();
        String bookShip =modelBook.getBookship();
        String bookDate =modelBook.getBookuploaddate();
        String timestamp =modelBook.getTimestamp();
        String uid =modelBook.getUid();


        holder.nameTV.setText(bookName);
        holder.priceTv.setText(bookPrice);
        holder.dateTv.setText(bookDate);
        holder.shipTv.setText(bookShip);

        try{
            Picasso.get().load(image).placeholder(R.drawable.ic_image_search).into(holder.bookIconIv);

        }catch (Exception e){
            holder.bookIconIv.setImageResource(R.drawable.ic_image_search);

        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                detailsBottomSheet(modelBook);


            }
        });

    }

    private void detailsBottomSheet(ModelBook modelBook) {
        final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(context);
        View view = LayoutInflater.from(context).inflate(R.layout.book_details_seller, null);

        bottomSheetDialog.setContentView(view);



        ImageButton backBtn = view.findViewById(R.id.backBtn);
        ImageButton deleteBtn = view.findViewById(R.id.deleteBtn);
        ImageButton editBtn = view.findViewById(R.id.editBtn);
        ImageView bookIconIv = view.findViewById(R.id.bookIconIv);
        TextView shipTv = view.findViewById(R.id.shipTv);
        TextView nameTV = view.findViewById(R.id.nameTV);
        TextView priceTv = view.findViewById(R.id.priceTv);
        TextView categoryTv = view.findViewById(R.id.categoryTv);
        TextView dateTv = view.findViewById(R.id.dateTv);

        final String id = modelBook.getBookid();
        String image = modelBook.getBookimage();
        String bookCategory = modelBook.getBookcategory();
        final String bookName = modelBook.getBookname();
        String bookPrice = modelBook.getBookprice();
        String bookShip = modelBook.getBookship();
        String bookDate = modelBook.getBookuploaddate();
        String timestamp = modelBook.getTimestamp();
        String uid = modelBook.getUid();


        shipTv.setText(bookShip);
        nameTV.setText(bookName);
        priceTv.setText(bookPrice);
        categoryTv.setText(bookCategory);
        dateTv.setText(bookDate);


        try {
            Picasso.get().load(image).placeholder(R.drawable.ic__image_search_white).into(bookIconIv);

        } catch (Exception e) {
            bookIconIv.setImageResource(R.drawable.ic__image_search_white);

        }

        bottomSheetDialog.show();

        editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                bottomSheetDialog.dismiss();
                Intent intent =new Intent(context,EditBookActivity.class);
                intent.putExtra("bookId",id);
                context.startActivity(intent);

            }
        });

        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomSheetDialog.dismiss();
                AlertDialog.Builder builder=new AlertDialog.Builder(context);
                builder.setTitle("Delete")
                        .setMessage("Are you sure you want to delete book"+bookName+"?")
                        .setPositiveButton("DELETE", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                deleteBook(id);

                            }
                        })
                        .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();

                            }
                        })
                        .show();

            }
        });

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                bottomSheetDialog.dismiss();
            }
        });
    }

    private void deleteBook(String id) {

        FirebaseAuth firebaseAuth=FirebaseAuth.getInstance();
        DatabaseReference reference= FirebaseDatabase.getInstance().getReference("Books");
        reference.child("book").child(id).removeValue()
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {

                        Toast.makeText(context,"Book Deleted..",Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(context,""+e.getMessage(),Toast.LENGTH_SHORT).show();

                    }
                });
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    @Override
    public Filter getFilter() {

        if(filter == null){
            filter=new FilterBook(this,filterList);
        }
        return filter;
    }

    class HolderProductSeller extends RecyclerView.ViewHolder{

        private ImageView bookIconIv;
        private TextView nameTV,priceTv,dateTv,shipTv;

        public HolderProductSeller(@NonNull View itemView) {
            super(itemView);

            bookIconIv=itemView.findViewById(R.id.bookIconIv);
            nameTV=itemView.findViewById(R.id.nameTV);
            priceTv=itemView.findViewById(R.id.priceTv);
            dateTv=itemView.findViewById(R.id.dateTv);
            shipTv=itemView.findViewById(R.id.shipTv);
        }
    }
}
