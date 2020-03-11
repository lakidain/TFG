package com.auditorias.springboot.backend.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.auditorias.springboot.backend.dto.DtoAuditList;
import com.auditorias.springboot.backend.mapper.AuditMapper;
import com.auditorias.springboot.backend.mapper.EmpresaMapper;
import com.auditorias.springboot.backend.mapper.UsuarioMapper;
import com.auditorias.springboot.backend.model.Audit;
import com.auditorias.springboot.backend.model.Audit_Type;

@CrossOrigin(origins = { "http://localhost:4200","*" }) // CrossOrigin es un porotocolo para comunicar peticiones que se
// realizan al navegador, desde aqui podemos controlar todo
// (metodos, direcciones)
@RestController // Como no va a tener vista
@RequestMapping("/api") // Aqui nos generara la url
public class AuditRestController {

	private AuditMapper auditMapper;
	private EmpresaMapper empresaMapper;
	private UsuarioMapper usuarioMapper;

	public AuditRestController(AuditMapper auditMapper, EmpresaMapper empresaMapper, UsuarioMapper usuarioMapper) {
		this.auditMapper = auditMapper;
		this.empresaMapper = empresaMapper;
		this.usuarioMapper = usuarioMapper;
	}

	/*
	 * Search a List of the Audits assigned to a worker
	 */
	@GetMapping("/audit/{id}")
	public List<DtoAuditList> getAuditsAssigned(@PathVariable Long id) {
		List<DtoAuditList> auditList = new ArrayList<DtoAuditList>();

		List<Audit> lista = auditMapper.getAuditsAssigned(id);

		for (int i = 0; i < lista.size(); i++) {
			DtoAuditList element = new DtoAuditList();
			element.setDate_end_audit(lista.get(i).getDate_end_audit());
			element.setDate_start_audit(lista.get(i).getDate_start_audit());
			element.setId_audit(lista.get(i).getId_audit());
			element.setId_audit_type(lista.get(i).getId_audit_type());
			element.setId_company_audited(lista.get(i).getId_company_audited());
			element.setId_company_auditing(lista.get(i).getId_company_auditing());
			element.setId_user_manager(lista.get(i).getId_user_manager());
			element.setName_audit_type(auditMapper.getNameType(lista.get(i).getId_audit_type()).get(0).getName_audit_type());
			element.setName_company(empresaMapper.getCompanyName(lista.get(i).getId_company_audited()).get(0).getName_company());
			auditList.add(element);
		}

		return auditList;
	}
	
	/*
	 * Search a List of the Audits related to an employee for an audited company
	 */
	@GetMapping("/auditAudited/{id}")
	public List<DtoAuditList> getAuditsRelated(@PathVariable Long id) {
		List<DtoAuditList> auditList = new ArrayList<DtoAuditList>();

		List<Audit> lista = auditMapper.getAuditsRelated(id);

		for (int i = 0; i < lista.size(); i++) {
			DtoAuditList element = new DtoAuditList();
			element.setDate_end_audit(lista.get(i).getDate_end_audit());
			element.setDate_start_audit(lista.get(i).getDate_start_audit());
			element.setId_audit(lista.get(i).getId_audit());
			element.setId_audit_type(lista.get(i).getId_audit_type());
			element.setId_company_audited(lista.get(i).getId_company_audited());
			element.setId_company_auditing(lista.get(i).getId_company_auditing());
			element.setId_user_manager(lista.get(i).getId_user_manager());
			element.setName_audit_type(auditMapper.getNameType(lista.get(i).getId_audit_type()).get(0).getName_audit_type());
			element.setName_company(empresaMapper.getCompanyName(lista.get(i).getId_company_auditing()).get(0).getName_company());
			auditList.add(element);
		}

		return auditList;
	}
	
	/*
	 * Search a List of the Audits assigned to a worker
	 */
	@GetMapping("/auditCompany/{id}")
	public List<DtoAuditList> getCompanyAudits(@PathVariable Long id) {
		List<DtoAuditList> auditList = new ArrayList<DtoAuditList>();

		List<Audit> lista = auditMapper.getCompanyAudits(id);

		for (int i = 0; i < lista.size(); i++) {
			DtoAuditList element = new DtoAuditList();
			element.setDate_end_audit(lista.get(i).getDate_end_audit());
			element.setDate_start_audit(lista.get(i).getDate_start_audit());
			element.setId_audit(lista.get(i).getId_audit());
			element.setId_audit_type(lista.get(i).getId_audit_type());
			element.setId_company_audited(lista.get(i).getId_company_audited());
			element.setId_company_auditing(lista.get(i).getId_company_auditing());
			element.setId_user_manager(lista.get(i).getId_user_manager());
			element.setName_user_manager(usuarioMapper.getUsuario(lista.get(i).getId_user_manager()).get(0).getName_user());
			element.setName_audit_type(auditMapper.getNameType(lista.get(i).getId_audit_type()).get(0).getName_audit_type());
			element.setName_company(empresaMapper.getCompanyName(lista.get(i).getId_company_audited()).get(0).getName_company());
			auditList.add(element);
		}

		return auditList;
	}

	/**
	 * Returns a List with all the possible types to audit
	 * 
	 * @link Audit_Type
	 */
	@GetMapping("/auditTypes")
	public List<Audit_Type> getAll() {
		return auditMapper.findAuditTypes();
	}

	/*
	 * Create Audit
	 */
	@PostMapping("/audit")
	@ResponseStatus(HttpStatus.CREATED)
	public boolean create(@Valid @RequestBody Audit audit) { // Como viene en formato JSON es necesario convertirlo
		auditMapper.insert(audit); // Esto habria que revisarlo, parece que MYBATIS no puede devolver una clase
		return true;
	}
	
	/* Add Employee Associated with an appointment */
	@PostMapping("/addAuditEmployee")
	public boolean addAuditEmployee() {
		return true;
	}
	
	/* Modify an Audit */
	@PutMapping("/audit/{id}")
	public boolean updateAudit(@Valid @RequestBody Audit audit, @PathVariable Long id){
		auditMapper.updateAudit(audit);
		return true;
	}
	
	/* Delete Audit */
	@DeleteMapping("/audit/{id}")
	@ResponseStatus(HttpStatus.ACCEPTED)
	public boolean deleteAudit(@PathVariable Long id) {
		auditMapper.deleteAudit(id);
		return true;
	}
}
