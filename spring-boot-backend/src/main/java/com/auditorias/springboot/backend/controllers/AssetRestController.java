package com.auditorias.springboot.backend.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.auditorias.springboot.backend.dto.DtoAssetCreation;
import com.auditorias.springboot.backend.mapper.AssetMapper;
import com.auditorias.springboot.backend.model.Audit_Asset;
import com.auditorias.springboot.backend.model.Audit_Threat;
import com.auditorias.springboot.backend.model.Audit_Type;

@CrossOrigin(origins= {"http://localhost:4200"}) //CrossOrigin es un porotocolo para comunicar peticiones que se realizan al navegador, desde aqui podemos controlar todo (metodos, direcciones)
@RestController //Como no va a tener vista
@RequestMapping("/api") //Aqui nos generara la url
public class AssetRestController {

	private AssetMapper assetMapper;
	
	public AssetRestController(AssetMapper assetMapper) {
		this.assetMapper = assetMapper;
	}
	
	/**
	 * Returns  
	 * @link      Audit_Type
	 */
	@GetMapping("/assets") //Para generar el endpoint
	public List <Audit_Asset> getAllAssets() {	
		return assetMapper.findAllAssets();
	}
	
	/*
	 *  Modify an audit asset 
	 */
	@PutMapping("/assets/{id}")
	public boolean updateAuditAsset(@RequestBody Audit_Asset auditAsset, @PathVariable Long id){
		assetMapper.updateAuditAsset(auditAsset);
		return true;
	}
	
	/*
	 * Get Threat List related to an Asset
	 */
	@GetMapping("assetThreats/{id}")
	public List<Audit_Threat> assetThreats(@PathVariable Long id){
		return assetMapper.assetThreats(id);
	}
	
	/*
	 * Returns a List with all the threats
	 */
	@GetMapping("/threats") //Para generar el endpoint
	public List <Audit_Threat> getAllThreats() {	
		return assetMapper.findAllThreats();
	}
	
	/*
	 *  Modify an audit threat 
	 */
	@PutMapping("/threats/{id}")
	public boolean updateAuditThreat(@RequestBody Audit_Threat auditThreat, @PathVariable Long id){
		assetMapper.updateAuditThreat(auditThreat);
		return true;
	}
	
	/**
	 * Creation of an audit type
	 */
	@PostMapping("/type" )
	@ResponseStatus(HttpStatus.CREATED)
	public boolean createType(@RequestBody Audit_Type audit_type) {	//Como viene en formato JSON es necesario convertirlo	
		assetMapper.insertAuditType(audit_type);	//Esto habria que revisarlo, parece que MYBATIS no puede devolver una clase
		return true;	
	}
	
	/*
	 * Get Assets List related to a type
	 */
	@GetMapping("typeAssets/{id}")
	public List<Audit_Asset> typeAssets(@PathVariable Long id){
		return assetMapper.typeAssets(id);
	}
	
	/*
	 *  Modify an audit type 
	 */
	@PutMapping("/type/{id}")
	public boolean updateAuditType(@RequestBody Audit_Type auditType, @PathVariable Long id){
		assetMapper.updateAuditType(auditType);
		return true;
	}
	
	/**
	 * Delete of an audit type
	 */
	@DeleteMapping("/type/{id}" )
	@ResponseStatus(HttpStatus.ACCEPTED)
	public boolean deleteType(@PathVariable Long id) {	//Como viene en formato JSON es necesario convertirlo	
		assetMapper.removeAuditType(id);	//Esto habria que revisarlo, parece que MYBATIS no puede devolver una clase
		return true;	
	}
	
	/**
	 * Creation of an audit asset
	 */
	@PostMapping("/assets" )
	@ResponseStatus(HttpStatus.CREATED)
	public boolean createAsset(@RequestBody DtoAssetCreation dtoAssetCreation) {	//Como viene en formato JSON es necesario convertirlo	
		if(!("".contentEquals(dtoAssetCreation.getName_audit_asset()))) {	/* Just need to create the asset if it's new*/
			assetMapper.insertAsset(dtoAssetCreation);
			dtoAssetCreation.setId_audit_asset(assetMapper.findAsset(dtoAssetCreation).get(0).getId_audit_asset());
		}
		/* If the field text is not completed we use the select, not need to create the asset, just the association */
		assetMapper.associateTypeAsset(dtoAssetCreation.getId_audit_type(), dtoAssetCreation.getId_audit_asset());
		return true;	
	}
	
	/**
	 * Delete of an audit asset association
	 */
	@DeleteMapping("/assets/{id_audit_asset}/{id_audit_type}" )
	@ResponseStatus(HttpStatus.ACCEPTED)
	public boolean deleteAsset(@PathVariable Long id_audit_asset, @PathVariable Long id_audit_type) {	//Como viene en formato JSON es necesario convertirlo	
		assetMapper.deleteAsset(id_audit_asset,id_audit_type);	//Esto habria que revisarlo, parece que MYBATIS no puede devolver una clase
		return true;	
	}
	
	/*
	 * Creation of a Threat
	 */
	@PostMapping("/threats" )
	@ResponseStatus(HttpStatus.CREATED)
	public boolean createThreat(@RequestParam("newThreat") String newThreat, @RequestParam("assetThreat") Long assetThreat, @RequestParam("existingThreat") Long existingThreat) {	//Como viene en formato JSON es necesario convertirlo	
		
		if(!("".contentEquals(newThreat))) {	/* Just need to create the threat if it's new*/
			assetMapper.insertThreat(newThreat);
			existingThreat = assetMapper.findThreat(newThreat).get(0).getId_audit_threat();
		}
		
		/* Para un tipo de auditoria concreto una amenaza podra aparecer solo una vez, si ya existe lanzamos error*/
		
		/* If the field text is not completed we use the select, not need to create the asset, just the association */
		assetMapper.associateAssetThreat(assetThreat, existingThreat);
		return true;	
	}
	
	/**
	 * Delete of an audit threat association
	 */
	@DeleteMapping("/threats/{id_audit_threat}/{id_audit_asset}" )
	@ResponseStatus(HttpStatus.ACCEPTED)
	public boolean deleteThreat(@PathVariable Long id_audit_threat, @PathVariable Long id_audit_asset) {	//Como viene en formato JSON es necesario convertirlo	
		assetMapper.deleteThreat(id_audit_threat,id_audit_asset);	//Esto habria que revisarlo, parece que MYBATIS no puede devolver una clase
		return true;	
	}
	
	/*
	 * Get Assets List for an audit
	 */
	@GetMapping("auditAssets/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public List<Audit_Asset> auditAssets(@PathVariable Long id){
		return assetMapper.auditAssets(id);
	}
}
