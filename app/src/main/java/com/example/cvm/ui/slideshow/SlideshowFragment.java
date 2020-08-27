package com.example.cvm.ui.slideshow;

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
import com.example.cvm.History;
import com.example.cvm.LocalDB;
import com.example.cvm.R;

import java.util.ArrayList;
//History.
public class SlideshowFragment extends Fragment {

    private SlideshowViewModel slideshowViewModel;
    LocalDB localDB;
    DataBase db;
    CurrentUser currentUser;
    ArrayList<History> history;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        slideshowViewModel =
                ViewModelProviders.of(this).get(SlideshowViewModel.class);
        View root = inflater.inflate(R.layout.fragment_slideshow, container, false);

        /*final TextView textView = root.findViewById(R.id.text_slideshow);
        slideshowViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });*/
        TextView textView = root.findViewById(R.id.text_history);
        localDB = new LocalDB(this.getContext());
        currentUser=localDB.isExists();
        db = new DataBase(this.getContext());
        history = db.getHistory(currentUser.getPhone());
        textView.setText(currentUser.getPhone());
        ArrayList<String>carNumber=getCarNumber(history);
        ArrayList<String>date=getDate(history);
        ArrayList<String>amount=getAmount(history);
        return root;
    }
    private ArrayList<String> getCarNumber(ArrayList<History> history){
        ArrayList<String> carnumber=new ArrayList<String>();
        for(int i = 0;i<history.size();i++){
            carnumber.add(history.get(i).getCarNumber());
        }
        return carnumber;
    }
    private ArrayList<String> getDate(ArrayList<History> history){
        ArrayList<String> carnumber=new ArrayList<String>();
        for(int i = 0;i<history.size();i++){
            carnumber.add(history.get(i).getDate());
        }
        return carnumber;
    }
    private ArrayList<String> getAmount(ArrayList<History> history){
        ArrayList<String> carnumber=new ArrayList<String>();
        for(int i = 0;i<history.size();i++){
            carnumber.add(history.get(i).getAmount());
        }
        return carnumber;
    }
}