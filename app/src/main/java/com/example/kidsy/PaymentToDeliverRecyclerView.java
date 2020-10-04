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

public class PaymentToDeliverRecyclerView {


    private Context context;
    private PaymentToDeliverRecyclerView.PaymentdataAdapter paymentdataAdapter;
    public void setConfig(RecyclerView recyclerView, Context dcontext, List<PaymentData> paymentData, List<String> keys){
        context=dcontext;
        paymentdataAdapter= new PaymentToDeliverRecyclerView.PaymentdataAdapter(paymentData,keys);
        recyclerView.setLayoutManager(new LinearLayoutManager(dcontext));
        recyclerView.setAdapter(paymentdataAdapter);
    }

    class PaymentItemView extends RecyclerView.ViewHolder{

        private TextView pid;
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

            pid =(TextView) itemView.findViewById(R.id.rpid);
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
                    Intent intent=new Intent(context, Deliver.class);
                    intent.putExtra("key",key);
                    intent.putExtra("payid",pid.getText().toString());
                    intent.putExtra("payfirstname",pfirstname.getText().toString());
                    intent.putExtra("payemail",pemail.getText().toString());
                    intent.putExtra("payaddress",paddress.getText().toString());
                    intent.putExtra("paybookname",pbookname.getText().toString());
                    intent.putExtra("payqty",pqty.getText().toString());

                    context.startActivity(intent);
                }
            });
        }
        public void bind(PaymentData paymentData, String key){

            pid.setText(paymentData.getPayid());
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
    class PaymentdataAdapter extends RecyclerView.Adapter<PaymentToDeliverRecyclerView.PaymentItemView>{
        private List<PaymentData> paymentDataList;
        private List<String> paykeys;

        public PaymentdataAdapter(List<PaymentData> paymentDataList, List<String> paykeys) {
            this.paymentDataList = paymentDataList;
            this.paykeys = paykeys;
        }

        @NonNull
        @Override
        public PaymentToDeliverRecyclerView.PaymentItemView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new PaymentToDeliverRecyclerView.PaymentItemView(parent);
        }

        @Override
        public void onBindViewHolder(@NonNull PaymentToDeliverRecyclerView.PaymentItemView holder, int position) {
            holder.bind( paymentDataList.get(position),paykeys.get(position) );
        }

        @Override
        public int getItemCount() {
            return paymentDataList.size();
        }
    }

}
