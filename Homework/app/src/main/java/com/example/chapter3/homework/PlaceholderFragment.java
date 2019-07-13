package com.example.chapter3.homework;


import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.content.Context;
import android.view.animation.LinearInterpolator;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.airbnb.lottie.LottieAnimationView;

public class PlaceholderFragment extends Fragment {

    private ListView listView;
    private LottieAnimationView animationView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO ex3-3: 修改 fragment_placeholder，添加 loading 控件和列表视图控件

        View view = inflater.inflate(R.layout.fragment_placeholder, container, false);
        listView = view.findViewById(R.id.list_view);
        listView.setVisibility(View.INVISIBLE);
        listView.setAdapter(new ListViewAdapter(container.getContext()));
        animationView = view.findViewById(R.id.animation_view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        getView().postDelayed(new Runnable() {
            @Override
            public void run() {
                // 这里会在 5s 后执行
                // TODO ex3-4：实现动画，将 lottie 控件淡出，列表数据淡入

                animationView.setVisibility(View.INVISIBLE);
                ObjectAnimator alphaAnimator1 = ObjectAnimator.ofFloat(animationView,
                        "alpha", 1f, 0f);
                alphaAnimator1.setDuration(2000);
                alphaAnimator1.setInterpolator(new LinearInterpolator());

                listView.setVisibility(View.VISIBLE);
                ObjectAnimator alphaAnimator2 = ObjectAnimator.ofFloat(listView,
                        "alpha", 0f, 1f);
                alphaAnimator2.setDuration(2000);
                alphaAnimator2.setInterpolator(new LinearInterpolator());

                AnimatorSet animatorSet = new AnimatorSet();
                animatorSet.playTogether(alphaAnimator1, alphaAnimator2);
                animatorSet.start();
            }
        }, 5000);
    }


    public static class ListViewAdapter extends BaseAdapter {

        private Context mContext;

        public ListViewAdapter(Context context) {
            mContext = context;
        }


        @Override public int getCount() {
            return 10;
        }

        @Override public Object getItem(int position) {
            return null;
        }

        @Override public long getItemId(int position) {
            return 0;
        }

        @Override public View getView(int position, View convertView, ViewGroup parent) {
            View view;
            if (convertView == null) {
                LayoutInflater inflater = LayoutInflater.from(mContext);
                view = inflater.inflate(R.layout.im_list_item, null);
            } else {
                //!=null
                view = convertView;
            }
            return view;
        }
    }
}
