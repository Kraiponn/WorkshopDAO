package com.ksn.kraiponn.workshopdao.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.MultiTransformation;
import com.bumptech.glide.request.RequestOptions;
import com.ksn.kraiponn.workshopdao.R;
import com.ksn.kraiponn.workshopdao.dao.PhotoItemCollectionDao;
import com.ksn.kraiponn.workshopdao.dao.PhotoItemDao;

import jp.wasabeef.glide.transformations.BlurTransformation;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

public class PhotoItemAdapter
        extends RecyclerView.Adapter<PhotoItemAdapter.PhotoItemViewHolder> {
    private Context mContext;
    private PhotoItemCollectionDao mDao;

    public PhotoItemAdapter(Context mContext, PhotoItemCollectionDao mDao) {
        this.mContext = mContext;
        this.mDao = mDao;
    }

    @NonNull
    @Override
    public PhotoItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent,
                                                  int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(
                R.layout.photo_item_layout, parent, false
        );

        PhotoItemViewHolder vHolder = new PhotoItemViewHolder(view);
        return vHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PhotoItemViewHolder holder,
                                 int position) {
        PhotoItemDao item = mDao.getData().get(position);
        holder.tvId.setText(item.getId()+"");
        holder.tvCaption.setText(item.getCaption());
        holder.tvDate.setText(item.getCreated_time());

        /*MultiTransformation transformation = new MultiTransformation(
                new BlurTransformation(25, 3),
                new RoundedCornersTransformation(
                        128, 0,
                        RoundedCornersTransformation.CornerType.BOTTOM
                )
        );*/

        RequestOptions options = new RequestOptions();
        options.placeholder(R.drawable.loading);

        Glide.with(mContext)
                .load(item.getImage_url())
                .apply(options)
                .into(holder.imgUrl);
    }

    @Override
    public int getItemCount() {
        if(mDao.getData() == null){
            return 0;
        }

        return mDao.getData().size();
    }

    class PhotoItemViewHolder extends RecyclerView.ViewHolder {
        public TextView tvId;
        public TextView tvCaption;
        public TextView tvDate;
        public ImageView imgUrl;

        public PhotoItemViewHolder(View cv) {
            super(cv);
            tvId = cv.findViewById(R.id.text_id);
            tvCaption = cv.findViewById(R.id.text_caption);
            tvDate = cv.findViewById(R.id.text_date);
            imgUrl = cv.findViewById(R.id.image_url);
        }
    }

}
