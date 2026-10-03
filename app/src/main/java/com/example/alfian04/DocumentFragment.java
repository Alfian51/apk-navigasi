package com.example.alfian04;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

public class DocumentFragment extends Fragment {

    public DocumentFragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_document, container, false);

        Button btnOpenMaps = view.findViewById(R.id.btn_doc_open_maps);
        btnOpenMaps.setText(R.string.btn_goto_maps_page);
        btnOpenMaps.setOnClickListener(v -> {
            if (getActivity() != null) {
                ViewPager2 viewPager = getActivity().findViewById(R.id.view_pager);
                if (viewPager != null) {
                    viewPager.setCurrentItem(4, true); // Pindah ke tab Maps (posisi 4)
                }
            }
        });

        return view;
    }
}
