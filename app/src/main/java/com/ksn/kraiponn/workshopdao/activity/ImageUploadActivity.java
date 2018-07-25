package com.ksn.kraiponn.workshopdao.activity;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;
import com.ksn.kraiponn.workshopdao.R;
import com.ksn.kraiponn.workshopdao.dao.ImageUploadDao;
import com.ksn.kraiponn.workshopdao.manager.ImageHttpManager;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ImageUploadActivity extends AppCompatActivity {
    private ImageView imgPreview;
    private EditText edtTitle;
    private Button btnSelect;
    private Button btnUpload;

    private final int IMAGE_REQUEST_KSN = 1809;
    private Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_upload);

        if (Build.VERSION.SDK_INT >= 21) {
            getRuntimePermission();
        } else {
            //
        }
        initInstance();
    }

    private void initInstance() {
        imgPreview = findViewById(R.id.imgPreview);
        edtTitle = findViewById(R.id.edtTitle);
        btnSelect = findViewById(R.id.btnSelect);
        btnUpload = findViewById(R.id.btnUpload);

        btnSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectImage();
            }
        });

        btnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadImage();
            }
        });
    }



    private void getRuntimePermission() {
        Dexter.withActivity(this)
                .withPermissions(
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.CAMERA
                )
                .withListener(new MultiplePermissionsListener() {
                    @Override
                    public void onPermissionsChecked(MultiplePermissionsReport report) {
                        //
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions,
                                                                   PermissionToken token) {
                        token.continuePermissionRequest();
                    }
                }).check();
    }

    private void uploadImage() {
        String image = imageToString();
        String title = edtTitle.getText().toString().trim();

        Call<ImageUploadDao> call = ImageHttpManager.getsInstance()
                .getService().uploadImage(title, image);
        call.enqueue(new Callback<ImageUploadDao>() {
            @Override
            public void onResponse(Call<ImageUploadDao> call, Response<ImageUploadDao> response) {
                if (response.isSuccessful()) {
                    ImageUploadDao dao = response.body();
                    showToast(dao.getResponse());

                    edtTitle.setText("");
                    edtTitle.setVisibility(View.GONE);
                    imgPreview.setVisibility(View.GONE);
                    btnSelect.setEnabled(true);
                    btnUpload.setEnabled(false);
                }else{
                    showToast(response.errorBody().toString());
                    btnSelect.setEnabled(true);
                    btnUpload.setEnabled(false);
                }
            }

            @Override
            public void onFailure(Call<ImageUploadDao> call, Throwable t) {
                showToast(t.toString());
            }
        });
    }

    private void showToast(String text) {
        Toast.makeText(this,
                text, Toast.LENGTH_SHORT).show();
    }

    private String imageToString() {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        byte[] imgContent = stream.toByteArray();

        return Base64.encodeToString(imgContent, Base64.DEFAULT);
    }


    private void selectImage() {
        Intent itn = new Intent();
        itn.setType("image/*");
        itn.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(itn,IMAGE_REQUEST_KSN);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //Log.d("REQUEST IMAGE", "Result ....." + resultCode + "  :  " + requestCode);

        if ((requestCode == IMAGE_REQUEST_KSN) && (resultCode == RESULT_OK)) {
            //Log.d("REQUEST IMAGE", "Result IF.....");
            Uri path = data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(
                        getContentResolver(), path);
                imgPreview.setImageBitmap(bitmap);
                imgPreview.setVisibility(View.VISIBLE);
                edtTitle.setVisibility(View.VISIBLE);
                btnUpload.setEnabled(true);
                btnSelect.setEnabled(false);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
