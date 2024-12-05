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

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val binding = if (convertView == null) {
            CocheItemBinding.inflate(LayoutInflater.from(context),
                parent, false)
        } else {
            CocheItemBinding.bind(convertView)
        }

        val iconosMarca = mapOf(
            "Mercedes" to R.drawable.mercedes_benz,
            "BMW" to R.drawable.bmw_logo,
            "Audi" to R.drawable.audi_logo
        )
        val elementoActual: Coche = listacoches[position]

        binding.textViewMarca.text = elementoActual.marca
        binding.textViewModelo.text = elementoActual.modelo

        binding.imageViewMarca.setImageResource(iconosMarca[elementoActual.marca] ?:
                                                    R.drawable.interrogacion)

        return binding.root
    }
}