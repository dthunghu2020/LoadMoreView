package com.example.apiseemore.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.apiseemore.R;

import java.util.List;

public class TextAdapter extends RecyclerView.Adapter<TextAdapter.ViewHolder> {

    private List<String> listItemString;
    private LayoutInflater layoutInflater;

    public TextAdapter(Context context, List<String> listItemString) {
        layoutInflater = LayoutInflater.from(context);
        this.listItemString = listItemString;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.item_text, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String itemText = listItemString.get(position);
        holder.txtItemText.setText(itemText);
    }

    @Override
    public int getItemCount() {
        return listItemString.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView txtItemText;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtItemText = itemView.findViewById(R.id.txtItemText);
        }
    }
}

