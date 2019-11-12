package com.example.carreview.favourite;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.carreview.R;
import com.example.carreview.home.HomeAdapter;
import com.example.carreview.model.Favorite;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.ViewHolder> implements IFavourite {
    private static final String TAG = "FavoriteAdapter";
    Context context;
    List<Favorite> favoriteList;
    private HomeAdapter.OnItemClickListener listener;

    public FavoriteAdapter(Context context, List<Favorite> favoriteList) {
        this.context = context;
        this.favoriteList = favoriteList;
    }

    @NonNull
    @Override
    public FavoriteAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item, parent, false);

        return new FavoriteAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull FavoriteAdapter.ViewHolder holder, int position) {
        final Favorite favorite = favoriteList.get(position);
        holder.postID.setText(favorite.getPostID());
        holder.name.setText(favorite.getCarName());
        holder.price.setText(favorite.getCarPrice());
        holder.brand.setText(favorite.getCarBrand());
        holder.type.setText(favorite.getCarType());
        holder.description.setText(favorite.getDescription());

        Glide.with(context)
                .load(favorite.getImageBefore())
                .into(holder.imgBefore);
        Glide.with(context)
                .load(favorite.getImageAfter())
                .into(holder.imgAfter);
        Glide.with(context)
                .load(favorite.getImageLeft())
                .into(holder.imgLeft);
        Glide.with(context)
                .load(favorite.getImageRight())
                .into(holder.imgRight);
        if (favorite.getCarName().contains("Mercedes")) {
            holder.imgBrand.setImageResource(R.drawable.mercedes_logo);
        } else if (favorite.getCarName().contains("Audi")) {
            holder.imgBrand.setImageResource(R.drawable.audi_logo);
        } else if (favorite.getCarName().contains("BMW")) {
            holder.imgBrand.setImageResource(R.drawable.bmw_logo);
        } else if (favorite.getCarName().contains("Lexus")) {
            holder.imgBrand.setImageResource(R.drawable.lexus_logo);
        } else if (favorite.getCarName().contains("Mazda")) {
            holder.imgBrand.setImageResource(R.drawable.mazda_logo);
        } else if (favorite.getCarName().contains("Toyota")) {
            holder.imgBrand.setImageResource(R.drawable.toyota_logo);
        } else {
            holder.imgBrand.setImageResource(R.drawable.ic_brand_default);
        }

    }

    @Override
    public int getItemCount() {
        return favoriteList.size();
    }

    @Override
    public void onSuccessFul() {

    }

    @Override
    public void onMessenger(String mes) {

    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView postID, name, price, brand, type, description, tvLike, totalLike;
        private ImageView imgBefore, imgAfter, imgLeft, imgRight, imgBrand,
                imgLike, imgTotalLike, imgSend, imgFavorite;
        private LinearLayout lnLike, lnComment, lnCommentUser;
        private EditText edtComment;
        private RelativeLayout rlPost;

        private ViewHolder(final View itemView) {
            super(itemView);
            postID = itemView.findViewById(R.id.postID);
            name = itemView.findViewById(R.id.carName);
            price = itemView.findViewById(R.id.carPrice);
            brand = itemView.findViewById(R.id.carBrand);
            type = itemView.findViewById(R.id.carType);
            imgBefore = itemView.findViewById(R.id.imgBefore);
            imgAfter = itemView.findViewById(R.id.imgAfter);
            imgLeft = itemView.findViewById(R.id.imgLeft);
            imgRight = itemView.findViewById(R.id.imgRight);
            description = itemView.findViewById(R.id.description);

            edtComment = itemView.findViewById(R.id.edtComment);

            lnLike = itemView.findViewById(R.id.lnLike);
            lnComment = itemView.findViewById(R.id.lnComment);
            imgTotalLike = itemView.findViewById(R.id.imgTotalLike);
            lnCommentUser = itemView.findViewById(R.id.lnCommentUser);

            rlPost = itemView.findViewById(R.id.rlPost);

            totalLike = itemView.findViewById(R.id.totalLike);
            tvLike = itemView.findViewById(R.id.tvLike);
            imgLike = itemView.findViewById(R.id.imgLike);
            imgBrand = itemView.findViewById(R.id.imgBrand);
            imgSend = itemView.findViewById(R.id.imgSend);
            imgFavorite = itemView.findViewById(R.id.imgFavorite);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null)
                        listener.onItemClick(itemView, getAdapterPosition());
                }
            });
        }
    }
}
