package com.puci.qs.myspace.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.puci.qs.myspace.entity.AllBean;
import com.puci.qs.qishuier.R;


import java.util.List;

public class AllProgramAdapter extends RecyclerView.Adapter<AllProgramAdapter.ViewHolder> {
    private Context mContext;
    private List<AllBean.PodcastsBean> mData;
    private OnItemClickListener itemClickListener;
    public AllProgramAdapter(Context mContext, List<AllBean.PodcastsBean> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new AllProgramAdapter.ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_all_program,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Glide.with(mContext).load(mData.get(position).getImage()).into(holder.imageView);
        holder.title.setText(mData.get(position).getName());
        holder.category.setText(mData.get(position).getCategory());
        holder.itemView.setTag(position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position1 = (Integer) v.getTag();
                itemClickListener.onItemClick(position1);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData!=null?mData.size():0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView imageView;
        private TextView title,category;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.avatar);
            title = itemView.findViewById(R.id.tv_title);
            category = itemView.findViewById(R.id.tv_category);
        }
    }

    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    public void setItemClickListener(OnItemClickListener itemClickListener){
        this.itemClickListener = itemClickListener;
    }
}
