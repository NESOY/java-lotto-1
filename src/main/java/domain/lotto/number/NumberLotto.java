package domain.lotto.number;

import domain.lotto.Lotto;
import domain.lotto.LottoValueable;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class NumberLotto implements Lotto {

    List<LottoValueable> values;

    public NumberLotto(List<LottoValueable> items) {
        this.values = items;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        NumberLotto that = (NumberLotto) o;
        return Objects.equals(values, that.values);
    }

    @Override
    public String toString() {
        return values.stream()
                .map(Objects::toString)
                .collect(Collectors.joining(","));
    }

    @Override
    public int hashCode() {
        return Objects.hash(values);
    }
}