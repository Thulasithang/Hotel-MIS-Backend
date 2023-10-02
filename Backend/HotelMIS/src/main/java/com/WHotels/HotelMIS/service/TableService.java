package com.WHotels.HotelMIS.service;
import org.springframework.data.domain.Sort;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.WHotels.HotelMIS.repository.TableRepository;
import com.WHotels.HotelMIS.model.Table;

import java.util.List;
import java.util.Optional;

@Service
public class TableService
{
    private final TableRepository tableRepository;

    @Autowired
    public TableService(TableRepository tableRepository) {
        this.tableRepository = tableRepository;
    }

    public List<Table> getTables(){

        return tableRepository.findByOrderByIdAsc();
    }

    public void updateTableStatus(Long tableId, Boolean waiterRequested, Boolean occupied) {
        Optional<Table> optionalTable = tableRepository.findById(tableId);
        if (optionalTable.isPresent()) {
            Table table = optionalTable.get();
            if (waiterRequested != null) {
                table.setWaiterRequested(waiterRequested);
            }
            if (occupied != null) {
                table.setOccupied(occupied);
            }
            tableRepository.save(table);
        } else {
            throw new EntityNotFoundException("Table with ID " + tableId + " not found");
        }
    }

}
