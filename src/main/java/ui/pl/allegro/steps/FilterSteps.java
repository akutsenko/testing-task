package ui.pl.allegro.steps;

import io.qameta.allure.Step;
import ui.pl.allegro.interfaces.FilterApplier;

import java.util.List;

public class FilterSteps {

    @Step("Applying all filters \"{filters}\"")
    public void applyAllFilters(List<FilterApplier> filters) {
        filters.forEach(this::applyFilter);
    }

    @Step("Applying filter \"{filter}\"")
    public void applyFilter(FilterApplier filter) {
        filter.applyFilter();
    }

    @Step("Checking that all filters \"{filters}\" were applied correctly")
    public void checkThatAllFiltersWereAppliedCorrectly(List<FilterApplier> filters) {
        filters.forEach(this::checkThatFilterIsAppliedCorrectly);
    }

    @Step("Checking that filter \"{filter}\" was applied correctly")
    public void checkThatFilterIsAppliedCorrectly(FilterApplier filter) {
        filter.checkThatFilterIsApplied();
    }
}
