package com.dishant.kotlinpractices.recyclerViewDemo.adapter

import android.annotation.SuppressLint
import androidx.recyclerview.widget.RecyclerView
import com.dishant.kotlinpractices.recyclerViewDemo.`interface`.Interaction
import com.dishant.kotlinpractices.recyclerViewDemo.viewHolder.ErrorViewHolderBind
import com.dishant.kotlinpractices.recyclerViewDemo.viewHolder.ItemViewHolderBind
import com.dishant.kotlinpractices.recyclerViewDemo.utils.Operation
import com.dishant.kotlinpractices.recyclerViewDemo.utils.OperationEnum
import com.dishant.kotlinpractices.recyclerViewDemo.utils.RecyclerViewEnum
import com.dishant.kotlinpractices.recyclerViewDemo.viewHolder.PaginationExhaustViewBind


@SuppressLint("NotifyDataSetChanged")
abstract class BaseAdapter<T>(open val interaction: Interaction<T>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var errorMessage: String? = null
    var isLoading = true
    var isPaginating = false
    var canPaginating = true

    protected var arrayList: ArrayList<T> = arrayListOf()

    protected abstract fun handleDiffUtil(newList: ArrayList<T>)

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (getItemViewType(position)) {
            RecyclerViewEnum.View.value ->{
                (holder as ItemViewHolderBind<T>).bind(arrayList[position],position,interaction)
            }
            RecyclerViewEnum.Error.value ->{
                (holder as ErrorViewHolderBind<T>).bind(errorMessage,interaction)
            }
            RecyclerViewEnum.PaginationExhaust.value -> {
                (holder as PaginationExhaustViewBind<T>).bind(interaction)
            }
        }
    }


    override fun getItemViewType(position: Int): Int {
        return if (isLoading)
            RecyclerViewEnum.Loading.value
        else if (errorMessage != null)
            RecyclerViewEnum.Error.value
        else if (isPaginating && position == arrayList.size)
            RecyclerViewEnum.PaginationLoading.value
        else if (!canPaginating && position == arrayList.size)
            RecyclerViewEnum.PaginationExhaust.value
        else if (arrayList.isEmpty())
            RecyclerViewEnum.Empty.value
        else
            RecyclerViewEnum.View.value
    }

    override fun getItemCount(): Int {
        return if (isLoading || errorMessage != null || arrayList.isEmpty())
            1
        else {
            if (arrayList.isNotEmpty() && !isPaginating && canPaginating) {
                arrayList.size
            } else {
                arrayList.size.plus(1)
            }
        }
    }

    fun setErrorView(errorMessage : String, isPaginationError : Boolean){
        if(isPaginationError){
            setState(RecyclerViewEnum.PaginationExhaust)
            notifyItemInserted(itemCount)
        }else{
            setState(RecyclerViewEnum.Error)
            this.errorMessage = errorMessage
            notifyDataSetChanged()
        }
    }

    fun setLoadingView(isPaginating : Boolean){
        if(isPaginating){
            setState(RecyclerViewEnum.PaginationLoading)
            notifyItemInserted(itemCount)
        }else{
            setState(RecyclerViewEnum.Loading)
            notifyDataSetChanged()
        }
    }

    fun handleOperation(operation : Operation<T>){
        val newList = arrayList.toMutableList()
        var index: Int =-1
        when(operation.operationEnum){
            OperationEnum.Insert ->{
                newList.add(operation.data)
            }
            OperationEnum.Delete ->{
                newList.remove(operation.data)
            }
            OperationEnum.Update ->{
                index = newList.indexOfFirst {
                    it == operation.data
                }
                newList[index] = operation.data
            }
        }
        handleDiffUtil(newList as ArrayList<T>)
        if(index!= -1){
            notifyItemChanged(index)
        }
    }

    fun setData(newList : ArrayList<T> ,isPaginationData : Boolean =false){
        setState(RecyclerViewEnum.View)

        if(!isPaginationData){
            if(arrayList.isNotEmpty()){
                arrayList.clear()
            }
            arrayList.addAll(newList)
            notifyDataSetChanged()
        }else{
            notifyItemRemoved(itemCount)
            newList.addAll(0,arrayList)
            handleDiffUtil(newList)
        }
    }

    private fun setState(recyclerViewEnum: RecyclerViewEnum) {
        when(recyclerViewEnum){
            RecyclerViewEnum.Empty ->{
                isLoading = false
                isPaginating = false
                errorMessage = null
            }
            RecyclerViewEnum.Loading ->{
                isLoading = true
                isPaginating=false
                errorMessage = null
                canPaginating= true
            }
            RecyclerViewEnum.Error ->{
                isLoading = false
                isPaginating = false
            }
            RecyclerViewEnum.View ->{
                isLoading = false
                isPaginating = false
                errorMessage = null
            }
            RecyclerViewEnum.PaginationLoading ->{
                isLoading= false
                isPaginating = true
                errorMessage = null
            }
            RecyclerViewEnum.PaginationExhaust ->{
                isLoading= false
                isPaginating= false
                canPaginating = false
            }
        }
    }
}