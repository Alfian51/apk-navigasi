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

public class HomeFragment extends Fragment {

    public HomeFragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        Button btnQuickFeature = view.findViewById(R.id.btn_quick_feature);
        Button btnQuickCalc = view.findViewById(R.id.btn_quick_calc);
        Button btnQuickMaps = view.findViewById(R.id.btn_quick_maps);

        btnQuickFeature.setOnClickListener(v -> switchTab(1));
        btnQuickCalc.setOnClickListener(v -> switchTab(2));
        btnQuickMaps.setOnClickListener(v -> switchTab(3));

        return view;
    }

    private void switchTab(int position) {
        if (getActivity() != null) {
            ViewPager2 viewPager = getActivity().findViewById(R.id.view_pager);
            if (viewPager != null) {
                viewPager.setCurrentItem(position, true);
            }
        }
    }
}
