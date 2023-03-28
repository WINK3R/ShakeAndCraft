package com.example.shakecraft.data;
import androidx.room.Dao;
import androidx.room.Query;
import com.example.shakecraft.model.Boss;
import java.util.List;
import kotlinx.coroutines.flow.Flow;

@Dao
interface BossDao {
    @Query("SELECT * from Boss ")
    fun getBoss(): Flow<List<Boss>>

    @Query("SELECT * from Boss WHERE name = :name")
    fun getBoss(name : String): Flow<Boss>
}
