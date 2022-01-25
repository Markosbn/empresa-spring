package com.marcosschulz.lancamentoempresa.domain.service;

import com.marcosschulz.lancamentoempresa.domain.model.Empresa;
import com.marcosschulz.lancamentoempresa.domain.repository.EmpresaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@Service
public class EmpresaService {

    private EmpresaRepository empresaRepository;

    @Transactional//inserção
    public Empresa insertEmpresa(Empresa empresa){
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
        empresa.setId(id);
        empresa = empresaRepository.save(empresa);
        return empresa;
    }

    @Transactional
    public List<Empresa> buscaTodas(){
        return empresaRepository.findAll();
    }

    @Transactional
    public Empresa buscaPorId(Long id){
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
