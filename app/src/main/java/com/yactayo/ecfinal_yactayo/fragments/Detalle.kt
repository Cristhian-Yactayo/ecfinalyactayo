package com.yactayo.ecfinal_yactayo.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.yactayo.ecfinal_yactayo.databinding.FragmentDetalleBinding
import com.yactayo.ecfinal_yactayo.model.ListaModel

class Detalle : Fragment() {

    private lateinit var binding: FragmentDetalleBinding

    val args: DetalleArgs by navArgs()

    private lateinit var lista: ListaModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lista = args.lista

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentDetalleBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Glide.with(binding.root.context)
            .load(lista.images.icon)
            .into(binding.imgView)
        binding.txtName.text = lista.name
        binding.txtDescripcion.text = lista.description
        binding.txtIntroduccion.text = lista.introduction.text

    }

}