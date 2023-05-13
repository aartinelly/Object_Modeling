package com.crio.jukebox.repositories;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import com.crio.jukebox.entities.User;

public class UserRepository implements IUserRepository{

    private final Map<String,User> userMap;
    private Integer autoIncrement = 0;

    public UserRepository(){
        userMap = new HashMap<String,User>();
    }

    public UserRepository(Map<String, User> userMap) {
        this.userMap = userMap;
        this.autoIncrement = userMap.size();
    }

    @Override
    public long count() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void delete(User entity) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void deleteById(String id) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public boolean existsById(String id) {
        // TODO Auto-generated method stub
       if (userMap.containsKey(id)) {
        return true;
       }
        return false;
    }

    @Override
    public List findAll() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Optional<User> findById(String id) {
        // TODO Auto-generated method stub
        return Optional.ofNullable(userMap.get(id));

    }

    //save user 
    @Override
    public User save(User entity) {
        // TODO Auto-generated method stub
        if(entity.getId()==null){
            autoIncrement++;
           User u = new User(Integer.toString(autoIncrement), entity.getUsername());
            userMap.put(u.getId(), u);   
            return u;         
        }
        
        User user = new User(entity.getId(), entity.getUsername());
        userMap.put(user.getId(), user);
        return user;
    }

    @Override
    public Optional<User> findByName(String name) {
        // TODO Auto-generated method stub
        return null;
    }

    
}
