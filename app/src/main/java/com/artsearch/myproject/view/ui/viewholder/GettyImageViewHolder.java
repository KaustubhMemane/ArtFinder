package com.artsearch.myproject.view.ui.viewholder;

import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.artsearch.myproject.R;
import com.artsearch.myproject.api.model.ImageResult;
import com.artsearch.myproject.util.LongPressGestureDetector;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GettyImageViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.image_view)
    ImageView imageView;

    public GettyImageViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bind(ImageResult imageResult, LongPressGestureDetector.Listener listener) {
        GestureDetectorCompat gestureDetector = new GestureDetectorCompat(itemView.getContext(), new LongPressGestureDetector(listener));

        itemView.setOnTouchListener((v, event) -> {
            gestureDetector.onTouchEvent(event);
            return true;
        });

        Picasso.with(itemView.getContext())
                .load(imageResult.getThumbUri())
                .into(imageView);
    }
}
