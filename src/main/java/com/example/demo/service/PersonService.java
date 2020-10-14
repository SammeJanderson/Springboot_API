package com.example.demo.service;

import com.example.demo.doa.PersonDoa;
import com.example.demo.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PersonService {

   private final PersonDoa personDoa;

    @Autowired
    public PersonService(@Qualifier("fakeDao") PersonDoa personDoa) {
        this.personDoa = personDoa;
    }


    public int addPerson(Person person){
        return personDoa.insertPerson(person);
    }

      public List<Person> getAllPeople(){
        return personDoa.selectAllPeople();
      }

      public Optional<Person> getPersonById(UUID id){
        return personDoa.selectPersonbyId(id);
      }
}
