package org.weishe.baselibrary.adapter;

import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;



import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


/**
 * Created by Tmp on 2018/12/18.
 */

public abstract class BaseAdapter<T> extends RecyclerView.Adapter<BaseViewHolder> implements View.OnClickListener {
    protected Context context;
    protected List<T> list;


    public BaseAdapter(Context context) {
        this.context = context;
        list = new ArrayList<>();
    }


    protected abstract int bindLayout();


    @Override
    public BaseViewHolder onCreateViewHolder( ViewGroup viewGroup, int i) {
        return new BaseViewHolder(LayoutInflater.from(context).inflate(bindLayout(), viewGroup, false));
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, final int i) {
        bindHolder(holder, i);
        holder.itemView.setTag(i);
        holder.itemView.setOnClickListener(this);
    }

    protected abstract void bindHolder(@NonNull BaseViewHolder holder, int i);

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    public void addItem(T t) {
        list.add(t);
        notifyItemInserted(list.size());
    }
    public void addItemFirst(T t) {
        list.add(0,t);
        notifyItemInserted(list.size());
    }
    public void addItem(Collection<T> collections) {
        list.addAll(collections);
        notifyDataSetChanged();
    }

    public void removePosition(int position) {
        list.remove(position);
        notifyDataSetChanged();
    }

    public void clearAllItem() {
        list.clear();
    }

    public T getItem(int position) {
        return list.get(position);
    }

    private ItemClick itemClick;

    public void addItemClick(ItemClick itemClick) {
        this.itemClick = itemClick;
    }

    @Override
    public void onClick(View v) {
        int position = (int) v.getTag();
        if (itemClick != null) {
            itemClick.itemClick(position);
        }
    }

    public interface ItemClick {
        void itemClick(int position);
    }
}
