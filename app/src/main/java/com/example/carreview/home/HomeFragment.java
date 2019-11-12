package com.example.carreview.home;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import com.example.carreview.model.LikePost;
import com.example.carreview.model.Post;
import com.example.carreview.R;
import com.example.carreview.login.LoginActivity;
import com.example.carreview.model.Suggestion;

import java.util.ArrayList;
import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class HomeFragment extends Fragment implements IHome {
    private static final String TAG = "HomeFragment";


    private RecyclerView recyclerView;
    HomePresenter presenter;
    private HomeAdapter mAdapter;
    private List<Post> postList;
    private List<LikePost> likePostList;

    private List<Suggestion> mSuggestions = new ArrayList<>();

    public HomeFragment() {
    }

    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_fragment, container, false);
        final EditText edtSearch = view.findViewById(R.id.edtSearch);
        ImageView imgSearch = view.findViewById(R.id.imgSearch);
        recyclerView = view.findViewById(R.id.recycler_view);
        postList = new ArrayList<>();
        likePostList = new ArrayList<>();
        mAdapter = new HomeAdapter(postList, likePostList, getContext());

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getActivity(), 1);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(1, dpToPx(8), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
        recyclerView.setNestedScrollingEnabled(false);
        presenter = new HomePresenter(this, getContext());
        presenter.fetchCarItems(mAdapter, postList);
        presenter.getLikePost(likePostList);

        imgSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String search = edtSearch.getText().toString();
                ArrayList<Post> newList = new ArrayList<>();
                for (Post post : postList) {
                    if (post.getCarName().toLowerCase().contains(search)) {
                        newList.add(post);
                    }
                }
                postList.clear();
                postList.addAll(newList);
                mAdapter.notifyDataSetChanged();
                recyclerView.setAdapter(mAdapter);
            }
        });

        mAdapter.notifyDataSetChanged();
        recyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new HomeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View itemView, int position) {
                Intent intent = new Intent(getContext(), LoginActivity.class);
                startActivity(intent);
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

    @Override
    public void onClickName(String name) {

    }

    @Override
    public void onClickPhone(String phone) {

    }
}
