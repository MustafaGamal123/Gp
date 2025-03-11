package com.example.my_app;

import androidx.fragment.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class GamesFragment extends Fragment {

    public GamesFragment() {
        // Required empty constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_games, container, false);

        Button btnStartQuiz = view.findViewById(R.id.btnStartQuiz);
        Button btnMemoryGame = view.findViewById(R.id.btnMemoryGame);

        if (btnStartQuiz != null) {
            btnStartQuiz.setOnClickListener(v ->
                    Toast.makeText(requireContext(), "Starting Recycling Quiz!", Toast.LENGTH_SHORT).show()
            );
        }

        if (btnMemoryGame != null) {
            btnMemoryGame.setOnClickListener(v ->
                    Toast.makeText(requireContext(), "Launching Memory Matching Game!", Toast.LENGTH_SHORT).show()
            );
        }

        return view;
    }
}
