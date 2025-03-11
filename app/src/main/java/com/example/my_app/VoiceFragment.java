package com.example.my_app;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.speech.RecognitionListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.Locale;

public class VoiceFragment extends Fragment {

    private TextView tvVoiceResult;
    private Button btnStartVoice;
    private SpeechRecognizer speechRecognizer;

    public VoiceFragment() {
        // Required empty constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_voice, container, false);

        tvVoiceResult = view.findViewById(R.id.tvVoiceResult);
        btnStartVoice = view.findViewById(R.id.btnStartVoice);

        // إنشاء SpeechRecognizer مرة واحدة
        speechRecognizer = SpeechRecognizer.createSpeechRecognizer(requireContext());
        setupSpeechRecognizer();

        btnStartVoice.setOnClickListener(v -> startVoiceRecognition());

        return view;
    }

    private void setupSpeechRecognizer() {
        speechRecognizer.setRecognitionListener(new RecognitionListener() {
            @Override
            public void onReadyForSpeech(Bundle params) {
                tvVoiceResult.setText("Listening...");
            }

            @Override
            public void onBeginningOfSpeech() {}

            @Override
            public void onRmsChanged(float rmsdB) {}

            @Override
            public void onBufferReceived(byte[] buffer) {}

            @Override
            public void onEndOfSpeech() {
                tvVoiceResult.setText("Processing...");
            }

            @Override
            public void onError(int error) {
                tvVoiceResult.setText("Error recognizing speech. Try again.");
                Toast.makeText(requireContext(), "Recognition Error: " + error, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResults(Bundle results) {
                ArrayList<String> matches = results.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
                if (matches != null && !matches.isEmpty()) {
                    tvVoiceResult.setText("You said: " + matches.get(0));
                }
            }

            @Override
            public void onPartialResults(Bundle partialResults) {}

            @Override
            public void onEvent(int eventType, Bundle params) {}
        });
    }

    private void startVoiceRecognition() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Say something...");

        speechRecognizer.startListening(intent);
    }

    @Override
    public void onDestroy() {
        if (speechRecognizer != null) {
            speechRecognizer.destroy();
        }
        super.onDestroy();
    }
}
