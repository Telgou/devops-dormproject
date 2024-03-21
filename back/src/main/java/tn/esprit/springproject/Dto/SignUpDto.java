package tn.esprit.springproject.Dto;

import tn.esprit.springproject.entity.Role;

public record SignUpDto (String firstName, String lastName, String login, char[] password, Role role){


}
