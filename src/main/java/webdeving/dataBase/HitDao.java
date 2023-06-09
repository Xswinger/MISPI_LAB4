package webdeving.dataBase;

import webdeving.entity.Hit;

import java.util.List;

public interface HitDao {

    void add(Hit hit);

    List<Hit> getAll();

    void clear();

}
