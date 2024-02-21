package com.example.a210223homeworkforfiveweeks.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.a210223homeworkforfiveweeks.models.MovieListModel

@Dao
interface MovieListDao {
    //suspend команда для принудительной остановки карутин

    @Insert
    suspend fun insertMovieList(movie_listModel: MovieListModel)
    //метод создания записи в базу данных

    @Update
    suspend fun updateMovieList(movie_listModel: MovieListModel)
    //метод обновления записи в базу данных

    @Delete
    suspend fun deleteMovieList(movie_listModel: MovieListModel)
    //метод удаления записи в базе данных

    @Query("DELETE FROM movie_list_data_table")
    suspend fun deleteAllMovieList()

    @Query("SELECT * FROM movie_list_data_table")
    fun getAllMovieList(): LiveData<List<MovieListModel>>
    //метод удаление всех записей в базе данных

    @Query("SELECT * FROM movie_list_data_table WHERE movie_time = :MovieTime OR movie_rating = :MovieRating")
    //значения movie_time и movie_rating мерем из файла MovieListModel.
    // а значение после знака равно прописываем в строчке ниже
    fun getFilter(MovieTime:String, MovieRating:String): LiveData<List<MovieListModel>>
    //метод получение выбранных записей в базе данных


    // @Query("SELECT * FROM product_data_table WHERE MovieList_time = '120' AND MovieList_rating = '2'")
    //fun getClothes(): LiveData<List<ProductModel>>

    // @Query("SELECT * FROM product_data_table WHERE product_category = :nameCategory OR product_price = :price")
    //fun getThreeVariant(nameCategory:String, price:String): LiveData<List<ProductModel>>


}