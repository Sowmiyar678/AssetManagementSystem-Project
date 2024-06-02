package com.ams.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import com.ams.entity.Asset;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
@Repository
@Transactional
public class AssetDAOImpl implements AssetDAO {

private EntityManager eman;
	
	public AssetDAOImpl() {
		super();
	}
	
	@Autowired
	public AssetDAOImpl(EntityManager entity) {
		super();
		this.eman=entity;
	}
	
	@Override
    public ResponseEntity<String> addasset(Asset asset) {
        try {
            eman.persist(asset);
 
        } catch (Exception e) {
            e.printStackTrace();
        }
		return null;
    }
	
	
	
	public void updateAssetInfo(Asset asset) {
	    try {
	        eman.createQuery
	        ("UPDATE Asset E SET E.status = :status,  E.employee.empid = :employee WHERE E.assetid= :assetid")
	      
	       .setParameter("status", asset.getStatus())
	       .setParameter("employee", asset.getEmployee())
	       .executeUpdate();

	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	
	  @Override
			public ResponseEntity<?> updateAsset(long assetid, Asset updateAsset) {
				Asset existingAsset = eman.find(Asset.class, assetid);
						
		        if (existingAsset == null) {
		            return ResponseEntity.badRequest().body("Asset not found");
		        }
		        
		        // Update
		        existingAsset.setStatus(updateAsset.getStatus());
		        existingAsset.setEmployee(updateAsset.getEmployee());
		 
		        
		        eman.persist(existingAsset);
		        return ResponseEntity.ok("Details updated successfully");
			}

	 @SuppressWarnings("unchecked")
		@Override
	    public List<Asset> getAssetById(Long assetid) {
	        List<Asset> asset = new ArrayList<>();
	        try {
	            asset = eman.createQuery("from Asset E where E.assetid = :assetid").setParameter("assetid", assetid).getResultList();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return asset;
	    }


	@SuppressWarnings("unchecked")
	@Override
    public List<Asset> getallAsset() {
        List<Asset> asset = new ArrayList<>();
        try {
            asset = eman.createQuery("from Asset E").getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return asset;
    }
	

	@Override
	public List<Asset> getByassetId(Long empid) {
		try {
			return eman.createQuery("FROM Asset where employee.empid =:empid", Asset.class)
			.setParameter("empid", empid).getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
			
		}
	}

	@Override
	public Object deleteAsset(Long assetid) {
		  List<Asset> AssetById = getAssetById(assetid);
		  eman.remove(AssetById);
		return AssetById;
		
	}

	

	




	


}
