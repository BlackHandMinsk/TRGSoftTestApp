package com.example.catsapi.ui

import androidx.core.content.ContextCompat.getSystemService
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.ExperimentalPagingApi
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import androidx.test.core.app.ApplicationProvider
import com.example.catsapi.NetworkResult
import com.example.catsapi.data.CatImagesRepository
import com.example.catsapi.data.Repository
import com.example.catsapi.model.CatImageModel
import com.foodrecipesapp.data.database.entities.CatsEntity
import com.foodrecipesapp.data.database.entities.FavoritesEntity
import dagger.hilt.android.internal.Contexts.getApplication
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject
import androidx.test.core.app.ApplicationProvider.getApplicationContext
import java.lang.Exception


@ExperimentalPagingApi
@HiltViewModel
class MainViewModel @Inject constructor(
  private  val repository: Repository
) : ViewModel() {

//    fun fetchCatGoImages(): Flow<PagingData<String>> {
//        return repository.letCatGoImages()
//            .map { it.map { it.url.toString() } }
//            .cachedIn(viewModelScope)
//    }


//    //Room
//    val readCats: Flow<List<CatsEntity>> = repository.local.readCats()
//    //Retrofit
//    val catsResponse: MutableStateFlow<NetworkResult<String>> = MutableStateFlow(NetworkResult.Error("Loading"))



//    private fun offlineCacheCat(url: CatImageModel) {
//        val catEntity = CatsEntity(url)
//        insertCats(catEntity)
//    }
//
//    private fun insertCats(catsEntity: CatsEntity) =
//        viewModelScope.launch(Dispatchers.IO) {
//           repository.local.insertCats(catsEntity)
//        }




    fun deleteAllFavoriteCats() = viewModelScope.launch(Dispatchers.IO) {
     repository.local.deleteAllFavoriteCats()
    }


    fun fetchCatGoImages(): Flow<PagingData<String>> {
        return repository.remote.letCatGoImages()
            .map {
                it.map {
                    it.url.toString() } }
            .cachedIn(viewModelScope)
    }
}