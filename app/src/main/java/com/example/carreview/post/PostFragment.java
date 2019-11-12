package com.example.carreview.post;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.carreview.R;
import com.example.carreview.databinding.PostFragmentBinding;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

public class PostFragment extends Fragment implements IPost {

    PostFragmentBinding binding;
    PostPresenter presenter;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.post_fragment, container, false);

        return binding.getRoot();
    }

    @Override
    public void onSuccessFul() {

    }

    @Override
    public void onMessenger(String mes) {

    }
}
