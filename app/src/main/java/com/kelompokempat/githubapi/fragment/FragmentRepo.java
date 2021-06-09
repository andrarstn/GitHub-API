package com.kelompokempat.githubapi.fragment;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.kelompokempat.githubapi.R;
import com.kelompokempat.githubapi.adapter.RepoAdapter;
import com.kelompokempat.githubapi.model.search.ModelSearchData;
import com.kelompokempat.githubapi.viewmodel.UserViewModel;

public class FragmentRepo extends Fragment {

    ModelSearchData modelSearchData;
    UserViewModel repoViewModel;
    RepoAdapter repoAdapter;
    RecyclerView rvListRepo;
    ConstraintLayout layoutEmpty;
    ProgressDialog progressDialog;
    String strUsername;

    public FragmentRepo() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_repo, container, false);

        progressDialog = new ProgressDialog(getContext());
        progressDialog.setTitle("Mohon Tunggu...");
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Sedang menampilkan data");

        rvListRepo = view.findViewById(R.id.rvListRepo);
        layoutEmpty = view.findViewById(R.id.layoutEmpty);

        modelSearchData = this.getArguments().getParcelable("modelSearchData");
        if (modelSearchData != null) {
            strUsername = modelSearchData.getLogin();
        }

        repoAdapter = new RepoAdapter(getContext());
        rvListRepo.setLayoutManager(new LinearLayoutManager(getContext()));
        rvListRepo.setAdapter(repoAdapter);
        rvListRepo.setHasFixedSize(true);

        //method set viewmodel
        repoViewModel = new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(UserViewModel.class);
        repoViewModel.setRepoUser(strUsername);
        progressDialog.show();
        repoViewModel.getRepoUser().observe(getViewLifecycleOwner(), modelRepo -> {
            if (modelRepo.size() != 0) {
                layoutEmpty.setVisibility(View.GONE);
                repoAdapter.setRepoList(modelRepo);
            } else {
                layoutEmpty.setVisibility(View.VISIBLE);
                Toast.makeText(getContext(), "Repo Tidak Ditemukan!", Toast.LENGTH_SHORT).show();
            }
            progressDialog.dismiss();
        });

        return view;
    }

}