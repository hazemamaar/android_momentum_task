package com.example.android_momentum_task.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.android_momentum_task.databinding.FragmentProductDetailsBinding


class ProductDetailsFragment : Fragment() {
    private var _binding: FragmentProductDetailsBinding? = null
    private val binding get() = _binding!!
    private val args: ProductDetailsFragmentArgs by navArgs()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val product = args.product
        Glide.with(this).load(product.image).into(binding.productImage)
        binding.productDescription.text = product.description
        binding.productTitle.text = product.title
        binding.productRating.text = product.rating!!.rate.toString()
        "${product.price}LE".also { binding.price.text = it }
        "${product.rating.count} Piece".also { binding.count.text = it }
        binding.backIcon.setOnClickListener {
            findNavController().popBackStack()
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProductDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }


}