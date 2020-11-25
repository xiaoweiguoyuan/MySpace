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
import com.wuba.car.myspace.entity.SingleBean;
import com.wuba.car.qishuier.R;

import java.util.List;

public class SingleAdapter extends RecyclerView.Adapter<SingleAdapter.ViewHolder> {
    private Context mContext;
    private List<SingleBean.EpisodesBean> mData;
    private OnItemClickListener itemClickListener;
    public SingleAdapter(Context mContext, List<SingleBean.EpisodesBean> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SingleAdapter.ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_single,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Glide.with(mContext).load(mData.get(position).getEpisode_art_url()).into(holder.imageView);
        holder.title.setText(mData.get(position).getTitle());
        holder.program.setText(mData.get(position).getPodcast().getName());
        holder.time.setText(mData.get(position).getTotal_time()+"分钟");
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
        private ImageView imageView;
        private TextView title,program,time;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.avatar);
            title = itemView.findViewById(R.id.name);
            program = itemView.findViewById(R.id.content);
            time = itemView.findViewById(R.id.time);
        }
    }

    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    public void setItemClickListener(OnItemClickListener itemClickListener){
        this.itemClickListener = itemClickListener;
    }
}
