package com.yactayo.ecfinal_yactayo.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.yactayo.ecfinal_yactayo.R
import com.yactayo.ecfinal_yactayo.RVListaListAdapter
import com.yactayo.ecfinal_yactayo.databinding.FragmentFavoritosBinding

class Favoritos : Fragment() {

    private lateinit var binding: FragmentFavoritosBinding
    //private lateinit var viewModel: FavoritoViewModel
    /*
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(FavoritoViewModel::class.java)
    }*/

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentFavoritosBinding.inflate(inflater, container, false)
        return binding.root
    }
    /*
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = RVListaListAdapter(listOf()){lista ->

        }
        binding.rvFavoritos.adapter = adapter
        binding.rvFavoritos.layoutManager = GridLayoutManager(requireContext(), 2, RecyclerView.VERTICAL, false)
        viewModel.favoritos.observe(requireActivity()){
            adapter.listas = it
            adapter.notifyDataSetChanged()
        }

    }*/


}