package com.example.rest;

import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ExecutionException;

@RestController
public class CRUDContoller {


public CRUDService crudService;
public CRUDContoller(CRUDService crudService){
    this.crudService=crudService;
}

@PostMapping("/create")
    public String createCRUD(@RequestBody CRUD crud) throws InterruptedException, ExecutionException{
    return crudService.createCRUD(crud);
}
@GetMapping("/get")
    public String getCRUD(@RequestParam String documentId) throws InterruptedException, ExecutionException{
        return crudService.getCRUD(documentId);
    }
}

