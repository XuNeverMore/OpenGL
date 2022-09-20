package com.example.opengl.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavControllerKt;
import androidx.navigation.NavHost;
import androidx.navigation.Navigation;

import com.example.opengl.R;

/**
 * @author xct
 * create on: 2022/9/20 15:46
 */
public class HomeFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        bindNav(R.id.btn_triangle, R.id.triangleFragment);
        bindNav(R.id.btn_square, R.id.squareFragment);
        bindNav(R.id.btn_texture,R.id.textureFragment);
    }

    private void bindNav(@IdRes int viewId, int destId) {
        View view = getView();
        if (view == null) {
            return;
        }
        view.findViewById(viewId).setOnClickListener(v -> Navigation.findNavController(v).navigate(destId));
    }
}
