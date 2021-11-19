package com.firebase.spakebrony.myandroid.utilities;

import android.content.Context;
import android.graphics.drawable.Drawable;

import androidx.annotation.ColorInt;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.vectordrawable.graphics.drawable.VectorDrawableCompat;

/**
 * Created by LiCola on  2016/04/08  14:19
 */
public final class CompatUtils {
    public static Drawable getTintListDrawable(Context mContext, int mResDrawableId, int mResTintId){

//        Drawable drawable=ContextCompat.getDrawable(mContext,mResDrawableId);
        Drawable drawable= VectorDrawableCompat.create(mContext.getResources(),mResDrawableId,null);
        Drawable drawable1= DrawableCompat.wrap(drawable.mutate());
        DrawableCompat.setTintList(drawable1, ContextCompat.getColorStateList(mContext,mResTintId));
//        Drawable drawable = DrawableCompat.wrap(ContextCompat.getDrawable(mContext, mResDrawableId).mutate());
//        DrawableCompat.setTintList(drawable, ContextCompat.getColorStateList(mContext, mResTintId));
        return drawable1;
    }

    public static Drawable getTintDrawable(Context mContext ,int mResDrawableId, @ColorInt int tint){
//        Drawable drawable = DrawableCompat.wrap(ContextCompat.getDrawable(mContext, mResDrawableId).mutate());
//        DrawableCompat.setTint(drawable,tint);

        Drawable drawable=VectorDrawableCompat.create(mContext.getResources(),mResDrawableId,null);
        Drawable drawable1= DrawableCompat.wrap(drawable.mutate());
        DrawableCompat.setTint(drawable1,tint);

        return drawable1;
    }
}
