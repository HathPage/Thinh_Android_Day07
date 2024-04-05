package com.example.thinh_android_day07.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.thinh_android_day07.R;
import com.example.thinh_android_day07.models.Item;

import java.util.ArrayList;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder> {
    private Context mContext;
    private IItemClickListener callback;
    private ArrayList<Item> mListItem;
    public ItemAdapter(Context callback, ArrayList<Item> listItem) {
        this.mContext = callback;
        this.mListItem = listItem;
    }
    public void setCallback(IItemClickListener callback) {
        this.callback = callback;
    }
    @NonNull
    @Override
    public ItemAdapter.ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_item, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemAdapter.ItemViewHolder holder, int position) {
        Item item = mListItem.get(position);
        if (mContext != null) {
            Glide.with(mContext).load(item.getImg()).into(holder.img);
        }
        holder.tv_onion.setText(item.getName());
        holder.tv_price.setText(String.valueOf(item.getPrice()));
        // Hiển thị số lượng và đơn vị
        if (item.getQuantity() >= 1 && item.getQuantity()<100) {
            holder.tv_weight.setText(String.valueOf(item.getQuantity()));
            holder.tv_weight_2.setText("kg");
        } else {
            holder.tv_weight.setText(String.valueOf(item.getQuantity()));
            holder.tv_weight_2.setText("g");
        }
    }

    @Override
    public int getItemCount() {
        return mListItem != null ? mListItem.size():0;
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private ImageButton btnMinus, btnPlus;
        private RelativeLayout btnCheckout;
        private ImageView img;
        private TextView tv_weight, tv_price, tv_total, tv_onion, tv_weight_2;
        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.img_onion);
            btnMinus = itemView.findViewById(R.id.btn_minus);
            btnPlus = itemView.findViewById(R.id.btn_plus);
            tv_weight = itemView.findViewById(R.id.tv_weight);
            tv_price = itemView.findViewById(R.id.tv_price);
            tv_onion = itemView.findViewById(R.id.tv_onion);
            tv_weight_2 = itemView.findViewById(R.id.tv_weight_2);
            btnMinus.setOnClickListener(this);
            btnPlus.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.btn_minus){
                if(callback != null){
                    callback.onMinusQuantity(getAdapterPosition(), mListItem.get(getAdapterPosition()).getQuantity(), mListItem.get(getAdapterPosition()).getPrice());
                }
            }
            if(v.getId() == R.id.btn_plus){
                if(callback != null){
                    callback.onPlusQuantity(getAdapterPosition(), mListItem.get(getAdapterPosition()).getQuantity(), mListItem.get(getAdapterPosition()).getPrice());
                }
            }
        }
    }
    public interface IItemClickListener{
        void onMinusQuantity(int pos, double quantity, double price);
        void onPlusQuantity(int pos, double quantity, double price);
    }
}
