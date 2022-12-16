package com.cleaningan.service;

import com.cleaningan.entity.Membership;

import java.util.List;

public interface IMembershipService extends IBaseService<Membership>{

    Membership findOne(int id);

    Membership update(int id, Membership data);

    Membership delete(int id);
}
