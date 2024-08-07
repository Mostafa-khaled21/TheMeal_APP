package com.example.themeal_app.DatabaseModel.AllDatabase.Database



import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.themeal_app.DatabaseModel.AllDatabase.Dao.FavoriteRecipeDao
import com.example.themeal_app.DatabaseModel.model.Meal

@Database(entities = [Meal::class], version = 1)
abstract class FavoriteDatabase : RoomDatabase() {
    abstract fun favoriteRecipeDao(): FavoriteRecipeDao

    companion object {
        @Volatile
        private var INSTANCE: FavoriteDatabase? = null

        fun getDatabase(context: Context): FavoriteDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    FavoriteDatabase::class.java,
                    "app_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}