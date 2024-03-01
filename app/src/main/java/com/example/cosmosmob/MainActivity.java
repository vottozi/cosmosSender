package com.example.cosmosmob;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import com.example.cosmosmob.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private static final int PICK_MEDIA_REQUEST = 1;
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

        binding.btnSelectMedia.setOnClickListener(v -> {
            selectMedia();
        });
    }

    private void selectMedia() {
        Intent intent = new Intent();
        intent.setType("*/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Выберите медиафайл"), PICK_MEDIA_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_MEDIA_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri selectedMediaUri = data.getData();
            sendMedia(selectedMediaUri);
        }
    }

    private void sendMedia(Uri mediaUri) {
        Intent sendIntent = new Intent(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_STREAM, mediaUri);
//        sendIntent.setType("image/*");
        String type = getContentResolver().getType(mediaUri);
        sendIntent.setType(type);

        if (sendIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(Intent.createChooser(sendIntent, "Отправить файл через:"));
        }
    }
}
