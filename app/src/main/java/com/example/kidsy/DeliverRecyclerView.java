package com.example.kidsy;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class DeliverRecyclerView {
    private Context context;
    private DeliverdataAdapter deliverdataAdapter;
    public void setConfig(RecyclerView recyclerView,Context dcontext,List<DeliverData> deliverData,List<String> keys){
        context=dcontext;
        deliverdataAdapter= new DeliverdataAdapter(deliverData,keys);
        recyclerView.setLayoutManager(new LinearLayoutManager(dcontext));
        recyclerView.setAdapter(deliverdataAdapter);
    }

    class DeliverItemView extends RecyclerView.ViewHolder{
        private TextView doid;
        private TextView dfirstname;
        private TextView demail;
        private TextView daddress;
        private TextView dbook;
        private TextView dqty;
        private TextView ddate;

        private String key;

        public DeliverItemView(ViewGroup parent){
            super(LayoutInflater.from(context).
            inflate(R.layout.deliver_list_item,parent,false));

            doid =(TextView) itemView.findViewById(R.id.roid);
            dfirstname =(TextView) itemView.findViewById(R.id.rdfirstname);
            demail =(TextView) itemView.findViewById(R.id.rdemail);
            daddress =(TextView) itemView.findViewById(R.id.rdaddress);
            dbook =(TextView) itemView.findViewById(R.id.rdbook);
            dqty =(TextView) itemView.findViewById(R.id.rdqty);
            ddate =(TextView) itemView.findViewById(R.id.rddate);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent=new Intent(context,DeliverDetails.class);
                    intent.putExtra("key",key);
                    intent.putExtra("orderid",doid.getText().toString());
                    intent.putExtra("delfirst",dfirstname.getText().toString());
                    intent.putExtra("delemail",demail.getText().toString());
                    intent.putExtra("deladdress",daddress.getText().toString());
                    intent.putExtra("delbook",dbook.getText().toString());
                    intent.putExtra("delqty",dqty.getText().toString());
                    intent.putExtra("deldate",ddate.getText().toString());

                    context.startActivity(intent);
                }
            });
        }
        public void bind(DeliverData deliverData,String key){
            doid.setText(deliverData.getOrderid());
            dfirstname.setText(deliverData.getDelfirst());
            demail.setText(deliverData.getDelemail());
            daddress.setText(deliverData.getDeladdress());
            dbook.setText(deliverData.getDelbook());
            dqty.setText(deliverData.getDelqty());
            ddate.setText(deliverData.getDeldate());
            this.key = key;
        }
    }
    class DeliverdataAdapter extends RecyclerView.Adapter<DeliverItemView>{
        private List<DeliverData> deliverDataList;
        private List<String> delkeys;

        public DeliverdataAdapter(List<DeliverData> deliverDataList, List<String> delkeys) {
            this.deliverDataList = deliverDataList;
            this.delkeys = delkeys;
        }

        @NonNull
        @Override
        public DeliverItemView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new DeliverItemView(parent);
        }

        @Override
        public void onBindViewHolder(@NonNull DeliverItemView holder, int position) {
            holder.bind( deliverDataList.get(position),delkeys.get(position) );
        }

        @Override
        public int getItemCount() {
            return deliverDataList.size();
        }
    }
}
