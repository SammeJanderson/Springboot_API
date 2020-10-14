package com.example.demo.doa;

import com.example.demo.model.Person;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PersonDoa {

    //método para inserir pessoa com id
    int insertPerson(UUID id, Person person);

    //método pra inserir pessoa sem id gerando um aleatorio.
    default int insertPerson(Person person){
        UUID id = UUID.randomUUID();
        return insertPerson(id, person);
    }
    List<Person> selectAllPeople();

    Optional<Person> selectPersonbyId(UUID id);

    int deletePersonById(UUID id);

    int updatePersonbyId(UUID id, Person person);
}
