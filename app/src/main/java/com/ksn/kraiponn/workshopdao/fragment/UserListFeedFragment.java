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
import android.widget.TextView;
import android.widget.Toast;

import com.ksn.kraiponn.workshopdao.R;
import com.ksn.kraiponn.workshopdao.adapter.PhotoItemAdapter;
import com.ksn.kraiponn.workshopdao.dao.PhotoItemCollectionDao;
import com.ksn.kraiponn.workshopdao.dao.UserListCollectionDao;
import com.ksn.kraiponn.workshopdao.manager.Contextor;
import com.ksn.kraiponn.workshopdao.manager.HttpPhotoItemManager;
import com.ksn.kraiponn.workshopdao.manager.HttpUserListManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserListFeedFragment extends Fragment {
    private RecyclerView rcv;
    private TextView tvResult;


    public static UserListFeedFragment newInstance() {
        UserListFeedFragment fm = new UserListFeedFragment();
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
                R.layout.fragment_user_list,
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
        tvResult = view.findViewById(R.id.text_result);

        Call<UserListCollectionDao> call =
                HttpUserListManager.getsInstance().getService().feedUserList();
        call.enqueue(new Callback<UserListCollectionDao>() {
            @Override
            public void onResponse(Call<UserListCollectionDao> call,
                                   Response<UserListCollectionDao> response) {
                if (response.isSuccessful()) {
                    UserListCollectionDao dao = response.body();
                    showToast(dao.getData().get(0).getUserName());
                    String str = "";
                    for (int i=0; i<dao.getData().size(); i++) {
                        str += dao.getData().get(i).getUserId() + "\n";
                        str += dao.getData().get(i).getUserName() + "\n";
                        str += dao.getData().get(i).getEmail() + "\n";
                        str += dao.getData().get(i).getGender() + "\n";
                        str += dao.getErrMsg() + "\n";
                        str += dao.isError() + "\n";
                        str += "\n------------------------\n";
                    }

                    tvResult.setText(str);
                } else {
                    showToast(response.errorBody().toString());
                }
            }

            @Override
            public void onFailure(Call<UserListCollectionDao> call,
                                  Throwable t) {
                showToast("Throw" + t.toString());
            }
        });
    }


    /*********************************
     *  Listener Zone
     */



}
