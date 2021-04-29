package com.library.mymusic.UTILITY;

import android.app.Dialog;
import android.content.Context;
import android.os.Build;
import android.view.Gravity;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.LinearLayout;

import androidx.annotation.RequiresApi;

import com.library.mymusic.R;

import pl.droidsonroids.gif.GifImageView;

public class TransparentProgressDialog extends Dialog {
    private GifImageView iv;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public TransparentProgressDialog(Context context, int resourceIdOfImage) {
        super(context,R.style.TransparentProgressDialog);
        WindowManager.LayoutParams wlmp = getWindow().getAttributes();
        wlmp.gravity = Gravity.CENTER_HORIZONTAL;
        getWindow().setAttributes(wlmp);
        setTitle(null);
        setCancelable(false);
        setOnCancelListener(null);
        LinearLayout layout = new LinearLayout(context);
        layout.setOrientation(LinearLayout.VERTICAL);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(250, 250);
        iv = new GifImageView(context);
//        iv.setBackground(context.getDrawable(R.drawable.loaderbackground));
//        iv.setBackgroundColor(context.getResources().getColor(R.color.white));
        iv.setImageResource(resourceIdOfImage);
        layout.addView(iv, params);
        addContentView(layout, params);
    }

    @Override
    public void show() {

        RotateAnimation anim = new RotateAnimation(0.0f, 360.0f , Animation.RELATIVE_TO_SELF, .5f, Animation.RELATIVE_TO_SELF, .5f);
//        RotateAnimation anim = new RotateAnimation(0.0f, 0.0f , Animation.RELATIVE_TO_SELF, .0f, Animation.RELATIVE_TO_SELF, .0f);

        anim.setInterpolator(new LinearInterpolator());
        anim.setRepeatCount(Animation.INFINITE);
        anim.setDuration(3000);
        iv.setAnimation(anim);
        iv.startAnimation(anim);
        super.show();
    }
}
