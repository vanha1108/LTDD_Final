package com.laptrinhdidong.electroniccomunications.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.laptrinhdidong.electroniccomunications.R;

public class HomeFragment extends Fragment {
    @NonNull
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container, @NonNull Bundle saveInsatnceState){
        return inflater.inflate(R.layout.fragment_home, container, false);
    }
}
