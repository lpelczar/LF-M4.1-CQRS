package legacyfighter.dietary.taxconfigview;

import legacyfighter.dietary.TaxConfig;
import legacyfighter.dietary.TaxRule;
import legacyfighter.dietary.TaxRuleService;
import legacyfighter.dietary.Utils;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
class OldTaxConfigViewCreator {

    private final TaxRuleService taxRuleService;

    OldTaxConfigViewCreator(TaxRuleService taxRuleService) {
        this.taxRuleService = taxRuleService;
    }

    Map<String, List<TaxRule>>  create() {
        List< TaxConfig > taxConfigs = taxRuleService.findAllConfigs();

        Map<String, List<TaxRule>> map = new HashMap<>();
        for (TaxConfig tax : taxConfigs) {
            if (map.get(tax.getCountryCode()) == null) {

                if(tax.getTaxRules() == null) {
                    map.put(tax.getCountryCode(), new ArrayList<>());
                } else {
                    map.put(tax.getCountryCode(), new ArrayList<>(tax.getTaxRules()));

                }


            } else {
                map.get(tax.getCountryCode()).addAll(tax.getTaxRules());
            }
        }
        Map<String, List<TaxRule>> newRuleMap= new HashMap<>();
        for (Map.Entry<String, List<TaxRule>> taxList : map.entrySet()) {
            Collection<TaxRule> values = taxList.getValue();
            List<TaxRule> newList = values
                    .stream()
                    .filter(Utils.distinctByKey(TaxRule::getTaxCode))
                    .collect(Collectors.toList());
            newRuleMap.put(taxList.getKey(), newList);
        }

        return newRuleMap;
    }
}
