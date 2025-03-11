package com.example.my_app;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.Random;

public class TipsActivity extends AppCompatActivity {

    private TextView tvTip;
    private ImageView ivTip;
    private Button btnNextTip;
    private Random random = new Random();

    public static class Tip {
        public String text;
        public int imageResId;
        public Tip(String text, int imageResId) {
            this.text = text;
            this.imageResId = imageResId;
        }
    }

    private Tip[] tips = {
            new Tip("♻️ Separate recyclables from non-recyclables to improve sorting.", R.drawable.tip_recyclables),
            new Tip("🌍 Reduce, reuse, and recycle for a greener planet.", R.drawable.tip_reduce_reuse_recycle),
            new Tip("🔋 Dispose of batteries and electronics properly to avoid hazards.", R.drawable.tip_batteries),
            new Tip("🚰 Save water by efficiently cleaning recyclables.", R.drawable.tip_water_conservation),
            new Tip("✅ Check recycling symbols to ensure proper disposal.", R.drawable.tip_recycling_symbols),
            new Tip("📦 Flatten cardboard boxes before recycling to save space.", R.drawable.tip_flatten_boxes),
            new Tip("🌱 Compost organic waste to enrich the soil naturally.", R.drawable.tip_compost)
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tips);

        tvTip = findViewById(R.id.tvTip);
        ivTip = findViewById(R.id.ivTip);
        btnNextTip = findViewById(R.id.btnNextTip);

        showRandomTip();
        btnNextTip.setOnClickListener(v -> showRandomTip());
    }

    private void showRandomTip() {
        int index = random.nextInt(tips.length);
        Tip tip = tips[index];
        tvTip.setText(tip.text);
        ivTip.setImageResource(tip.imageResId);
    }
}
