package com.cleaningan.service;

import com.cleaningan.constant.MessageException;
import com.cleaningan.entity.PacketType;
import com.cleaningan.repository.IPacketTypeRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class PacketTypeServiceImpl implements IPacketTypeService {

    private final IPacketTypeRepository packetTypeRepository;

    public PacketTypeServiceImpl(IPacketTypeRepository packetTypeRepository) {
        this.packetTypeRepository = packetTypeRepository;
    }

    @Override
    public List<PacketType> findAll() {
        try {
            return packetTypeRepository.findAllPacketType();
        } catch (Exception e) {
            throw new RuntimeException(MessageException.INTERNAL_SERVER_ERROR.toString());
        }
    }

    @Override
    public PacketType save(PacketType packetType) {
        try {
            boolean existsPacketTypeById = packetTypeRepository.existsByPacketTypeId(packetType.getPacketTypeId());
            if (existsPacketTypeById) throw new DataIntegrityViolationException(MessageException.DATA_EXIST.toString());
            return packetTypeRepository.save(packetType);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException(Objects.requireNonNull(e.getMessage()));
        }
    }

    @Override
    public PacketType findOne(Integer id) {
        try {
            Optional<PacketType> packetTypeById = packetTypeRepository.findByPacketTypeId(id);
            if (packetTypeById.isEmpty())
                throw new DataIntegrityViolationException(MessageException.NOT_FOUND.toString());
            return packetTypeById.get();
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException(Objects.requireNonNull(e.getMessage()));
        } catch (Exception e) {
            throw new RuntimeException(MessageException.INTERNAL_SERVER_ERROR.toString());
        }
    }

    @Override
    public PacketType update(Integer id, PacketType packetType) {
        try {
            boolean existsByPacketTypeId = packetTypeRepository.existsByPacketTypeId(id);
            if (!existsByPacketTypeId) throw new DataIntegrityViolationException(MessageException.NOT_FOUND.toString());
            packetType.setPacketTypeId(id);
            return packetTypeRepository.save(packetType);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException(Objects.requireNonNull(e.getMessage()));
        }
    }

    @Override
    public PacketType delete(Integer id) {
        try {
            Optional<PacketType> packetTypeById = packetTypeRepository.findByPacketTypeId(id);
            if (packetTypeById.isEmpty())
                throw new DataIntegrityViolationException(MessageException.NOT_FOUND.toString());
            packetTypeRepository.deleteByPacketTypeId(id);
            return packetTypeById.get();
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException(Objects.requireNonNull(e.getMessage()));
        } catch (Exception e) {
            throw new RuntimeException(MessageException.INTERNAL_SERVER_ERROR.toString());
        }
    }

    @Override
    public PacketType findOne(String id) {
        return null;
    }

    @Override
    public PacketType update(String id, PacketType data) {
        return null;
    }

    @Override
    public PacketType delete(String id) {
        return null;
    }
}
