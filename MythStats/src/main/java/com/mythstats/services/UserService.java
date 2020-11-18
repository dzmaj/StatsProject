package com.mythstats.services;

import java.util.List;

import com.mythstats.data.entities.User;

public interface UserService {
	User find(int id);
	List<User> index();
	User create(User user);
	User update(User user, int id);
	Boolean delete(int id);
}
