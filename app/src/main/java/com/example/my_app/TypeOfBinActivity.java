package com.example.my_app;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class TypeOfBinActivity extends AppCompatActivity {

    private TextView tvBinDescription;
    private ImageView ivBinImage;

    private int currentIndex = 0;

    public static class BinType {
        public String name;
        public String description;
        public int imageResId;

        public BinType(String name, String description, int imageResId) {
            this.name = name;
            this.description = description;
            this.imageResId = imageResId;
        }
    }

    private BinType[] binTypes = {
            new BinType("♻️ Plastic Bin", "Used for plastic bottles, containers, and wrappers.", R.drawable.bin_plastic),
            new BinType("📰 Paper Bin", "Used for newspapers, magazines, and cardboard.", R.drawable.bin_paper),
            new BinType("🍏 Organic Bin", "Used for food waste and compostable items.", R.drawable.bin_organic),
            new BinType("🛢️ Metal Bin", "Used for aluminum cans and metal scraps.", R.drawable.bin_metal),
            new BinType("🔋 E-Waste Bin", "Used for batteries, electronic devices, and wires.", R.drawable.bin_ewaste),
            new BinType("🧪 Glass Bin", "Used for glass bottles and jars.", R.drawable.bin_glass)
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_type_of_bin);

        tvBinDescription = findViewById(R.id.tvBinDescription);
        ivBinImage = findViewById(R.id.ivBinImage);

        showBinInfo(currentIndex);
    }

    private void showBinInfo(int index) {
        BinType bin = binTypes[index];
        tvBinDescription.setText(bin.name + "\n" + bin.description);
        ivBinImage.setImageResource(bin.imageResId);
    }
}
