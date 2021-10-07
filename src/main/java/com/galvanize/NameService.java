package com.galvanize;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NameService {
    NameRepository nameRepository;

    public NameService(NameRepository nameRepository){
        this.nameRepository = nameRepository;
    }

    public NewName post(NewName before) {
        return nameRepository.save(before);
    }

    public Optional<NewName> getName(long l) {
        return nameRepository.findById(l);
    }

    public Optional<NewName> patchName(long id, NewName newName) {
        if(getName(id).get()!=null) {
            newName.setId(id);
            return Optional.of(post(newName));
        }
        else return null;
    }

    public boolean deleteName(long id) {
        if(getName(id).get()!=null){
            nameRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public List<NewName> getAllNames() {
        return nameRepository.findAll();
    }
}
