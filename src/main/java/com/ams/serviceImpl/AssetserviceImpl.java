package com.ams.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ams.dao.AssetDAO;
import com.ams.entity.Asset;
import com.ams.service.Assetservice;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class AssetserviceImpl implements Assetservice {
	@Autowired
	AssetDAO dao;

	@Override
	public List<Asset> getallAsset() {

		return dao.getallAsset();
	}

	@Override
	public List<Asset> getAssetById(Long assetid) {

		return dao.getAssetById(assetid);
	}

	@Override
	public List<Asset> getByassetId(Long empid) {

		return dao.getByassetId(empid);
	}

////////////////////////

	@Override
	public ResponseEntity<?> updateAsset(long assetid, Asset updatedAsset) {

		return dao.updateAsset(assetid, updatedAsset);
	}

	@Override
	public ResponseEntity<String> addasset(Asset asset) {

		return dao.addasset(asset);
	}

	@Override
	public Object deleteAsset(Long assetid) {

		return dao.deleteAsset(assetid);
	}

}
