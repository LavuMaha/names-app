package com.galvanize;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins ="*", allowedHeaders ="*")
@RestController
@RequestMapping("/names")
public class NameController {
    NameService nameService;

    public NameController(NameService nameService){
        this.nameService = nameService;
    }

    @PostMapping
    public ResponseEntity postName(@RequestBody NewName name){
        System.out.println("Creating name...");        
        return ResponseEntity.ok(nameService.post(name));
    }

    @GetMapping("/{id}")
    public ResponseEntity getName(@PathVariable Long id){
        System.out.println("Getting name with id...");
        return ResponseEntity.of(nameService.getName(id));
    }

    @PatchMapping("/{id}")
    public ResponseEntity patchName(@PathVariable Long id, @RequestBody NewName newName){
        System.out.println("Updating name with id...");
        return ResponseEntity.of(nameService.patchName(id, newName));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteName(@PathVariable Long id){
        System.out.println("Deleting name with id...");
        return ResponseEntity.ok(nameService.deleteName(id));
    }

    @GetMapping
    public ResponseEntity getAllNames(){
        System.out.println("Fetching all names...");
        return ResponseEntity.ok(nameService.getAllNames());
    }
}
