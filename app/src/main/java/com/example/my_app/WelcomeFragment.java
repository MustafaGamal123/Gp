package com.example.my_app;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.VideoView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class WelcomeFragment extends Fragment {

    public WelcomeFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_welcome, container, false);

        VideoView videoView = view.findViewById(R.id.videoView);
        Button btnExit = view.findViewById(R.id.btnExit);
        Button btnSkip = view.findViewById(R.id.btnSkip); // زر تخطي الفيديو

        // تشغيل الفيديو
        String videoPath = "android.resource://" + getActivity().getPackageName() + "/" + R.raw.meet_trashbot;
        Uri uri = Uri.parse(videoPath);
        videoView.setVideoURI(uri);

        MediaController mediaController = new MediaController(getActivity());
        mediaController.setAnchorView(videoView);
        videoView.setMediaController(mediaController);
        videoView.start();

        // إعادة تشغيل الفيديو تلقائيًا عند الانتهاء
        videoView.setOnCompletionListener(mp -> videoView.start());

        // زر الخروج - يعود إلى شاشة التسجيل
        btnExit.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), RegistrationActivity.class);
            startActivity(intent);
            getActivity().finish();
        });

        // زر تخطي الفيديو - ينتقل إلى الشاشة الرئيسية
        btnSkip.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), MainActivity.class);
            startActivity(intent);
            getActivity().finish();
        });

        return view;
    }
}
