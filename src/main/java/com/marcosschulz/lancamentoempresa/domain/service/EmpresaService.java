package com.marcosschulz.lancamentoempresa.domain.service;

import com.marcosschulz.lancamentoempresa.domain.exception.EntitiesException;
import com.marcosschulz.lancamentoempresa.domain.model.Empresa;
import com.marcosschulz.lancamentoempresa.domain.repository.EmpresaRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class EmpresaService {

    private EmpresaRepository empresaRepository;

    @Transactional//inserção
    public Empresa insertEmpresa(Empresa empresa){

        boolean cnpjEmUso = empresaRepository.findByCnpj(empresa.getCnpj())
                .stream().anyMatch(emp -> !emp.equals(empresa));
        if (cnpjEmUso){
            throw new EntitiesException("Ja existe cliente cadastrado com este CNPJ!");
        }
        Empresa novaEmpresa = new Empresa();

        novaEmpresa.setId(empresa.getId());
        novaEmpresa.setBairro(empresa.getBairro());
        novaEmpresa.setCep(empresa.getCep());
        novaEmpresa.setCidade(empresa.getCidade());
        novaEmpresa.setEmail(empresa.getEmail());
        novaEmpresa.setCnpj(empresa.getCnpj());
        novaEmpresa.setDataCadastro(LocalDateTime.now());
        novaEmpresa.setFantasia(empresa.getFantasia());
        novaEmpresa.setFone(empresa.getFone());
        novaEmpresa.setIe(empresa.getIe());
        novaEmpresa.setLogradouro(empresa.getLogradouro());
        novaEmpresa.setNumero(empresa.getNumero());
        novaEmpresa.setRazaoSocial(empresa.getRazaoSocial());
        novaEmpresa.setUf(empresa.getUf());
        novaEmpresa.setStatus(empresa.getStatus());

        return empresaRepository.save(novaEmpresa);
    }

    @Transactional//deleção
    public void deleteEmpresa(Long id){
        empresaRepository.deleteById(id);
    }

    @Transactional//update
    public Empresa updateEmpresa(Long id, Empresa empresa){
       if (!empresaRepository.existsById(id)){
            return null;
        }
        empresa.setId(id);
        empresa = insertEmpresa(empresa);
        return empresa;
    }

    @Transactional
    public List<Empresa> buscaTodas(){
        return empresaRepository.findAll();
    }

    @Transactional
    public Empresa buscaPorId(Long id){
        Optional<Empresa> empresa = empresaRepository.findById(id);
        if (empresa.isEmpty()){
            return null;
        }
        return empresaRepository.findById(id).get();
    }

    @Transactional
    public List<Empresa> buscaPorRazaoSocial(String razaoSocial){
        return empresaRepository.findByRazaoSocial(razaoSocial);
    }

    @Transactional
    public List<Empresa> buscaPorRazaoSocialParcial(String razaoSocialParcial){
        List<Empresa> empresaList = empresaRepository.findByRazaoSocialContaining(razaoSocialParcial);
        return empresaList;
    }

    @Transactional
    public List<Empresa> buscaPorCNPJParcial(String cnpjBusca){
        List<Empresa> empresaList = empresaRepository.findByCnpjContaining(cnpjBusca);
        return empresaList;
    }
}
