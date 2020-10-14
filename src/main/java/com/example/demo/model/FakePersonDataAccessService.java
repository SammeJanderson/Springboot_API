package com.example.demo.model;

import com.example.demo.doa.PersonDoa;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("fakeDao")                                              //permite ter multiplas implementações
public class FakePersonDataAccessService implements PersonDoa {
    private static List<Person> DB = new ArrayList<>();

    @Override
    public int insertPerson(UUID id, Person person) {
        DB.add(new Person(id, person.getName()));
        return 1;
    }

    @Override
    public List<Person> selectAllPeople() {
        return DB;
    }

    @Override
    public Optional<Person> selectPersonbyId(UUID id) {
        return DB.stream()
                .filter(person -> person.getId().equals(id))
                .findFirst();
    }

    @Override
    public int deletePersonById(UUID id) {
        Optional<Person> personMaybe = selectPersonbyId(id);
        if (personMaybe.isEmpty()){
            return 0;
        }
        DB.remove(personMaybe.get());
        return 1;
    }

    @Override
    public int updatePersonbyId(UUID id, Person person) {
        return selectPersonbyId(id)
                .map(p ->{
                    int indexOfPersonTODelete = DB.indexOf(person);
                    if (indexOfPersonTODelete >= 0) {
                        DB.set(indexOfPersonTODelete, person);
                        return 1;
                    }
                    return 0;
                })
                .orElse(0);
    }
}