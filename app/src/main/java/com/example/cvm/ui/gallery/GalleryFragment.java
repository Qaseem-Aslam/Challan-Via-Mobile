package com.example.cvm.ui.gallery;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.cvm.CurrentUser;
import com.example.cvm.DataBase;
import com.example.cvm.LocalDB;
import com.example.cvm.R;
import com.example.cvm.Reward;

import java.util.ArrayList;

public class GalleryFragment extends Fragment {

    private GalleryViewModel galleryViewModel;

    LocalDB localDB;
    DataBase db;
    CurrentUser currentUser;
    ArrayList<Reward> reward;
    TextView textView;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        galleryViewModel =
                ViewModelProviders.of(this).get(GalleryViewModel.class);
        View root = inflater.inflate(R.layout.fragment_gallery, container, false);
        /*final TextView textView = root.findViewById(R.id.text_gallery);
        galleryViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });*/
        localDB = new LocalDB(this.getContext());
        currentUser=localDB.isExists();
        db = new DataBase(this.getContext());
        reward = db.getReward(currentUser.getPhone());
        textView=root.findViewById(R.id.text_reward);
        textView.setText(currentUser.getPhone());
        ArrayList<String>phone=getPhone(reward);
        ArrayList<String>status=getStatus(reward);
        ArrayList<String>amount=getAmount(reward);
        return root;
    }
    private ArrayList<String> getPhone(ArrayList<Reward> history){
        ArrayList<String> carnumber=new ArrayList<String>();
        for(int i = 0;i<history.size();i++){
            carnumber.add(history.get(i).getPhone());
        }
        return carnumber;
    }
    private ArrayList<String> getStatus(ArrayList<Reward> history){
        ArrayList<String> carnumber=new ArrayList<String>();
        for(int i = 0;i<history.size();i++){
            carnumber.add(history.get(i).getStatus());
        }
        return carnumber;
    }
    private ArrayList<String> getAmount(ArrayList<Reward> history){
        ArrayList<String> carnumber=new ArrayList<String>();
        for(int i = 0;i<history.size();i++){
            carnumber.add(history.get(i).getAmount());
        }
        return carnumber;
    }
}