package com.example.shoppinglistapp.di
import android.content.Context
import androidx.room.Room
import com.androiddevs.shoppinglisttestingyt.data.local.ShoppingItemDatabase
import com.example.shoppinglistapp.other.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideShoppingItemDB(@ApplicationContext context: Context): ShoppingItemDatabase {
        return Room.databaseBuilder(context, ShoppingItemDatabase::class.java, Constants.DATABASE_NAME).build()
    }

    @Provides
    @Singleton
    fun provideShoppingDao(database: ShoppingItemDatabase)
            = database.shoppingDao()
}