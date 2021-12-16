package com.lelakowski.ERPMetalbud.pim.domain.repository;

import com.lelakowski.ERPMetalbud.pim.domain.model.Profession;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.*;

@RequiredArgsConstructor
public class TestProfessionRepository implements ProfessionRepository {

    final private TreeMap<Long, Profession> data;

    @Override
    public List<Profession> findAll() {
        return new ArrayList<>(data.values());
    }

    @Override
    public List<Profession> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<Profession> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public List<Profession> findAllById(Iterable<Long> iterable) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public void delete(Profession profession) {

    }

    @Override
    public void deleteAll(Iterable<? extends Profession> iterable) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public <S extends Profession> S save(S s) {
        TreeSet<Long> keys = new TreeSet<>(data.keySet());
        Long maxKey = keys.last();
        Long nextValue = ++maxKey;
        data.put(nextValue, s);
        return s;
    }

    @Override
    public <S extends Profession> List<S> saveAll(Iterable<S> iterable) {
        return null;
    }

    @Override
    public Optional<Profession> findById(Long aLong) {
        return Optional.of(data.get(aLong));
    }

    @Override
    public boolean existsById(Long aLong) {
        return data.containsKey(aLong);
    }

    @Override
    public void flush() {

    }

    @Override
    public <S extends Profession> S saveAndFlush(S s) {
        return null;
    }

    @Override
    public void deleteInBatch(Iterable<Profession> iterable) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public Profession getOne(Long aLong) {
        return data.get(aLong);
    }

    @Override
    public <S extends Profession> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends Profession> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends Profession> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends Profession> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends Profession> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends Profession> boolean exists(Example<S> example) {
        return false;
    }
}
