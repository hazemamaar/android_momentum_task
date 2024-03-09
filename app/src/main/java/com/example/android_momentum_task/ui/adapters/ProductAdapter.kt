package com.example.android_momentum_task.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.android_momentum_task.data.models.ProductItem
import com.example.android_momentum_task.databinding.ItemProductLayoutBinding
import javax.inject.Inject

class ProductAdapter @Inject constructor(private val context: Context) :
    RecyclerView.Adapter<ProductAdapter.ProductsViewHolder>() {

    inner class ProductsViewHolder(private val binding: ItemProductLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ProductItem) {
            binding.productName.text = item.title
            Glide.with(context).load(item.image).into(binding.productImg)
            binding.cardView.setOnClickListener {
                onItemClickListener?.let { it(item) }
            }
        }
    }

    var products: List<ProductItem>
        get() = differ.currentList
        set(value) = differ.submitList(value)

    private val differCallBack = object : DiffUtil.ItemCallback<ProductItem>() {
        override fun areItemsTheSame(oldItem: ProductItem, newItem: ProductItem): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }

        override fun areContentsTheSame(
            oldItem: ProductItem,
            newItem: ProductItem,
        ): Boolean {
            return oldItem == newItem
        }
    }
    private val differ = AsyncListDiffer(this, differCallBack)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductsViewHolder {
        return ProductsViewHolder(
            ItemProductLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    private var onItemClickListener: ((ProductItem) -> Unit)? = null

    override fun onBindViewHolder(holder: ProductsViewHolder, position: Int) {
        val product = products[position]
        holder.apply {
            bind(product)
        }
    }

    override fun getItemCount() = products.size

    fun setOnItemClickListener(listener: (ProductItem) -> Unit) {
        onItemClickListener = listener
    }
}