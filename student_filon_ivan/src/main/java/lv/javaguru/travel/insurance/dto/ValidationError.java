package lv.javaguru.travel.insurance.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ValidationError {

    private String field;
    private String message;

    @Override
    public boolean equals(Object o) {
        return this.getField().equals(((ValidationError)o).getField())
                && this.getMessage().equals(((ValidationError)o).getMessage());
    }
}
