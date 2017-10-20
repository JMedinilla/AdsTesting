package com.ncatz.jmed.adstesting;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.reward.RewardItem;
import com.google.android.gms.ads.reward.RewardedVideoAd;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;

public class MainActivity extends AppCompatActivity {

    private RewardedVideoAd rewardedVideoAd;
    private RewardedVideoAdListener rewardedVideoAdListener;
    private InterstitialAd interstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MobileAds.initialize(this, "ca-app-pub-3940256099942544~3347511713");
        initAd();

        Button button = findViewById(R.id.next);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (interstitialAd != null && interstitialAd.isLoaded()) {
                    interstitialAd.show();
                } else {
                    nextActivity();
                }
            }
        });
    }

    private void nextActivity() {
        Intent intent = new Intent(MainActivity.this, Main2Activity.class);
        startActivity(intent);
    }

    private void initAd() {
        rewardedVideoAd = MobileAds.getRewardedVideoAdInstance(this);
        rewardedVideoAdListener = new RewardedVideoAdListener() {
            @Override
            public void onRewardedVideoAdLoaded() {

            }

            @Override
            public void onRewardedVideoAdOpened() {

            }

            @Override
            public void onRewardedVideoStarted() {

            }

            @Override
            public void onRewardedVideoAdClosed() {
                nextActivity();
                rewardedVideoAd.loadAd("ca-app-pub-3940256099942544/5224354917", new AdRequest.Builder().build());
            }

            @Override
            public void onRewarded(RewardItem rewardItem) {
                Toast.makeText(MainActivity.this, rewardItem.getType() + " - " + rewardItem.getAmount(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onRewardedVideoAdLeftApplication() {

            }

            @Override
            public void onRewardedVideoAdFailedToLoad(int i) {

            }
        };
        rewardedVideoAd.setRewardedVideoAdListener(rewardedVideoAdListener);
        rewardedVideoAd.loadAd("ca-app-pub-3940256099942544/5224354917", new AdRequest.Builder().build());

        AdRequest adRequest = new AdRequest.Builder().build();
        AdView adExample = findViewById(R.id.adExample);
        adExample.loadAd(adRequest);
        adExample.setAdListener(new AdListener() {
        });

        interstitialAd = new InterstitialAd(MainActivity.this);
        interstitialAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712");
        interstitialAd.loadAd(new AdRequest.Builder().build());
        interstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdFailedToLoad(int i) {
                nextActivity();
            }

            @Override
            public void onAdClosed() {
                Log.i("Ads", "onAdClosed()");
                if (rewardedVideoAd.isLoaded()) {
                    rewardedVideoAd.show();
                } else {
                    nextActivity();
                }
                interstitialAd.loadAd(new AdRequest.Builder().build());
            }
        });
    }
}
