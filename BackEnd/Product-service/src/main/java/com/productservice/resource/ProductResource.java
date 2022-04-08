package com.productservice.resource;

import java.util.List;
import java.util.Optional;

import com.productservice.helper.FileUploadHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.productservice.model.Product;
import com.productservice.service.ProductService;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@CrossOrigin
@RequestMapping("products")
public class ProductResource {

	@Autowired
	ProductService productService;
	
	@PostMapping("/addproducts")
	public void addProducts(@RequestBody Product product) {
		
		productService.addProducts(product);
	}
	
	@GetMapping("/allproducts")
	public ResponseEntity<List<Product>> getAllProducts(){
		
		return ResponseEntity.ok(productService.getAllProducts());
	}
	
	@GetMapping("/oneproduct/{id}")
	public ResponseEntity<Optional<Product>> getProductById(@PathVariable("id") String id) {
		
		return ResponseEntity.ok(productService.getProductById(id));
	}
	
	
	@GetMapping("/multipleproduct/{type}")
	public ResponseEntity<List<Product>> getProductByType(@PathVariable("type") String type) {

		return ResponseEntity.ok(productService.getProductByType(type));
	}

	@GetMapping("/single/{name}")
	public ResponseEntity<Product> getProductByName(@PathVariable("name") String name) {

		return ResponseEntity.ok(productService.getProductByName(name).get());
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<Product> updateProduct(@RequestBody Product product){

		return ResponseEntity.ok(productService.updateProducts(product));
	}
	
	@DeleteMapping("/delete/{id}")
	public void deleteProduct(@PathVariable("id") String id) {

		productService.deleteProductById(id); 
	}

	@Autowired
	private FileUploadHelper fileUploadHelper;

	@PostMapping("/upload-image")
	public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
		try {
			boolean f = fileUploadHelper.uploadFile(file);
			if(f)
			{
				return ResponseEntity.ok(ServletUriComponentsBuilder.fromCurrentContextPath().path("/image/").path(file.getOriginalFilename()).toUriString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Some kind of prob is there");
	}

}
