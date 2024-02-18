package com.example.blogapi.services;

import com.example.blogapi.errors.ResourceNotFoundException;
import com.example.blogapi.errors.ServiceException;
import org.springframework.stereotype.Service;

import java.util.*;

class MockFactory{
    private static List<String> emails = Arrays.asList("asdf@gmail.com","john@hotmail.com","sergei@yahoo.es","tanja@aol.com");
    private static List<String> loremIpsum = Arrays.asList("Morbi ut efficitur massa. Praesent tortor risus, egestas non dolor at, dictum pharetra arcu. Nullam viverra, urna in finibus laoreet, nulla turpis cursus augue, eget aliquet risus ante sit amet est. Duis vestibulum suscipit felis, sed ultrices quam porttitor eget. Aenean a velit accumsan, feugiat ipsum vitae, tincidunt odio. Aliquam.".split(" "));

    public static <T> T randomItem(List<T> list){
        int index = (int)Math.floor(Math.random() * list.size());
        return list.get(index);
    }
    public static String randomEmail(){return randomItem(emails);}
    public static String randomBody(int numWords){
        StringBuffer sb = new StringBuffer();
        for(int c = 0; c < numWords; c++) {
            sb.append(randomItem(loremIpsum));
            if(c < numWords-1) sb.append(" ");
        }
        return sb.toString();
    }
    public static List<Post> generateRandomPosts(int numPosts){
        List<String> subjects = Arrays.asList(
                "El hombre",
                "El perro",
                "La azafata",
                "El conejo",
                "La doctora"
        );
        List<String> objects = Arrays.asList(
                "que sabía demasiado",
                "que soñaba con viajar",
                "que domaba pulgas en un circo",
                "que dominaba las mareas",
                "que conjuraba contra el destino"
        );
        List<Post> posts = new ArrayList();
        for(String subject:subjects){
            for(String object:objects){
                posts.add(
                    new Post(
                        UUID.randomUUID().toString(),
                        randomEmail(),
                        subject + " " + object,
                        randomBody(25),
                        new Date(),
                        new Date()
                    )
                );
            };
        }
        return posts;
    }
    public static List<Comment> generateRandomComments(Post post, int numComments){
        List<Comment> comments = new ArrayList();
        for(int c=0;c<numComments;c++){
            comments.add(
                    new Comment(
                            UUID.randomUUID().toString(),
                            randomEmail(),
                            post.getId(),
                            randomBody(10)
                    )
            );
        }
        return comments;
    }
}
@Service
public class PostServiceImpl implements PostService {
    private Map<String, Post> postsMap;
    private Map<String, List<Comment>> commentsMap;

    public PostServiceImpl() {
        this.postsMap = new HashMap<>();
        this.commentsMap = new HashMap<>();
        for(Post post:MockFactory.generateRandomPosts(10)){
            postsMap.put(post.getId(),post);
            List<Comment> comments = MockFactory.generateRandomComments(post,5);
            commentsMap.put(post.getId(),comments);
        }
    }

    @Override
    public Optional<Post> findPostById(String id) {
        Optional<Post> result = Optional.empty();
        if(postsMap.containsKey(id)) result = Optional.of(postsMap.get(id));
        return result;
    }

    @Override
    public Post createPost(CreatePostInput input) {
        Post post = new Post();
        Date now = new Date();

        post.setId(UUID.randomUUID().toString());
        post.setTitle(input.getTitle());
        post.setBody(input.getBody());
        post.setCreationDate(now);
        post.setModificationDate(now);
        post.setAuthorEmail(input.getAuthor());

        postsMap.put(post.getId(), post);

        return post;
    }

    @Override
    public Post editPost(String id, EditPostInput input) {
        Post post = postsMap.get(id);
        if(post == null){
            throw new ServiceException("No se ha encontrado el post "+id);
        }

        post.setBody(input.getBody());
        post.setTitle(input.getTitle());
        post.setModificationDate(new Date());

        return post;
    }

    @Override
    public boolean deletePost(String id) {
        return postsMap.remove(id) != null;
    }

    @Override
    public List<Post> findPosts(PostFilter filter) {
        List<Post> filteredPosts = new ArrayList<>();
        for (Post post : postsMap.values()) {
            if (filter.matches(post)) {
                filteredPosts.add(post);
            }
        }
        return filteredPosts;
    }

    @Override
    public List<Comment> getPostComments(String id) {
        if(!postsMap.containsKey(id)) throw new ResourceNotFoundException(Post.class,id);
        return commentsMap.getOrDefault(id, new ArrayList<>());
    }

    @Override
    public Comment addComment(String id, CreateCommentInput input) {
        if(!postsMap.containsKey(id)) throw new ResourceNotFoundException(Post.class,id);

        Comment comment = new Comment();
        comment.setPostId(id);
        comment.setBody(input.getText());
        comment.setId(UUID.randomUUID().toString());
        comment.setAuthor(input.getAuthor());

        List<Comment> comments = commentsMap.getOrDefault(id, new ArrayList<>());
        comments.add(comment);
        commentsMap.put(id, comments);
        return comment;
    }
}