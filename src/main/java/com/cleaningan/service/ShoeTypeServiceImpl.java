package com.cleaningan.service;

import com.cleaningan.constant.MessageException;
import com.cleaningan.entity.ShoeType;
import com.cleaningan.repository.IShoeTypeRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ShoeTypeServiceImpl implements IShoeTypeService{

    private final IShoeTypeRepository shoeTypeRepository;

    public ShoeTypeServiceImpl(IShoeTypeRepository shoeTypeRepository) {
        this.shoeTypeRepository = shoeTypeRepository;
    }

    @Override
    public List<ShoeType> findAll() {
        try {
            return shoeTypeRepository.findAllShoeType();
        } catch (Exception e) {
            throw new RuntimeException(MessageException.INTERNAL_SERVER_ERROR.toString());
        }
    }

    @Override
    public ShoeType save(ShoeType shoeType) {
        try {
            boolean existsShoeTypeId = shoeTypeRepository.existsByShoeTypeId(shoeType.getShoeTypeId());
            if (existsShoeTypeId) throw new DataIntegrityViolationException(MessageException.DATA_EXIST.toString());
            return shoeTypeRepository.save(shoeType);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException(Objects.requireNonNull(e.getMessage()));
        }
    }

    @Override
    public ShoeType findOne(Integer id) {
        try {
            Optional<ShoeType> shoeTypeById = shoeTypeRepository.findByShoeTypeId(id);
            if (shoeTypeById.isEmpty())
                throw new DataIntegrityViolationException(MessageException.NOT_FOUND.toString());
            return shoeTypeById.get();
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException(Objects.requireNonNull(e.getMessage()));
        } catch (Exception e) {
            throw new RuntimeException(MessageException.INTERNAL_SERVER_ERROR.toString());
        }
    }

    @Override
    public ShoeType update(Integer id, ShoeType shoeType) {
        try {
            boolean existsByShoeTypeId = shoeTypeRepository.existsByShoeTypeId(id);
            if (!existsByShoeTypeId) throw new DataIntegrityViolationException(MessageException.NOT_FOUND.toString());
            shoeType.setShoeTypeId(id);
            return shoeTypeRepository.save(shoeType);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException(Objects.requireNonNull(e.getMessage()));
        }
    }

    @Override
    public ShoeType delete(Integer id) {
        try {
            Optional<ShoeType> shoeTypeById = shoeTypeRepository.findByShoeTypeId(id);
            if (shoeTypeById.isEmpty())
                throw new DataIntegrityViolationException(MessageException.NOT_FOUND.toString());
            shoeTypeRepository.deleteByShoeTypeId(id);
            return shoeTypeById.get();
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException(Objects.requireNonNull(e.getMessage()));
        } catch (Exception e) {
            throw new RuntimeException(MessageException.INTERNAL_SERVER_ERROR.toString());
        }
    }

    @Override
    public ShoeType findOne(String id) {
        return null;
    }

    @Override
    public ShoeType update(String id, ShoeType data) {
        return null;
    }

    @Override
    public ShoeType delete(String id) {
        return null;
    }
}
