package com.kelompokempat.githubapi.auth.entity;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface UsersDAO {
    @Insert
    Long insertData(DataUsers dataUsers);

    @Query("SELECT * FROM users_db")
    List<DataUsers> getAllData();

    @Query("SELECT * FROM users_db WHERE id = :id")
    DataUsers selectUser(int id);

    @Update
    int updateData(DataUsers user);

    @Delete
    void deleteData(DataUsers user);
}
