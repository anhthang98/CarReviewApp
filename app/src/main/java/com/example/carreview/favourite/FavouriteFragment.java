package com.example.carreview.favourite;

import android.content.res.Resources;
import android.database.DatabaseUtils;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.carreview.R;
import com.example.carreview.home.HomeAdapter;
import com.example.carreview.home.HomeFragment;
import com.example.carreview.home.HomePresenter;
import com.example.carreview.model.Favorite;
import com.example.carreview.model.Post;


import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import static com.arlib.floatingsearchview.util.Util.dpToPx;

public class FavouriteFragment extends Fragment implements IFavourite {

    RecyclerView recyclerView;
    FavoriteAdapter favoriteAdapter;
    List<Favorite> favoriteList;
    FavouritePresenter presenter;

    public FavouriteFragment() {
    }

    public static FavouriteFragment newInstance() {
        Bundle args = new Bundle();
        FavouriteFragment fragment = new FavouriteFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.favorite_fragment, container, false);
        final EditText edtSearch = view.findViewById(R.id.edtSearch);
        ImageView imgSearch = view.findViewById(R.id.imgSearch);
        recyclerView = view.findViewById(R.id.recycler_view);
        favoriteList = new ArrayList<>();
        favoriteAdapter = new FavoriteAdapter(getContext(), favoriteList);

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getActivity(), 1);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(1, dpToPx(8), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(favoriteAdapter);
        recyclerView.setNestedScrollingEnabled(false);
        presenter = new FavouritePresenter(this, getContext());
        presenter.getFavorite(favoriteAdapter, favoriteList);
        imgSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String search = edtSearch.getText().toString();
                ArrayList<Favorite> newList = new ArrayList<>();
                for (Favorite favorite : favoriteList) {
                    if (favorite.getCarName().toLowerCase().contains(search)) {
                        newList.add(favorite);
                    }
                }
                favoriteList.clear();
                favoriteList.addAll(newList);
                favoriteAdapter.notifyDataSetChanged();
                recyclerView.setAdapter(favoriteAdapter);
            }
        });
        return view;
    }

    public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view);
            int column = position % spanCount;

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount;
                outRect.right = (column + 1) * spacing / spanCount;

                if (position < spanCount) {
                    outRect.top = spacing;
                }
                outRect.bottom = spacing;
            } else {
                outRect.left = column * spacing / spanCount;
                outRect.right = spacing - (column + 1) * spacing / spanCount;
                if (position >= spanCount) {
                    outRect.top = spacing;
                }
            }
        }
    }


    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }

    @Override
    public void onSuccessFul() {

    }

    @Override
    public void onMessenger(String mes) {

    }
}
