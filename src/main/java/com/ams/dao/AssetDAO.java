package com.ams.dao;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import com.ams.entity.Asset;
@Repository
public interface AssetDAO  {
	
	public ResponseEntity<String> addasset(Asset asset);

    List<Asset> getallAsset();

    Object deleteAsset(Long assetid);

	List<Asset> getAssetById(Long assetid);
 

	 ResponseEntity<?> updateAsset(long assetid,Asset updatedAsset);
	
    List<Asset> getByassetId(Long empid);
}