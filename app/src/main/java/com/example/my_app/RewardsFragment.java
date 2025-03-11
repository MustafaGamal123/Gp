package com.example.my_app;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import java.util.ArrayList;

public class RewardsFragment extends Fragment {

    private TextView tvPoints;
    private ListView lvRewards;
    private Button btnRedeem;
    private int userPoints = 100; // نقاط المستخدم الافتراضية

    public RewardsFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_rewards, container, false);

        tvPoints = view.findViewById(R.id.tvPoints);
        lvRewards = view.findViewById(R.id.lvRewards);
        btnRedeem = view.findViewById(R.id.btnRedeem);

        // تحديث عدد النقاط
        tvPoints.setText("Your Points: " + userPoints);

        // إعداد قائمة المكافآت
        ArrayList<String> rewardsList = new ArrayList<>();
        rewardsList.add("Discount Coupon - 50 Points");
        rewardsList.add("Free Coffee - 30 Points");
        rewardsList.add("Recycling Badge - 20 Points");
        rewardsList.add("Special Gift - 100 Points");

        RewardsAdapter adapter = new RewardsAdapter(requireContext(), rewardsList);
        lvRewards.setAdapter(adapter);

        // زر استبدال النقاط
        btnRedeem.setOnClickListener(v -> {
            if (userPoints >= 50) {
                userPoints -= 50;
                tvPoints.setText("Your Points: " + userPoints);
                Toast.makeText(getContext(), "You redeemed a reward!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getContext(), "Not enough points!", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
}
