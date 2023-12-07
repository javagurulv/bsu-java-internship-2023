package lv.javaguru.travel.insurance.core.services;

import lv.javaguru.travel.insurance.core.util.ErrorFileLoaderUtil;
import lv.javaguru.travel.insurance.dto.Placer;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicReference;

@Component
public class ValidationErrorFactory {

    @Autowired
    ErrorFileLoaderUtil errorFileLoaderUtil;
    public ValidationError buildError(String code) {
        AtomicReference<String> src = new AtomicReference<>(errorFileLoaderUtil.getErrorDescription(code));
        return new ValidationError(code, src.get());
    }

    public ValidationError buildError(String code, List<Placer> placers) {
        AtomicReference<String> src = new AtomicReference<>(errorFileLoaderUtil.getErrorDescription(code));
        placers.forEach(
                p -> src.set(replaceText(src.get(), p.getKey(), p.getValue()))
        );
        return new ValidationError(code, src.get());
    }

    public ValidationError buildError(String code, Placer placer) {
        return buildError(code, List.of(placer));
    }

    private String replaceText(String src, String it, String to) {
        return src.replace("${".concat(it).concat("}"), to);
    }
}
