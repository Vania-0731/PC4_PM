package com.sifuentes.vania.lab13

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.sifuentes.vania.lab13.databinding.ActivityGalleryBinding
import java.io.File

/**
 * Actividad que muestra la galería de fotos tomadas con la cámara
 * Utiliza ViewPager2 para permitir deslizar entre las imágenes
 */
class GalleryActivity : AppCompatActivity() {
    
    // Binding para acceder a las vistas de manera segura
    private lateinit var binding: ActivityGalleryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        // Configurar ViewBinding
        binding = ActivityGalleryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Configurar la barra de acción con botón de regreso
        setupActionBar()
        
        // Obtener el directorio donde se guardan las fotos
        val directorio = File(externalMediaDirs[0].absolutePath)
        
        // Obtener todos los archivos del directorio
        val archivos = directorio.listFiles() as Array<File>
        
        // Crear el adaptador con las imágenes en orden inverso (más recientes primero)
        val adaptador = GalleryAdapter(archivos.reversedArray())
        
        // Asignar el adaptador al ViewPager2
        binding.viewPager.adapter = adaptador
    }
    
    /**
     * Configura la barra de acción con título y botón de regreso
     */
    private fun setupActionBar() {
        // Habilitar el botón de regreso en la barra de acción
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
            title = "Galería de Fotos"
        }
    }
    
    /**
     * Maneja el evento cuando se presiona el botón de regreso en la barra de acción
     */
    override fun onSupportNavigateUp(): Boolean {
        // Regresar a la actividad anterior (MainActivity)
        onBackPressed()
        return true
    }
} 