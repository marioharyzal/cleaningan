package com.cleaningan.service;

import com.cleaningan.entity.Membership;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.List;


public interface IBaseService<T> {

    List<T> findAll();

    T save(T data);

    T findOne(String id);

    T update (String id, T data);

    T delete(String id);
}
