package com.ksn.kraiponn.workshopdao.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ksn.kraiponn.workshopdao.R;
import com.ksn.kraiponn.workshopdao.dao.UserListItemDao;
import com.ksn.kraiponn.workshopdao.manager.Contextor;
import com.ksn.kraiponn.workshopdao.manager.HttpUserListManager;
import com.ksn.kraiponn.workshopdao.manager.http.ApiService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginFragment extends Fragment {
    private EditText edtUserName;
    private EditText edtPassword;
    private Button btnConfirmLogIn;
    private String mUserName;
    private String mPassword;

    public static LoginFragment newInstance() {
        LoginFragment fm = new LoginFragment();
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
                R.layout.fragment_login,
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
        edtPassword = view.findViewById(R.id.edtPassword);
        edtUserName = view.findViewById(R.id.edtUserName);
        btnConfirmLogIn = view.findViewById(R.id.btnConfirmLogin);

        btnConfirmLogIn.setOnClickListener(btnConfirmLogInClickListener);
    }


    /*********************************
     *  Listener Zone
     */
    View.OnClickListener btnConfirmLogInClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            feedLogIn();
        }
    };


    /*********************************
     *  Method Zone
     */
    private void feedLogIn() {
        mUserName = edtUserName.getText().toString().trim();
        mPassword = edtPassword.getText().toString().trim();

        Call<UserListItemDao> call =
                HttpUserListManager.getsInstance().getService()
                        .feedLogIn(mUserName, mPassword, "requestLogin");
        call.enqueue(new Callback<UserListItemDao>() {
            @Override
            public void onResponse(Call<UserListItemDao> call,
                                   Response<UserListItemDao> response) {
                if (response.isSuccessful()) {
                    UserListItemDao dao = response.body();
                    String str = "";
                    str = "error: " + dao.isErr() + "\n";
                    str += "err_msg: " + dao.getErrMsg() + "\n";
                    str += "Success_msg: " + dao.getSuccessMsg();
                    showToast(str);
                }else {
                    showToast(response.errorBody().toString());
                }
            }

            @Override
            public void onFailure(Call<UserListItemDao> call,
                                  Throwable t) {
                showToast(t.toString());
            }
        });
    }


}
