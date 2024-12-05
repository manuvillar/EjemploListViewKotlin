package es.iesoretania.ejemplolistviewkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.AdapterView
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.android.material.floatingactionbutton.FloatingActionButton
import es.iesoretania.ejemplolistviewkotlin.databinding.ActivityMainBinding
import es.iesoretania.ejemplolistviewkotlin.databinding.DialogAddCarBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var miadaptador: MiAdaptadorCoches
    val miLista = mutableListOf(
        Coche("GLA", "Mercedes"),
        Coche("X3", "BMW"),
        Coche("A4", "Audi"),
        Coche("GLC", "Mercedes"),
        Coche("Prueba", "Extraña")
    )


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        miadaptador = MiAdaptadorCoches(this, R.layout.coche_item, miLista)
        binding.lvpersonalizado.adapter = miadaptador

        binding.lvpersonalizado.onItemClickListener =
            AdapterView.OnItemClickListener {
                    parent, view, position, id ->
                        val coche = miLista[position]
                        Toast.makeText(this, "${coche.modelo} - ${coche.marca}",
                            Toast.LENGTH_SHORT).show()
        }

        // Configuración del botón flotante
        binding.fabAdd.setOnClickListener {
            muestraAddCarDialog()
        }
    }

    private fun muestraAddCarDialog() {
        // Inflar el diseño personalizado del diálogo
        val dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_add_car, null)
        val etModelo = dialogView.findViewById<EditText>(R.id.et_modelo)
        val etMarca = dialogView.findViewById<EditText>(R.id.et_marca)

        // Crear el cuadro de diálogo
        val dialog = AlertDialog.Builder(this)
            .setTitle("Agregar Coche")
            .setView(dialogView)
            .setPositiveButton("Agregar") { _, _ ->
                val modelo = etModelo.text.toString()
                val marca = etMarca.text.toString()

                if (modelo.isNotEmpty() && marca.isNotEmpty()) {
                    // Agregar el coche a la lista y actualizar el adaptador
                    miLista.add(Coche(modelo, marca))
                    miadaptador.notifyDataSetChanged()
                } else {
                    Toast.makeText(this,
                        "Por favor, ingresa todos los datos", Toast.LENGTH_SHORT).show()
                }
            }
            .setNegativeButton("Cancelar", null)
            .create()

        dialog.show()
    }
}