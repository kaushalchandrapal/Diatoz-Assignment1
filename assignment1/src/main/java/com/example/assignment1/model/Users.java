package com.example.assignment1.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Getter
@Setter
@ToString

@Document(collection = "Users")
public class Users {

    public enum UserStatusValues{
        ACTIVE {
            public String toString() {
                return "ACTIVE";
            }
        },
        DELETED{
            public String toString() {
                return "DELETED";
            }
        },
        INACTIVE{
            public String toString() {
                return "INACTIVE";
            }
        },
        LOCKED{
            public String toString() {
                return "LOCKED";
            }
        }
    }
    @Id
    private int userId;
    private String userName;
    private String emailId;
    private String mobileNumber;
//    @DateTimeFormat(iso = ISO.DATE_TIME)
    private String createdOn;
    private String updatedOn;
    private UserStatusValues userStatus;
}
