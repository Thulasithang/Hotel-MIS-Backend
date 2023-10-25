package com.WHotels.HotelMIS.controller;

import com.WHotels.HotelMIS.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.WHotels.HotelMIS.service.TableService;
import com.WHotels.HotelMIS.model.Table;

import java.util.List;

@RestController
@RequestMapping(path="api/v1/table")
class TableController {
    private final TableService tableService;
    private final ReservationService reservationService;

    @Autowired
    TableController(TableService tableService, ReservationService reservationService) {
        this.tableService = tableService;
        this.reservationService=reservationService;
    }

    @GetMapping("/getAllTables")
    public List<Table> getTables(){
        List<Table> tables = tableService.getTables();
        // Iterate through the tables and set the reserved property
        for (Table table : tables) {
            boolean isReserved = reservationService.isReserved((int) table.getId());
            table.setReserved(isReserved);
        }
        return tables;

    }

    @PatchMapping("/update/{tableId}")
    public ResponseEntity<String> updateTableStatus(
            @PathVariable Long tableId,
            @RequestParam(value = "waiterRequested", required = false) Boolean waiterRequested,
            @RequestParam(value = "occupied", required = false) Boolean occupied) {
        //System.out.println("Table id fron the front"+tableId.toString());
        //System.out.println("Table id fron the front"+waiterRequested.toString());
        try {
            tableService.updateTableStatus(tableId, waiterRequested, occupied);
            return ResponseEntity.ok("Table status updated successfully: "+tableId.toString());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error updating table status: " + e.getMessage());
        }
        
    }




}
