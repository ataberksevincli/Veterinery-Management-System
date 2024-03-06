package dev.patika.veterinary.business.concretes;

import dev.patika.veterinary.business.abstracts.IAvailableDateService;
import dev.patika.veterinary.core.exception.NotFoundException;
import dev.patika.veterinary.core.utilies.Msg;
import dev.patika.veterinary.dao.AvailableDateRepository;
import dev.patika.veterinary.entity.AvailableDate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class AvailableDateManager implements IAvailableDateService {

    private final AvailableDateRepository availableDateRepository;

    public AvailableDateManager(AvailableDateRepository availableDateRepository) {
        this.availableDateRepository = availableDateRepository;
    }

    @Override
    public AvailableDate save(AvailableDate availableDate) {
        return this.availableDateRepository.save(availableDate);
    }

    @Override
    public AvailableDate get(int id) {
        return this.availableDateRepository.findById(id).orElseThrow(() -> new NotFoundException(Msg.NOT_FOUND));
    }

    @Override
    public Page<AvailableDate> cursor(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page,pageSize);
        return this.availableDateRepository.findAll(pageable);
    }

    @Override
    public AvailableDate update(AvailableDate availableDate) {
        this.get(availableDate.getId());
        return this.availableDateRepository.save(availableDate);
    }

    @Override
    public boolean delete(int id) {
        AvailableDate availableDate = this.get(id);
        this.availableDateRepository.delete(availableDate);
        return true;
    }
}
