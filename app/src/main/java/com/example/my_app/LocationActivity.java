package com.example.my_app;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class LocationActivity extends AppCompatActivity implements OnMapReadyCallback {
    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);

        // تحميل الخريطة
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        } else {
            Toast.makeText(this, "Error loading map", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // إضافة صناديق إعادة التدوير على الخريطة
        LatLng bin1 = new LatLng(30.0444, 31.2357); // موقع صندوق في القاهرة (كمثال)
        LatLng bin2 = new LatLng(29.9792, 31.1342); // موقع آخر (كمثال)

        mMap.addMarker(new MarkerOptions().position(bin1).title("Recycling Bin - Downtown Cairo"));
        mMap.addMarker(new MarkerOptions().position(bin2).title("Recycling Bin - Near Pyramids"));

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(bin1, 12));
    }
}
