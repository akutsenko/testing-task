package ui.pl.allegro;

import io.qameta.allure.Description;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ui.pl.allegro.interfaces.CategoryNavigator;
import ui.pl.allegro.interfaces.FilterApplier;
import ui.pl.allegro.pages.SortingOption;
import ui.pl.allegro.pages.filters.PojemnoscDyskuGB;
import ui.pl.allegro.steps.CategoryNavigationSteps;
import ui.pl.allegro.steps.FilterSteps;
import ui.pl.allegro.steps.SubCategoryPageSteps;
import ui.webdriver.BaseWebTest;

import java.util.List;
import java.util.stream.Stream;

import static java.util.Collections.singletonList;
import static ui.pl.allegro.pages.SortingOption.CENA_OD_NAJNIZSEJ;
import static ui.pl.allegro.pages.SortingOption.CENA_OD_NAJWYZSZEJ;
import static ui.pl.allegro.pages.categories.DyskiIPamieciPrzenosneSubCategory.DYSKI_ZEWNETRZNE_I_PRZENOSNE;

public class CategoryTest extends BaseWebTest {

    private CategoryNavigationSteps categoryNavigationSteps = new CategoryNavigationSteps();
    private SubCategoryPageSteps subCategoryPageSteps = new SubCategoryPageSteps();
    private FilterSteps filterSteps = new FilterSteps();

    @ParameterizedTest
    @Description("In this cool test we will check cool thing")
    @MethodSource("provideDataFor_verifySortingOfFilteredItemsOnSubcategoryPage")
    void verifySortingOfFilteredItemsOnSubcategoryPage(CategoryNavigator targetCategory, List<FilterApplier> filters, SortingOption sortingOption) {
        categoryNavigationSteps.navigateToCategory(targetCategory);
        filterSteps.applyAllFilters(filters);
        filterSteps.checkThatAllFiltersWereAppliedCorrectly(filters);
        subCategoryPageSteps.sortItemsBy(sortingOption);
        subCategoryPageSteps.checkSortingIsCorrect(sortingOption);
    }

    private static Stream<Arguments> provideDataFor_verifySortingOfFilteredItemsOnSubcategoryPage() {
        return Stream.of(
                Arguments.of(DYSKI_ZEWNETRZNE_I_PRZENOSNE,
                        singletonList(new PojemnoscDyskuGB().pojemnoscOd("500").pojemnoscDo("1000")), CENA_OD_NAJWYZSZEJ),
                Arguments.of(DYSKI_ZEWNETRZNE_I_PRZENOSNE,
                        singletonList(new PojemnoscDyskuGB().pojemnoscOd("1000")), CENA_OD_NAJNIZSEJ)
        );
    }

}
