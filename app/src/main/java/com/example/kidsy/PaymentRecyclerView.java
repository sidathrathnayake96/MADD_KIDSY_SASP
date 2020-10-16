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

public class PaymentRecyclerView {

    private Context context;
    private PaymentdataAdapter paymentdataAdapter;
    public void setConfig(RecyclerView recyclerView, Context dcontext, List<PaymentData> paymentData, List<String> keys){
        context=dcontext;
        paymentdataAdapter= new PaymentdataAdapter(paymentData,keys);
        recyclerView.setLayoutManager(new LinearLayoutManager(dcontext));
        recyclerView.setAdapter(paymentdataAdapter);
    }

    class PaymentItemView extends RecyclerView.ViewHolder{


        private TextView pfirstname;
        private TextView pemail;
        private TextView paddress;
        private TextView pcardnumber;
        private TextView pccv;
        private TextView pcardexpiredate;
        private TextView pbookname;
        private TextView pqty;
        private TextView ptotalprice;
        private TextView pdate;

        private String key;

        public PaymentItemView(ViewGroup parent){
            super(LayoutInflater.from(context).
                    inflate(R.layout.payment_list_item,parent,false));


            pfirstname =(TextView) itemView.findViewById(R.id.rpfirstname);
            pemail =(TextView) itemView.findViewById(R.id.rpemail);
            paddress =(TextView) itemView.findViewById(R.id.rpaddress);
            pcardnumber =(TextView) itemView.findViewById(R.id.rpcardnumber);
            pccv =(TextView) itemView.findViewById(R.id.rpccv);
            pcardexpiredate =(TextView) itemView.findViewById(R.id.rpcardexpiredate);
            pbookname =(TextView) itemView.findViewById(R.id.rpbookname);
            pqty =(TextView) itemView.findViewById(R.id.rpqty);
            ptotalprice =(TextView) itemView.findViewById(R.id.rptotalprice);
            pdate =(TextView) itemView.findViewById(R.id.rpdate);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent=new Intent(context, PaymentDetails.class);
                    intent.putExtra("key",key);
                    intent.putExtra("payfirstname",pfirstname.getText().toString());
                    intent.putExtra("payemail",pemail.getText().toString());
                    intent.putExtra("payaddress",paddress.getText().toString());
                    intent.putExtra("paycardnumber",pcardnumber.getText().toString());
                    intent.putExtra("payccv",pccv.getText().toString());
                    intent.putExtra("paycardexpiredate",pcardexpiredate.getText().toString());
                    intent.putExtra("paybookname",pbookname.getText().toString());
                    intent.putExtra("payqty",pqty.getText().toString());
                    intent.putExtra("paytotalprice",ptotalprice.getText().toString());
                    intent.putExtra("paydate",pdate.getText().toString());

                    context.startActivity(intent);
                }
            });
        }
        public void bind(PaymentData paymentData, String key){


            pfirstname.setText(paymentData.getPayfirstname());
            pemail.setText(paymentData.getPayemail());
            paddress.setText(paymentData.getPayaddress());
            pcardnumber.setText(paymentData.getPaycardnumber());
            pccv.setText(paymentData.getPayccv());
            pcardexpiredate.setText(paymentData.getPaycardexpiredate());
            pbookname.setText(paymentData.getPaybookname());
            pqty.setText(paymentData.getPayqty());
            ptotalprice.setText(paymentData.getPaytotalprice());
            pdate.setText(paymentData.getPaydate());
            this.key = key;
        }
    }
    class PaymentdataAdapter extends RecyclerView.Adapter<PaymentItemView>{
        private List<PaymentData> paymentDataList;
        private List<String> paykeys;

        public PaymentdataAdapter(List<PaymentData> paymentDataList, List<String> paykeys) {
            this.paymentDataList = paymentDataList;
            this.paykeys = paykeys;
        }

        @NonNull
        @Override
        public PaymentItemView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new PaymentItemView(parent);
        }

        @Override
        public void onBindViewHolder(@NonNull PaymentItemView holder, int position) {
            holder.bind( paymentDataList.get(position),paykeys.get(position) );
        }

        @Override
        public int getItemCount() {
            return paymentDataList.size();
        }
    }
}

