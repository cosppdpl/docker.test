package com.cpap.rest.webservice.restfulwebservice.user;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
@RestController
public class UserController {
    
    @Autowired
    private UserDaoService userService;

    @GetMapping(path = "/users")
    public CollectionModel<User> getAllUsers(){
        CollectionModel<User> resource = CollectionModel.of(userService.findAll());
        WebMvcLinkBuilder linkTo = 
				linkTo(methodOn(this.getClass()).getOneUserById(1));
		
		resource.add(linkTo.withRel("specific-user"));
        return resource;
    }

    @GetMapping(path = "/users/{id}")
    public EntityModel<User> getOneUserById(@PathVariable Integer id){
        User user = userService.findOne(id);        
        
        if (user ==null) 
            throw new UserNotFoundException("id-"+id);
        
        //"all-users", SERVER_PATH + "/users"
        //HATEOAS
        EntityModel<User> resource = EntityModel.of(user);
        WebMvcLinkBuilder linkTo = 
				linkTo(methodOn(this.getClass()).getAllUsers());
		
		resource.add(linkTo.withRel("all-users"));
        return resource;
    }

    @DeleteMapping(path = "/users/{id}")
    public void deelteOneUserById(@PathVariable Integer id){
        User user = userService.deleteOne(id);        
        
        if (user ==null) 
            throw new UserNotFoundException("id-"+id);

    }

    @GetMapping(path = "/users/{id}/posts")
    public List<UserPost> getPostForUser(@PathVariable Integer id){
        List<UserPost> posts = userService.findPostsForUserId(id);

        if (posts == null)
            throw new UserNotFoundException("Invalid User id-"+id);
        else if (posts.size() == 0) 
            throw new PostNotFoundException("No post for User id -"+id);

        return posts;
    }

    @GetMapping(path = "/users/{id}/posts/{postId}")
    public UserPost getPostForUser(@PathVariable Integer id, @PathVariable Integer postId){
        UserPost post = userService.findOnePost(id, postId);

        return post;
    }

    //Created
    //input - details of the user
    //output - created & return the created URI
    @PostMapping(path = "/users")
    public ResponseEntity<Object> createUser(@Valid @RequestBody User user){
        User savedUser = userService.save(user);
        //return savedUser;
        URI location = ServletUriComponentsBuilder
                        .fromCurrentRequest()
                        .path("/{id}")
                        .buildAndExpand(savedUser.getId())
                        .toUri();
        return ResponseEntity.created(location).build();
    }

    //Created
    //input - details of the user
    //output - created & return the created URI
    @PostMapping(path = "/users/{id}/posts")
    public ResponseEntity<Object> createUser(
                                            @PathVariable Integer id,
                                            @RequestBody UserPost userPost){
        userPost.setUserId(id);
        UserPost savedPost = userService.save(userPost, id);
        //return savedPost;
        URI location = ServletUriComponentsBuilder
                        .fromCurrentRequest()
                        .path("/{id}")
                        .buildAndExpand(savedPost.getId())
                        .toUri();
        return ResponseEntity.created(location).build();
    }


}
