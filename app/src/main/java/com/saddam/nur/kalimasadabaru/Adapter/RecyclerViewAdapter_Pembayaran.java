package com.saddam.nur.kalimasadabaru.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.saddam.nur.kalimasadabaru.Model.pembayaran;
import com.saddam.nur.kalimasadabaru.R;
import com.saddam.nur.kalimasadabaru.View.lihat_pembayaran;

import java.util.List;

import javax.xml.transform.Result;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by sulistiyanto on 07/12/16.
 */

public class RecyclerViewAdapter_Pembayaran extends RecyclerView.Adapter<RecyclerViewAdapter_Pembayaran.ViewHolder> {


    private Context context;
    private List<pembayaran> pembayarans;



    public RecyclerViewAdapter_Pembayaran(Context context, List<pembayaran> pembayarans) {
        this.context = context;
        this.pembayarans = pembayarans;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_view_pembayaran, parent, false);
        ViewHolder holder = new ViewHolder(v);

        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        pembayaran Pembayaran = pembayarans.get(position);
        holder.txtId_pembayaran.setText(Pembayaran.getIdPembayaran());
        holder.txtId_pemesanan.setText(Pembayaran.getIdPemesanan());
        holder.txtStatus_pembayaran.setText(Pembayaran.getStatusPembayaran());
        holder.txtTotal_bayar.setText(Pembayaran.getTotalBayar());
    }

    @Override
    public int getItemCount() {
        if (pembayarans == null)
            return 0;
        else
            return  pembayarans.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {


        private  TextView txtId_pembayaran, txtId_pemesanan, txtStatus_pembayaran, txtTotal_bayar;
//        @BindView(R.id.txtId_pembayaran) TextView txtId_pembayaran;
//        @BindView(R.id.txtId_pemesanan) TextView txtId_pemesanan;
//        @BindView(R.id.txtStatus_pembayaran) TextView txtStatus_pembayaran;
//        @BindView(R.id.txtTotal_bayar) TextView txtTotal_bayar;

        public ViewHolder(View itemView) {
            super(itemView);
//            ButterKnife.bind(this, itemView);
            txtId_pembayaran = (TextView) itemView.findViewById(R.id.txtId_pembayaran);
            txtId_pemesanan = (TextView) itemView.findViewById(R.id.txtId_pemesanan);
            txtStatus_pembayaran = (TextView) itemView.findViewById(R.id.txtStatus_pembayaran);
            txtTotal_bayar = (TextView) itemView.findViewById(R.id.txtTotal_bayar);
        }
    }
}