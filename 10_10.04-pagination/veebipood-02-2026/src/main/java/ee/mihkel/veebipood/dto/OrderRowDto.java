package ee.mihkel.veebipood.dto;

public record OrderRowDto(
        int quantity,
        Long productId
) {
}
