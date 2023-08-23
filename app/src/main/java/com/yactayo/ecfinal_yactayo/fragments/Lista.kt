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
import com.yactayo.ecfinal_yactayo.databinding.FragmentListaBinding


class Lista : Fragment() {

    private lateinit var binding: FragmentListaBinding
    private lateinit var viewModel: ListaViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(requireActivity()).get(ListaViewModel::class.java)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentListaBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = RVListaListAdapter(listOf()){lista ->
            val detalleDireccion = ListaDirections.actionListaToDetalle(lista)
            findNavController().navigate(detalleDireccion)
        }
        binding.rvLista.adapter = adapter
        binding.rvLista.layoutManager = GridLayoutManager(requireContext(), 2, RecyclerView.VERTICAL, false)
        viewModel.listaList.observe(requireActivity()){
            adapter.listas = it
            adapter.notifyDataSetChanged()
        }
        viewModel.getListarFromService()

    }

}