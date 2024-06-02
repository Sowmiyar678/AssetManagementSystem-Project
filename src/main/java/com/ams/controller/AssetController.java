package com.ams.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ams.entity.Asset;
import com.ams.serviceImpl.AssetserviceImpl;

@RestController
@CrossOrigin("http://localhost:3000/")

public class AssetController {
	@Autowired
	AssetserviceImpl dao;
	
	
	
	@PostMapping("/CreateAsset")
	public String addasset(@RequestBody Asset asset) {
		String msg="";
		try {
			dao.addasset(asset);
			msg="Asset Object Saved";
		}catch(Exception e) {	
			msg="Asset Object Not Saved";
		}
		return msg;
	}
	@DeleteMapping("DeleteAsset/{assetid}")
	public ResponseEntity<?> deleteAsset(@PathVariable Long assetid) {
	     try {
	         dao.deleteAsset(assetid);
	         return ResponseEntity.ok().build();
	     } catch (Exception e) {
	         // Handle the exception appropriately
	         return ResponseEntity.badRequest().body("Error deleting application: " + e.getMessage());
	     }
	}
	
	

	
	
//	update
//	  @PutMapping("/UpdateAsset")
//	    public String updateAssetInfo(@RequestBody Asset asset) {
//	        String msg = "";
//	        try {
//	            dao.updateAssetInfo(asset);
//	            msg = "Asset Details Updated";
//	        } catch (Exception e) {
//	            msg = "Asset Details not Updated";
//	        }
//	        return msg;
//	    }
	  @PutMapping("/updateAsset/{assetid}")
	    public ResponseEntity<?> updateAsset(@PathVariable("assetid") Long assetid,
	                                          @RequestBody Asset updatedAsset) {
	        return dao.updateAsset(assetid, updatedAsset);
	    }
	  
	  
	  
	  @GetMapping("/GetAssetById/{assetid}")
	    public List<Asset> getAsset(@PathVariable Long assetid) {
	        try {
	            return dao.getAssetById(assetid);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	 
	        return null;
	    }
	
	  @GetMapping("/GetAllAsset")
	    public List<Asset> getAllAsset() {
	        try {
	            return dao.getallAsset();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	 
	        return null;
	    }
	  

		@GetMapping("/getAssetByid/{empid}")
		public List<Asset> getByeassetId(@PathVariable Long empid){
			 try {
				return dao.getByassetId(empid);
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		}
	  
	  
}
