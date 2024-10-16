package com.example.shoppinglistapp.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.shoppinglistapp.R
import com.example.shoppinglistapp.ui.viewmodel.ShoppingViewModel

class shoppingFragment : Fragment(R.layout.fragment_shopping) {
    lateinit var viewModel: ShoppingViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity())[ShoppingViewModel::class.java]
    }
}