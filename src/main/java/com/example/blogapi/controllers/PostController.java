package com.example.blogapi.controllers;

import com.example.blogapi.dtos.*;
import com.example.blogapi.entities.Comment;
import com.example.blogapi.entities.Post;
import com.example.blogapi.errors.ResourceNotFoundException;
import com.example.blogapi.errors.ServiceException;
import com.example.blogapi.services.PostService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.extensions.Extension;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@OpenAPIDefinition(info = @Info(
        description = """
        # Especificación de la API Rest de un Blog
        
        ## Definición
        
        El proyecto consiste en la construcción de un controlador REST en _spring_ que cumpla
        con la especificación detallada en esta API. El controlador deberá tener 7 operaciones:
        - Creación, modificación y borrado de posts
        - Consulta de posts individuales y búsqueda con filtro
        - Alta y consulta de comentarios para un post determinado.
        
        A continuación se enumeran los recursos didácticos necesarios para completar con éxito la tarea:
        - [Controladores REST en Spring](https://www.baeldung.com/spring-controller-vs-restcontroller)
        - [Mapeo de rutas en Spring](https://www.baeldung.com/spring-new-requestmapping-shortcuts)
        - [Rutas con parámetros en Spring](https://www.baeldung.com/spring-pathvariable)
        - [Inyección de dependencias](https://www.baeldung.com/spring-dependency-injection)
        
        """,
        contact = @Contact(email = "manuel.lillo@gmail.com"),
        title = "Blog API REST"
))
@RequestMapping("/posts")
@RestController
public class PostController {
    @Autowired
    PostService postService;

    @Operation(summary = "Añade una nueva publicación a la lista")
    @ApiResponses({
        @ApiResponse(
            responseCode="200",
            description = "La publicación recién creada",
            content = {
                    @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Post.class)
                    )
            })
        })
    @PostMapping("/")
    public ResponseEntity<Post> addPost(
            @Parameter(description="Información necesaria para crear la publicación")
            @RequestBody CreatePostInput input) {
        Post post = postService.createPost(input);
        return new ResponseEntity<>(post, HttpStatus.OK);
    }

    @Operation(summary = "Modifica los valores de una publicación")
    @ApiResponses({
            @ApiResponse(
                    responseCode="200",
                    description = "La publicación con los valores modificados",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Post.class)
                            )
                    })
    })
    @PutMapping("/{id}")
    public ResponseEntity<Post> editPost(
            @Parameter(description = "Identificador de la publicación cuyos valores desean cambiarse") @PathVariable String id,
            @Parameter(description = "Objeto con los nuevos valores del post") @RequestBody EditPostInput input
    ) {
        Post post = postService.editPost(id, input);
        return new ResponseEntity<>(post, HttpStatus.OK);
    }

    @Operation(summary = "Accede a la información de una publicación")
    @ApiResponses({
            @ApiResponse(
                    responseCode="200",
                    description = "La publicación requerida",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Post.class)
                            )
                    }),
            @ApiResponse(
                    responseCode="404",
                    description = "El post seleccionado no existe",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))
                    }
            )
    })
    @GetMapping("/{id}")
    public ResponseEntity<Post> getPost(
            @Parameter(description = "Identificador de la publicación que se desea obtener") @PathVariable String id
    ) {
        Optional<Post> post = postService.findPostById(id);
        if (!post.isPresent()) throw new ResourceNotFoundException(Post.class, id);

        return new ResponseEntity<>(post.get(), HttpStatus.OK);
    }
    @Operation(summary = "Borra una publicación existente")
    @ApiResponses({
            @ApiResponse(
                    responseCode="200",
                    description = "El estado de la operación",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ResourceDeletionStatus.class)
                            )
                    })
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<ResourceDeletionStatus> deletePost(
            @Parameter(description = "Identificador de la publicación que se desea eliminar") @PathVariable String id
    ) {
        boolean deleted = postService.deletePost(id);
        return new ResponseEntity(new ResourceDeletionStatus("Post", id, deleted), HttpStatus.OK);
    }
    @Operation(summary = "Busca publicaciones usando un filtro")
    @ApiResponses({
            @ApiResponse(
                    responseCode="200",
                    description = "El estado de la operación",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema( implementation = Post.class))
                            )
                    })
    })
    @PostMapping("/search")
    public ResponseEntity<List<Post>> findAllPosts(
            @Parameter(description = "Valores que se usarán para filtrar las publicaciones") @RequestBody PostFilter filter
    ) {
        List<Post> posts = postService.findPosts(filter);
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }
    @Operation(summary = "Obtiene una lista de los comentarios de un post")
    @ApiResponses({
            @ApiResponse(
                    responseCode="200",
                    description = "La lista de comentarios de la publicación",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema( implementation = Comment.class))
                            )
                    }),
            @ApiResponse(
                    responseCode="404",
                    description = "El post seleccionado no existe",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))
                    }
            )
    })
    @GetMapping("/{id}/comments")
    public ResponseEntity<List<Comment>> getPostComments(
            @Parameter(description = "Identificador de la publicación cuyos comentarios se desean obtener") @PathVariable String id
    ){
        List<Comment> comments = postService.getPostComments(id);
        return new ResponseEntity<>(comments,HttpStatus.OK);
    }

    @PostMapping("/{id}/comments")
    public ResponseEntity<Comment> addComment(
            @Parameter(description = "Identificador de la publicación donde se desea comentar") @PathVariable String id,
            @Parameter(description = "Datos del comentario") @RequestBody CreateCommentInput input
    ){
        Comment comment = postService.addComment(id,input);
        return new ResponseEntity<>(comment,HttpStatus.OK);
    }

    @ExceptionHandler({ResourceNotFoundException.class, ServiceException.class})
    public ResponseEntity<ApiError> handleExceptions(Exception ex) {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;

        if (ex instanceof ResourceNotFoundException) {
            status = HttpStatus.NOT_FOUND;
        } else if (ex instanceof ServiceException) {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity(new ApiError(ex.getMessage()), status);
    }
}
