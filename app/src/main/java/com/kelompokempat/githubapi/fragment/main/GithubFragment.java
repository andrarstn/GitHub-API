package com.kelompokempat.githubapi.fragment.main;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.kelompokempat.githubapi.R;
import com.kelompokempat.githubapi.adapter.SearchAdapter;
import com.kelompokempat.githubapi.viewmodel.UserViewModel;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link GithubFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GithubFragment extends Fragment {
    private TextView tv;
    private SearchAdapter searchAdapter;
    private UserViewModel searchViewModel;
    private ProgressDialog progressDialog;
    private RecyclerView rvListUser;
    private EditText searchUser;
    private ImageView imageClear;
    private ConstraintLayout layoutEmpty;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public GithubFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment GithubFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static GithubFragment newInstance(String param1, String param2) {
        GithubFragment fragment = new GithubFragment();
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
        View view = inflater.inflate(R.layout.fragment_github, container, false);

        SharedPreferences getPreferences = this.getActivity().getSharedPreferences("SAVED_LOGIN", Context.MODE_PRIVATE);
        String username = getPreferences.getString("USERNAME", "DEFAULT");

        tv = view.findViewById(R.id.tv_Hello);
        tv.setText(username);

        searchUser = view.findViewById(R.id.searchUser);
        imageClear = view.findViewById(R.id.imageClear);
        rvListUser = view.findViewById(R.id.rvListUser);
        layoutEmpty = view.findViewById(R.id.layoutEmpty);

        progressDialog = new ProgressDialog(this.getActivity());
        progressDialog.setTitle("Mohon Tunggu...");
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Sedang menampilkan data");

        imageClear.setOnClickListener(v -> {
            searchUser.getText().clear();
            imageClear.setVisibility(View.GONE);
            layoutEmpty.setVisibility(View.VISIBLE);
            rvListUser.setVisibility(View.GONE);
        });

        //method action search
        searchUser.setOnEditorActionListener((v, actionId, event) -> {
            String strUsername = searchUser.getText().toString();
            if (strUsername.isEmpty()) {
                Toast.makeText(this.getContext(), "Form tidak boleh kosong!", Toast.LENGTH_SHORT).show();
            } else {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    progressDialog.show();
                    searchViewModel.setSearchUser(strUsername);
                    InputMethodManager inputMethodManager = (InputMethodManager) v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                    inputMethodManager.hideSoftInputFromWindow(v.getWindowToken(), 0);
                    imageClear.setVisibility(View.VISIBLE);
                    layoutEmpty.setVisibility(View.GONE);
                    return true;
                }
            }
            return false;
        });

        searchAdapter = new SearchAdapter(this.getContext());
        rvListUser.setLayoutManager(new LinearLayoutManager(this.getContext()));
        rvListUser.setAdapter(searchAdapter);
        rvListUser.setHasFixedSize(true);

        //method set viewmodel
        searchViewModel = new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(UserViewModel.class);
        searchViewModel.getResultList().observe(getViewLifecycleOwner(), modelSearchData -> {
            progressDialog.dismiss();
            if (modelSearchData.size() != 0) {
                searchAdapter.setSearchUserList(modelSearchData);
            } else {
                Toast.makeText(this.getContext(), "Pengguna Tidak Ditemukan!", Toast.LENGTH_SHORT).show();
            }
        });


        return view;
    }
}