package com.example.my_app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.List;

public class RewardsAdapter extends ArrayAdapter<String> {

    public RewardsAdapter(Context context, List<String> rewards) {
        super(context, 0, rewards);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_reward, parent, false);
        }

        String reward = getItem(position);
        TextView tvReward = convertView.findViewById(R.id.tvRewardItem);
        tvReward.setText(reward);

        return convertView;
    }
}
