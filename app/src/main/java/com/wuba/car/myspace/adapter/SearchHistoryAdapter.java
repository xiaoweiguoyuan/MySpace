package com.wuba.car.myspace.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.wuba.car.myspace.entity.ProgramBean;
import com.wuba.car.qishuier.R;

import java.util.List;

public class SearchHistoryAdapter extends RecyclerView.Adapter<SearchHistoryAdapter.ViewHolder> {
    private Context mContext;
    private List<String> mData;
    private OnItemClickListener itemClickListener;
    public SearchHistoryAdapter(Context mContext, List<String> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SearchHistoryAdapter.ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_search_history,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvTitle.setText(mData.get(position));
        holder.itemView.setTag(position);
//        Animation animation = AnimationUtils.loadAnimation(mContext, R.anim.alpha_anim);
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
        private TextView tvTitle;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.name);
        }
    }

    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    public void setItemClickListener(OnItemClickListener itemClickListener){
        this.itemClickListener = itemClickListener;
    }
}
