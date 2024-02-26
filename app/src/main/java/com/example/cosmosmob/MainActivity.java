package com.example.cosmosmob;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import com.example.cosmosmob.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnWriteWish.setOnClickListener(v -> {
            binding.etMyWish.setVisibility(View.VISIBLE);
            binding.btnSendWish.setVisibility(View.VISIBLE);
            binding.btnWriteWish.setVisibility(View.INVISIBLE);
        });

        binding.btnSendWish.setOnClickListener(v1 -> {
            String message = binding.etMyWish.getText().toString();

            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_TEXT, message);
            sendIntent.setType("text/plain");

            if (sendIntent.resolveActivity(getPackageManager()) != null) {
                startActivity(Intent.createChooser(sendIntent, "Отправить сообщение через:"));
            }
        });
    }
}