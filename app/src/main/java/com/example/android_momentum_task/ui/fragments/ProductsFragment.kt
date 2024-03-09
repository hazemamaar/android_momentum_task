package com.example.android_momentum_task.ui.fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.android_momentum_task.databinding.FragmentProductsBinding
import com.example.android_momentum_task.ui.adapters.ProductAdapter
import com.example.android_momentum_task.ui.events.ProductsAction
import com.example.android_momentum_task.ui.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@AndroidEntryPoint
class ProductsFragment : Fragment() {
    private var _binding: FragmentProductsBinding? = null

    @Inject
    lateinit var productAdapter: ProductAdapter
    private val binding get() = _binding!!

    private val mViewModel: MainViewModel by viewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.spinKit.visibility =View.VISIBLE
        mViewModel.getProducts(this)
        mViewModel.productsUIState.onEach {
            handleUiState(it)
        }.launchIn(lifecycleScope)
        productAdapter.setOnItemClickListener {
            findNavController().navigate(
                ProductsFragmentDirections.actionProductsFragmentToProductDetailsFragment(
                    it
                )
            )
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProductsBinding.inflate(inflater, container, false)
        return binding.root
    }

    private fun setupProductRecyclerView() = binding.recyclerViewProduct.apply {
        itemAnimator = null
        isNestedScrollingEnabled = true
        layoutManager = StaggeredGridLayoutManager(2, RecyclerView.VERTICAL)
        adapter = productAdapter
    }

    private fun handleUiState(action: ProductsAction) {
        when (action) {
            is ProductsAction.Success -> {
                binding.spinKit.visibility =View.GONE
                setupProductRecyclerView()
                productAdapter.products = action.products
            }

            is ProductsAction.Failure -> {
                binding.spinKit.visibility =View.GONE
                Toast.makeText(requireContext(),"Error! ${action.massage}",Toast.LENGTH_LONG).show()
            }

            ProductsAction.Loading -> {
                binding.spinKit.visibility =View.VISIBLE
            }
        }
    }
}