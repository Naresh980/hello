package com.ltp.springbootcrudexample.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
@Table(name = "PRODUCT_TBL")
public class Product {
    @Id
    @GeneratedValue
    private int id;

    @NotNull(message = "name cannot be blank")
    @Pattern(regexp = "^[A-Z,a-z]*$",message = "enter only alphabets")
    @Size(min=3,max = 10,message = "name should be min=3 and max=10")
    private String name;

    private String mobilenumber;

  @NotBlank(message = "enter mail id")
   @Email(message = "enter correct email id")
    private String gmail;
}
