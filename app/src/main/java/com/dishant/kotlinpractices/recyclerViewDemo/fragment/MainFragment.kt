package com.dishant.kotlinpractices.recyclerViewDemo.fragment

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.icu.text.Transliterator.Position
import android.nfc.tech.MifareUltralight.PAGE_SIZE
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dishant.kotlinpractices.R
import com.dishant.kotlinpractices.databinding.FragmentMainBinding
import com.dishant.kotlinpractices.recyclerViewDemo.utils.NetworkResponse
import com.dishant.kotlinpractices.recyclerViewDemo.adapter.RecyclerViewAdapter
import com.dishant.kotlinpractices.recyclerViewDemo.RecyclerViewModel
import com.dishant.kotlinpractices.recyclerViewDemo.`interface`.Interaction
import com.dishant.kotlinpractices.recyclerViewDemo.`interface`.RecyclerViewInteraction
import com.dishant.kotlinpractices.recyclerViewDemo.utils.quickToScrollToTop
import com.dishant.kotlinpractices.recyclerViewDemo.viewmodel.MainViewModel
import kotlinx.coroutines.launch

class MainFragment : BaseFragment<FragmentMainBinding>() {

    private lateinit var viewModel: MainViewModel
    private var recyclerViewAdapter: RecyclerViewAdapter? = null
    private var loadingDialog: Dialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setDialog(view.context)
        setObserver()
        setRecyclerView()
        setListeners()

    }

    private fun setRecyclerView() {
        binding.mainRV.apply {
            val linearLayoutManager = LinearLayoutManager(context)
            layoutManager = linearLayoutManager
            addItemDecoration(DividerItemDecoration(context,linearLayoutManager.orientation))
            recyclerViewAdapter = RecyclerViewAdapter(interaction = object : Interaction<RecyclerViewModel>{
                override fun onItemSelected(item: RecyclerViewModel , position: Int) {
                    Toast.makeText(context,"Item $position ${item.content}", Toast.LENGTH_SHORT).show()
                }

                override fun onLongPressed(item: RecyclerViewModel) {

                }

                override fun onErrorRefreshPressed() {
                    viewModel.refreshData()
                }

                override fun onExhaustButtonPressed() {
                    viewLifecycleOwner.lifecycleScope.launch {
                        binding.mainRV.quickToScrollToTop()
                    }
                }
            }, extraInteraction = object : RecyclerViewInteraction<RecyclerViewModel>{
                override fun onUpdatePressed(item: RecyclerViewModel) {
                    viewModel.updateData(item)
                }

                override fun onDeletePressed(item: RecyclerViewModel) {
                   viewModel.deleteData(item)
                }

                override fun onLikePressed(item: RecyclerViewModel) {
                    viewModel.toggleData(item)
                }
            }
            )

            adapter = recyclerViewAdapter

            var isScrolling =false
            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                    super.onScrollStateChanged(recyclerView, newState)
                    isScrolling = newState != AbsListView.OnScrollListener.SCROLL_STATE_IDLE
                }

                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)

                    val itemCount = linearLayoutManager.itemCount
                    val lastVisibleItemPosition = linearLayoutManager.findLastVisibleItemPosition()

                    if (lastVisibleItemPosition > PAGE_SIZE.plus(PAGE_SIZE.div(2)) && dy <= -75) {
                        binding.fab.show()
                    } else if (lastVisibleItemPosition <= PAGE_SIZE.plus(PAGE_SIZE.div(2)) || dy >= 60) {
                        binding.fab.hide()
                    }

                    recyclerViewAdapter?.let {
                        if (
                            isScrolling &&
                            lastVisibleItemPosition >= itemCount.minus(5) &&
                            it.canPaginating &&
                            !it.isPaginating
                        ) {
                            viewModel.fetchData()
                        }
                    }


                }
            })
        }


    }

    private fun setObserver() {
        viewModel.rvList.observe(viewLifecycleOwner) { response ->
            binding.swipeRefreshLayout.isEnabled = when (response) {
                is NetworkResponse.Success -> {
                    true
                }
                is NetworkResponse.Failure -> {
                    response.isPaginatingError
                }
                else -> {
                    false
                }
            }

            when (response) {
                is NetworkResponse.Failure -> {
                    recyclerViewAdapter?.setErrorView(
                        response.errorMessage,
                        response.isPaginatingError
                    )
                }
                is NetworkResponse.Loading -> {
                    recyclerViewAdapter?.setLoadingView(response.isPaginating)
                }
                is NetworkResponse.Success -> {
                    recyclerViewAdapter?.setData(response.data, response.isPaginating)
                }
            }
        }

        viewModel.rvOperation.observe(viewLifecycleOwner){ response ->

            when(response){
                is NetworkResponse.Failure ->{
                    if(loadingDialog?.isShowing == true){
                        loadingDialog?.dismiss()
                    }
                }

                is NetworkResponse.Loading ->{
                    if(recyclerViewAdapter?.isLoading == false)
                        loadingDialog?.show()
                }

                is NetworkResponse.Success ->{
                    if(loadingDialog?.isShowing ==true){
                        loadingDialog?.dismiss()
                    }
                    recyclerViewAdapter?.handleOperation(response.data)
                }
            }

        }
    }

    private fun setListeners() {
        binding.swipeRefreshLayout.setOnRefreshListener {
            viewModel.refreshData()
            binding.swipeRefreshLayout.isRefreshing = false
        }

        binding.errorButton.setOnClickListener {
            viewModel.throwError()
        }

        binding.fab.setOnClickListener {
            viewLifecycleOwner.lifecycleScope.launch {
                    binding.mainRV.quickToScrollToTop()
            }
        }

        binding.paginateErrorButton.setOnClickListener {
            viewModel.exhaustPagination()
        }

        binding.appendButton.setOnClickListener {
            if(recyclerViewAdapter?.canPaginating == true && recyclerViewAdapter?.isPaginating == false){
                    viewModel.fetchData()
            }

            binding.mainRV.scrollToPosition(recyclerViewAdapter?.itemCount ?: 0)
        }


    }

    private fun setDialog(context: Context) {
        loadingDialog = Dialog(context)
        loadingDialog?.setCancelable(false)
        loadingDialog?.setContentView(R.layout.loading_dialog)
        loadingDialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }


}