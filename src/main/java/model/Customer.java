package model;


import lombok.*;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Customer {

    private String id;

    private String name;

    private String address;

    private String phone;

}
