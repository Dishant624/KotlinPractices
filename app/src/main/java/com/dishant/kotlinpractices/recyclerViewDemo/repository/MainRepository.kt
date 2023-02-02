package com.dishant.kotlinpractices.recyclerViewDemo.repository

import com.dishant.kotlinpractices.recyclerViewDemo.utils.NetworkResponse
import com.dishant.kotlinpractices.recyclerViewDemo.RecyclerViewModel
import com.dishant.kotlinpractices.recyclerViewDemo.utils.Operation
import com.dishant.kotlinpractices.recyclerViewDemo.utils.OperationEnum
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.util.UUID
import kotlin.jvm.internal.Intrinsics.Kotlin

class MainRepository {

    private val PAGE_SIZE: Int = 50
    private val tempList = arrayListOf<RecyclerViewModel>().apply {
        for (i in 0..PAGE_SIZE) {
            add(RecyclerViewModel(UUID.randomUUID().toString(), "Content $i"))
        }
    }

    fun fetchData(page: Int): Flow<NetworkResponse<ArrayList<RecyclerViewModel>>> = flow {
        emit(NetworkResponse.Loading(page != 1))

        kotlinx.coroutines.delay(2000L)

        try {
            if (page == 1) {
                emit(NetworkResponse.Success(tempList.toList() as ArrayList<RecyclerViewModel>))
            }

            val tempPaginationList = arrayListOf<RecyclerViewModel>().apply {
                for (i in 0..PAGE_SIZE) {
                    add(RecyclerViewModel(UUID.randomUUID().toString(), "Content ${i * page}"))
                }
            }

            if (page <= 4) {
                emit(NetworkResponse.Success(tempPaginationList, isPaginating = true))
            } else {
                emit(NetworkResponse.Failure("Pagination failed", isPaginatingError = true))
            }

        } catch (e: Exception) {
            emit(NetworkResponse.Failure(e.message ?: e.toString(), isPaginatingError = page != 1))
        }
    }.flowOn(Dispatchers.IO)


    fun deleteData(item: RecyclerViewModel): Flow<NetworkResponse<Operation<RecyclerViewModel>>> =
        flow {
            emit(NetworkResponse.Loading())

            kotlinx.coroutines.delay(1000L)

            try {
                emit(NetworkResponse.Success(Operation(item, OperationEnum.Delete)))
            } catch (e: Exception) {
                emit(NetworkResponse.Failure(e.message ?: e.toString()))
            }
        }.flowOn(Dispatchers.IO)

    fun updateData(item: RecyclerViewModel): Flow<NetworkResponse<Operation<RecyclerViewModel>>> =
        flow {
            emit(NetworkResponse.Loading())

            kotlinx.coroutines.delay(1000L)

            try {
                item.content = "Update Content ${(0..100).random()}"
                emit(NetworkResponse.Success(Operation(item, OperationEnum.Update)))
            } catch (e: Exception) {
                emit(NetworkResponse.Failure(e.message ?: e.toString()))
            }
        }

    fun toggleLikeData(item: RecyclerViewModel): Flow<NetworkResponse<Operation<RecyclerViewModel>>> =
        flow {
            emit(NetworkResponse.Loading())

            kotlinx.coroutines.delay(1000L)

            try{
                item.isLiked = !item.isLiked
                emit((NetworkResponse.Success(Operation(item, OperationEnum.Update))))
            }catch (e :Exception){
                emit(NetworkResponse.Failure(e.message ?: e.toString()))
            }
        }


}