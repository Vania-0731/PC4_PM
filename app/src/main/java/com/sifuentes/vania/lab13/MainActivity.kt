package com.sifuentes.vania.lab13

import android.Manifest
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.content.ContextCompat
import androidx.core.net.toUri
import com.google.android.material.snackbar.Snackbar
import com.google.common.util.concurrent.ListenableFuture
import com.sifuentes.vania.lab13.databinding.ActivityMainBinding
import java.io.File
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

/**
 * Actividad principal que maneja la funcionalidad de cámara usando CameraX
 * Permite tomar fotos, cambiar entre cámaras y acceder a la galería
 */
class MainActivity : AppCompatActivity() {

    // Binding para acceder a las vistas de manera segura
    private lateinit var binding: ActivityMainBinding
    
    // Futuro que proporciona acceso al ProcessCameraProvider
    private lateinit var cameraProviderFuture: ListenableFuture<ProcessCameraProvider>
    
    // Selector de cámara (frontal o trasera)
    private lateinit var cameraSelector: CameraSelector
    
    // Caso de uso para capturar imágenes
    private var imageCapture: ImageCapture? = null
    
    // Ejecutor para operaciones de cámara en segundo plano
    private lateinit var imgCaptureExecutor: ExecutorService
    
    // Resultado del permiso de cámara
    private val cameraPermissionResult = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { permissionGranted ->
        if (permissionGranted) {
            // Si se otorga el permiso, iniciar la cámara
            startCamera()
        } else {
            // Si se deniega el permiso, mostrar mensaje
            Snackbar.make(
                binding.root,
                "El permiso de la cámara es necesario",
                Snackbar.LENGTH_INDEFINITE
            ).show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        // Configurar ViewBinding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Inicializar componentes de CameraX
        cameraProviderFuture = ProcessCameraProvider.getInstance(this)
        cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA
        imgCaptureExecutor = Executors.newSingleThreadExecutor()

        // Solicitar permiso de cámara
        cameraPermissionResult.launch(Manifest.permission.CAMERA)

        // Configurar listeners de los botones
        setupButtonListeners()
    }

    /**
     * Configura los listeners para todos los botones de la interfaz
     */
    private fun setupButtonListeners() {
        // Botón para tomar foto
        binding.imgCaptureBtn.setOnClickListener {
            takePhoto()
        }

        // Botón para cambiar entre cámara frontal y trasera
        binding.switchBtn.setOnClickListener {
            cameraSelector = if (cameraSelector == CameraSelector.DEFAULT_BACK_CAMERA) {
                CameraSelector.DEFAULT_FRONT_CAMERA
            } else {
                CameraSelector.DEFAULT_BACK_CAMERA
            }
            startCamera()
        }

        // Botón para abrir la galería
        binding.galleryBtn.setOnClickListener {
            val intent = Intent(this, GalleryActivity::class.java)
            startActivity(intent)
        }
    }

    /**
     * Inicia la cámara y configura la vista previa
     */
    private fun startCamera() {
        // Configurar la vista previa
        val preview = Preview.Builder()
            .build()
            .also {
                it.setSurfaceProvider(binding.preview.surfaceProvider)
            }

        // Configurar la captura de imágenes
        imageCapture = ImageCapture.Builder().build()

        // Agregar listener para cuando el ProcessCameraProvider esté listo
        cameraProviderFuture.addListener({
            val cameraProvider = cameraProviderFuture.get()

            try {
                // Desvincular todos los casos de uso antes de volver a vincular
                cameraProvider.unbindAll()
                
                // Vincular los casos de uso al ciclo de vida de la actividad
                cameraProvider.bindToLifecycle(
                    this,
                    cameraSelector,
                    preview,
                    imageCapture
                )
            } catch (e: Exception) {
                Log.d(TAG, "Error en la vinculación del caso de uso: ${e.message}")
            }
        }, ContextCompat.getMainExecutor(this))
    }

    /**
     * Toma una foto y la guarda en el almacenamiento externo
     */
    private fun takePhoto() {
        imageCapture?.let { capture ->
            // Crear nombre único para el archivo
            val fileName = "JPEG_${System.currentTimeMillis()}"
            
            // Crear archivo en el directorio de medios externos
            val file = File(externalMediaDirs[0], fileName)
            
            // Configurar opciones de salida
            val outputFileOptions = ImageCapture.OutputFileOptions.Builder(file).build()

            // Tomar la foto
            capture.takePicture(
                outputFileOptions,
                imgCaptureExecutor,
                object : ImageCapture.OnImageSavedCallback {
                    override fun onImageSaved(outputFileResults: ImageCapture.OutputFileResults) {
                        // Mostrar mensaje de confirmación al usuario
                        runOnUiThread {
                            Toast.makeText(
                                this@MainActivity,
                                "¡Foto guardada exitosamente!",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                        Log.i(TAG, "La imagen se ha guardado en ${file.toUri()}")
                    }

                    override fun onError(exception: ImageCaptureException) {
                        // Mostrar mensaje de error al usuario
                        runOnUiThread {
                            Toast.makeText(
                                this@MainActivity,
                                "Error al tomar la foto",
                                Toast.LENGTH_LONG
                            ).show()
                        }
                        Log.d(TAG, "Error al tomar la foto: $exception")
                    }
                }
            )
        }
    }

    companion object {
        private const val TAG = "MainActivity"
    }
}