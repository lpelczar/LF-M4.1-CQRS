package legacyfighter.dietary.taxconfigview;

import legacyfighter.dietary.TaxConfig;
import legacyfighter.dietary.TaxConfigRepository;
import legacyfighter.dietary.TaxRule;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
class FasterTaxConfigViewCreator {

    private final TaxConfigRepository taxConfigRepository;

    FasterTaxConfigViewCreator(TaxConfigRepository taxConfigRepository) {
        this.taxConfigRepository = taxConfigRepository;
    }

    Map<String, List<TaxRule>> create() {
        return taxConfigRepository.getAllBy().stream()
                .collect(Collectors.groupingBy(TaxConfig::getCountryCode,
                        Collectors.mapping(
                                TaxConfig::getTaxRules,
                                Collectors.collectingAndThen(Collectors.toList(),
                                        list -> list.stream()
                                                .flatMap(List::stream)
                                                .collect(Collectors.toList())))
                ));
    }
}
