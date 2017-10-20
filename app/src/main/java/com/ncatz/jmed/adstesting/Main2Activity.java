package com.ncatz.jmed.adstesting;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        initAd();
    }

    private void initAd() {
        AdRequest adRequest = new AdRequest.Builder().build();
        AdView adExample2 = findViewById(R.id.adExample2);
        adExample2.loadAd(adRequest);
        adExample2.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                Log.i("Ads", "onAdLoaded()");
            }

            @Override
            public void onAdFailedToLoad(int i) {
                Log.i("Ads", "onAdFailedToLoad(i)");
            }

            @Override
            public void onAdOpened() {
                Log.i("Ads", "onAdOpened()");
            }

            @Override
            public void onAdLeftApplication() {
                Log.i("Ads", "onAdLeftApplication");
            }

            @Override
            public void onAdClosed() {
                Log.i("Ads", "onAdClosed()");
            }

            @Override
            public void onAdClicked() {
                Log.i("Ads", "onAdClicked()");
            }

            @Override
            public void onAdImpression() {
                Log.i("Ads", "onAdImpression()");
            }
        });
    }
}
