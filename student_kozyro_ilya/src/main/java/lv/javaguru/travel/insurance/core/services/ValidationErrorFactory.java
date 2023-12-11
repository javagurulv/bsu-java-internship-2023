package lv.javaguru.travel.insurance.core.services;

import lv.javaguru.travel.insurance.core.util.ErrorFileLoaderUtil;
import lv.javaguru.travel.insurance.dto.Placeholder;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Component
public class ValidationErrorFactory {

    @Autowired
    ErrorFileLoaderUtil errorFileLoaderUtil;
    public ValidationError buildError(String code) {
        AtomicReference<String> src = new AtomicReference<>(errorFileLoaderUtil.getErrorDescription(code));
        return new ValidationError(code, src.get());
    }

    public ValidationError buildError(String code, List<Placeholder> placeholders) {
        var desr = errorFileLoaderUtil.getErrorDescription(code);
        AtomicReference<String> src = new AtomicReference<>(errorFileLoaderUtil.getErrorDescription(code));
        placeholders.forEach(
                p -> src.set(replaceText(src.get(), p.getKey(), p.getValue()))
        );
        return new ValidationError(code, src.get());
    }

    public ValidationError buildError(String code, Placeholder placeholder) {
        return buildError(code, List.of(placeholder));
    }

    private String replaceText(String src, String it, String to) {
        return src.replace("${".concat(it).concat("}"), to);
    }
}
