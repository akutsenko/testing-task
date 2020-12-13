package ui.pl.allegro.pages.filters;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import ui.pl.allegro.interfaces.FilterApplier;

import java.util.ArrayList;
import java.util.List;

import static common.reporting.AssertWrapper.assertThat;
import static java.lang.Integer.parseInt;
import static java.lang.String.format;
import static org.hamcrest.Matchers.hasSize;
import static ui.pl.allegro.utils.WaitUtils.DEFAULT_TIMEOUT_IN_SECONDS;
import static ui.pl.allegro.utils.WaitUtils.waitForPresenceOfElement;
import static ui.pl.allegro.utils.WebdriverUtils.findElement;
import static ui.pl.allegro.utils.WebdriverUtils.findElements;

@Getter
@Setter
@Accessors(fluent = true)
public class PojemnoscDyskuGB implements FilterApplier {

    private static String FILTER_BLOCK = "//fieldset[h3[@data-slot='Pojemność dysku']]";
    private static String OD_INPUT = FILTER_BLOCK + "//input[@name='od']";
    private static String DO_INPUT = FILTER_BLOCK + "//input[@name='do']";
    private static String APPLIED_FILTER_INDICATOR = "//div[@data-prototype-id='allegro.listing.chips']//div[span][contains(.,'%s')]";
    private static String NON_SPONSORED_ITEMS = "//div[@class='opbox-listing']//article[not(@data-analytics-view-label='showSponsoredItems')]";
    private static String POJEMNOSC_DYSKU_ALL_VALUES = NON_SPONSORED_ITEMS + "//dt[.='Pojemność dysku']/following-sibling::dd[1]";
    private static String POJEMNOSC_DYSKU_VALUE_BY_INDEX = "(" + POJEMNOSC_DYSKU_ALL_VALUES + ")[%d]";

    private String pojemnoscOd;
    private String pojemnoscDo;

    @Override
    public void applyFilter() {
        enterValue(OD_INPUT, pojemnoscOd);
        enterValue(DO_INPUT, pojemnoscDo);
    }

    @Override
    public void checkThatFilterIsApplied() {
        int itemsNumber = findElements(POJEMNOSC_DYSKU_ALL_VALUES).size();
        List<Integer> itemsWithWrongPojemnosc = new ArrayList<>();
        for (int i = 1; i <= itemsNumber; i++) {
            int pojemnosc = Double.valueOf(findElement(format(POJEMNOSC_DYSKU_VALUE_BY_INDEX, i))
                        .getText().replace(" GB", "")).intValue();
            if (!isPojemnoscGreaterThanPojemnoscOd(pojemnosc) || !isPojemnoscSmallerThanPojemnoscDo(pojemnosc)) {
                itemsWithWrongPojemnosc.add(pojemnosc);
            }
        }
        assertThat("there are no items with incorrect pojemnosc", itemsWithWrongPojemnosc, hasSize(0));
    }

    private void enterValue(String locator, String value) {
        if (null != value) {
            findElement(locator).click();
            findElement(locator).sendKeys(value);
            waitForPresenceOfElement(format(APPLIED_FILTER_INDICATOR, value), DEFAULT_TIMEOUT_IN_SECONDS);
        }
    }

    private boolean isPojemnoscGreaterThanPojemnoscOd(int pojemnosc) {
        if (null != pojemnoscOd) {
            return pojemnosc >= parseInt(pojemnoscOd);
        }
        return true;
    }

    private boolean isPojemnoscSmallerThanPojemnoscDo(int pojemnosc) {
        if (null != pojemnoscDo) {
            return pojemnosc <= parseInt(pojemnoscDo);
        }
        return true;
    }

    @Override
    public String toString() {
        String pojemnoscOd = (null == this.pojemnoscOd) ? "-" : this.pojemnoscOd;
        String pojemnoscDo = (null == this.pojemnoscDo) ? "-" : this.pojemnoscDo;
        return format("Pojemność dysku od: \"%s\" do: \"%s\"", pojemnoscOd, pojemnoscDo);
    }
}
