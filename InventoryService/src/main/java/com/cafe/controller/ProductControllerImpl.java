package com.cafe.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

//import com.cafe.jwt.JwtFilter;
import com.cafe.Wrapper.ProductWrapper;
import com.cafe.constants.CafeConstants;
import com.cafe.service.ProductService;
import com.cafe.utils.CafeUtils;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author rasmi
 *
 */
@CrossOrigin
@Slf4j
@RestController
public class ProductControllerImpl implements ProductController {
	
//	@Autowired
//	JwtFilter jwtFilter;
	
	@Autowired
	ProductService productService;


	@Override
	public ResponseEntity<String> addNewProduct(Map<String, String> requestMap) {
		// TODO Auto-generated method stub
		try {
			return productService.addNewProduct(requestMap);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return CafeUtils.getResponseEntity(CafeConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR );
	
	}

	@Override
	public ResponseEntity<List<ProductWrapper>> getAllProduct() {
		try {
			log.info("Display Product ",productService.getAllProduct());
			return productService.getAllProduct();
		}catch(Exception ex) {
			ex.printStackTrace();
			
		}
		
		return new ResponseEntity<>(new ArrayList<>(),HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@Override
	public ResponseEntity<String> updateProduct(Map<String, String> requestMap) {
		
		log.info("Update {}",requestMap );
		try {
			if(true) {
//				if(jwtFilter.isAdmin()) {
				productService.updateProduct(requestMap);
				return CafeUtils.getResponseEntity("Product Updated SuccessFul", HttpStatus.OK);
				
			}
			else {
				return CafeUtils.getResponseEntity(CafeConstants.UNATHORIZED_ACCESS, HttpStatus.UNAUTHORIZED);
			}
		}
		catch(Exception ex) {
			ex.printStackTrace(); 
			
		}
		return CafeUtils.getResponseEntity(CafeConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
	}


	@Override
	public ResponseEntity<String> deleteProduct(Integer id) {
		try {
			return productService.deleteProduct(id);
			}
		catch(Exception ex) {
			ex.printStackTrace();
			
		}
		return CafeUtils.getResponseEntity(CafeConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	
	@Override
	public ResponseEntity<String> updateStatus(Map<String, String> requestMap) {
		
		log.info("now updated data is inside updateStaus");
		try {
			return productService.updateStatus(requestMap);
			
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return CafeUtils.getResponseEntity(CafeConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@Override
	public ResponseEntity<List<ProductWrapper>> getByCategory(Integer id) {
		try {
			return productService.getByCategory(id);
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	@Override
	public ResponseEntity<ProductWrapper> getProductById(Integer id) {
		try {
			return productService.getProductById(id);
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return new ResponseEntity<>(new ProductWrapper(),HttpStatus.INTERNAL_SERVER_ERROR);
	}


}
