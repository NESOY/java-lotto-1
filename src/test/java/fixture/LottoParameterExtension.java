package fixture;

import domain.lotto.Lotto;
import domain.lotto.LottoBuilder;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.api.extension.ParameterResolver;

public class LottoParameterExtension implements ParameterResolver {

    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.PARAMETER)
    public @interface MockLottoList {

        int size() default 0;
    }

    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.PARAMETER)
    public @interface MockLotto {

    }


    @Override
    public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        return parameterContext.isAnnotated(MockLottoList.class) || parameterContext.isAnnotated(MockLotto.class);
    }

    @Override
    public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        if (parameterContext.getParameter().getType() == List.class && parameterContext.isAnnotated(MockLottoList.class)) {
            Optional<MockLottoList> annotation = parameterContext.findAnnotation(MockLottoList.class);
            return new LottoBuilder().size(annotation.get().size()).build();
        } else if (parameterContext.getParameter().getType() == Lotto.class && parameterContext.isAnnotated(MockLotto.class)) {
            return new LottoBuilder().size(1).build().get(0);
        }

        throw new ParameterResolutionException("Not Supported Annotation in Lotto Extension");
    }
}