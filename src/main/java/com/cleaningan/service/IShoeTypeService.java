package com.cleaningan.service;

import com.cleaningan.entity.Customer;
import com.cleaningan.entity.ShoeType;

public interface IShoeTypeService extends IBaseService<ShoeType>{

    ShoeType findOne(Integer id);

    ShoeType update(Integer id, ShoeType shoeType);

    ShoeType delete(Integer id);
}
