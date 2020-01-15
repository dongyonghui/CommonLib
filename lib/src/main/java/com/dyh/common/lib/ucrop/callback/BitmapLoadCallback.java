package com.dyh.common.lib.ucrop.callback;

import android.graphics.Bitmap;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.dyh.common.lib.ucrop.model.ExifInfo;

public interface BitmapLoadCallback {

    void onBitmapLoaded(@NonNull Bitmap bitmap, @NonNull ExifInfo exifInfo, @NonNull Uri imageInputUri,  Uri imageOutputUri);

    void onFailure(@NonNull Exception bitmapWorkerException);

}