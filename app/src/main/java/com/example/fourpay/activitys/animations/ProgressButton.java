package com.example.fourpay.activitys.animations;

import android.content.Context;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.fourpay.R;

public class ProgressButton {

    private CardView cardView;
    private ConstraintLayout constraintLayout;
    private ProgressBar progressBar;
    private TextView textView;

    private Animation fade_in;

    public ProgressButton(Context context, View view) {
        fade_in = AnimationUtils.loadAnimation(context, R.anim.progress_btn_anim);
        cardView = view.findViewById(R.id.card_view);
        constraintLayout = view.findViewById(R.id.constraint);
        progressBar = view.findViewById(R.id.progress);
        textView = view.findViewById(R.id.txt);
    }

    public void buttonActivated() {
        progressBar.setAnimation(fade_in);
        progressBar.setVisibility(View.VISIBLE);
        textView.setAnimation(fade_in);
        textView.setText("Aguarde um momento");
    }

    public void buttonFinishedSucess() {
        progressBar.setVisibility(View.INVISIBLE);
        constraintLayout.setBackgroundColor(cardView.getResources().getColor(R.color.verde));
        textView.setText("Pronto");
    }

}
