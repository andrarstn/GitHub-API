package com.kelompokempat.githubapi.fragment.main;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;
import com.kelompokempat.githubapi.MainActivity;
import com.kelompokempat.githubapi.R;
import com.kelompokempat.githubapi.auth.entity.DataUsers;
import com.kelompokempat.githubapi.auth.entity.UsersAppDatabase;
import com.kelompokempat.githubapi.auth.entity.UsersDAO;
import com.kelompokempat.githubapi.auth.view.WelcomeActivity;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileFragment extends Fragment {
    private TextView tvFullName,tvUsername;
    private Button btnUpdate, btnSignOut, btnDelete;
    private TextInputEditText etusername, etpassword;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ProfileFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProfileFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        SharedPreferences getPreferences = this.getActivity().getSharedPreferences("SAVED_LOGIN", Context.MODE_PRIVATE);
        String username = getPreferences.getString("USERNAME", "DEFAULT");
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        UsersAppDatabase usersAppDatabase = UsersAppDatabase.inidb(this.getContext());
        UsersDAO usersDAO = usersAppDatabase.usersDAO();
        DataUsers dataUsers = usersDAO.checkUser(username);

        tvFullName = view.findViewById(R.id.tv_fullname_profilefragment);
        tvFullName.setText(dataUsers.getName());

        tvUsername = view.findViewById(R.id.tv_username_profilefragment);
        tvUsername.setText(dataUsers.getUsername());

        btnDelete = view.findViewById(R.id.btn_delete_fragmentprofile);
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                usersDAO.deleteSingleData(username);
                SharedPreferences.Editor editor = getPreferences.edit();
                editor.putBoolean("LOGGED",false);
                editor.putString("USERNAME","DEFAULT");
                editor.apply();

                Intent intent = new Intent(v.getContext(), WelcomeActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });

        btnSignOut = view.findViewById(R.id.btn_signout_fragmentprofile);
        btnSignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = getPreferences.edit();
                editor.putBoolean("LOGGED",false);
                editor.putString("USERNAME","DEFAULT");
                editor.apply();

                Intent intent = new Intent(v.getContext(), WelcomeActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });

        return view;
    }
}