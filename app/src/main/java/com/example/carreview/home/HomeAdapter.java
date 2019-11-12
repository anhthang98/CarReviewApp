package com.example.carreview.home;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.text.Editable;
import android.text.TextWatcher;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.carreview.model.LikePost;
import com.example.carreview.model.Post;
import com.example.carreview.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> implements IHome {
    private Context context;
    private List<Post> postList;
    private List<LikePost> likePostList;
    private OnItemClickListener listener;
    private HomePresenter presenter;
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;
    private String postID;
    private static final String TAG = "HomeAdapter";

    public HomeAdapter(List<Post> postList, List<LikePost> likePostList, Context context) {
        this.postList = postList;
        this.likePostList = likePostList;
        this.context = context;

        preferences = PreferenceManager.getDefaultSharedPreferences(context);
        editor = preferences.edit();
    }

    @Override
    public void onSuccessFul() {

    }

    @Override
    public void onMessenger(String mes) {

    }

    @Override
    public void onClickName(String name) {

    }

    @Override
    public void onClickPhone(String phone) {

    }

    public interface OnItemClickListener {
        void onItemClick(View itemView, int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public HomeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item, parent, false);

        return new HomeAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final HomeAdapter.ViewHolder holder, int position) {
        final Post post = postList.get(position);
        holder.postID.setText(post.getPostID());
        holder.name.setText(post.getCarName());
        holder.price.setText(post.getCarPrice());
        holder.brand.setText(post.getCarBrand());
        holder.type.setText(post.getCarType());
        holder.description.setText(post.getDescription());


        Glide.with(context)
                .load(post.getImageBefore())
                .into(holder.imgBefore);
        Glide.with(context)
                .load(post.getImageAfter())
                .into(holder.imgAfter);
        Glide.with(context)
                .load(post.getImageLeft())
                .into(holder.imgLeft);
        Glide.with(context)
                .load(post.getImageRight())
                .into(holder.imgRight);
        if (post.getCarName().contains("Mercedes")) {
            holder.imgBrand.setImageResource(R.drawable.mercedes_logo);
        } else if (post.getCarName().contains("Audi")) {
            holder.imgBrand.setImageResource(R.drawable.audi_logo);
        } else if (post.getCarName().contains("BMW")) {
            holder.imgBrand.setImageResource(R.drawable.bmw_logo);
        } else if (post.getCarName().contains("Lexus")) {
            holder.imgBrand.setImageResource(R.drawable.lexus_logo);
        } else if (post.getCarName().contains("Mazda")) {
            holder.imgBrand.setImageResource(R.drawable.mazda_logo);
        } else if (post.getCarName().contains("Toyota")) {
            holder.imgBrand.setImageResource(R.drawable.toyota_logo);
        } else {
            holder.imgBrand.setImageResource(R.drawable.ic_brand_default);
        }
        String userID = preferences.getString("userId", null);

        final String[] checkLike = {""};
        if (likePostList.size() >= 1) {
            checkLike[0] = "T";
        } else if (likePostList.size() == 0) {
            checkLike[0] = "F";
        }
        for (LikePost likePost : likePostList) {
            if (likePost.getUserID().equals(userID) && post.getPostID().equals(likePost.getPostID())) {
                checkLike[0] = likePost.getCheckLike();
                holder.imgLike.setImageResource(R.drawable.ic_like_blue);
                int resource = holder.tvLike.getResources().getColor(R.color.text_like);
                holder.tvLike.setTextColor(resource);
                break;
            } else {
                checkLike[0] = "F";
                holder.imgLike.setImageResource(R.drawable.like_icon);
                int resource = holder.tvLike.getResources().getColor(R.color.text_unlike);
                holder.tvLike.setTextColor(resource);
            }
        }
        holder.lnLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (LikePost likePost : likePostList) {
                    if (likePost.getCheckLike().equals("T")) {
                        holder.imgLike.setImageResource(R.drawable.like_icon);
                        int resource = holder.tvLike.getResources().getColor(R.color.text_unlike);
                        holder.tvLike.setTextColor(resource);
                        post.setTotalLike(String.valueOf(Integer.valueOf(post.getTotalLike()) - 1));
                        holder.totalLike.setText(post.getTotalLike());
                        holder.imgTotalLike.setVisibility(View.VISIBLE);
                        String userID = preferences.getString("userId", null);
                        postID = post.getPostID();
                        presenter.deleteLikePost(userID, postID);
                        likePostList.remove(likePost);
                        checkLike[0] = "F";
                        break;
                    }
                }
                if (checkLike[0] == "F") {
                    checkLike[0] = "T";
                    holder.imgLike.setImageResource(R.drawable.ic_like_blue);
                    int resource = holder.tvLike.getResources().getColor(R.color.text_like);
                    holder.tvLike.setTextColor(resource);
                    post.setTotalLike(String.valueOf(Integer.valueOf(post.getTotalLike()) + 1));
                    holder.totalLike.setText(post.getTotalLike());
                    String userID = preferences.getString("userId", null);
                    postID = post.getPostID();
                    presenter.addLike(userID, postID, checkLike[0]);
                    Toast.makeText(context, userID + postID + checkLike[0], Toast.LENGTH_SHORT).show();
                } else if (checkLike[0] == "T") {
                    checkLike[0] = "F";
                    holder.imgLike.setImageResource(R.drawable.like_icon);
                    int resource = holder.tvLike.getResources().getColor(R.color.text_unlike);
                    holder.tvLike.setTextColor(resource);
                    post.setTotalLike(String.valueOf(Integer.valueOf(post.getTotalLike()) - 1));
                    holder.totalLike.setText(post.getTotalLike());
                    holder.imgTotalLike.setVisibility(View.VISIBLE);
                    String userID = preferences.getString("userId", null);
                    postID = post.getPostID();
                    presenter.deleteLikePost(userID, postID);
                }
            }
        });
        holder.imgTotalLike.setVisibility(View.VISIBLE);
        holder.edtComment.setVisibility(View.GONE);
        holder.imgSend.setVisibility(View.GONE);
        holder.rlPost.setHovered(true);
        presenter = new HomePresenter(this, context);

        holder.imgFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userID = preferences.getString("userId", null);
                postID = post.getPostID();
                String checkFavorite = "T";
                Toast.makeText(context, userID + postID + checkFavorite, Toast.LENGTH_SHORT).show();

                presenter.addFavorite(userID, postID, checkFavorite);
                holder.imgFavorite.setImageResource(R.drawable.ic_star_blue);
            }
        });


        holder.lnComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String getNameUser = preferences.getString("userName", null);
                holder.lnCommentUser.setVisibility(View.VISIBLE);
                holder.edtComment.setVisibility(View.VISIBLE);
                holder.edtComment.setText(getNameUser);
            }
        });

        holder.edtComment.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                holder.imgSend.setVisibility(View.GONE);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (holder.edtComment.getText().toString().isEmpty()) {
                    holder.imgSend.setVisibility(View.GONE);
                } else {
                    holder.imgSend.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return postList.size();
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
