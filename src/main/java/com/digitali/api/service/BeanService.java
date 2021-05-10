package com.digitali.api.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.digitali.api.messages.ErrorMessages;
import com.digitali.api.messages.InfoMessages;

import io.jsonwebtoken.lang.Collections;

//Trata os objetos recebidos pela camada de negocio e se responsabiliza por persistir os dados
public abstract class BeanService<T, R extends JpaRepository>{

    private R repository;

    public BeanService(R repository){
        this.repository = repository;
    }

    public String processUpdate(T t) {
        this.repository.save(t);
        return InfoMessages.USER_UPDATED;
    }

    private String processInsert(T t) {
        this.repository.save(t);
        return InfoMessages.USER_INSERTED;
    }

    private String processRemove(T t) throws Exception {
        this.repository.delete(t);
        return InfoMessages.USER_REMOVED;
    }
    
    protected String save(T t) throws Exception {
            if (Objects.nonNull(t)) {
//                    if (Objects.nonNull(t.getId())) {
                            processUpdate(t);
//                    } else {
                            processInsert(t);
//                    }
            }
            return InfoMessages.USER_EMPTY_NULL;
    }

    protected String update(T t) throws Exception{
        if (Objects.nonNull(t)){
            //Verifica se existe um id e confirma a atualizaçao
            if (true){
                repository.save(t);
                return InfoMessages.UPDATE_SUCESS;
            }
        }else{
            throw new Exception(ErrorMessages.OBJECT_NULL);
        }

        return null;
    }

    protected String insert(T t) throws Exception{
        if (Objects.nonNull(t)){
            //Verifica se existe um id e confirma a atualizaçao
            // TODO - imlementar
            if (true){
                repository.save(t);
                return InfoMessages.UPDATE_SUCESS;
            }
        }else{
            throw new Exception(ErrorMessages.OBJECT_NULL);
        }

        return null;
    }

    protected String remove(T t) throws Exception {
            if (Objects.nonNull(t)) {
                processRemove(t);
            }
            return InfoMessages.USER_EMPTY_NULL;
    }

    protected T find(int id) throws Exception {
        Optional<T> bean = repository.findById(id);
        if (Objects.isNull(bean) && Objects.isNull(bean.get())) {
            return bean.get();
        }
        return null;
    }

    protected List<T> list() throws Exception {
        List bean = repository.findAll();
        if (Collections.isEmpty(bean)) {
            return bean;
        }else{
            throw new Exception(InfoMessages.LIST_EMPTY);
        }
    }

    protected R getRepository(){
        return this.repository;
    }
}