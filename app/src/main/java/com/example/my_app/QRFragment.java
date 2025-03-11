package com.example.my_app;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.fragment.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class QRFragment extends Fragment {

    private TextView tvQRResult;
    private Button btnScanQR;
    private ActivityResultLauncher<Intent> qrLauncher;

    public QRFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_qr, container, false);

        tvQRResult = view.findViewById(R.id.tvQRResult);
        btnScanQR = view.findViewById(R.id.btnScanQR);

        // Initialize QR Scanner result handler
        qrLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getData() != null) {
                        IntentResult scanResult = IntentIntegrator.parseActivityResult(result.getResultCode(), result.getData());
                        if (scanResult != null && scanResult.getContents() != null) {
                            tvQRResult.setText("Scanned QR Code: " + scanResult.getContents());
                        } else {
                            tvQRResult.setText("No QR Code scanned.");
                        }
                    }
                });

        btnScanQR.setOnClickListener(v -> startQRScanner());

        return view;
    }

    private void startQRScanner() {
        IntentIntegrator integrator = new IntentIntegrator(requireActivity());
        integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE);
        integrator.setPrompt("Scan a QR Code");
        integrator.setCameraId(0); // Use rear camera
        integrator.setBeepEnabled(true);
        integrator.setBarcodeImageEnabled(true);
        qrLauncher.launch(integrator.createScanIntent());
    }
}
