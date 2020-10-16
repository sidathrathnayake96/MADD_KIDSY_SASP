package com.example.kidsy;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AdapterProductUser extends RecyclerView.Adapter<AdapterProductUser.HolderProductUser>implements Filterable {

    private Context context;
    public ArrayList<ModelBook>bookList,filteList;
    private FilterBookUser filter;
    private String key;

    public AdapterProductUser(Context context, ArrayList<ModelBook> bookList) {
        this.context = context;
        this.bookList = bookList;
        this.filteList = bookList;
    }

    @NonNull
    @Override
    public HolderProductUser onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(context).inflate(R.layout.row_product_user,parent,false);

        return new HolderProductUser(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HolderProductUser holder, int position) {
        final ModelBook modelBook =bookList.get(position);
        String bookcategory=modelBook.getBookcategory();
        String bookname=modelBook.getBookname();
        String bookprice=modelBook.getBookprice();
        String bookship=modelBook.getBookship();
        String bookuploaddate=modelBook.getBookuploaddate();
        String timestamp=modelBook.getTimestamp();
        String bookid=modelBook.getBookid();
        String bookimage=modelBook.getBookimage();

        holder.nameTV.setText(bookname);
        holder.priceTv.setText(bookprice);
        holder.shipTv.setText(bookship);

        try {
            Picasso.get().load(bookimage).placeholder(R.drawable.ic_image_search).into(holder.bookIconIv);

        } catch (Exception e) {
            holder.bookIconIv.setImageResource(R.drawable.ic_image_search);

        }

        holder.addtocartTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                detailsBottomSheet(modelBook);


            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



            }
        });



    }

    private void detailsBottomSheet(ModelBook modelBook) {
        final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(context);
        View view = LayoutInflater.from(context).inflate(R.layout.order_details, null);

        bottomSheetDialog.setContentView(view);



        ImageButton backBtn = view.findViewById(R.id.backBtn);
        ImageButton cartBtn = view.findViewById(R.id.cartBtn);
        //ImageButton editBtn = view.findViewById(R.id.editBtn);
        ImageView bookIconIv = view.findViewById(R.id.bookIconIv);
        final TextView shipTv = view.findViewById(R.id.shipTv);
        final TextView nameTV = view.findViewById(R.id.nameTV);
        final TextView priceTv = view.findViewById(R.id.priceTv);
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
        //categoryTv.setText(bookCategory);
        //dateTv.setText(bookDate);


        try {
            Picasso.get().load(image).placeholder(R.drawable.ic__image_search_white).into(bookIconIv);

        } catch (Exception e) {
            bookIconIv.setImageResource(R.drawable.ic__image_search_white);

        }

        bottomSheetDialog.show();

        cartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                bottomSheetDialog.dismiss();
                Intent intent =new Intent(context,Buybook.class);
                intent.putExtra("key",key);
                intent.putExtra("paybookname",nameTV.getText().toString());
                intent.putExtra("paybookprice",priceTv.getText().toString());
                intent.putExtra("paybookship",shipTv.getText().toString());

                context.startActivity(intent);

            }
        });


        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                bottomSheetDialog.dismiss();
            }
        });
    }

    @Override
    public int getItemCount() {
        return bookList.size();
    }

    @Override
    public Filter getFilter() {
        if(filter==null){

            filter=new FilterBookUser(this,filteList);
        }
        return filter;
    }

    class HolderProductUser extends RecyclerView.ViewHolder{
        private ImageView bookIconIv;
        private TextView nameTV,priceTv,shipTv;
        private Button addtocartTv;

        public HolderProductUser(@NonNull View itemView) {
            super(itemView);

            bookIconIv=itemView.findViewById(R.id.bookIconIv);
            nameTV=itemView.findViewById(R.id.nameTV);
            priceTv=itemView.findViewById(R.id.priceTv);
            addtocartTv=itemView.findViewById(R.id.addtocartTv);
            shipTv=itemView.findViewById(R.id.shipTv);

        }
    }
}
