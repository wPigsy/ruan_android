package com.ruan.mydialog;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class DemoAdapter extends RecyclerView.Adapter<DemoAdapter.DemoViewHolder> {
    private List<String> localDataSet;

    public DemoAdapter(List<String> localDataSet) {
        this.localDataSet = localDataSet;
    }

    public class DemoViewHolder extends RecyclerView.ViewHolder {

        private final TextView viewById;

        public DemoViewHolder(@NonNull View itemView) {
            super(itemView);
            viewById = itemView.findViewById(R.id.center_text);
        }

        public TextView getMyTv() {
            return viewById;
        }
    }


    @NonNull
    @Override
    public DemoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        MyDemoItem myDemoItem = new MyDemoItem(parent.getContext());
        return new DemoViewHolder(myDemoItem);
    }

    @Override
    public void onBindViewHolder(@NonNull DemoViewHolder holder, int position) {
        String s = localDataSet.get(position);
        holder.getMyTv().setText(s);
    }

    @Override
    public int getItemCount() {
        return localDataSet.size();
    }
}
