package com.yahoo.apps.instagram;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.makeramen.RoundedImageView;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by yzhang29 on 2/6/15.
 */
public class ImageAdapter extends ArrayAdapter<ImageData> {
    public ImageAdapter(Context context, List<ImageData> objects) {
        super(context, 0, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageData imageData = getItem(position);
        if(convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_photo, parent, false);
        }
        TextView tvCaption = (TextView) convertView.findViewById(R.id.tvCaption);
        ImageView ivPhoto = (ImageView) convertView.findViewById(R.id.ivPhoto);
        tvCaption.setText(imageData.caption);
        ivPhoto.setImageResource(0);
        Picasso.with(getContext()).load(imageData.imageUrl).into(ivPhoto);
        RoundedImageView ivUserImage = (RoundedImageView) convertView.findViewById(R.id.ivUserPhoto);
        ivUserImage.setImageResource(0);
        Picasso.with(getContext()).load(imageData.userProfileImageUrl).into(ivUserImage);
        TextView tvUsername = (TextView) convertView.findViewById(R.id.tvUsername);
        tvUsername.setText(imageData.username);
        return convertView;
    }
}
