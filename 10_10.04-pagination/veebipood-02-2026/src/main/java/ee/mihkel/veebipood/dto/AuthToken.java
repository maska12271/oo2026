package ee.mihkel.veebipood.dto;

import lombok.Data;

@Data
public class AuthToken {
    private String token;
    private Long expiration;
}
