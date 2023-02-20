package com.ltp.springbootcrudexample.service;

 //import com.mysql.springbootcrudexample.Exceptions.EmailException;
import com.ltp.springbootcrudexample.Exceptions.ItemNotFoundException;
import com.ltp.springbootcrudexample.Exceptions.MobileNumberException;
import com.ltp.springbootcrudexample.Exceptions.ProductNotFoundException;
import com.ltp.springbootcrudexample.entity.Product;
import com.ltp.springbootcrudexample.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class ProductService {
    @Autowired
    private ProductRepository repository;

    public Product saveProduct(Product product) throws Exception {
            String str = product.getMobilenumber();
            product.setMobilenumber(str);
            if(isValidMobileNumber(str)) {
                return repository.save(product);
            }else{
                throw new MobileNumberException("enter correct Mobile Number");
            }
    }

    private boolean isValidMobileNumber(String str) {
        Pattern ptrn = Pattern.compile("(0/91)?[7-9][0-9]{9}");
        Matcher match = ptrn.matcher(str);
        return (match.find() && match.group().equals(str));
}

    private boolean isValidGmail(String mtr) {
        Pattern ptrn = Pattern.compile("^(.+)@(.+)$");
        Matcher match = ptrn.matcher(mtr);
        return (match.find() && match.group().equals(mtr));
    }


    public List<Product> saveProducts(List<Product> products) {
        return repository.saveAll(products);
    }

    public Optional<Product> getProductById(int id)throws Exception {
        Optional<Product> product=repository.findById(id);
        if(product!=null){
            return product;
        }
        else {

            throw new ItemNotFoundException("Id not Found");
        }
    }

    public List<Product> getProducts() {
        return repository.findAll();
    }

    public Product getProductByName(String name) throws Exception {
        Product product = repository.findByName(name);
        if (product != null) {
            return product;
        } else {
            throw new ProductNotFoundException("Product name not found");
        }
    }

    public String deleteProduct(int id) {
        repository.deleteById(id);
        return "product removed !!" + id;
    }

    public Product updateProduct(Product product) {
        Product existingProduct = repository.findById(product.getId()).orElse(null);
        existingProduct.setName(product.getName());
        existingProduct.setMobilenumber(product.getMobilenumber());
        existingProduct.setGmail(product.getGmail());
        return repository.save(existingProduct);
    }
}
