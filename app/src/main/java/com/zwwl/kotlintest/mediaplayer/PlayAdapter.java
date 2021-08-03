package com.zwwl.kotlintest.mediaplayer;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.zwwl.kotlintest.R;

import java.util.List;

/**
 * Created by zhanghuipeng on 8/3/21.
 */
public class PlayAdapter extends RecyclerView.Adapter<PlayAdapter.PlayViewHolder> {

    private List<PlayBean> data;
    private OnPlayItemClickListener onClickListener;
    private int currentPosition = 0;

    public PlayAdapter(List<PlayBean> data, OnPlayItemClickListener onClickListener) {
        this.data = data;
        this.onClickListener = onClickListener;
    }

    public void setCurrentPosition(int position) {
        this.currentPosition = position;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public PlayAdapter.PlayViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_play, parent, false);
        return new PlayViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull PlayAdapter.PlayViewHolder holder, final int position) {
        final PlayBean playBean = data.get(position);
        holder.tvContent.setText(playBean.getName());
        if (position == currentPosition) {
            holder.tvContent.setTextColor(holder.itemView.getResources().getColor(R.color.colorAccent));
        } else {
            holder.tvContent.setTextColor(holder.itemView.getResources().getColor(R.color.colorUnCheck));
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onClickListener != null) {
                    onClickListener.onItemClick(playBean, position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class PlayViewHolder extends RecyclerView.ViewHolder {
        public final TextView tvContent;
        public final View provider;
        public PlayViewHolder(@NonNull View itemView) {
            super(itemView);
            this.tvContent = itemView.findViewById(R.id.tvContent);
            this.provider = itemView.findViewById(R.id.itemProvider);
        }
    }
}
