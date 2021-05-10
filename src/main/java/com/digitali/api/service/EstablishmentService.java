package com.digitali.api.service;

import com.digitali.api.entity.Establishment;
import com.digitali.api.entity.request.EstablishmentRequest;
import com.digitali.api.repository.EstablishmentRepository;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;

@Service
public class EstablishmentService extends BeanService<Establishment, EstablishmentRepository> {
    
    @Autowired
    public EstablishmentService(EstablishmentRepository repository) {
        super(repository);
    }

    public String save(Establishment establishment) throws Exception {
        if (!Objects.isNull(establishment)){
            return super.save(establishment);
        }else{
            throw new Exception("Informações do estabelecimento vazias");
        }
    }

    public List<Establishment> findByDescription(String description) throws Exception {
        if (!Objects.isNull(description)){
            
            List<Establishment> establishmentsBean = list();
            
            if (!CollectionUtils.isEmpty(establishmentsBean)){
                return establishmentsBean;
            }else{
                throw new Exception("A consulta não retornou nenhum resultado!");
            }
        }else{
            throw new Exception("Informações do estabelecimento vazias");
        }
    }

//    public List<Establishment> findByDescription(String description) throws Exception {
//        if (!Objects.isNull(description)){
//
//            List<Establishment> establishmentsBean = repository.findAll();
//
//            if (!CollectionUtils.isEmpty(establishmentsBean)){
//                return establishmentsBean;
//            }else{
//                throw new Exception("A consulta não retornou nenhum resultado!");
//            }
//        }else{
//            throw new Exception("Informações do estabelecimento vazias");
//        }
//    }
    
    public List<Establishment> listAll() throws Exception {
        List<Establishment> establishmentsBean = list();
        if (!CollectionUtils.isEmpty(establishmentsBean)){
            return establishmentsBean;
        }else{
            throw new Exception("A consulta não retornou nenhum resultado!");
        }
     }
}