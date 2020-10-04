package com.example.kidsy;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AdapterProductUser extends RecyclerView.Adapter<AdapterProductUser.HolderProductUser>implements Filterable {

    private Context context;
    public ArrayList<ModelBook>bookList,filteList;
    private FilterBookUser filter;

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
        ModelBook modelBook =bookList.get(position);
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




            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

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
        private TextView nameTV,priceTv,addtocartTv,shipTv;

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
