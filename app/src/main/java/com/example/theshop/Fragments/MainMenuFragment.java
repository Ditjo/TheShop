package com.example.theshop.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.theshop.Activities.ContactActivity;
import com.example.theshop.Activities.MainActivity;
import com.example.theshop.Activities.MissionActivity;
import com.example.theshop.Activities.ProfileActivity;
import com.example.theshop.Activities.SettingsActivity;
import com.example.theshop.Activities.ShopActivity;
import com.example.theshop.R;

public class MainMenuFragment extends Fragment {

    private View context;

    private Button btn_home, btn_mission, btn_shop, btn_contacts, btn_userProfiler;
    ImageView iv_settings;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        context = inflater.inflate(R.layout.main_menu_fragment, container, false);

        initGui();
        return context;
    }

    void initGui(){
        btn_home = context.findViewById(R.id.btn_home);
        btn_mission = context.findViewById(R.id.btn_mission);
        btn_shop = context.findViewById(R.id.btn_shop);
        btn_contacts = context.findViewById(R.id.btn_contacts);
        btn_userProfiler = context.findViewById(R.id.btn_userProfile);
        iv_settings = context.findViewById(R.id.iv_settings);

        btn_home.setOnClickListener(x -> onNavigateToActivity(MainActivity.class));
        btn_mission.setOnClickListener(x -> onNavigateToActivity(MissionActivity.class));
        btn_shop.setOnClickListener(x -> onNavigateToActivity(ShopActivity.class));
        btn_contacts.setOnClickListener(x -> onNavigateToActivity(ContactActivity.class));
        btn_userProfiler.setOnClickListener(x -> onNavigateToActivity(ProfileActivity.class));
        iv_settings.setOnClickListener(x -> onNavigateToActivity(SettingsActivity.class));

    }

    void onNavigateToActivity(Class<?> cls){
        Intent intent = new Intent(context.getContext(), cls);
//        if (cls == MainActivity.class) {
//            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
//        }
        startActivity(intent);
//        getActivity().finish();
    }
}
