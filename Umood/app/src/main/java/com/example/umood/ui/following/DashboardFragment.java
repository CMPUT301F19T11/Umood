package com.example.umood.ui.following;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.umood.MainActivity;
import com.example.umood.R;
import com.example.umood.User;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class DashboardFragment extends Fragment {
    User user;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private DocumentReference docref;
    private CollectionReference collectionReference = db.collection("users");
    private static final String TAG = "yan";
    private ListView listView;
    ArrayAdapter<String> adapter;
    ArrayList<String> list;
    private ArrayList<String> following;

    Intent intent;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);

        //MainActivity activity = (MainActivity) getActivity();
        //user = activity.getUser();

        EditText editText = root.findViewById(R.id.fsearch);
        Button request = root.findViewById(R.id.request);
        Button follower = root.findViewById(R.id.follower);
        Button addFollowing = root.findViewById(R.id.addFollowing);
        listView = root.findViewById(R.id.friendList);


        list = new ArrayList<>();
        list.add("aFriend");
        list.add("cFriend");



        

        return root;
    }
}