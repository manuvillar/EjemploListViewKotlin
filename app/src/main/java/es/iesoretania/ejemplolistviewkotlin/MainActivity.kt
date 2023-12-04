package es.iesoretania.ejemplolistviewkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.AdapterView
import android.widget.Toast
import es.iesoretania.ejemplolistviewkotlin.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val miLista = listOf<Coche>(
            Coche("GLA", "Mercedes"),
            Coche("X3", "BMW"),
            Coche("A4", "Audi"),
            Coche("GLC", "Mercedes"),
            Coche("Prueba", "Extraña")
        )

        val miadaptador = MiAdaptadorCoches(this, R.layout.coche_item, miLista)
        binding.lvpersonalizado.adapter = miadaptador

        binding.lvpersonalizado.onItemClickListener =
            AdapterView.OnItemClickListener {
                    parent, view, position, id ->
                        val coche = miLista.get(position)
                        Toast.makeText(this, coche.modelo, Toast.LENGTH_SHORT).show()
        }

    }
}