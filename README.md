# MarvelTestOB
## Este proyecto fue desarrollo y testeado en Android Studio Arctic Fox | 2020.3.1 Patch 3
Utiliza bibliotecas de generación de código como Databinding, Room. Por lo tanto, primero sincronice el gradle del proyecto y compílelo una vez.
Si es necesario, "invalide los cachés y reinicie" usando la opción de menú 'Archivo' en el estudio de Android.

## Se utilizó:
- [x] Kotlin
- [x] Dagger Hilt (Dependency injection)
- [x] Coroutines (extension)
- [x] Glide (Image library)
- [x] DataBinding (Android architecture component from jetpak)
- [x] ViewModel (Android architecture component from jetpak)
- [x] LiveDate (Android architecture component from jetpak)
- [x] Room (SQLite backed DB for persistence)
- [x] Retrofit2 (For service calls)
- [x] Espresso (AndroidJUnit4ClassRunner for UITests)
- [x] JUnit4 (For unit tests)

## El proyecto usa CLEAN architecture.

Se separó la funcionalidad en las diferentes capas que nos brinda CA (Clean Architecture).
Core: contiene una extensión de View.
Ui: o presentation. Contiene las vistas de la app (Listado  de personajes y el detalle de cada uno).
Domain: contiene la lógica del negocio. 
      * Model: Data class que contiene el modelo que recibe esta capa
      * Repository: Interfaz que defino los métodos para consultar los datos
      * UseCases: los casos de uso para llamar al listado de personajes y para llamar a un personaje en particular
Data: 
      * Database: Contiene los DAO, Entities y la Database local Room
      * Model: Contiene las Data Class para obtener la respuesta de Retrofit
      * Network: Defino la interfaz de llamada a la API y el CharacterService que implementa los métodos de consulta a la API
      CharactersRepository: es el encargado de gestionar de dónde se obtienen los datos (Remote o Local)
Di:

Se utiliza la arquitectura MVVM para observar cambios en los LiveData y actualizar la vista (lista de Personajes). 
Tenemos un repositorio que es el encargado de determinar que fuente de datos se utiliza para recuperar los datos de los personajes. 

Se consulta la API de Marvel para recuperar los datos, que luego son guardados en la base de datos local mediante Room. Si se detecta algún error en la consulta a la API
entonces recupera los datos de la BD local.

	
