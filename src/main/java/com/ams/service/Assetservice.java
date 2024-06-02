package com.ams.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import com.ams.entity.Asset;

@Repository
public interface Assetservice {

	public ResponseEntity<String> addasset(Asset asset);

	ResponseEntity<?> updateAsset(long assetid, Asset updatedAsset);

	List<Asset> getallAsset();

	Object deleteAsset(Long assetid);

	List<Asset> getAssetById(Long assetid);

	List<Asset> getByassetId(Long empid);
}