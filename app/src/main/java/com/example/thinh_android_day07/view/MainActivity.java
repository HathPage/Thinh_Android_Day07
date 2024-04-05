package com.example.thinh_android_day07.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.thinh_android_day07.R;
import com.example.thinh_android_day07.adapter.ItemAdapter;
import com.example.thinh_android_day07.models.CartModel;
import com.example.thinh_android_day07.models.Item;
import com.example.thinh_android_day07.models.Price;
import com.example.thinh_android_day07.database.SqliteCartTable;

import java.lang.reflect.Array;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity{
    private SqliteCartTable sqliteCartTable;
    private ImageButton btnMinus, btnMinus2, btnMinus3, btnMinus4, btnPlus, btnPlus2, btnPlus3, btnPlus4;
    private RelativeLayout btnCheckout;
    private TextView tv_weight, tv_weight2, tv_weight3, tv_weight4, tv_price, tv_price2, tv_price3, tv_price4, tv_total;
    private ArrayList<Item> mListItem;
    private RecyclerView rvItem;
    private ItemAdapter mItemAdapter;
    private ArrayList<Double> priceList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_item);
        sqliteCartTable = new SqliteCartTable(this);
        sqliteCartTable.addPrice(1.5, 2.25, 3, 2.5);
        priceList = new ArrayList<>();
        priceList.add(sqliteCartTable.getPrice(1).getOnion());
        priceList.add(sqliteCartTable.getPrice(1).getLemon());
        priceList.add(sqliteCartTable.getPrice(1).getPotato());
        priceList.add(sqliteCartTable.getPrice(1).getCucumber());
        initData();
        initView();
    }

    private void initView() {
        btnCheckout = findViewById(R.id.btn_checkout);
        tv_total = findViewById(R.id.tv_total);
        tv_total.setText(String.format("%.2f", priceList.get(0)+priceList.get(1)+priceList.get(2)+priceList.get(3)));
        rvItem = findViewById(R.id.rv_item);
        mItemAdapter = new ItemAdapter(this, mListItem);
        rvItem.setAdapter(mItemAdapter);
        mItemAdapter.setCallback(new ItemAdapter.IItemClickListener() {
            @Override
            public void onMinusQuantity(int pos, double quantity, double price) {
                minusQuantity(pos,quantity,price);
            }

            @Override
            public void onPlusQuantity(int pos, double quantity, double price) {
                plusQuantity(pos, quantity, price);
            }
        });
        btnCheckout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CartModel cart = new CartModel();
                double total = mListItem.get(0).getPrice()+mListItem.get(1).getPrice()+mListItem.get(2).getPrice()+mListItem.get(3).getPrice();
                cart.setOnion(mListItem.get(0).getQuantity());
                cart.setLemon(mListItem.get(1).getQuantity());
                cart.setPotato(mListItem.get(2).getQuantity());
                cart.setCucumber(mListItem.get(3).getQuantity());
                cart.setTotalPrice(total);
                sqliteCartTable.addCart(cart);
                Toast.makeText(MainActivity.this, "Check out thành công!", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void minusQuantity(int pos, double quantity, double price){
        DecimalFormat df = new DecimalFormat("#.##");
        double pricePer1g = 0;
        if (quantity==1){
            quantity = 900;
            price = price*(quantity/1000);
        }else if(quantity>100){
            pricePer1g = price/quantity;
            quantity = quantity - 100;
            price = pricePer1g*quantity;
        }else if(quantity == 100){
            quantity = 0;
            price = 0;
        }else if (quantity>1){
            pricePer1g = price/(quantity*1000);
            quantity = quantity - 0.1;
            price = pricePer1g*quantity*1000;
        }
        mListItem.get(pos).setQuantity(Double.parseDouble(df.format(quantity)));
        mListItem.get(pos).setPrice(Double.parseDouble(df.format(price)));
        calculateTotalPrice();
        mItemAdapter.notifyItemChanged(pos);
    }
    private void plusQuantity(int pos, double quantity, double price){
        DecimalFormat df = new DecimalFormat("#.##");
        double pricePer1g = 0;
        if (quantity==900){
            pricePer1g = price/quantity;
            quantity = 1;
            price = pricePer1g * 1000;
        }else if(quantity == 0){
            quantity += 100;
            price = priceList.get(pos)/10;
        }else if(quantity<100){
            pricePer1g = price/(quantity*1000);
            quantity = quantity + 0.1;
            price = pricePer1g*quantity*1000;
        }else if(quantity >= 100){
            pricePer1g = price/quantity;
            quantity = quantity +100;
            price = pricePer1g*quantity;
        }
        mListItem.get(pos).setQuantity(Double.parseDouble(df.format(quantity)));
        mListItem.get(pos).setPrice(Double.parseDouble(df.format(price)));
        calculateTotalPrice();
        mItemAdapter.notifyItemChanged(pos);
    }

    private void calculateTotalPrice() {
        double total = 0;
        for (Item item : mListItem) {
            total += item.getPrice();
        }
        tv_total.setText(String.format("%.2f", total));
    }

    private void initData() {
        mListItem = new ArrayList<>();
            Item item = new Item();
            item.setImg(R.drawable.onion);
            item.setName("Onion");
            item.setQuantity(1);
            item.setPrice(priceList.get(0));
            mListItem.add(item);
        item = new Item();
        item.setImg(R.drawable.lemon);
        item.setName("Lemon");
        item.setQuantity(1);
        item.setPrice(priceList.get(1));
        mListItem.add(item);
            item = new Item();
            item.setImg(R.drawable.potatoe);
            item.setName("Potato");
            item.setQuantity(1);
            item.setPrice(priceList.get(2));
            mListItem.add(item);
        item = new Item();
        item.setImg(R.drawable.cucumber);
        item.setName("Cucumber");
        item.setQuantity(1);
        item.setPrice(priceList.get(3));
        mListItem.add(item);
        }
}