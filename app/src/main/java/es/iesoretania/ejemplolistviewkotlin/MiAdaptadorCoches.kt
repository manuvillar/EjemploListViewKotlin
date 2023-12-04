package es.iesoretania.ejemplolistviewkotlin

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import es.iesoretania.ejemplolistviewkotlin.databinding.CocheItemBinding

class MiAdaptadorCoches(private val miContext: Context,
                        val resource: Int,
                        private val listacoches: List<Coche>)
    :ArrayAdapter<Coche>(miContext, resource, listacoches) {
    private lateinit var binding: CocheItemBinding

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        binding = CocheItemBinding.inflate(LayoutInflater.from(miContext), parent, false)
        val v = binding.root
        val elementoActual: Coche = listacoches.get(position)

        binding.textViewMarca.text = elementoActual.marca
        binding.textViewModelo.text = elementoActual.modelo

        when (elementoActual.marca){
            "Mercedes" -> binding.imageViewMarca.setImageResource(R.drawable.mercedes_benz)
            "Audi" -> binding.imageViewMarca.setImageResource(R.drawable.audi_logo)
            "BMW" -> binding.imageViewMarca.setImageResource(R.drawable.bmw_logo)
            else -> binding.imageViewMarca.setImageResource(R.drawable.mercedes_benz)
        }

        return v
    }
}