package com.example.myapplication.ui.transports;

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

public class TransportsFragment extends Fragment {

    private TransportsViewModel transportsViewModel;
    private WebView webView;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        transportsViewModel =
                ViewModelProviders.of(this).get(TransportsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_transports, container, false);
        final TextView textView = root.findViewById(R.id.text_transports);
        transportsViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        webView = (WebView)root.findViewById(R.id.transportsWebview);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl("file:///android_asset/html/transport.html");
        return root;
    }
}