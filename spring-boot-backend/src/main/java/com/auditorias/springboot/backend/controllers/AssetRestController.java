package com.auditorias.springboot.backend.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.auditorias.springboot.backend.dto.DtoAssetCreation;
import com.auditorias.springboot.backend.mapper.AssetMapper;
import com.auditorias.springboot.backend.model.Audit_Asset;
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
	public List <Audit_Asset> getAll() {	
		return assetMapper.findAll();
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
	
	/**
	 * Creation of an audit asset
	 */
	@PostMapping("/assets" )
	@ResponseStatus(HttpStatus.CREATED)
	public boolean createAsset(@RequestBody DtoAssetCreation dtoAssetCreation) {	//Como viene en formato JSON es necesario convertirlo	
		if(dtoAssetCreation.getName_audit_asset() != "") {	/* Just need to create the asset if it's new*/
			assetMapper.insertAsset(dtoAssetCreation);
			dtoAssetCreation.setId_audit_asset(assetMapper.findName(dtoAssetCreation).get(0).getId_audit_asset());
		}
		/* If the field text is not completed we use the select, not need to create the asset, just the association */
		assetMapper.associateTypeAsset(dtoAssetCreation.getId_audit_type(), dtoAssetCreation.getId_audit_asset());
		return true;	
	}
}
