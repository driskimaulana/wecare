package com.kelompok4.wecare.view.elder;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.zxing.BarcodeFormat;
import com.journeyapps.barcodescanner.BarcodeEncoder;
import com.kelompok4.wecare.R;
import com.kelompok4.wecare.databinding.FragmentElderQrCodeBinding;
import com.kelompok4.wecare.model.user.User;
import com.kelompok4.wecare.view.MainActivity;
import com.kelompok4.wecare.viewmodel.utils.GsonUtils;

public class ElderQrCodeFragment extends Fragment {
    private FragmentElderQrCodeBinding binding;
    private User currentUser;



    public ElderQrCodeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) { super.onCreate(savedInstanceState); }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentElderQrCodeBinding.inflate(inflater);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Bundle bundle = getActivity().getIntent().getExtras();

//        get current logged in userW
        currentUser = GsonUtils.getGson().fromJson(bundle.getString("USER_LOGGED_IN"), User.class);

        String urlConnect = currentUser.getId();
        Toast.makeText(getActivity(), urlConnect, Toast.LENGTH_SHORT).show();
        try {
            BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
            Bitmap bitmap = barcodeEncoder.encodeBitmap(urlConnect,
                    BarcodeFormat.QR_CODE, 400, 400);
            ImageView imageViewQrCode = (ImageView) view.findViewById(R.id.img_qrcode);
            imageViewQrCode.setImageBitmap(bitmap);
            imageViewQrCode.setClipToOutline(true);
        } catch(Exception e) {
            e.printStackTrace();
        }

        binding.btnRestart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                getActivity().startActivity(intent);
                Runtime.getRuntime().exit(0);
            }
        });
    }
}