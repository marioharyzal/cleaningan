package com.cleaningan.service;

import com.cleaningan.entity.PacketType;
import com.cleaningan.entity.ShoeType;

public interface IPacketTypeService extends IBaseService<PacketType>{

    PacketType findOne(Integer id);

    PacketType update(Integer id, PacketType packetType);

    PacketType delete(Integer id);
}
