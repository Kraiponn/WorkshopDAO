package com.ksn.kraiponn.workshopdao.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.ksn.kraiponn.workshopdao.R;
import com.ksn.kraiponn.workshopdao.adapter.PhotoItemAdapter;
import com.ksn.kraiponn.workshopdao.dao.PhotoItemCollectionDao;
import com.ksn.kraiponn.workshopdao.manager.Contextor;
import com.ksn.kraiponn.workshopdao.manager.HttpPhotoItemManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainLive500pxFragment extends Fragment {
    private PhotoItemAdapter mAdapter;
    private PhotoItemCollectionDao mDao;
    private RecyclerView rcv;


    public static MainLive500pxFragment newInstance() {
        MainLive500pxFragment fm = new MainLive500pxFragment();
        Bundle args = new Bundle();
        fm.setArguments(args);
        return fm;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(
                R.layout.fragment_main_live500,
                container, false
        );

        initInstance(view);
        return view;
    }


    /*********************************
     *  Method Zone
     */
    private void showToast(String text) {
        Toast.makeText(Contextor.getsInstance().getContext(),
                text, Toast.LENGTH_SHORT).show();
    }

    private void initInstance(View view) {
        rcv = view.findViewById(R.id.recyclerView);

        Call<PhotoItemCollectionDao> call =
                HttpPhotoItemManager.getsInstance().getService().loadData();
        call.enqueue(new Callback<PhotoItemCollectionDao>() {
            @Override
            public void onResponse(Call<PhotoItemCollectionDao> call,
                                   Response<PhotoItemCollectionDao> response) {
                if (response.isSuccessful()) {
                    mDao = response.body();
                    showToast(mDao.getData().get(0).getCamera());
                    mAdapter = new PhotoItemAdapter(
                            Contextor.getsInstance().getContext(),
                            mDao
                    );
                    rcv.setAdapter(mAdapter);
                    rcv.setLayoutManager(new LinearLayoutManager(
                            Contextor.getsInstance().getContext()
                    ));
                } else {
                    showToast(response.errorBody().toString());
                }
            }

            @Override
            public void onFailure(Call<PhotoItemCollectionDao> call,
                                  Throwable t) {
                showToast(t.toString());
            }
        });
    }


    /*********************************
     *  Listener Zone
     */



}
