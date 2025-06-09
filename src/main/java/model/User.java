package model;


import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User {

    private  String userName;

    private String email;

    private String password;
}
