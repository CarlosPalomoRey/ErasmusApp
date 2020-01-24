package com.example.myapplication.ui.things2do;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.myapplication.R;

public class Things2doFragment extends Fragment {

    private Things2doViewModel things2doViewModel;
    private WebView webView;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        things2doViewModel =
                ViewModelProviders.of(this).get(Things2doViewModel.class);
        View root = inflater.inflate(R.layout.fragment_things2do, container, false);
        final TextView textView = root.findViewById(R.id.text_things2do);
        things2doViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        webView = (WebView)root.findViewById(R.id.things2doWebview);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());



        webView.loadUrl("file:///android_asset/html/things2do.html");

        return root;
    }
}