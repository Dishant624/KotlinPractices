package com.dishant.kotlinpractices.recyclerViewDemo.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.viewModelScope
import com.dishant.kotlinpractices.recyclerViewDemo.RecyclerViewModel
import com.dishant.kotlinpractices.recyclerViewDemo.repository.MainRepository
import com.dishant.kotlinpractices.recyclerViewDemo.utils.NetworkResponse
import com.dishant.kotlinpractices.recyclerViewDemo.utils.Operation
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel : ViewModel() {

    private val repository = MainRepository()

    private val _rvList = MutableLiveData<NetworkResponse<ArrayList<RecyclerViewModel>>>()
    val rvList : LiveData<NetworkResponse<ArrayList<RecyclerViewModel>>> = _rvList

    private val _rvOperation = MutableLiveData<NetworkResponse<Operation<RecyclerViewModel>>>()
    val rvOperation: LiveData<NetworkResponse<Operation<RecyclerViewModel>>> = _rvOperation

    private var page = 1

    init {
        fetchData()
    }

     fun fetchData()  = viewModelScope.launch (Dispatchers.IO){
        repository.fetchData(page).collect{ state ->
            withContext(Dispatchers.Main){
                _rvList.value = state

                if(state is NetworkResponse.Success){
                    page +=1
                }
            }
        }
    }

    fun refreshData(){
        page = 1
        fetchData()
    }

    fun deleteData(item:RecyclerViewModel) = viewModelScope.launch(Dispatchers.IO) {
        repository.deleteData(item).collect{ state ->
            withContext(Dispatchers.Main){
                _rvOperation.value = state
            }
        }
    }

    fun updateData(item: RecyclerViewModel) = viewModelScope.launch(Dispatchers.IO) {
        repository.updateData(item).collect{ state ->
            withContext(Dispatchers.Main){
                _rvOperation.value = state
            }

        }
    }

    fun toggleData(item: RecyclerViewModel) = viewModelScope.launch(Dispatchers.IO) {
        repository.toggleLikeData(item).collect{ state ->
            withContext(Dispatchers.Main){
                _rvOperation.value = state
            }

        }
    }

    fun throwError()  = viewModelScope.launch(Dispatchers.Main) {
        _rvList.value = NetworkResponse.Failure("Error occurred!")
    }

    fun exhaustPagination()  = viewModelScope.launch(Dispatchers.Main) {
        _rvList.value = NetworkResponse.Failure(
            "Error Occurred",
            isPaginatingError = true,

        )
    }


}