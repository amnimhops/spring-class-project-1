# spring-class-project-1

Proyecto para practicar la creación de controladores en **Spring Boot**

## Tareas
Las tareas a realizar son:
1. Estudiar la documentación adjunta en la carpeta `docs`
2. Implementar el controlador PostController según la especificación de la API proporcionada

Para llevar a cabo el punto 2, solo será necesario
completar la clase `PostController`, añadiendo los métodos
y anotaciones necesarias para exponer los _endpoints_ indicados
por la API. Toda la gestión de publicaciones o _posts_ se lleva
a cabo mediante la interfaz de servicio `PostService`, implementada
por la clase `PostServiceImpl`. Para poder usarla, solo será necesario
inyectarla dentro de la clase con las anotaciones de Spring necesarias.

Cabe señalar que a la hora de cargar el proyecto, habrá errores sintácticos
en la interfaz `PostService` y la clase `PostServiceImpl`. Esto se debe a
que se han eliminado las clases que modelan las entidades y _data transfer objects_
correspondientes a las instancias de publicaciones y comentarios. Parte del trabajo
será crear las clases que faltan de acuerdo a la especificación de la API. La lista
de clases completa está en la siguiente relación:
- EditPostInput
- Post
- CreateCommentInput
- Comment
- PostFilter
- CreatePostInput
- ApiError
- ResourceDeletionStatus
## Documentación API

La documentación de la API que habrá que desarrollar se encuentra embebida en un servidor
web que sirve tanto de documentación, como de versión funcional de pruebas. Para ejecutar
el servidor habrá que lanzar el comando:
```
java -jar bin\project1-solved.jar
```
Esto ejecutará una versión funcional completa del proyecto que hay que desarrollar, además
de la documentación en formato OpenAPI. Las URLs de acceso son:
- URL de la API terminada : http://localhost:8080/posts
- URL de la documentación : http://localhost:8080/swagger-ui/index.html
