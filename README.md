# 📸 Lab13 - CameraX Implementation

## 📋 Descripción del Proyecto

Aplicación Android desarrollada en Kotlin que implementa la funcionalidad de cámara usando **CameraX**, la biblioteca moderna de Google para el manejo de cámaras en Android. La aplicación permite tomar fotos, cambiar entre cámara frontal y trasera, y visualizar las fotos en una galería integrada.

## ✨ Características Principales

- 📱 **Vista previa en tiempo real** de la cámara
- 📸 **Captura de fotos** con guardado automático
- 🔄 **Cambio entre cámara frontal y trasera**
- 🖼️ **Galería integrada** con ViewPager2
- 🎯 **Interfaz moderna** con Material Design
- 🔒 **Manejo de permisos** de cámara
- 📝 **Feedback visual** con mensajes Toast

## 🛠️ Tecnologías Utilizadas

- **Kotlin** - Lenguaje de programación principal
- **CameraX** - Biblioteca moderna para cámara
- **ViewBinding** - Acceso seguro a vistas
- **Glide** - Carga eficiente de imágenes
- **ViewPager2** - Navegación entre imágenes
- **ConstraintLayout** - Diseño de interfaz flexible

## 📁 Estructura del Proyecto

```
app/src/main/
├── java/com/sifuentes/vania/lab13/
│   ├── MainActivity.kt          # Actividad principal con cámara
│   ├── GalleryActivity.kt       # Actividad de galería
│   └── GalleryAdapter.kt        # Adaptador para ViewPager2
├── res/layout/
│   ├── activity_main.xml        # Layout principal
│   ├── activity_gallery.xml     # Layout de galería
│   └── list_item_img.xml        # Layout de imagen individual
└── AndroidManifest.xml          # Configuración de permisos y actividades
```

## 🚀 Funcionalidades Implementadas

### 1. **Pantalla Principal (MainActivity)**
- Vista previa de cámara en tiempo real
- Botón "Take a photo" para capturar imágenes
- Botón "Switch" para cambiar entre cámaras
- Botón "Gallery" para ver fotos tomadas
- Solicitud automática de permisos de cámara

### 2. **Galería (GalleryActivity)**
- Visualización de todas las fotos tomadas
- Navegación con gestos de deslizamiento
- Ordenamiento por fecha (más recientes primero)
- Botón de regreso integrado en ActionBar

### 3. **Manejo de Permisos**
- Solicitud automática de permiso de cámara
- Mensajes informativos si se deniega el permiso
- Manejo elegante de errores de permisos

## 📦 Dependencias Principales

```kotlin
// CameraX
implementation("androidx.camera:camera-camera2:1.0.1")
implementation("androidx.camera:camera-lifecycle:1.0.1")
implementation("androidx.camera:camera-view:1.0.0-alpha27")

// Glide para imágenes
implementation("com.github.bumptech.glide:glide:4.12.0")

// ViewBinding
buildFeatures {
    viewBinding = true
}
```

## 🔧 Configuración del Proyecto

### Requisitos Previos
- Android Studio Arctic Fox o superior
- Android SDK API 24+ (Android 7.0)
- Dispositivo o emulador con cámara

### Instalación
1. Clonar el repositorio:
   ```bash
   git clone https://github.com/Vania-0731/PC4_PM.git
   ```

2. Abrir el proyecto en Android Studio

3. Sincronizar dependencias Gradle

4. Ejecutar en dispositivo/emulador

## 📱 Cómo Usar la Aplicación

1. **Abrir la app** - Se solicitará permiso de cámara
2. **Tomar foto** - Presionar "Take a photo"
3. **Cambiar cámara** - Presionar "Switch"
4. **Ver galería** - Presionar "Gallery"
5. **Regresar** - Usar flecha de regreso o botón físico

## 🎯 Características Técnicas

### Manejo de Cámara
- **CameraX Lifecycle**: Integración automática con el ciclo de vida
- **Preview**: Vista previa en tiempo real
- **ImageCapture**: Captura de fotos de alta calidad
- **CameraSelector**: Cambio dinámico entre cámaras

### Almacenamiento
- **Ubicación**: `externalMediaDirs[0]`
- **Formato**: JPEG con timestamp único
- **Organización**: Ordenadas por fecha de creación

### Interfaz de Usuario
- **Material Design**: Componentes modernos
- **Responsive**: Adaptable a diferentes tamaños de pantalla
- **Accessible**: Navegación intuitiva

## 🐛 Solución de Problemas

### Error de Permisos
- Verificar que se otorgó permiso de cámara
- Reiniciar la aplicación si es necesario

### Cámara No Funciona
- Verificar que el dispositivo tiene cámara
- Comprobar que no hay otras apps usando la cámara

### Fotos No Aparecen en Galería
- Verificar permisos de almacenamiento
- Comprobar espacio disponible en el dispositivo

## 📝 Comentarios y Observaciones

### Cambios Realizados en AndroidManifest.xml
- ✅ Agregado `<uses-feature android:name="android.hardware.camera.any" />`
- ✅ Agregado `<uses-permission android:name="android.permission.CAMERA" />`
- ✅ Declarada `GalleryActivity` para navegación

### Cambios en build.gradle.kts
- ✅ Habilitado ViewBinding para acceso seguro a vistas
- ✅ Agregadas dependencias de CameraX con versiones específicas
- ✅ Incluida Glide para manejo eficiente de imágenes

### Implementación de Layouts
- ✅ `activity_main.xml`: Interfaz principal con PreviewView y botones
- ✅ `activity_gallery.xml`: ViewPager2 para navegación de imágenes
- ✅ `list_item_img.xml`: Layout individual para cada imagen

### Clases Kotlin Implementadas
- ✅ `MainActivity`: Funcionalidad principal de cámara con CameraX
- ✅ `GalleryActivity`: Visualización de galería con navegación
- ✅ `GalleryAdapter`: Adaptador para ViewPager2 con Glide

## 🎓 Aprendizajes del Proyecto

1. **CameraX**: Uso de la biblioteca moderna de Google para cámara
2. **ViewBinding**: Acceso seguro y eficiente a vistas
3. **Lifecycle**: Integración con el ciclo de vida de Android
4. **Permisos**: Manejo adecuado de permisos de cámara
5. **Navegación**: Implementación de navegación entre actividades
6. **Almacenamiento**: Guardado de archivos en almacenamiento externo

## 👨‍💻 Autor

**Vania Sifuentes** - Estudiante de Programación Móvil

## 📄 Licencia

Este proyecto es parte del laboratorio de Programación Móvil y está destinado únicamente para fines educativos.

---

**Fecha de Creación**: Julio 2025  
**Versión**: 1.0  
**Plataforma**: Android  
**Lenguaje**: Kotlin 