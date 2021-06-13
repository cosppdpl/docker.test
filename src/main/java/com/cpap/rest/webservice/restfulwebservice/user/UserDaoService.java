package com.cpap.rest.webservice.restfulwebservice.user;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Date;
import java.util.Iterator;

import org.springframework.stereotype.Component;

@Component
public class UserDaoService {
    
    private static Integer usersCount = 4;
    private static List<User> users = new ArrayList<>();
    private static List<UserPost> userPosts = new ArrayList<>();


    static {
        userPosts.add( new UserPost(1, 1, new Date(), "User Post 1"));
        userPosts.add( new UserPost(2, 3, new Date(), "User Post 2"));
        userPosts.add( new UserPost(3, 4, new Date(), "User Post 3"));

        users.add( new User(1, "Bob",  new Date()));
        users.add( new User(2, "Jack", new Date()));
        users.add( new User(3, "Eve",  new Date()));
        users.add( new User(4, "Ana",  new Date()));
    }

    public List<User> findAll(){
        return users;
    }

    public UserPost findOnePost(Integer userId, Integer postId){
        for (User user:users){
            if (user.getId()==userId){
                for (UserPost userPost:userPosts){
                    if (userPost.getId()==postId){
                        return userPost;
                    }
                }
                return null;
            }
        }
        return null;
    }

    public User save(User user){
        if (user.getId() == null){
            user.setId(++usersCount);
        }
        users.add(user);
        return user;
    }
    public UserPost save(UserPost userPost, Integer userId){
        if (userPost.getId() == null){
            userPost.setId(findPostsForUserId(userId).size()+1);
            userPost.setTimestamp(new Date());
        }
        userPosts.add(userPost);
        return userPost;
    }

    public User findOne(int id){
        for (User user:users){
            if (user.getId()==id){
                return user;
            }
        }
        return null;
    }

    public User deleteOne(int userId){
        Iterator<User> userIterator = users.iterator();
        while (userIterator.hasNext()){
            User user = userIterator.next();
            if (user.getId()==userId){
                userIterator.remove();
                return user;
            }
        }
        return null;
    }

    public List<UserPost> findPostsForUserId(Integer id) {
        final List<UserPost> result = new ArrayList<UserPost>();
        if (findOne(id) == null) return null;
        for (UserPost userPost:userPosts){
            if (userPost.getUserId()==id){
                result.add(userPost);
            }
        }
        return result;  
    }

}
